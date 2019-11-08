JAVA_COMPILER=javac
JAVA_EXECUTER=java
JAVA_SOURCE_DIR=src
JAVA_CLASS_OUTPUT_DIR=target

all:
	@echo Hello World

build:
	$(JAVA_COMPILER) -d $(JAVA_CLASS_OUTPUT_DIR) -sourcepath $(JAVA_SOURCE_DIR) $(JAVA_SOURCE_DIR)/server/ServerMainProcess.java
	$(JAVA_COMPILER) -d $(JAVA_CLASS_OUTPUT_DIR) -sourcepath $(JAVA_SOURCE_DIR) $(JAVA_SOURCE_DIR)/client/ClientMainProcess.java

clean:
	rm -rf $(JAVA_CLASS_OUTPUT_DIR)