/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pytteserver;

import java.io.IOException;

/**
 *
 * @author Theo
 */
public class PytteServer {

    private static int port;
    private static TcpServer server;

    public static void main(String[] args) throws IOException {
        port = Integer.parseInt(args[0]);
        System.out.println(port);
        if (args.length > 0) {
            TcpServer server = new TcpServer(port);
             server.startServer();
        }
        
       

    }

}
