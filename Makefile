JAVA_COMPILER=javac
JAVA_EXECUTER=java
JAVA_SOURCE_DIR=src
JAVA_CLASS_OUTPUT_DIR=target
SOURCES=$(shell ls $(JAVA_SOURCE_DIR)/*.java)
CLASSES=$(subst $(JAVA_SOURCE_DIR), $(SOURCES:.java=.class))

.PHONY

all:
	@echo Hello World

build:
	$(JAVA_COMPILER) -d $(JAVA_CLASS_OUTPUT_DIR) -sourcepath $(JAVA_SOURCE_DIR) $(JAVA_SOURCE_DIR)/server/ServerMainProcess.java

clean:
	rm -f $(JAVA_SOURCE_DIR)/server/*.class
	rm -f $(JAVA_SOURCE_DIR)/client/*.class
	rm -f $(JAVA_SOURCE_DIR)/server/*.class
