package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ErrorServer implements Runnable{

    private final int PORT = 8189;
    private final ExecutorService executorService = Executors.newCachedThreadPool();

    @Override
    public void run() {

        try {
            ServerSocket server = new ServerSocket(PORT);
            while (true) {
                System.out.println("Server wait connection");
                Socket socket = server.accept();
                System.out.println("Connection accepted");
                ClientHandler client = new ClientHandler(this, socket);
                executorService.execute(client);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        executorService.shutdown();
    }

}
