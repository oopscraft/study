# RabbitMQ

## Installation

### Installs via package
```
user@host> echo "deb https://dl.bintray.com/rabbitmq/debian bionic main" | sudo tee /etc/apt/sources.list.d/bintray.rabbitmq.list

user@host> sudo apt update
user@host> sudo apt-get install rabbitmq-server
```

### Installs manually
``` shell
# sync package metadata
user@host> user@host>sudo apt-get update
# install dependencies manually
user@host> sudo apt-get -y install socat logrotate init-system-helpers adduser

# download the package
user@host> sudo apt-get -y install wget
user@host> wget https://github.com/rabbitmq/rabbitmq-server/releases/download/v3.8.5/rabbitmq-server_3.8.5-1_all.deb

# install the package with dpkg
user@host> sudo dpkg -i rabbitmq-server_3.8.5-1_all.deb
user@host> rm rabbitmq-server_3.8.5-1_all.deb
```

### Kills process
sometimes process start and stop stuck. kill process.
``` shell
user@host> sudo pkill -KILL -u rabbitmq
```

### Starts rabbitmq-server
``` shell
# start service
user@host> sudo service rabbitmq-server start
# test socket open(default port is 5672)
user@host> telnet 127.0.0.1 5672
```

### Setting Management plugin
```
user@host> sudo rabbitmq-plugins enable rabbitmq_management
user@host> sudo rabbitmq-server restart
```

### Adds administrator user
```
user@host> sudo rabbitmqctl add_user admin rhksflwk
user@host> sudo rabbitmqctl set_user_tags admin administrator
```

### Connection admin web console
> http://127.0.0.1:15672
> admin / rhksflwk


## CLI interface

### list queue
```
user@host> rabbitmqadmin list queues
```

### declare queue
```
user@host> rabbitmqadmin declare queue name=test durable=false
```