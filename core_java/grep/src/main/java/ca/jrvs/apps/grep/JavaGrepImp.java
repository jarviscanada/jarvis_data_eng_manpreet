package ca.jrvs.apps.grep;

import java.io.*;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
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
        } catch (IOException ex) {
            throw new RuntimeException("failed to write to output file", ex);
        }

    }

    @Override
    public void process() throws IOException {

        List<File> files = listFiles(rootPath);
        List<String> matchedLines =  new ArrayList<>();;

        for(File file : files){
            List<String> lines = readLines(file);
            boolean matchFound = false;
            for(String line: lines){
                matchFound = containsPattern(line);

                if(matchFound){
                    matchedLines.add(line);
                }
            }
        }
        
        if(matchedLines!=null){
            this.writeToFile(matchedLines);
            System.out.println("Patterns has been written to File");
        }else{
            System.out.println("Pattern Not found");
        }

    }
    
   /**
   * Traverses the directory and gets all the files
   * @param rootDir Path of Directory to be traversed.
   * @return List of files.
   */
    @Override
    public List<File> listFiles(String rootDir)
    {
        File directory = new File(rootDir);

        if(!directory.exists() || !directory.isDirectory()){
            throw new IllegalArgumentException(String.format("%s is not a directory", rootDir));
        }

        List<File> fileList = new ArrayList<>();
        File rootdir = new File(rootDir);

        //Get the name of directories contained in the dir
        if (rootdir.isDirectory()) {
            File[] files = rootdir.listFiles();

                for (File file : files) {

                    //check if file, then add to list
                    if (file.isFile()) {
                        fileList.add(file);
                    } else if (file.isDirectory()) {

                        //add files into list from sub-dir recursively
                        fileList.addAll(listFiles(file.getAbsolutePath()));
                    }
                }
            }

        return fileList;

    }

   /**
   * Read a file and return all the lines
   * @param inputFile File to be read.
   * @return List of lines.
   */
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
            throw new RuntimeException("failed to read lines", e);
        }

    }

   /**
   * Checks if a line contains the regex pattern.
   * @param line Lookup String for pattern.
   * @return true if line contains pattern else false.
   */
    @Override
    public boolean containsPattern(String line)
    {
        try {
             Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(line);

            while (matcher.find()) {
                if(matcher.group()!=null)
                {
                    return true;
                }
            }
        } catch (PatternSyntaxException e) {
            throw new RuntimeException("failed to compile regex pattern", e);
        }
        return false;
    }
   
    /**
   * Write lines to a file.
   * @param lines List of lines to write.
   * @return Nothing.
   */
    @Override
    public void writeToFile(List<String> lines) throws IOException {

        //convert given string to path
        File file = new File(outFile);

        // Check if inputFile is a file
        if(!file.exists() || !file.isFile()){
            throw new IllegalArgumentException(String.format("%s is not a file", file.getPath()));
        }

        BufferedWriter  writer = new BufferedWriter(new FileWriter(file));
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
