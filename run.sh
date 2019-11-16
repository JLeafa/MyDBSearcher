#!/bin/bash

if [ -z $1 ]; then
    cat << EOS
** How to use **
1.server
    ./run.sh server

2.client
    ./run.sh client
EOS
    exit 1
fi

if [ ! -d ./target ]; then
    mkdir target
    make build
fi

jar_class_path="../src/resource/sqlite_db/sqlite-jdbc-3.27.2.1.jar"

if [ $1 = "server" ]; then
    cd target && java -cp .:${jar_class_path} jp.akatsubakij.server.ServerMainProcess
elif [ $1 = "client" ]; then
    cd target && java jp.akatsubakij.client.ClientMainProcess localhost
fi
