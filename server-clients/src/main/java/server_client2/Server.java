package server_client2;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) {

        try (ServerSocket server = new ServerSocket(5678)) {
            int threadId = 0;

            server.setReuseAddress(true);
            System.out.println("Server > Waiting for connections ...");

            while (true) {

                Socket client = server.accept();

                System.out.println("New client " + client.getRemoteSocketAddress().toString() + " connected at "
                        + client.getInetAddress()
                        .getHostAddress());

                threadId++;
                ClientThread clientSock
                        = new ClientThread(client, threadId);

                new Thread(clientSock).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}