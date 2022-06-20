package server_client1;

public class InteractWithClientsImpl implements  InterfaceInteractWithClients{
    private Server server;

    public InteractWithClientsImpl(Server server) {
        this.server = server;
    }

    @Override
    public synchronized String messageToSendToAllClients() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\nSERVER CLIENT LIST\n");
        this.server.listOfClients.entrySet().forEach(entry ->{
            stringBuilder.append("   Server > Clients list : ID#"+entry.getKey()+", Socket Port:"+entry.getValue().getPort()+"\n");
        });
        stringBuilder.append("\n");
        return stringBuilder.toString();
    }

    @Override
    public synchronized void deleteClient(int threadId) {
        System.out.println("   Deleting client at ThreadId#"+threadId);
        this.server.listOfClients.remove(threadId);
        System.out.println("   Client #"+threadId+" has been deleted");
    }
}
