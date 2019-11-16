# java_network_test

This project is production of light network application that is coded by Java.

## Specification

This application provides the "searching service" from database.  
A server pripare database, then client connect to it.  
Also application supports the GUI function.  
model.png shows the application sequence chart.  

## Environment

I tested it with the following environment

- OS
  - MacOS Mojave 10.14.6
- Java version
  - OpenJDK Runtime Environment (build 13+33)
- Database
  - Sqlite3 (3.24.0)

## Build

At first, a database table has to be created in server.  
For example, I recomend you to use Chinook sample dataset.
Then, you type the following command in the top directory.

```
make build
```

But, you have to set the classpath that refers corrent directory and postgresql.jar.
(Currently the path is hardcoded in server source file)

## Run

At first, server program is started.

```bash
./run.sh server
```

Client program is started as well after server running.

```bash
./run.sh client localhost
```
