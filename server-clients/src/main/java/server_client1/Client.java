package server_client1;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        int port = Integer.parseInt(args[0]);

        try (Socket socket = new Socket("localhost", port)) {


            InputStream inputStream = socket.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            OutputStream outputStream = socket.getOutputStream();
            PrintWriter printWriter = new PrintWriter(outputStream, true);

            Scanner scanner = new Scanner(System.in);


            String line = null;

            while (!"exit".equalsIgnoreCase(line)) {

                line = scanner.nextLine();

                printWriter.println(line);

                String serverMessage = bufferedReader.readLine();

                System.out.println(serverMessage);
            }

        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
