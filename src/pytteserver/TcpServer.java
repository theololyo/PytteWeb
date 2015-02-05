/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pytteserver;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Theo
 */
public class TcpServer {

    String _message;
    int _port;
    ServerSocket serverSocket;

    public TcpServer(int port) {
        _port = port;
        
 
    }

    public void startServer() throws IOException {

        InetAddress addr = InetAddress.getByName("127.0.0.1");
        serverSocket = new ServerSocket(_port);
        //Skriver ut att servern är startad
        System.out.println("Server started");

        //Här väntar servern på att en klient ska ansluta
        Socket clientHandlerSocket = serverSocket.accept();

        //När en klient ansluter går vi vidare och skapar
        //strömmar på samma sätt som i klienten
        DataOutputStream writer = new DataOutputStream(clientHandlerSocket.getOutputStream());
        BufferedReader reader = new BufferedReader(new InputStreamReader(clientHandlerSocket.getInputStream()));

        //Läser meningen från klienten, gör om den till
        //VERSALER och skickar tillbaka den till klienten
        _message = reader.readLine();
        _message = _message.toUpperCase();
        writer.writeBytes(_message + "\n");
        
        //Stänger socketen vilket även stänger strömmarna
        //och ser även till att allt skickas om det inte
        //redan har skickats
        clientHandlerSocket.close();
        serverSocket.close();

    }

}


