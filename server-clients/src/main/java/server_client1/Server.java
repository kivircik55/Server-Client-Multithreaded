package server_client1;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class Server{
    static final Map<Integer, Socket> listOfClients = new HashMap<>();

    public static void main(String[] args) {
        Server server = new Server();

        try (ServerSocket serverSocket = new ServerSocket(1027)) {
            int threadId = 0;

            serverSocket.setReuseAddress(true);
            System.out.println("Server > Waiting for connections ...");

            Socket client = null;
            while (true) {

                client = serverSocket.accept();

                System.out.println("Server > New client " + client.getRemoteSocketAddress().toString() + " connected at "
                        + client.getInetAddress()
                        .getHostAddress());

                threadId++;

                DataInputStream dataInputStream = new DataInputStream(client.getInputStream());
                DataOutputStream dataOutputStream = new DataOutputStream(client.getOutputStream());
                InterfaceInteractWithClients interactWithClients = new InteractWithClientsImpl(server);

                ClientThread clientSock
                        = new ClientThread(client, threadId, dataInputStream, dataOutputStream, server, interactWithClients);

                new Thread(clientSock).start();

                listOfClients.put(threadId, client);

                listOfClients.entrySet().forEach(entry ->{
                    System.out.println("Server clients list : ID#"+entry.getKey()+", Socket Port:"+entry.getValue().getPort());
                });
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}