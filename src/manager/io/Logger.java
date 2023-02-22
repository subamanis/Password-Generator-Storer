package manager.io;

import manager.Utilities;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class Logger {
    public enum LogType {
        ERROR,
        WARNING,
        INFO
    }

    public enum LogMode {
        DEBUG_PC,
        RELEASE_PC
    }

    public static final String LOG_FILE_NAME = "log.txt";
    public static final DateTimeFormatter LOG_TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");

    private PrintStream ps;
    private boolean isLogSessionOpen;

    public Logger(LogMode logMode) {
        try {
            switch (logMode) {
                case DEBUG_PC ->   ps = new PrintStream(new FileOutputStream("./" + LOG_FILE_NAME));
                case RELEASE_PC -> ps = new PrintStream(new FileOutputStream("./" + LOG_FILE_NAME, true));
            }
        } catch (IOException e) {
             e.printStackTrace();
        }
    }

    public void logError(String message) {
        log(LogType.ERROR, message);
    }

    public void logWarning(String message) {
        log(LogType.WARNING, message);
    }

    public void logInfo(String message) {
        log(LogType.INFO, message);
    }


    private void createNewLogSession() {
        ps.println("\n\n================= " + LocalDateTime.now().format(Utilities.DATETIME_FORMATTER) + " =================");
    }

    private void log(LogType logType, String message) {
        if (!this.isLogSessionOpen) {
            this.createNewLogSession();
            this.isLogSessionOpen = true;
        }
        this.printLogTypePrefix(logType);
        ps.println(message);
    }

    private void printLogTypePrefix(LogType logType) {
        var timePrefix = LocalDateTime.now().format(LOG_TIME_FORMATTER);
        switch (logType) {
            case ERROR -> ps.print(timePrefix + "  ERROR : ");
            case WARNING -> ps.print(timePrefix + "  WARNING: ");
            case INFO -> ps.print(timePrefix + "  INFO: ");
        }
    }
}
