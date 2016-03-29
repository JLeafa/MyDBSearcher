COMPILER=javac
JVM=java
FILE_MAIN=server_10000
FILE_SQL=test_sql
PACKAGE=databasetest
SCRIPT_FILE=clp

build:
	$(COMPILER) $(PACKAGE)/$(FILE_SQL).java
	$(COMPILER) $(FILE_MAIN).java

run:
	$(JVM) $(FILE_MAIN)

clean:
	rm $(FILE_NAME).class $(PACKAGE)/$(FILE_SQL).class
