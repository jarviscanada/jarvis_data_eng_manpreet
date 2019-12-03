/* Question 1: group hosts by CPU number and sort by their memory size in descending order(within each cpu_number group)*/

select
        cpu_number, id, total_mem
from
        host_info
order by
        cpu_number, total_mem desc;


/* Question 2: Average used memory in percentage over 5 mins interval for each host. (used memory = total memory - free memory). */

select
        host_usage.host_id,
        host_info.hostname,
        AVG(((((host_info.total_mem - host_usage.memory_free)*100.0)/host_info.total_mem * 1.0)))
                OVER (PARTITION BY host_info.id,
                date_part('year', host_usage.timestamp),
                date_part('month', host_usage.timestamp),
                date_part('day', host_usage.timestamp),
                date_part('hour', host_usage.timestamp),
                round(date_part('minute', host_usage.timestamp) / 5)
        )
as
        used_memory_percentage
from
        host_info, host_usage
where
        host_info.id = host_usage.host_id;
