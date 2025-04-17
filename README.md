Cache Manager CLI Tool
======================

Code is deployed to [Azure Web Apps](https://spring-cli-d6fdhva8dyg8hya2.eastus2-01.azurewebsites.net/)
A command-line interface (CLI) application built with Spring Boot and Spring Shell to interact with a cache management API (mocked for now). Provides commands to refresh, refill, get the content of specific caches, and list available cache names.

Prerequisites
-------------

-   Java Development Kit (JDK) 17 or higher (JDK 21+ recommended as per your logs)

-   Apache Maven 3.6 or higher

-   Lombok (dependency needed for `@RequiredArgsConstructor`)

Building the Application
------------------------

1.  Clone this repository (if applicable).

2.  Navigate to the project root directory in your terminal.

3.  Build the executable JAR file using Maven:

    ```
    mvn clean package

    ```

    This will create a JAR file in the `target/` directory (e.g., `target/cache-cli-0.0.1-SNAPSHOT.jar`, the exact name depends on your `pom.xml`).

Running the Application
-----------------------

You can run the application in a few ways:

1.  Using the Executable JAR:

    ```
    java -jar target/cache-cli-0.0.1-SNAPSHOT.jar

    ```

2.  Using Spring Boot Maven Plugin (for development):

    ```
    mvn spring-boot:run

    ```

Once the application starts, you should see a welcome message (if implemented) and the shell prompt (default `shell:>` or your custom prompt if configured).

```
---------------------------------------------
 You can type 'help' for a list of commands
---------------------------------------------
shell:> # Or your custom prompt

```

Configuration
-------------

You can customize the shell prompt by adding the following line to `src/main/resources/application.properties`:

```
spring.shell.interactive.prompt=cache-manager:>

```

Usage
-----

Type commands directly into the shell prompt. You can type `help` to see a list of available commands.

### Available Commands:

-   `help`: Displays help information. Type `help [command]` for specific command details.

-   `exit` or `quit`: Exits the shell.

The custom cache commands are:

1.  `refresh-cache <cacheName>`

    -   Description: Sends a request to refresh a specific cache by making a (mocked) API call.

    -   Parameters:

        -   `<cacheName>`: (Required) The name of the cache to refresh.

    -   Usage: Type the command followed by the cache name.

    -   Example:

        ```
        shell:> refresh-cache users-cache

        ```

2.  `refill-cache <cacheName>`

    -   Description: Sends a request to clear and refill a specific cache by making a (mocked) API call.

    -   Parameters:

        -   `<cacheName>`: (Required) The name of the cache to refill.

    -   Usage: Type the command followed by the cache name.

    -   Example:

        ```
        shell:> refill-cache product-inventory

        ```

3.  `get-cache <cacheName>`

    -   Description: Sends a request to retrieve the data of a specific cache by making a (mocked) API call.

    -   Parameters:

        -   `<cacheName>`: (Required) The name of the cache to retrieve.

    -   Usage: Type the command followed by the cache name.

    -   Example:

        ```
        shell:> get-cache config-settings

        ```

4.  `cache-names`

    -   Description: Gets all the cache names that the service knows about by making a (mocked) API call.

    -   Parameters: None.

    -   Usage: Simply type the command.

    -   Example:

        ```
        shell:> cache-names

        ```

Implementation Details (Relevant Code Snippets):

Here is the code for the shell commands:
