package ca.jrvs.apps.grep;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import java.util.stream.Collectors;


public class JavaGrepImp implements JavaGrep{

    private String regex;
    private String rootPath;
    private String outFile;

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
            ex.printStackTrace();
        }

    }

    @Override
    public void process() throws IOException {

        List<File> files = this.listFiles(rootPath);
        List<String> matchedLines =  new ArrayList<>();;

        for(File file : files){
            List<String> lines = this.readLines(file);
            boolean matchFound = false;
            for(String line: lines){
                matchFound = this.containsPattern(line);

                //check if match is found
                if(matchFound){
                    matchedLines.add(line);
                }
            }
        }

        //check if matched Lines found and write to file
        if(matchedLines!=null){
            this.writeToFile(matchedLines);
            System.out.println("Patterns has been written to File");
        }else{
            System.out.println("Pattern Not found");
        }

    }

    //traverse a directory and return all the files
    @Override
    public List<File> listFiles(String rootDir)
    {
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
            e.printStackTrace();
        }
        return null;
    }

    //Read a file and return all the lines
    @Override
    public List<String> readLines(File inputFile)
    {
        // Check if inputFile is a file
        if(!inputFile.exists() || !inputFile.isFile()){
            throw new IllegalArgumentException(String.format("%s is not a file", inputFile.getPath()));
        }

        List<String> result = new ArrayList<>();
        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader(inputFile));

            String line;
            while ((line = br.readLine()) != null) {
                result.add(line);
            }
            return result;

        } catch (IOException e) {
             e.printStackTrace();
        }
        return null;
    }

    //check if a line contains the regex pattern (passed by user)
    @Override
    public boolean containsPattern(String line)
    {
        PatternSyntaxException exc = null;
        try {
            Pattern.compile(regex);
        } catch (PatternSyntaxException e) {
            exc = e;
        }

        //check if regex if valid
        if (exc != null) {
            exc.printStackTrace();
        } else {
            Matcher matcher = Pattern.compile(regex).matcher(line);

            while (matcher.find()) {
                if(matcher.group()!=null)
                {
                    return true;
                }
            }
        }
        return false;
    }

    //Write lines to a file
    @Override
    public void writeToFile(List<String> lines) throws IOException {

        //convert given string to path
        File file = new File(outFile);

        // Check if inputFile is a file
        if(!file.exists() || !file.isFile()){
            throw new IllegalArgumentException(String.format("%s is not a file", file.getPath()));
        }

        FileWriter writer = new FileWriter(outFile);
        for(String str: lines) {
            writer.write(str + System.lineSeparator());
        }
        writer.close();
    }

    @Override
    public String getRegex() {
        return regex;
    }

    @Override
    public void setRegex(String regex) {
        this.regex = regex;
    }

    @Override
    public String getRootPath() {
        return rootPath;
    }

    @Override
    public void setRootPath(String rootPath) {
        this.rootPath = rootPath;
    }

    @Override
    public String getOutFile() {
        return outFile;
    }

    @Override
    public void setOutFile(String outFile) {
        this.outFile = outFile;
    }
}
