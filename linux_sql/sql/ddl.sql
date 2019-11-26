/* Create db if doesn't exists  */
DO
$do$
DECLARE
  _db TEXT := 'host_agent';
BEGIN
CREATE EXTENSION IF NOT EXISTS dblink;
   IF EXISTS (SELECT FROM pg_database WHERE datname = _db) THEN
      RAISE NOTICE 'Database already exists';  -- optional
   ELSE
      PERFORM dblink_exec('CREATE DATABASE ' || _db);
   END IF;
END
$do$;

--CREATE DATABASE host_agent;
/* connect to the new database;*/
 \c host_agent;

/* create table hostinto */
CREATE TABLE IF NOT EXISTS PUBLIC.host_info 
  ( 
     id               SERIAL PRIMARY KEY, 
     hostname         VARCHAR UNIQUE NOT NULL,
     cpu_number       INTEGER NOT NULL,
     cpu_architecture VARCHAR NOT NULL,
     cpu_model        VARCHAR NOT NULL,
     cpu_mhz          DECIMAL NOT NULL,
     L2_cache         INTEGER NOT NULL,
     total_mem        INTEGER NOT NULL,
     timestamp        TIMESTAMP NOT NULL
  );

/*create table host_usage*/
 CREATE TABLE IF NOT EXISTS PUBLIC.host_usage 
  ( 
     "timestamp"    TIMESTAMP NOT NULL, 
     host_id        SERIAL REFERENCES host_info(id), 
     memory_free    INTEGER NOT NULL,
     cpu_idel       INTEGER NOT NULL,
     cpu_kernel     INTEGER NOT NULL,
     disk_io        INTEGER NOT NULL,
     disk_available INTEGER NOT NULL
  ); 
