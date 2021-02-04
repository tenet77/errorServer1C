package httpServer;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class ErrorHttpServer {

    public static void main(String[] args) {

        try {
            ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
            HttpServer server = HttpServer.create(new InetSocketAddress("localhost", 8189), 0);
            server.createContext("/erp", new ErrorClientHandler());
            server.setExecutor(threadPoolExecutor);
            server.start();
            System.out.println("Http server started at 8189");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
