package server.lib;

import server.main.ConnectionHandler;

public interface Context {
    void log(String msg);
    boolean isRunning();
    String getAppVersion(String key);
}