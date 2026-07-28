package server.main;

import java.util.List;
import java.util.ArrayList;

import server.lib.Context;
import server.lib.CliEffect;
import server.utils.RainEffect;
import server.utils.LimitedSizeList;
import server.lib.Log;

public final class Logger implements Runnable {
    private static final int HEIGHT = 60;
    private static final int WIDTH  = 150;

    private final Context context;
    private final List<String> mMessagesToLog = new LimitedSizeList<>(HEIGHT);
    private final CliEffect mGraphics = new RainEffect(WIDTH, HEIGHT);

    public Logger(Context context) {
        this.context = context;
        mMessagesToLog.add("Server logger is ready.");
    }
    
    @Override
    public void run() {
        try {
            while(context.isRunning()) {
                log();
                Thread.sleep(1_000);
            }
        } catch (InterruptedException ie) {
            throw new RuntimeException(ie);
        }
    }

    private void log() {
        for(int i=0; i<=HEIGHT; i++) {
            mGraphics.typeLine(i);
            Log.inc.write(" | ");
            if(mMessagesToLog.size()>i)
                Log.inc.write(mMessagesToLog.get(i));
            Log.inc.write("\n");
        }
        mGraphics.nextFrame();
        Log.inc.end();
    }

    public void log(String message) {
        mMessagesToLog.add(message);
    }
}