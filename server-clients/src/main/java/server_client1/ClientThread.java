package server_client1;

import java.io.*;
import java.net.Socket;

public class ClientThread implements Runnable{
    private final Socket clientSocket;
    private final int threadId;
    private final DataInputStream dataInputStream;
    private final DataOutputStream dataOutputStream;
    private InterfaceInteractWithClients interactWithClients;

    public ClientThread(Socket serverSocket, int threadId, DataInputStream dataInputStream, DataOutputStream dataOutputStream,
                        Server server, InterfaceInteractWithClients interactWithClients) {
        this.clientSocket = serverSocket;
        this.threadId = threadId;
        this.dataInputStream = dataInputStream;
        this.dataOutputStream = dataOutputStream;
        this.interactWithClients = interactWithClients;
    }

    @Override
    public void run() {
        String receivedMessage;
        String toSendMessage;

        while (true) {
            try {

                dataOutputStream.writeUTF("Server > Welcome on the server !\n" +
                        "Server > What do you want ? [Clients]\n" +
                        "Server > Type EXIT to leave the server ...");

                receivedMessage = dataInputStream.readUTF();

                if (receivedMessage.equals("Exit")) {
                    System.out.println("See you space cowboy ...");
                    this.clientSocket.close();
                    dataOutputStream.close();
                    dataInputStream.close();
                    System.out.println("Connection to Client #" + this.threadId + " has been closed");
                    interactWithClients.deleteClient(this.threadId);
                    break;
                }

                switch (receivedMessage) {
                    case "Clients":
                        toSendMessage = interactWithClients.messageToSendToAllClients();
                        dataOutputStream.writeUTF(toSendMessage);
                        break;

                    default:
                        dataOutputStream.writeUTF("Invalid output");
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
