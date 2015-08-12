package pytteserver;
/**
 * @author Theo Markovic
 * @version 0.9ish
 *
 * Parses and validates a supplied portnumber, starts initializes and starts a
 * omni-listening TCP-socket
 *
 */
public class PytteServer {
    /**
     * @param args a list of parameters sent in as run-flags
     * @throws NumberFormatException if the argument, in this case portnumber is
     * invalid
     */
    public static void main(String[] args) throws NumberFormatException {
        int port = 8080;
        if (args.length > 0) {
            try {
                if (Integer.parseInt(args[0]) >= 4) {
                    port = Integer.valueOf(args[0]);
                } else {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException nfe) {
                System.out.println("'" + args[0] + "'" + " Is not a valid portnumber or is reserved, starting server on " + port + " Instead");
            }
        }
        try {
            TcpServer server = new TcpServer();
            server.startServer(port);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
