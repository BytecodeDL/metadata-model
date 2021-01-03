package org.clyze.persistent.metadata;

/**
 * A simple class to control printing to the standard output/error.
 */
public class Printer {
    /** If true, messages will be printed; if false, the printer is disabled. */
    public final boolean output;

    /**
     * Create an output printer.
     * @param output   if false, the printer will not print
     */
    public Printer(boolean output) {
        this.output = output;
    }

    /**
     * Print a string to the standard output. A newline is added.
     * @param s  the string to print
     */
    public void println(String s) {
        if (output)
            System.out.println(s);
    }

    /**
     * Print a string to the standard output.
     * @param s  the string to print
     */
    public void print(String s) {
        if (output)
            System.out.print(s);
    }

    /**
     * Print a string to the standard error output.
     * @param s  the string to print
     */
    public void err_println(String s) {
        if (output)
            System.err.println(s);
    }
}
