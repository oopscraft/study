
# install
```
user@host> echo "deb https://dl.bintray.com/rabbitmq/debian bionic main" | sudo tee /etc/apt/sources.list.d/bintray.rabbitmq.list

user@host> sudo apt update
user@host> sudo apt-get install rabbitmq-server
```

# install(manually)
```
# sync package metadata
sudo apt-get update
# install dependencies manually
sudo apt-get -y install socat logrotate init-system-helpers adduser

# download the package
sudo apt-get -y install wget
wget https://github.com/rabbitmq/rabbitmq-server/releases/download/v3.8.5/rabbitmq-server_3.8.5-1_all.deb

# install the package with dpkg
sudo dpkg -i rabbitmq-server_3.8.5-1_all.deb

rm rabbitmq-server_3.8.5-1_all.deb

```

# kill process
sometimes process start and stop stuck. kill process.

```
user@host> sudo pkill -KILL -u rabbitmq
```

# starts rabbitmq-server
``` shell
user@host> sudo service rabbitmq-server start
```

# Setting Management plugin
```
user@host> sudo rabbitmq-plugins enable rabbitmq_management
user@host> sudo rabbitmq-server restart
```

# Adds administrator user
```
user@host> sudo rabbitmqctl add_user admin rhksflwk
user@host> sudo rabbitmqctl set_user_tags admin administrator
```

# Connection admin web console
> http://127.0.0.1:15672


