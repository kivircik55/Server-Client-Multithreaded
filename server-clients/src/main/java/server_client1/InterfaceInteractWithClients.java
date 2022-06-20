package server_client1;

public interface InterfaceInteractWithClients {
    public String messageToSendToAllClients();
    public void deleteClient(int threadId);
}
