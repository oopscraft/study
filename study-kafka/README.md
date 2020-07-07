# Kafka

## Installation

### Installs Jookeeper
```
# getting zookeeper distribution file
user@host> wget http://apache.tt.co.kr/zookeeper/zookeeper-3.6.1/apache-zookeeper-3.6.1-bin.tar.gz
user@host> tar -zxvf ./apache-zookeeper-3.6.1-bin.tar.gz
user@host> ln -s apache-zookeeper-3.6.1-bin apache-zookeeper

# edits configuration file
user@host> cp zoo_sample.cfg zoo.cfg
user@host> vim zoo.cfg
...
# custer mode(optional)
server.1=0.0.0.0:2888:3888
server.2={Node IP}:2888:3888
server.3={Node IP}:2888:3888
...

# start zookeeper
user@host> ./bin/zkServer.sh start

# stop zookeeper
user@host> ./bin/zkServer.sh stop

# tailing logs
user@host> ./logs/zookeeper*.*

```

### Installs Kafka
```
# getting kafka distribution file
user@host> wget http://apache.tt.co.kr/kafka/2.5.0/kafka_2.12-2.5.0.tgz
user@host> tar -cvf ./kafka_2.12-2.5.0.tgz
user@host> ln -s ./kafka_2.12-2.5.0 ./kafka

# creates data directory
user@host> mkdir data

# setting zookeeper
user@host> telnet 127.0.0.1 2181
user@host> vim ./config/server.properties
...
// TODO
...

# starts kafka
user@host> ./bin/kafka-server-start.sh -daemon ./config/server.properties

# stop kafka
user@host> ./bin/kafka-server-stop.sh

```

