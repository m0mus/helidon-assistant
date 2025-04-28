#!/bin/bash
DIR=$(pwd)

# LangChain4J with Oracle GenAI integration POC snapshot build:
cd /tmp
git clone -b kec/oci-genai-model --single-branch git@github.com:danielkec/langchain4j.git
cd langchain4j
mvn install -DskipTests -Dmaven.test.skip=true -Dspotless.check.skip=true -Dmaven.javadoc.skip=true

# Helidon 4 with Oracle GenAI integration LangChain4J POC snapshot build:
cd /tmp
git clone -b kec/lc4j-oci-genai --single-branch git@github.com:danielkec/helidon.git
cd helidon
mvn install -DskipTests -Dmaven.test.skip=true


cd ${DIR}
mvn clean package -DskipTests

export OCI_GENAI_TEST_KEY=/home/daniel/Downloads/oci_kec_ai_test_api_key.pem
pwd
java -jar ./target/*.jar