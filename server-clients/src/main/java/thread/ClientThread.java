package thread;

import java.io.*;
import java.net.Socket;

public class ClientThread implements Runnable{
    private final Socket clientSocket;
    private final int threadId;

    public ClientThread(Socket serverSocket, int threadId) {
        this.clientSocket = serverSocket;
        this.threadId = threadId;
    }

    @Override
    public void run() {
        try {
            //read data from client
            InputStream inputStream = clientSocket.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            //send data to client
            OutputStream outputStream = clientSocket.getOutputStream();
            PrintWriter printWriter = new PrintWriter(outputStream, true);

            //getting ip of the client
            String ip = clientSocket.getRemoteSocketAddress().toString();
            String line;

            while ((line = bufferedReader.readLine()) != null) {

                System.out.println(
                        " Client #"+ this.threadId+" with IP : "+ ip +" > "+line);
                printWriter.println("Server > Message of Client #"+this.threadId+ " received");
            }
            System.out.println("Connection to Client #"+ this.threadId+" has been closed");
            clientSocket.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }
}
