#!/usr/bin/env bash
sudo apt-get update && sudo apt-get install docker.io docker-compose || exit 1
sudo docker-compose pull || exit 1
sudo docker-compose up -d db
while ! sudo docker-compose exec -T db sh -c 'exec mysql -uroot -p"$MYSQL_ROOT_PASSWORD" -we "select 1;" benchmark'; do
    echo "waiting for database startup"
    sleep 1
done
sudo docker-compose exec -T db sh -c 'exec mysql --default-character-set=utf8 -uroot -p"$MYSQL_ROOT_PASSWORD" benchmark' < ./database.sql || exit 1
sudo docker-compose up -d
sleep 20
sudo docker-compose run newman
