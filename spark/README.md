# Spark Project
* [Introduction](#Introduction)
* [Spark Architecture](#SparkArchitecture)
* [Spark RDD Project](#SparkRDDProject)
* [Spark Dataframe Project](#SparkDataframeProject)

# Introduction
 This project processes data using Apache Spark and Scala. It helped me understanding the core concepts of Resilient Distributed Datasets (RDDs),Spark Dataframes and Datasets which is constituted in Spark's Structured APIs and Spark SQL. As a project, there are two zeppline notebooks that performs data preocessing using RDDs and Dataframes and Datasets
 
# Spark Architecture

[Architecture](/spark/assets/architecture.PNG)

* ##### Driver and Executor: 
    * Driver and Executor cooperate together to execute job submitted by the user.
    * **Driver**: The driver is the process where the main method runs. First it converts the user program into tasks and after that it schedules the tasks on the executors.
    * **Executer**: Executors are worker nodes' processes in charge of running individual tasks in a given Spark job. They are launched at the beginning of a Spark application and typically run for the entire lifetime of an application. Once they have run the task they send the results to the driver. They also provide in-memory storage for RDDs that are cached by user programs through Block Manager.
    
* ##### Spark Session: 
    * It provides a way to interact with various sparks functionality with a lesser number of constructs which is also responsible for the translation process. The user code accepted by the Spark application JVM will be translated by Spark session into lower-level instructions which is executable by the executors. 


# Spark RDD Project
 An RDD is a resilient and distributed collection of records spread over one or many partitions.
 They are logical reference of a dataset which is partitioned across many server machines in the cluster. RDDs are Immutable and are self recovered in case of failure.
 
 Purpose of this project involved data processing on sample retail data in CSV format, and handling parsing issues. 
 [Project](/spark/assets/rdd.PNG)

#  Spark Dataframe Project
#### Spark Structured API
In the Spark Structured APIs, we have mainly two APIs namely Datasets and DataFrames. Dataframes are untyped means Spark checks its schema at the Runtime and on the other hand, Datasets are typed meaning Spark checks its Schema at the compile time and Datasets are available to only typed languages[Java, scala]. DataFrames are simply Datasets of Type Row. The Row type is Sparks internal representation of its optimized in-memory format for computation.
RDD API is slower to perform simple grouping and aggregation operations. DataFrame API is very easy to use. It is faster for exploratory analysis, creating aggregated statistics on large data sets.Dataset are also faster to perform aggregation operation on plenty of data sets.

Purpose of this project involved data processing on sample retail data in CSV format, and get familiar with  structured APIs. Business operations data are solved.
 [Project](/spark/assets/df.PNG)

