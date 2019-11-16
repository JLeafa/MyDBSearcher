JAVA_COMPILER = javac
JAVA_EXECUTER = java

JAVA_SOURCE_DIR       = src/jp/akatsubakij
JAVA_TEST_DIR         = src/test
JAVA_CLASS_OUTPUT_DIR = target

JARFILE_SERVER = server-process.jar
JARFILE_CLIENT = client-process.jar
SOURCEDIR      = application util security
LIBDIR         = src/resource
CLASSPATH      = $(LIBDIR)/sqlite_db/sqlite-jdbc-3.27.2.1.jar

all:
	@echo Hello World

build:
	$(JAVA_COMPILER) -cp $(JAVA_CLASS_OUTPUT_DIR):$(CLASSPATH) -d $(JAVA_CLASS_OUTPUT_DIR) $(JAVA_SOURCE_DIR)/config/*.java
	$(JAVA_COMPILER) -cp $(JAVA_CLASS_OUTPUT_DIR):$(CLASSPATH) -d $(JAVA_CLASS_OUTPUT_DIR) $(JAVA_SOURCE_DIR)/util/*.java
	$(JAVA_COMPILER) -cp $(JAVA_CLASS_OUTPUT_DIR):$(CLASSPATH) -d $(JAVA_CLASS_OUTPUT_DIR) $(JAVA_SOURCE_DIR)/common/packet/*.java
	$(JAVA_COMPILER) -cp $(JAVA_CLASS_OUTPUT_DIR):$(CLASSPATH) -d $(JAVA_CLASS_OUTPUT_DIR) $(JAVA_SOURCE_DIR)/server/*.java
	$(JAVA_COMPILER) -cp $(JAVA_CLASS_OUTPUT_DIR):$(CLASSPATH) -d $(JAVA_CLASS_OUTPUT_DIR) $(JAVA_SOURCE_DIR)/client/*.java
	$(JAVA_COMPILER) -cp $(JAVA_CLASS_OUTPUT_DIR):$(CLASSPATH) -d $(JAVA_CLASS_OUTPUT_DIR) $(JAVA_TEST_DIR)/*.java

clean:
	rm -rf $(JAVA_CLASS_OUTPUT_DIR)/*
