package server_client1;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class Server{
    private static final Map<Integer, Socket> listOfClients = new HashMap<>();

    public void removeFromLists(int clientId){
        listOfClients.remove(clientId);
    }

    public static void main(String[] args) {

        //Map<Integer, Socket> listOfClients= new HashMap<>();

        try (ServerSocket server = new ServerSocket(1234)) {
            int threadId = 0;

            server.setReuseAddress(true);
            System.out.println("Server > Waiting for connections ...");

            while (true) {

                Socket client = server.accept();

                System.out.println("Server > New client " + client.getRemoteSocketAddress().toString() + " connected at "
                        + client.getInetAddress()
                        .getHostAddress());

                threadId++;
                ClientThread clientSock
                        = new ClientThread(client, threadId);

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