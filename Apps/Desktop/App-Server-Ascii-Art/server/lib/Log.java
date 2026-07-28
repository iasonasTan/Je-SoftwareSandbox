package server.lib;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;

public final class Log {
    public static final Log inc = new Log();

    private final BufferedWriter mOut = new BufferedWriter(new OutputStreamWriter(System.out));

    private Log() {}

    public void write(char c) {
        write(String.valueOf(c));
    }

    public void write(String s) {
        try {
            mOut.write(s);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void end() {
        try {
            System.out.print("\033[H\033[2J");
            System.out.flush();
            mOut.flush();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}