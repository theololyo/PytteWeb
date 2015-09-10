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
 * @author Theo Markovic
 *
 * Contains a TCPsocket listening on a given port. Handles both incoming and
 * outgoing data
 *
 */
public class TcpServer {
    private ServerSocket _serverSocket;
    private String _request;
    /**
     *
     * @param port the port this socket listens on
     * @throws IOException if the FileHandler class encounters an error
     */
    public void startServer(int port) throws IOException {
        InetAddress addr = InetAddress.getByName("0.0.0.0");
        _serverSocket = new ServerSocket(port, 20, addr);
        System.out.println("Server started, listening on port " + port);
        while (true) {
            
            Socket clientHandlerSocket = _serverSocket.accept();
            DataOutputStream writer = new DataOutputStream(clientHandlerSocket.getOutputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientHandlerSocket.getInputStream()));
            _request = reader.readLine();
            System.out.println(_request);
            new HttpRequestParser(_request, writer).parseRequest();
            clientHandlerSocket.close();
        }
    }
}
