/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pytteserver;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Theo
 */
public class TcpServer {

    String _message;
    ServerSocket serverSocket;
    String _path;
    String _request;
    String _method;
    String[] _splitter;
    Filter _filter;
    FileHandler _fh;

    public void startServer(int port) throws IOException, Exception {

        InetAddress addr = InetAddress.getByName("127.0.0.1");
        serverSocket = new ServerSocket(port);
        //Skriver ut att servern är startad
        System.out.println("Server started");

        //Här väntar servern på att en klient ska ansluta
        Socket clientHandlerSocket = serverSocket.accept();
        while (true) {

            DataOutputStream writer = new DataOutputStream(clientHandlerSocket.getOutputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientHandlerSocket.getInputStream()));

            _request = reader.readLine();
            _filter = new Filter(_request);
            _filter.parseRequest();
            
            writer.write(_fh.getDir("/src"));
            
     
           
          
            

        }

        //serverSocket.close();
    }

}
