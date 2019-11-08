#!/bin/bash

if [ -z $1 ]; then
    cat << EOS
** How to use **
1. Server
    ./run.sh server

2. Client
    ./run.sh client
EOS
    exit 1
fi

if [ ! -d ./target ]; then
    make build
fi

if [ $1 = "server" ]; then
    cd target && java server.ServerMainProcess
elif [ $! = "client" ]; then
    cd target && java client.ClientMainProcess localhost
fi
