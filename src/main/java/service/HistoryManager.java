package service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.ConversionRecord;

import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * Mantém um histórico em memória (Deque) com persistência simples em JSON.
 */
public class HistoryManager {
    private final Deque<ConversionRecord> history;
    private final int maxSize;
    private final Path file;
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public HistoryManager(int maxSize, Path file) {
        this.maxSize = maxSize;
        this.file = file;
        this.history = new ArrayDeque<>(maxSize);
        loadFromFile();
    }

    public synchronized void add(ConversionRecord r) {
        if (history.size() == maxSize) history.removeLast();
        history.addFirst(r);
        saveToFile();
    }

    public synchronized List<ConversionRecord> listAll() {
        return new ArrayList<>(history);
    }

    public synchronized void clear() {
        history.clear();
        saveToFile();
    }

    private void saveToFile() {
        try {
            List<ConversionRecord> list = new ArrayList<>(history);
            String json = gson.toJson(list);
            Files.writeString(file, json, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            System.err.println("Falha ao salvar histórico: " + e.getMessage());
        }
    }

    private void loadFromFile() {
        try {
            if (Files.exists(file)) {
                String json = Files.readString(file);
                ConversionRecord[] arr = gson.fromJson(json, ConversionRecord[].class);
                if (arr != null) {
                    for (int i = arr.length - 1; i >= 0; i--) {
                        history.addFirst(arr[i]);
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Falha ao carregar histórico: " + e.getMessage());
        }
    }
}
