package helper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_BLACK = "\u001B[30m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_PURPLE = "\u001B[35m";
    private static String name;
    private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

    public Logger(String name) {
        Logger.name = name;
    }

    /**
     * Logs info message
     *
     * @param message message you want to log
     */
    public static void info(String message) {
        System.out.println(ANSI_PURPLE + "INFO : " + message + ANSI_RESET);
    }

    /**
     * Logs fine message
     *
     * @param message message you want to log
     */
    public static void fine(String message) {
        System.out.println(ANSI_GREEN + "FINE : " + message + ANSI_RESET);
    }

    /**
     * Logs warning message
     *
     * @param message message you want to log
     */
    public static void warning(String message) {
        System.out.println(ANSI_YELLOW + "WARNING : " + message + ANSI_RESET);
    }
}