package com.taskcli.taskcli.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

public class FileUtil {
    public static void writeToFile(String filePath, String content) throws IOException {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(content);
        }
    }

    public static String readFromFile(String filePath) throws IOException {
        File file = new File(filePath);
        return new String(Files.readAllBytes(file.toPath()));
    }
}
