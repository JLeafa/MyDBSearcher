## java_network_test
This project is production of light network application that is coded by Java.

### Specification
This application provides the "searching service" from database.  
A server pripare database, then client connect to it.  
Also application supports the GUI function.  
model.png shows the application sequence chart.  

### Environment
I tested it with the following environment

- Server
    - MySQL (ver7.x.x)
    - Ubuntu 14
    - JDBC driver in order to access to MySQL server from Java application
    - add ```/usr/share/java/mysql.jar``` into linux $CLASSPATH 
- Client
    - environment in which Java available
    - any system (tested Windows10 here)

### Build
At first, a database table is created in server.  
I proposed you a sample dabase script ```student.sql```.  
It contains an aerial student data with *name, age, department in a university*  
Then you can build this project as below.

```
make build
```

But, you have to set the classpath that refers corrent directory and mysql.jar

### Do
To execute class files, two consoles or two PC are needed.
#### Client Side

You type the command below
```
java client "IP Address of Server"
```

then, GUI window is opened

#### Server Side
You type the command below

```
make run
```
