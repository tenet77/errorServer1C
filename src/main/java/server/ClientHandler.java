package server;

import java.io.*;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class ClientHandler implements Runnable {

    private ErrorServer server;
    private Socket socket;

    public ClientHandler(ErrorServer server, Socket socket) {
        this.server = server;
        this.socket = socket;
    }

    private void handleInputData(byte[] data) {

        String mes = new String(data);
        String[] stringData = mes.split("\n");
        System.out.println(mes);

    }

    @Override
    public void run() {
        try {
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
            PrintWriter out = new PrintWriter(socket.getOutputStream());

            DataInputStream is = new DataInputStream(socket.getInputStream());
            DataOutputStream os = new DataOutputStream(socket.getOutputStream());

            byte[] byteBuffer = new byte[1024];

            int readBytes = is.read(byteBuffer);
            if (readBytes != -1) {
                handleInputData(byteBuffer);
            }

            StringBuilder responseBody = new StringBuilder();
            responseBody.append("{\n");
            responseBody.append("\t\"needSendReport\": true,\n");
            responseBody.append("\t\"userMessage\": \"Need send message\",\n");
            responseBody.append("\t\"dumpType\": 1\n");
            responseBody.append("}\n");

            StringBuilder outString = new StringBuilder();
            outString.append("HTTP/1.1 200 OK\n");
            outString.append("Content-Type: application/json\n");
            outString.append("Content-Length: " + responseBody.toString().getBytes().length + "\n");

            os.write(outString.toString().getBytes());
            os.write(responseBody.toString().getBytes(), 0, responseBody.toString().getBytes().length);
            os.flush();
            os.close();
//
//            while (true) {
//                int readBytes = is.read(byteBuffer);
//                if (readBytes == -1) break;
//                handleInputData(byteBuffer);
//                break;
//            }

//            out.println("HTTP/1.1 200 OK");
//            out.println("Content-Type: application/json");
//            out.println();
//            out.println("{");
//            out.println("\"needSendReport\": true,");
//            out.println("\"userMessage\": \"Need send message\",");
//            out.println("\"dumpType\": 1");
//            out.println("}");
////            out.flush();
//
//            out.close();
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Connection close");

    }
}
