package manager;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class MainDesktop {
    public static void main(String[] args) {
        new MainDesktop().run();
    }

    private void run() {
//        try(ServerSocket providerSocket = new ServerSocket(super.getPort(), 10)) {
//            Socket connection = providerSocket.accept();
//            ObjectOutputStream out;
//            ObjectInputStream in;
//            try {
//                out = new ObjectOutputStream(connection.getOutputStream());
//                in = new ObjectInputStream(connection.getInputStream());
//            } catch (IOException e) {
//                e.printStackTrace();
//                return;
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }
}
