package server.main;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Properties;
import java.util.Scanner;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.Executors;

import server.lib.Context;
import server.lib.Log;

public final class Server implements Runnable, Context {
    private volatile boolean mRunning = true;
    private final ServerSocket mServer;
    private final ThreadPoolExecutor mThreadPool;
    private final Thread mServerThread;
    private Properties mVersionCodes = new Properties();
    private final Thread mLoggingThread;
    private final Logger mLogger = new Logger(this);

    public Server(final String filePath) {
        try {
            loadVersions(filePath);
            mServer = new ServerSocket(1422, 50, InetAddress.getByName("0.0.0.0"));
            mThreadPool = (ThreadPoolExecutor)Executors.newCachedThreadPool();
            mServerThread = new Thread(this);

            mLoggingThread = new Thread(mLogger);
            mLoggingThread.start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        mServerThread.start();
        System.out.println("Server started... ");
        new Thread(() -> {
            Scanner scanner = new Scanner(System.in);
            while(true) {
                String line = scanner.nextLine();
                if(line.contains("rel")) {
                    try {
                        loadVersions(filePath);
                        mLogger.log("Versions reloaded successfully.");
                        mLogger.log("New version codes are: "+mVersionCodes);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }).start();
    }

    public void shutdown() {
        mRunning = false;
        mThreadPool.shutdownNow();
    }

    private void loadVersions(String filePath) throws IOException {
        InputStream inputStream = new FileInputStream(filePath);
        mVersionCodes.clear();
        mVersionCodes.load(inputStream);
        inputStream.close();
    }

    public static void main(String[] args) {
        if(args.length < 1) {
            throw new IllegalArgumentException("You must pass version code file as parameter.");
        }
        new Server(args[0]);
    }

    @Override
    public void run() {
        while(mRunning) {
            try {
                Socket client = mServer.accept();
                ConnectionHandler cHandler = new ConnectionHandler(this, client);
                mThreadPool.execute(cHandler);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void log(String msg) {
        mLogger.log(msg);
    }

    @Override
    public boolean isRunning() {
        return mRunning;
    }

    @Override
    public String getAppVersion(String key) {
        return mVersionCodes.getProperty(key);
    }

    // end of server
}