package utils;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class FileUtils {
    public static boolean copyFolder(Path source, Path destination) {
        try {
            System.out.println("Files is coping");
            Files.walkFileTree(source, new SimpleFileVisitor<>() {

                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    Path targetFile = destination.resolve(source.relativize(file));
                    Files.createDirectories(targetFile.getParent());
                    Files.copy(file, targetFile, StandardCopyOption.REPLACE_EXISTING);
                    return FileVisitResult.CONTINUE;
                }
            });
            return true;
        } catch (IOException e) {
            System.err.println("Ошибка копирования файлов: " + e.getMessage());
            return false;
        }
    }
}
