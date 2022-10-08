package ru.job4j.io.echoserver;

import java.io.*;
import java.net.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EchoServer {
    private static final Logger LOG = LoggerFactory.getLogger(EchoServer.class.getName());

    public static void main(String[] args)   {
        
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    String input = in.readLine();
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    if (input.contains("?msg=Exit")) {
                        out.write("Server stopped".getBytes());
                        System.out.println("server stopped");
                        server.close();
                        break;
                    }
                    if (input.contains("?msg=Hello")) {
                        out.write("Hello, dear friend.".getBytes());
                    } else if (input.contains("?msg=")) {
                        out.write("What".getBytes());
                    }
                    for (String str = in.readLine(); str != null && !str.isEmpty(); str = in.readLine()) {
                        System.out.println(str);
                    }
                    out.flush();
                }
            }
        } catch (IOException e) {
            LOG.error("IO exception: ", e);
        }
    }
}
