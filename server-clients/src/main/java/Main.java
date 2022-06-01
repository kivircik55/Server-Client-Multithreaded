import server.Server;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int serverPortNumber=1234;

        System.out.println("Please enter the number of server you would like to launch : ");
        int numberOfServers=Integer.parseInt(scanner.nextLine());

        if (numberOfServers <=0){
            System.out.println("[ERROR] The number you provided is not accepted.");
            return;
        }

        for (int count = 0; count < numberOfServers; count++){
            serverPortNumber+=count;
            Server.main(new String[]{Integer.toString(serverPortNumber)});
            System.out.println("Server #"+count+1+" has been created at port : "+serverPortNumber);
        }



    }
}
