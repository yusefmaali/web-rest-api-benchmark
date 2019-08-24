#!/bin/bash

mkdir -p data
mkdir -p graphs

host=$1

if [[ "$host" = "" ]]; then
    echo "Missing the target host"
    exit 1;
fi

bench() {
    concurency=$1
    endpoint=$2
    host=$3
    ab -t 30 -c ${concurency} -n 1000000 -g data/${endpoint}-c${concurency}-java.tsv http://${host}:8080/${endpoint}
    ab -t 30 -c ${concurency} -n 1000000 -g data/${endpoint}-c${concurency}-laravel.tsv http://${host}:8081/${endpoint}
    ab -t 30 -c ${concurency} -n 1000000 -g data/${endpoint}-c${concurency}-go.tsv http://${host}:8082/${endpoint}
    ab -t 30 -c ${concurency} -n 1000000 -g data/${endpoint}-c${concurency}-node.tsv http://${host}:8083/${endpoint}
    ab -t 30 -c ${concurency} -n 1000000 -g data/${endpoint}-c${concurency}-dotnet.tsv http://${host}:8084/${endpoint}
    ab -t 30 -c ${concurency} -n 1000000 -g data/${endpoint}-c${concurency}-falcon.tsv http://${host}:8085/${endpoint}
    ab -t 30 -c ${concurency} -n 1000000 -g data/${endpoint}-c${concurency}-django+debug.tsv http://${host}:8086/${endpoint}
    ab -t 30 -c ${concurency} -n 1000000 -g data/${endpoint}-c${concurency}-django+gunicorn.tsv http://${host}:8087/${endpoint}
    ab -t 30 -c ${concurency} -n 1000000 -g data/${endpoint}-c${concurency}-django+uwsgi.tsv http://${host}:8088/${endpoint}
}

bench 10 hello ${host}
bench 10 compute ${host}
# bench 10 countries ${host}
# bench 10 users ${host}

rps() {
    endpoint=$1
    rm data/rps-${endpoint}.dat
    for f in data/${endpoint}*.tsv; do
        filename=$(basename -- "$f")
        filename="${filename%.*}"
        wc -l ${f} | echo ${filename##*-} `awk '{ result = ($1 - 1) / 30; print result}'` >> data/rps-${endpoint}.dat
    done
}

rps hello
rps compute
rps countries
rps users
