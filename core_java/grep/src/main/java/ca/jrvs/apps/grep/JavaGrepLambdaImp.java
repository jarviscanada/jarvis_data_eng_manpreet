package ca.jrvs.apps.grep;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class JavaGrepLambdaImp extends JavaGrepImp{

    public static void main(String[] args) {
        if (args.length != 3) {
            throw new IllegalArgumentException("USAGE: regex rootPath outFile");
        }

        JavaGrepImp javaGrepImp = new JavaGrepImp();
        javaGrepImp.setRegex(args[0]);
        javaGrepImp.setRootPath(args[1]);
        javaGrepImp.setOutFile(args[2]);

        try {
            javaGrepImp.process();
        } catch (Exception ex) {
            throw new RuntimeException("failed to write to output file", ex);
        }

    }

    //Read a file and return all the lines
    @Override
    public List<File> listFiles(String rootDir) {
        File directory = new File(rootDir);

        // Check if rootDir is Directory
        if(!directory.exists() || !directory.isDirectory()){
            throw new IllegalArgumentException(String.format("%s is not a directory", rootDir));
        }

        //get all files recursively
        try {
            List<File>  resultList = Files.walk(Paths.get(rootDir))
                    .filter(Files::isRegularFile)
                    .map(path -> path.toFile())
                    .collect(Collectors.toList());
            return resultList;
        } catch (IOException e) {
            throw new RuntimeException("Failed to list files",e);
        }
    }

    //Read a file and return all the lines
    @Override
    public List<String> readLines(File inputFile) {

        // Check if inputFile is a file
        if(!inputFile.exists() || !inputFile.isFile()){
            throw new IllegalArgumentException(String.format("%s is not a file", inputFile.getPath()));
        }

        //convert file path as string
        String filePath = inputFile.getAbsolutePath();
        try {
            List<String> lines = Files.lines(Paths.get(filePath)).collect(Collectors.toList());
            return lines;
        } catch (IOException e) {
            throw new RuntimeException("Failed to read lines",e);
        }
    }

}
