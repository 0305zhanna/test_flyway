package tools;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class FileSystemTools {
    private String migrationRootDir = "C:\\Users\\zhanna_fedorova\\IdeaProjects\\test_flyway\\r_sql";
    private String tempDirectoryName = "temp";

    public void deleteLink(String desc) {
        String name = "R__" + desc + ".sql";
        Path path = Paths.get(migrationRootDir + "\\" + tempDirectoryName + "\\" + name);
        try {
            Files.deleteIfExists(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createTempDirectoryWithLinks() {
        Path sql = Paths.get(migrationRootDir);
        Path temp = Paths.get(migrationRootDir + "\\" + tempDirectoryName);
        try (DirectoryStream<Path> dirStream = Files.newDirectoryStream(sql)) {
            if (Files.notExists(temp))
                Files.createDirectory(temp);

            for (Path path : dirStream) {
                Path fileName = path.getFileName();
                Path newLink = Paths.get(temp.toString() + "\\" + fileName);
                if (!Files.isDirectory(path) && Files.notExists(newLink))
                    Files.createLink(newLink, path);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteTempDirectory() {
        Path path = Paths.get(migrationRootDir + "\\" + tempDirectoryName);
        try {
            Files.walkFileTree(path, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    Files.delete(file);
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                    Files.delete(dir);
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
