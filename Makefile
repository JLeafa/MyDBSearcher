COMPILER=javac
JVM=java
FILE_SERVER=server_program
FILE_CLIENT=client_program

build:
	$(COMPILER) $(FILE_CLIENT).java
	$(COMPILER) $(FILE_SERVER).java

server:
	$(JVM) $(FILE_SERVER)

clean:
	rm *.class
