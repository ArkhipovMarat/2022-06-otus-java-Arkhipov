package ru.otus.dataprocessor;

import com.google.gson.Gson;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

public class FileSerializer implements Serializer {
    private String fileName;

    public FileSerializer(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void serialize(Map<String, Double> data) {
        var json = new Gson().toJson(data);

        try {
            Files.write(Path.of(fileName), json.getBytes());
        } catch (IOException ioe) {
            throw new FileProcessException("File Serialize Exeption", ioe);
        }
    }
}
