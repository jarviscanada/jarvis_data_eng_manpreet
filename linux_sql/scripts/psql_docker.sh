#! /bin/bash


case "$1" in
  start)
	#start docker
	sudo systemctl status docker || systemctl start docker

	#get psql docker image
	sudo docker pull postgres

	#psql docker docs https://hub.docker.com/_/postgres
	#set password for default user `postgres`
	export PGPASSWORD="$2"
	#create a local volumn to persist data
	sudo docker volume create pgdata
	#run psql
	sudo docker run --rm --name jrvs-psql -e POSTGRES_PASSWORD=$PGPASSWORD -d -v pgdata:/var/lib/postgresql/data -p 5432:5432 postgres
	#check running status
	sudo docker ps	
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
