#!/bin/bash

#set Command line arguments
psql_host=$1
psql_port=$2    
db_name=$3      
psql_user=$4    
psql_password=$5

#save hostid variable
host_name=$(hostname -f)
#save memory_free to a variable
memory_free=$(cat /proc/meminfo | grep MemFree | awk '{print $2/1000}')
#save cpu idel
vmstat=$(vmstat | tail -1)
free_memory=$(echo $vmstat | awk '{print $4}')
echo "Free memory $free_memory"
total_memory=$(grep MemTotal /proc/meminfo | awk '{print $2}')
cpu_idle=$(( ($free_memory * 100) / $total_memory  ))
#save cpu_kernel
cpu_kernel=$(echo $vmstat | awk '{print $15}')
#save disk_io
disk_bi=$(echo $vmstat | awk '{print $9}')
disk_bo=$(echo $vmstat | awk '{print $10}')
disk_io=$(( $disk_bi+$disk_bo ))
#save disk_available
disk_available=$(df | awk '{sum+=$4} END {print sum}')
#timestamp
timestamp=$(date '+%Y-%m-%d %H:%M:%S')
#Set the value of variable

host_id=`PGPASSWORD=$psql_password psql -X -A -d $db_name -U "postgres" -h $psql_host  -p $psql_port -t -c "SELECT id FROM host_info WHERE hostname='$host_name'"`

echo "host_id: $host_id "
echo "memory_free: $memory_free "
echo "cpu_idle: $cpu_idle"
echo "cpu_kernel: $cpu_kernel "
echo "disk_io: $disk_io "
echo "disk_available: $disk_available "
echo "Timestamp: $timestamp "

PGPASSWORD=$psql_password psql -h $psql_host -p $psql_port -d $db_name -U $psql_user << EOF 
INSERT INTO host_usage(host_id, memory_free, cpu_idel, cpu_kernel, disk_io, disk_available, timestamp)
VALUES
   ('$host_id', $memory_free, '$cpu_idle', '$cpu_kernel', $disk_io, $disk_available, '$timestamp');
EOF

exit 0
