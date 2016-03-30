COMPILER=javac
JVM=java
FILE_SERVER=server
FILE_CLIENT=client

build:
	$(COMPILER) $(FILE_CLIENT).java
	$(COMPILER) $(FILE_SERVER).java

run:
	$(JVM) $(FILE_CLIENT)

clean:
	rm *.class
