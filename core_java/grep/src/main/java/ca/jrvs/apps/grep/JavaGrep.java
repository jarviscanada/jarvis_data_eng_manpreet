package ca.jrvs.apps.grep;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public interface JavaGrep {

    void process() throws IOException;

    /*traverse given directory and returns all files*/
    List<File> listFiles(String rootDir);

    /*Read a file and return all the lines*/
    List<String> readLines(File inputFile);

    /*check if a line contains the regex pattern which is passed by user*/
    boolean containsPattern(String line);

    /*write lines to the file*/
    void writeToFile(List<String> lines) throws IOException;

    String getRootPath();

    void setRootPath(String rootPath);

    String getRegex();

    void setRegex(String regex);

    String getOutFile();

    void setOutFile(String outFile);

}
