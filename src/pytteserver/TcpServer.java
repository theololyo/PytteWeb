package pytteserver;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Theo Markovic
 *
 * Contains a TCPsocket listening on a given port. Handles both incoming and
 * outgoing data
 *
 */
public class TcpServer {

    private String _message;
    private ServerSocket _serverSocket;
    private String _path;
    private String _request;
    private Filter _filter;
    
    /**
     * 
     * @param port the port this socket listens on
     * @throws IOException if the FileHandler class encounters an error 
     */
    public void startServer(int port) throws IOException {

        _serverSocket = new ServerSocket(port);

        System.out.println("Server started, listening on port " + port);

        Socket clientHandlerSocket = _serverSocket.accept();
        while (true) {

            DataOutputStream writer = new DataOutputStream(clientHandlerSocket.getOutputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientHandlerSocket.getInputStream()));

            _request = reader.readLine();
            _filter = new Filter(_request);
            writer.write(new FileHandler(_filter.parseRequest()).getFile());

            writer.writeBytes("\r\n");

        }

    }

}
