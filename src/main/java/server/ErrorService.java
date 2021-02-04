package server;

public class ErrorService {

    public static void main(String[] args) {

        ErrorServer server = new ErrorServer();

        new Thread(server).start();

    }
}
