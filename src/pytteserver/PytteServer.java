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

    public static void main(String[] args) throws IOException, NumberFormatException {
        int port = 8080;

        if (args.length > 0) {
            try {
                port = Integer.valueOf(args[0]);
            } catch (NumberFormatException nfe) {
                System.out.println(args[0] + " Is not a valid integer, try " + port + " Instead");
            }
        }

        TcpServer server = new TcpServer();
        try{
               server.startServer(port);
        }
        catch(Exception e){
            e.printStackTrace();
        }

    }

}
