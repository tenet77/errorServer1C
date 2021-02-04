package httpServer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.*;

public class ErrorClientHandler implements HttpHandler {

    // TODO: add logging

    @Override
    public void handle(HttpExchange exchange) throws IOException {

        System.out.println("Method: " + exchange.getRequestMethod());
        System.out.println("URI: " + exchange.getRequestURI());
        String[] uri = exchange.getRequestURI().toString().split("/");

        InputStream is = exchange.getRequestBody();
        byte[] requestBody = is.readAllBytes();

        if (uri[uri.length - 1].equals("getInfo")) {
            ObjectMapper mapper = new ObjectMapper();

            try {
                ConfigMessage message = mapper.readValue(new String(requestBody), ConfigMessage.class);
                System.out.println(message.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }

            handleInfoResponse(exchange);

        } else if (uri[uri.length - 1].equals("pushReport")){

            DataOutputStream out = new DataOutputStream(new FileOutputStream("report.zip"));
            out.write(requestBody);
            out.close();

            handlePushResponse(exchange);

        }

    }

    private void handleInfoResponse(HttpExchange exchange) throws IOException {

        OutputStream out = exchange.getResponseBody();

        StringBuilder outString = new StringBuilder();
        outString.append("{\n");
        outString.append("\t\"needSendReport\": true,\n");
        outString.append("\t\"userMessage\": \"Need send message\",\n");
        outString.append("\t\"dumpType\": 1\n");
        outString.append("}\n");

        exchange.sendResponseHeaders(200, outString.length());
        out.write(outString.toString().getBytes());
        out.flush();
        out.close();

    }

    private void handlePushResponse(HttpExchange exchange) throws IOException {

        exchange.sendResponseHeaders(200, 0);

    }
}
