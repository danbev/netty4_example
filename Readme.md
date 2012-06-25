## Netty 4 example project
This is a very basic example to get familiar with Netty4 (4.0.0.Alpha1-SNAPSHOT).
The example consists of a server and a client. The client starts up and waits for input from the users, 
which the client will send to the server which simply logs the messages to the console.
___

## Building
Gradle 1.0 is used to build and run this project.

### Start the server

    ./gradlew server
    
### Start the client
In a new console window start the client using the following command.

    ./gradlew client
    
After the client is started it will display the following message:

    Enter line to be sent (exit to quit):
    
Enter a line of text and press enter. This line of text should now be displayed by the server console.

---