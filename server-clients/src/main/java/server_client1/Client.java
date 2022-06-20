package server_client1;

import java.io.*;
import java.net.Socket;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {

        try (Socket socket = new Socket("localhost", 1027)) {

            Scanner scanner = new Scanner(System.in);

            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());


            String line = null;

            while (true) {

                System.out.println(dataInputStream.readUTF());
                line = scanner.nextLine();

                dataOutputStream.writeUTF(line);

                if (line.equals("Exit")){
                    System.out.println("Closing this connection : " + socket);
                    socket.close();
                    System.out.println("Connection closed");
                    break;
                }

                String clientMessage = dataInputStream.readUTF();

                System.out.println(clientMessage);
            }
            scanner.close();
            dataInputStream.close();
            dataOutputStream.close();

        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
