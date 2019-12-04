#!/bin/bash

#setting command line arguments
mode=$1
PGPASSWORD="$2" #set password for default user `postgres`

#checking correct number of arguments
if [[ $# -gt "2" ]] || [[ $# -lt "1" ]]; then
	echo 'Please enter correct number of arguments
	For ex:h psql_docker.sh start|stop [db_password]'
>&2
	exit 1
fi

case "$mode" in
  start)
	if [[ -z "$2" ]]; then
		echo "Please enter the password as: 
		./psql_docker.sh start <password>"
		exit 1
	fi
	#start docker
	sudo systemctl status docker || systemctl start docker

	#get psql docker image
	sudo docker pull postgres

	#create a local volumn to persist data
	sudo docker volume create pgdata

	#check if docker is running
        if [[ ! -z  `sudo docker ps | grep 'jrvs-psql'` ]]; then
                echo "The instance is already running"
        else
		if [[ ! -z `sudo docker container ls -al -f NAME=jrvs-psql --format '{{.Names}}'` ]];  then
		containerId=`sudo docker container ls -al -f NAME=jrvs-psql --format '{{.ID}}'`
		sudo docker start $containerId
		else
        	#run psql
        	sudo docker run --name jrvs-psql -e POSTGRES_PASSWORD=$PGPASSWORD -v pgdata:/var/lib/postgresql/data -p 5432:5432 -d postgres
		fi
        fi	
    ;;
  stop)
	#Get docker container id from container name
  	pid=$(sudo docker ps -aqf "name=jrvs-psql")
	sudo docker stop $pid
     ;;
  *)
    echo "Usage: $0 {start|stop}"
    ;;
esac

exit 0
