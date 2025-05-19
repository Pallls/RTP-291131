package Assignment1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileAnalyzer {

    private int javaSourceTotal = 0;
    private int solvedTagTotal = 0;

    public void scanFolder(File directoryPath) {
        File[] contentList = directoryPath.listFiles();
        if (contentList == null) return;

        for (File currentFile : contentList) {
            if (currentFile.isDirectory()) {
                scanFolder(currentFile);
            } else if (currentFile.getName().endsWith(".java")) {
                javaSourceTotal++;
                checkSolvedComments(currentFile);
            }
        }
    }

    private void checkSolvedComments(File javaFile) {
        try (Scanner fileScanner = new Scanner(javaFile)) {
            while (fileScanner.hasNextLine()) {
                String sourceLine = fileScanner.nextLine().trim();
                if (sourceLine.contains("//SOLVED")) {
                    solvedTagTotal++;
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Cannot open file: " + javaFile.getName());
        }
    }

    public int retrieveJavaFileCount() {
        return javaSourceTotal;
    }

    public int retrieveSolvedCount() {
        return solvedTagTotal;
    }

    public static void main(String[] args) {
        FileAnalyzer analyzerTool = new FileAnalyzer();

        String directoryToScan = "./src/main/java/Assignment1/src";

        File inputFolder = new File(directoryToScan);
        if (inputFolder.exists() && inputFolder.isDirectory()) {
            analyzerTool.scanFolder(inputFolder);
            System.out.println("Number of Java Files = " + analyzerTool.retrieveJavaFileCount());
            System.out.println("Number of Issues = " + analyzerTool.retrieveSolvedCount());
        } else {
            System.out.println("Provided path does not exist: " + directoryToScan);
        }
    }
}

