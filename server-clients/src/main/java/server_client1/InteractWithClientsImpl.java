package server_client1;

public class InteractWithClientsImpl implements InterfaceInteractWithClients{
    private final Server server;

    public InteractWithClientsImpl(Server server) {
        this.server = server;
    }

    @Override
    public synchronized String messageToSendToAllClients() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\nSERVER CLIENT LIST\n");
        Server.listOfClients.forEach((key, value) -> stringBuilder.append("   Server > Clients list : ID#")
                .append(key)
                .append(", Socket Port:")
                .append(value.getPort())
                .append("\n"));
        stringBuilder.append("\n");
        return stringBuilder.toString();
    }

    @Override
    public synchronized void deleteClient(int threadId) {
        System.out.println("   Deleting client at ThreadId#"+threadId);
        Server.listOfClients.remove(threadId);
        System.out.println("   Client #"+threadId+" has been deleted");
    }
}
