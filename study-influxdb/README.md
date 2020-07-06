
# install
```
sudo apt-get install influxdb influxdb-client
user@host> sudo service influxdb start
```

# tailing logs
user@host> tail -F /var/log/influxdb/influxdb.log

# connect for test
user@host> influx
...
-- creates database
create database test;
show databases;

-- insert measurement
insert memory,host=server01,region=korea value=5.5
show measurement
select * from memory
```

# Configuration

##
sudo vim /etc/influxdb/influxdb.conf
...
auth-enable = true
...

## creates user
```
CREATE USER test1 WITH PASSWORD 'xptmxm1' WITH ALL PRIVILEGES

```

