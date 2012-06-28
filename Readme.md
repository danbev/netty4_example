## Netty 4 example project
This is a very basic examples to get familiar with Netty4 (4.0.0.Alpha1-SNAPSHOT).


## Simple Client/Server example
The example consists of a server and a client. The client starts up and waits for input from the users, 
which the client will send to the server which simply logs the messages to the console.

### Building
Gradle 1.0 is used to build and run this project.

### Start the server

    ./gradlew server
    
### Start the client
In a new console window start the client using the following command.

    ./gradlew client
    
After the client is started it will display the following message:

    Enter line to be sent (exit to quit):
    
Enter a line of text and press enter. This line of text should now be displayed by the server console.

## WebSocket example
The server side of this example was taken from the Netty project's example and only slightly modified. It does
not have a Netty client but insteads provides a HTML5 UI.

### Start the WebSocket server

    ./gradlew wsserver
    
    
### Run the client
Start a browser and open the file _src/main/webapp/websocket.html_.

The example has been tested with Chrome 19.0.1084.56, Firefox 10.0.2, and Safari 5.1.1.

