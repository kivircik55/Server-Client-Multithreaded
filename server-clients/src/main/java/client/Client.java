package client;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 1234)) {


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
