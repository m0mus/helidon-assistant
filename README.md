# Helidon Assistant

Helidon Assistant is a Retrieval-Augmented Generation (RAG) application built around Helidon's documentation. It provides a friendly AI chat interface to help answer Helidon-related questions.

## Getting Started

### 1. Clone the Helidon Documentation

First, clone the [Helidon GitHub repository](https://github.com/helidon-io/helidon) to a temporary location on your local machine.

### 2. Configure the Application

Update the project's `application.yaml` file to point to the Helidon documentation source, typically located at `{helidon_repo_root}/docs/src/main/asciidoc`. You can also define inclusion and exclusion filters as needed:

```yaml
app:
  root: "//{helidon_repo_root}/docs/src/main/asciidoc"
#  exclusions:
  inclusions: "*.adoc"
```

### 3. Build the Project

Use Maven to build the application:

```bash
mvn clean package
```

### 4. Run the Application

Launch the assistant with:

```bash
java -jar target/helidon-assistant.jar
```

### 5. Chat with the Assistant

Open your browser and start chatting with the Helidon Assistant to get helpful insights and answers related to the Helidon framework.
