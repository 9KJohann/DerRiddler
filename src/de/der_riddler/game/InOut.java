package de.der_riddler.game;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class InOut {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
    public static final String ANSI_BOLD = "\u001b[1m";

    private final Scanner scanner;
    private final PrintStream printStream;


    public InOut() {
        this(System.in, System.out);
    }

    public InOut(InputStream inputStream, PrintStream printStream) {
        this.scanner = new Scanner(inputStream);
        this.printStream = printStream;
    }

    public void print(String message) {
        printStream.print(message);
    }

    public void println(String message) {
        printStream.println(message);
    }

    public void printError(String error) {
        printStream.println(ANSI_RED + error + ANSI_RESET);
    }

    public String nextLine() {
        return scanner.nextLine();
    }
}
