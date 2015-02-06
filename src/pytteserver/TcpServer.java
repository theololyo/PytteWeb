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
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
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
    ServerSocket serverSocket;
    String _path;
    String _request;
    String _method;
    String[] _splitter;

    public void startServer(int port) throws IOException {

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
            _splitter = _request.split(" ");
            Path file = Paths.get("index.html");
            try {
                if (_splitter[0].equals("GET") && _splitter[1].contains((CharSequence) "\\r\\n")) {
                    writer.write(Files.readAllBytes(file));
                    writer.writeBytes("\r\n");
                } else {
                    writer.writeBytes("FEL" + "\n");
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                writer.writeBytes("Invalid request" + "\r\n");
            }

        }

        //serverSocket.close();
    }

}
