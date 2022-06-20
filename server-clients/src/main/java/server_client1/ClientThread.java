package server_client1;

import java.io.*;
import java.net.Socket;

public class ClientThread implements Runnable{
    private final Socket clientSocket;
    private final int threadId;
    private final DataInputStream dataInputStream;
    private final DataOutputStream dataOutputStream;
    private final Server server;

    private InterfaceInteractWithClients interactWithClients;

    public ClientThread(Socket serverSocket, int threadId, DataInputStream dataInputStream, DataOutputStream dataOutputStream,
                        Server server, InterfaceInteractWithClients interactWithClients) {
        this.clientSocket = serverSocket;
        this.threadId = threadId;
        this.dataInputStream = dataInputStream;
        this.dataOutputStream = dataOutputStream;
        this.server = server;
        this.interactWithClients = interactWithClients;
    }

    @Override
    public void run() {
        String receivedMessage;
        String toSendMessage;

            while (true) {
                try {
            /*read data from client
            InputStream inputStream = clientSocket.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            //send data to client
            OutputStream outputStream = clientSocket.getOutputStream();
            PrintWriter printWriter = new PrintWriter(outputStream, true);
            */

                    dataOutputStream.writeUTF("Server > Welcome on the server !\n" +
                            "Server > What do you want ? [Clients]\n" +
                            "Server > Type EXIT to leave the server ...");
                    //getting ip of the client
                    //String ip = clientSocket.getRemoteSocketAddress().toString();

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

            /*
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(
                        " Client #"+ this.threadId+" with IP : "+ ip +" > "+line);
                printWriter.println("Server > Message of Client #"+this.threadId+ " received");

            }*/
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

    }
}
