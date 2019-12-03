#!/bin/bash

#set Command line arguments
psql_host=$1
psql_port=$2    
db_name=$3      
psql_user=$4    
psql_password=$5

if [[ $# -lt 1 || $# -gt 5 ]]; then
	echo "Invalid number of arguments
	For ex: ./host_info.sh <host> <port> <database_name> <username> <password>"
	exit 1
fi


#save hostname to a variable
hostname=$(hostname -f)

#save number of CPU to a variable
lscpu_out=$(lscpu)

cpu_number=$(echo "$lscpu_out"  | egrep "^CPU\(s\):" | awk '{print $2}')

#save cpu architecture
cpu_architecture=$(echo "$lscpu_out" | grep Architecture | awk '{print $2}')

#save cpu_model name and number
cpu_model=$(cat /proc/cpuinfo | grep 'model name' | uniq | awk '{print $4 $5 $6 $7 $8}')

#save cpu MHz
cpu_mhz=$(sudo cat /proc/cpuinfo | grep 'cpu MHz' | uniq | awk '{print $4}' )

#save L2 Cache
L2_cache=$(echo "$lscpu_out" | grep 'L2 cache' | awk '{print $3}' | sed 's/K//')

#save total memory
total_mem=$(grep MemTotal /proc/meminfo | awk '{print $2}')

#timestamp
timestamp=$(date -d @1332468005 '+%Y-%m-%d %H:%M:%S')

#Set the value of variable

echo "Hostname: $hostname"
echo "CPU number: $cpu_number"
echo "CPU Architecture: $cpu_architecture"
echo "CPU Model: $cpu_model"
echo "CPU MHZ: $cpu_mhz"
echo "L2 cache: $L2_cache"
echo "Total memory: $total_mem"
echo "Timestamp: $timestamp"

PGPASSWORD=$psql_password psql -h $psql_host -p $psql_port -d $db_name -U $psql_user<<EOF
INSERT INTO host_info(hostname, cpu_number, cpu_architecture, cpu_model, cpu_mhz, L2_cache, total_mem, timestamp)
VALUES
   ('$hostname', $cpu_number, '$cpu_architecture', '$cpu_model', $cpu_mhz, $L2_cache, $total_mem, '$timestamp');
EOF

psql_exit_status=$?

if [ $psql_exit_status != 0 ]; then
    echo "psql failed while trying to run this sql script"
    exit $psql_exit_status
fi

exit 0
