# Spring Boot Trading REST API
* [Introduction](#Introduction)
* [Hadoop Cluster](#HadoopCluster)
* [Hive Project](#HiveProject)
* [Improvements](#Improvements)

# Introduction
 This project processes data using Apache Hadoop and evaluate different tools. It helped me understanding the core concepts of HDFS, MapReduce and hive. Google CLoud Platform is used build hadoop cluster: master node and slave node. YARN manages both the nodes. User is able to connect with hive server by CLI via beeline or by Zeppelin Notebook. Data within google storage is first written to HDFS and is manupilated using HiveQL.
 
 # Hadoop Cluster
![Architecture](/hadoop/assets/architecture.PNG)
 ## Big Data Tools
 * **HDFS** (Hadoop Distributed File System): The Hadoop Distributed File System (HDFS) is the primary data storage system used by Hadoop applications. It employs a NameNode and DataNode architecture to implement a distributed file system that provides high-performance access to data across highly scalable Hadoop clusters.
    * **NameNode** (The Master Node): It is the master daemon that maintains and manages the DataNodes (slave nodes). It records the metadata of all the files stored in the cluster, e.g. The location of blocks stored, the size of the files, permissions, hierarchy, etc.
    * **DataNode** (The Worker Node): DataNodes are the slave nodes in HDFS.The actual data is stored on DataNodes and send it to resource manager.
* **Hive**: Hive is an ETL and Data warehousing tool developed on top of Hadoop Distributed File System (HDFS). Hive makes job easy for performing operations like Data Encapsulation, Ad-hoc queries and analysis on huge datasets. For connecting with hive, it can be done through CLI using beeline and or by zepelline.
    * **CLI(Beeline)**: Hive Beeline is a CLI tool which allows user to connect to a Hive instance via JDBC. It is installed along hive. 
    * **Zeppelin**: At times, it becomes difficult to manage run large number of queries. Hence, zeppeline provides a clean User interface for executing queries.
* **YARN** (Yet Another Resource Negotiator): Apache Hadoop YARN is the resource management and job scheduling technology in the open source Hadoop distributed processing framework.
    * **Resource Manager**: It runs on master node. It manages and assigns resources to different jobs.
    *  **Node Manager**:  It runs on worker node. It gather resources usage information and send it to resource manager. 
* **Map Reduce**: MapReduce is a framework using which we can write applications to process huge amounts of data, in parallel, on large clusters of commodity hardware in a reliable manner.

##### Hardware Specification
*   Hadoop cluster contains 1 master node and 2 worker nodes.
*   Each nodes has 2 cores, 13GB of RAM, and 100GB of disk space.

 # Hive Project
 In this project, first, data is loaded to HDFS from Google Platform, That data is manipulated or optimised using HiveQL queries and later, the comparisons are made via different ways of processing it. Below screenshots well explains the steps taken to complete it.
 
![Project](/hadoop/assets/project.PNG)

# Improvements
1. More comparisons can be done between columnar file formats with parquets format.
2. Perfomances can be compared for both columnar format approach and spark approach.
3. Project can be extended by manipulating data more and making more comparisons









