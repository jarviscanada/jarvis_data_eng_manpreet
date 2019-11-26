# Architecture Diagram
![Architecture Diagram] (./assets/Architechture.jpg)

## Introduction 

The Linux Cluster Administration (LCA) team manages a Linux cluster of 10 nodes/servers which are running CentOS 7. These servers are internally connected through a switch and able to communicate through internal IPv4 addresses.

The LCA team needs to record the hardware specifications of each node and monitor node resource usages (e.g. CPU/Memory) in realtime (see appendix A). The collected data should be stored in an RDBMS database. LCA team will use the data to generate some reports for future resource planning purposes (e.g. add/remove servers).

## Explaination of project tree

- **linux_sql**
1. scripts
* host_info.sh: collects the host hardware info and insert it into the database. It will be run only once at the installation time.
* host_usage.sh collects the current host usage (CPU and Memory) and then insert into the database. It will be triggered by the crontab job every minute.
* psql_docker.sh
	
2. sql
* ddl.sql
* queries.sql : contains queries to manipulate data

3. Readme.md: contains the high level description of project architecture

