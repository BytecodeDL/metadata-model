package org.clyze.persistent.metadata;

public class Printer {
    public final boolean output;

    public Printer(boolean output) {
        this.output = output;
    }

    public void println(String s) {
        if (output)
            System.out.println(s);
    }

    public void print(String s) {
        if (output)
            System.out.print(s);
    }

    public void err_println(String s) {
        if (output)
            System.err.println(s);
    }
}
