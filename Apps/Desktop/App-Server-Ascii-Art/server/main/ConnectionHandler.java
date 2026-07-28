package server.main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import server.lib.Context;

public final class ConnectionHandler implements Runnable {
    private final Context context;
    private final BufferedReader in;
    private final BufferedWriter out;
    private final Socket mClient;
    private boolean mAlive = true;

    public ConnectionHandler(Context context, Socket socket) throws IOException {
        this.mClient = socket;
        this.context = context;
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        context.log("["+System.currentTimeMillis()+"] Client connected.");
    }

    public void dispose() {
        mAlive = false;
        try {
            in.close();
            out.close();
            mClient.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while(mAlive) {
            String requestText = null;
            try {
                requestText = in.readLine();
            } catch (IOException e) {
                dispose();
            }
            if (requestText!=null&&requestText.contains("get_version_code")) {
                String[] requestTextSplit = requestText.split("\\?");
                if(requestTextSplit.length < 2) {
                    context.log("\n[WARNING] Client sent incorrect request format.");
                } else {
                    String appName = requestTextSplit[1];
                    sendVersionCode(appName);
                    context.log("\tA client requested version code for app "+appName);
                }
            } else {
                dispose();
                context.log("\tClient disconnected!");
            }
        }
    }

    private void sendVersionCode(String appName) {
        try {
            String code = context.getAppVersion(appName);
            context.log("\tCode "+code+" returned.");
            out.write(code);
            out.append('\n');
            out.flush();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // end of connection handler
}