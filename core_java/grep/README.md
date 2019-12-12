## Introduction 

This application basically tells the usage of 'grep' linux command which is implemented as a java application. It basically matches the regex pattern entered by user from an input directory (inpurt by the user). It recuresively traverse the directory i.e. traversing the directories inside the directories and writes the matched lines to a new file, specified by the user. 

The project utilizes the Java I/O classes, lambda and stream APIs.

## Usage
In order to run the application, there are three arguments that needs to be specified:

```
.*regexPatternSample.* /home/dev/ /tmp/grep.out
```

Here, we specified our regex pattern as `.*regexPattern*$` that will start to search from `/home/dev/` and recussively search through all the directories and subdirectories. Lastly, the lines that matches the regex pattern will be stored in location `/tmp/grep.out`.  

As an example, this application can be used to search particular lines in any location and writes them into file for any future references.
         
## Implementation & Pseudocode
 The workflow through pseudocode is as follows that typically describes the steps which application follows to write the matched lines to the ouput file.
 
 ```
 //Pseudocode
 matchedLines = []
for file in listFilesRecursively(rootDir)
  for line in readLines(file)
      if containsPattern(line)
        matchedLines.add(line)
writeToFile(matchedLines)
```

The above pseudocode's workflow is defined in the method called `process()` that defines the workflow of the application.

There are two .java files i.e.  `JavaGrep.java` which is an interface and `JavaGrepImp.java` which is a class that implements that interface. Methods mentioned above are declared in the interface whose functionality is defined by the class(JavaGrepImp.java) that implements the interface(JavaGrep.java). The detailed explaination of the these methods are given below.

1. `listFiles()` : It takes the root directory path and adds the generated file paths to a list.
2. `readLines()` : It takes the input file, adds all the generated lines to a list.
3. `containsPattern()` : It takes each line, matches with the regex pattern. 
4. `writeToFile()`: If the regex pattern is matched, this method writes the lines where the patter is matched, to a file. 

There is a third, last .java file called `JavaGrepLambdaImp.java` that performs the same functionality as `JavaGrepImp.java`. However, it implements those functionality using core new feature of Java 8 called Lamda & streams.

## Performance Issues

`JavaGrepImp.java` does implementation to read from files inside directory recursively, matching pattern and writing those lines to an output file. However, there is a point to note. Functionality defined in methods `listFiles()` and `readLines()` are based on the features defined in java versions before Java 8. Here, things work pretty well in small scale projects. However if the scope of project increases to large-scale (let’s say hefty GBs or more), it can cause memory/resource issues.

To overcome the above issue, `JavaGrepLambdaImp.java` implements purely new feature of Lambda and Stream APIs in `listFiles()` and `readLines()`  methods that does not run the same memory issue. Java Streams supports functional-style operations on streams of elements, such as map-reduce transformations on collections without actually storing the data.

## Improvements 
1. Application can be amended by adding some more new features to the app like the count of occurances of certain pattern that the following option `-c` does grep command or by displaying the lines not containing the specified pattern that `-v` does in grep options.
2. We can display the output written in files to the user so that he/she can see it and further features can be added through pipelining its output to another input/commands.
3. Application can be amended to add more features like displaying the line number and filename infront of the output lines that can help users to access metadata of their output for futher analysis. 

