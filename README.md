# Simple HTTP Server

A simple HTTP server built in Java.

## Features

- **Lightweight**: Minimal dependencies and straightforward implementation.
- **Static File Serving**: Serves static files from a specified directory.
- **Configurable Port**: Allows specifying the port to run the server on.

## Prerequisites

- **Java Development Kit (JDK)**: Ensure you have JDK 8 or higher installed. You can download it from the [official Oracle website](https://www.oracle.com/java/technologies/javase-downloads.html) or use an open-source alternative like [AdoptOpenJDK](https://adoptopenjdk.net/).

- **Maven**: This project uses Maven for build automation. If you don't have Maven installed, you can download it from the [official Maven website](https://maven.apache.org/download.cgi).

## Installation

1. **Clone the Repository**:

   ```bash
   git clone https://github.com/bhima2001/simple-http-server.git
   cd simple-http-server
   ```

2. **Build the Project**:

   Use Maven to build the project. This will compile the source code and package it into a runnable JAR file.

   ```bash
   mvn clean package
   ```

   After the build completes, you should find the JAR file in the `target` directory.

## Usage

To start the server, run the JAR file with the desired port number and the directory you want to serve:

```bash
java -jar target/simple-http-server-1.0-SNAPSHOT.jar <port> <directory>
```

- `<port>`: The port number on which the server will listen (e.g., `8080`).
- `<directory>`: The path to the directory containing the static files you want to serve.

**Example**:

```bash
java -jar target/simple-http-server-1.0-SNAPSHOT.jar 8080 /path/to/your/static/files
```

Once the server is running, you can access your static files by navigating to `http://localhost:8080` in your web browser.

## Contributing

Contributions are welcome! If you have suggestions or improvements, feel free to open an issue or submit a pull request.
