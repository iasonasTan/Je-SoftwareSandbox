package server.utils;

import server.lib.CliEffect;
import java.util.Optional;
import java.util.ArrayList;
import java.util.List;
import java.nio.*;
import java.nio.file.*;
import java.io.*;
import java.net.URISyntaxException;
import server.lib.Log;

public final class RainEffect implements CliEffect {
    private int mWidth, mHeight;
    private List<Node> mNodes;
    private Drawing mDrawing;
    private BufferedWriter mOut = new BufferedWriter(new OutputStreamWriter(System.out));

    public RainEffect(int w, int h) {
        mWidth  = w;
        mHeight = h;
        mDrawing = new CoffeeMug();
        double mugX = (mWidth-mDrawing.getWidth())/2;
        double mugY = (mHeight-mDrawing.getHeight())/2;
        mDrawing.setX((int)mugX);
        mDrawing.setY((int)mugY);
        mNodes = initNodes();
    }

    private List<Node> initNodes() {
        List<Node> list = new ArrayList<>();
        for(int i=0; i<(mWidth+mHeight)/2; i++) {
            int nodeX = (int)(Math.random()*mWidth);
            int nodeY = (int)(Math.random()*mHeight);
            list.add(new Node(nodeX, nodeY));
        }
        return list;
    }

    @Override
    public void typeLine(int y) {
        int dx = mDrawing.getX(), dy = mDrawing.getY();
        int dw = mDrawing.getWidth(), dh = mDrawing.getHeight();
        int x= 0;
        if(y==0) {
            String currTime = System.currentTimeMillis()+"";
            x += currTime.length();
            Log.inc.write(currTime);
        }
        for(; x<=mWidth; x++) {
            if(x>dx&&x<dx+dw&&y>dy&&dy<dh+dy) {
                Log.inc.write(mDrawing.charAt(x, y));
            } else {
                drawBackground(x, y);
            }
        }
    }

    private void drawBackground(int x, int y) {
        Optional<Node> nodeOpt = getNodeAt(x, y);
        if(nodeOpt.isPresent()) {
            Node node = nodeOpt.get();
            Log.inc.write(node.character());
        } else {
            Log.inc.write(" ");
        }
    }

    @Override
    public void nextFrame() {
        for(Node n: mNodes) {
            int speedY = (int)(Math.random()*3);
            n.move(0, speedY);
        }
        mDrawing.nextFrame();
    }

    private Optional<Node> getNodeAt(int x, int y) {
        for(Node n: mNodes) {
            if(n.isLocatedAt(x, y))
                return Optional.of(n);
        }
        return Optional.empty();
    }

    private abstract class Drawing {
        private int mX, mY;
        private int mW, mH;
        private final List<char[][]> mFrames = new ArrayList<>();
        private int mFrameIndex = 0;

        public Drawing(int x, int y, int w, int h) {
            mX = x;
            mY = y;
            mW = w;
            mH = h;
            try {
                initFrames(mFrames);
            } catch(URISyntaxException e) {
                throw new RuntimeException(e);
            }
        }

        protected abstract void initFrames(List<char[][]> frames) throws URISyntaxException;

        public void nextFrame() {
            mFrameIndex++;
            if(mFrameIndex>=mFrames.size()) {
                mFrameIndex = 0;
            }
        }

        public final char charAt(int x, int y) {
            // if(x>=mW||y>=mH) {
            //     return '!';
            //     //throw new ArrayIndexOutOfBoundsException("Given coords are out of range.");
            // }
            try {
                return mFrames.get(mFrameIndex)[y-getY()][x-getX()];
            } catch(ArrayIndexOutOfBoundsException e) {
                return ' ';
            }
        }

        public int getWidth() {
            return mW;
        }

        public int getHeight() {
            return mH;
        }

        public int getX() {
            return mX;
        }

        public int getY() {
            return mY;
        }

        public void setX(int x) {
            mX = x;
        }

        public void setY(int y) {
            mY = y;
        }
    }

    private final class CoffeeMug extends Drawing {
        public CoffeeMug() {
            super(0, 0, 81, 41); // ascii frame size
        }

        @Override
        protected void initFrames(List<char[][]> frames) throws URISyntaxException {
            Path folderPath = Paths.get(getClass().getResource("/res/frames/").toURI());
            try (DirectoryStream<Path> stream=Files.newDirectoryStream(folderPath)) {
                for(Path path: stream) {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(Files.newInputStream(path)));
                    frames.add(decodeLine(reader));
                }
            } catch (IOException exception) {
                throw new RuntimeException(exception);
            }
        }
        
        private char[][] decodeLine(BufferedReader reader) {
            try {
                char[][] out = new char[getHeight()][getWidth()];
                String line = null;
                int y = 0;
                while((line = reader.readLine()) != null) {
                    for(int x=0; x<line.length(); x++) {
                        out[y][x] = line.charAt(x);
                    }
                    y++;
                }
                return out;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private class Node {
        private int mX, mY;
        private char mC;

        public Node(int x, int y, char c) {
            mX = x;
            mY = y;
            mC = c;
        }

        public Node(int x, int y) {
            this(x, y, '@');
        }

        public void move(int x, int y) {
            mX += x;
            mY += y;
            if(mY >= mHeight||mX >= mWidth) {
                mY = (int)(Math.random()*mHeight);
                mX = (int)(Math.random()*mWidth);
            }
        }

        public char character() {
            return mC;
        }

        public boolean isLocatedAt(int x, int y) {
            return x == mX && y == mY;
        }

        @Override
        public String toString() {
            return "Node{x:"+mX+",y:"+mY+"}";
        }
    }
}