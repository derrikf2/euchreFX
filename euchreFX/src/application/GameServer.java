package application;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author Josh T, Derek F, Jarret S
 *
 */
public class GameServer {
    /**
     *
     * @param numClients is a parameter.
     * @throws IOException is acceptable.
     */
    public GameServer() throws IOException {
        final int portNum = 9878;
        ServerSocket listenSocket = new ServerSocket(portNum);
        Socket clientSocket;
        for (int i = 0; i < 2; i++) {
            clientSocket = listenSocket.accept(); // waits for client connection
            new Thread(new ReceiveFromClient(clientSocket, i)).start();
            E.getClients().add(new SendToClient(clientSocket));
        }
        listenSocket.close();
    }
}

/**
 *
 * @author Josh T, Derek F, Jarret S
 *
 */
class ReceiveFromClient implements Runnable {
    /** the main socket. */
    private Socket clientSocket;

    /** the thread's ID. */
    private int threadID;

    /**
     *
     * @param connection is the socket the thread uses.
     * @param num is the ID assigned.
     */
    ReceiveFromClient(final Socket connection, final int num) {
        clientSocket = connection;
        threadID = num;
    }

    /**
     * the run method.
     */
    public void run() {
        try {
            ObjectInputStream inFromClient =
                    new ObjectInputStream(clientSocket.getInputStream());
            boolean continueLoop = true;
            Object data;
            while (continueLoop) {
                data = inFromClient.readObject();
                for (int i = 0; i < E.getClients().size(); i++) {
                    if (i != threadID) {
                        E.getClients().get(i).send(data);
                    }
                }
            }
        } catch (Exception e) {
            System.exit(1);
        }
    }
}

/**
 *
 * @author Josh T, Derek F, Jarret S
 *
 */
class SendToClient {
    /** the main socket used. */
    private Socket clientSocket;

    /** the way we send over the socket. */
    private ObjectOutputStream outToClient;

    /** variable determines if client is still connected to the server. */
    private boolean isAlive;

    /**
     *
     * @param connection is the socket.
     * @throws IOException which is fine.
     */
    SendToClient(final Socket connection) throws IOException {
        clientSocket = connection;
        outToClient = new ObjectOutputStream(clientSocket.getOutputStream());
        isAlive = true;
    }

    /**
     *
     * @param data is the object being sent.
     * @throws IOException is fine.
     */
    public void send(final Object data) throws IOException {
        outToClient.writeObject(data);
    }

    /**
     *
     * @return true when it is alive.
     */
    public boolean isAlive() {
        return isAlive;
    }

    /**
     *
     */
    public void kill() {
        isAlive = false;
    }
}

/**
 *
 * @author Josh T, Derek F, Jarret S
 *
 */
class E {
    /** a list of clients to be used by the server. */
    private static ArrayList<SendToClient> clients =
            new ArrayList<SendToClient>();

    /**
     *
     * @return the list of clients.
     */
    public static ArrayList<SendToClient> getClients() {
        return clients;
    }
}