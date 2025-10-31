package service;

import model.ConversionRecord;

import java.io.IOException;
import java.nio.file.*;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Serviço simples de logs que grava linhas em arquivo.
 */
public class LogService {
    private final Path logFile;
    private final DateTimeFormatter fmt = DateTimeFormatter.ISO_ZONED_DATE_TIME;

    public LogService(Path logFile) {
        this.logFile = logFile;
    }

    public synchronized void log(ConversionRecord r) {
        String line = String.format("%s | %s -> %s | amount=%s | rate=%s | result=%s%n",
                r.getTimestampIso(), r.getFrom(), r.getTo(), r.getAmount(), r.getRate(), r.getResult());
        try {
            Files.writeString(logFile, line, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.err.println("Falha ao gravar log: " + e.getMessage());
        }
    }
}
