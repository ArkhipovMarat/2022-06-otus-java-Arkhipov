package ru.otus.dataprocessor;

import com.google.gson.Gson;
import ru.otus.model.Measurement;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ResourcesFileLoader implements Loader {
    private String fileName;

    public ResourcesFileLoader(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public List<Measurement> load() {
        try (var isr = new InputStreamReader(getResourceAsStream(fileName))) {
            return readFromJson(isr);
        } catch (Exception e) {
            throw new FileProcessException("File Deserialization Exception", e);
        }
    }

    private List<Measurement> readFromJson(Reader reader) {
        Measurement[] measurements = new Gson().fromJson(reader, Measurement[].class);
        return Arrays.asList(measurements);
    }

    private InputStream getResourceAsStream(String fileName) {
        var resourceName = String.format("%s%s", File.separator, fileName);
        return Objects.requireNonNull(getClass().getResourceAsStream(resourceName));
    }
}
