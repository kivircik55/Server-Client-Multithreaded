package server_client1;

import java.util.stream.Collectors;

public class InteractWithClientsImpl implements InterfaceInteractWithClients{
    private final Server server;

    public InteractWithClientsImpl(Server server) {
        this.server = server;
    }

    @Override
    public synchronized String messageToSendToAllClients() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\nSERVER CLIENT LIST\n");

        return Server.listOfClients.entrySet().stream().map((entry)-> stringBuilder.append("\t Server > Clients list : ID#")
                .append(entry.getKey()+", Socket Port:"+entry.getValue().getPort()+"\n")).collect(Collectors.joining("\n"));
    }

    @Override
    public synchronized void deleteClient(int threadId) {
        System.out.println("\t Deleting client at ThreadId#"+threadId);
        Server.listOfClients.remove(threadId);
        System.out.println("\t Client #"+threadId+" has been deleted");
    }
}
