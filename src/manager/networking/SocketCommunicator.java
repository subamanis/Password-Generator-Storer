package manager.networking;

import manager.domain.Account2;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

//TODO: add: private static final long serialVersionUID = -8242968608169744520L; to all classes to be sent over sockets

public final class SocketCommunicator
{
    // write the ip of localhost:
    private static final String IP = "127.0.0.1";
    private static final int PORT = 8888;

    @SuppressWarnings("unchecked")
    public void sync(List<Account2> accounts)
    {
        try(ServerSocket providerSocket = new ServerSocket(PORT, 1);
            Socket connection           = providerSocket.accept();
            ObjectOutputStream out      = new ObjectOutputStream(connection.getOutputStream());
            ObjectInputStream  in       = new ObjectInputStream(connection.getInputStream()))
        {
            System.out.println("Just connected to client "+connection.getInetAddress()+" "+connection.getPort());

            List<Account2> otherAccounts = (List<Account2>) in.readObject();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
