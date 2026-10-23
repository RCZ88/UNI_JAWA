package NAR.reNHlorist;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class FileStorage {
    public final Path usersFile;
    public final Path productsFile;
    public final Path floristsFile;

    public FileStorage() {
        usersFile = Path.of("users.txt");
        productsFile = Path.of("products.txt");
        floristsFile = Path.of("florists.txt");
    }

    public List<String> readLines(Path file) throws IOException {
        return Files.readAllLines(file);
    }

    public void appendLine(Path file, String record) throws IOException {
        Files.writeString(
                file,
                record + System.lineSeparator(),
                StandardOpenOption.CREATE,
                StandardOpenOption.APPEND
        );
    }

    public void overwriteLines(Path file, List<String> records) throws IOException {
        // TODO: Replace file contents when a full rewrite is needed.
        Files.writeString(
                Path.of("users.txt"),
                "",
                StandardOpenOption.CREATE,
                StandardOpenOption.TRUNCATE_EXISTING
        );
        for (String record : records) {
            appendLine(file, record);
        }
    }

    public Path getUsersFile() {
        return usersFile;
    }

    public Path getProductsFile() {
        return productsFile;
    }

    public Path getFloristsFile() {
        return floristsFile;
    }
}
