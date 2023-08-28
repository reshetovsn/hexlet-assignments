package exercise;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

class App {

    // BEGIN
    public static CompletableFuture<String> unionFiles(String source1, String source2, String dest) {

        CompletableFuture<String> readFile1 = CompletableFuture.supplyAsync(() -> {
            String file;
            try {
                file = Files.readString(Paths.get(source1));
            } catch (Exception e) {
                throw new IllegalStateException(e);
            }
            return file;
        });

        CompletableFuture<String> readFile2 = CompletableFuture.supplyAsync(() -> {
            String file;
            try {
                file = Files.readString(Paths.get(source2));
            } catch (Exception e) {
                throw new IllegalStateException(e);
            }
            return file;
        });

        return readFile1.thenCombine(readFile2, (file1, file2) -> {
            String union = file1 + " " + file2;
            try {
                Files.writeString(Paths.get(dest), union, StandardOpenOption.CREATE);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return "ok";
        }).exceptionally(ex -> {
            System.out.println("Oops! We have an exception - " + ex.getMessage());
            return null;
        });
    }
    // END

    public static void main(String[] args) throws Exception {
        // BEGIN
        unionFiles(
                "./src/main/resources/file1.txt",
                "./src/main/resources/file2.txt",
                "./src/main/resources/result.txt");
        // END
    }
}

