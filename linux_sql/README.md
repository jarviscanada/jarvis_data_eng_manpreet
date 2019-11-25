# Architecture Diagram
![Architecture Diagram] (./assets/Architechture.jpg)


#Explaination of project tree

*linux_sql
	* scripts
		* host_info.sh: collects the host hardware info and insert it into the database. It will be run only once at the installation time.
		* host_usage.sh collects the current host usage (CPU and Memory) and then insert into the database. It will be triggered by the crontab job every minute.
		*psql_docker.sh
	
	* sql
		* ddl.sql
		* queries.sql : contains queries to manipulate data

	* Readme.md: contains the high level description of project architecture

