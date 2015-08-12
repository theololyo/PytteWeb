package pytteserver;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
/**
 * Finds and returns requested files and folders.
 *
 * @author Theo Markovic
 */
public class FileHandler {
    private String _absolutePath;
    private boolean _indexExists = false;
    private File _requestFile;
    private byte[] _response;
    private final String ROOT_PATH = System.getProperty("user.dir") + "\\";
    private String _requestPath;
    /**
     * Constructs a new File Handler with a given workingpath Looks for any type
     * of file named index
     *
     * @param requestPath the requested path
     * @throws IOException if any file operations goes sideways
     */
    public FileHandler(String requestPath) throws IOException {
        _requestFile = new File(ROOT_PATH + requestPath);
        _requestPath = requestPath;
        _absolutePath = ROOT_PATH + requestPath;
    }
    
    /**
     * Searches the working directory for the the requested file or directory If
     * the requested resource is missing returns an error-page
     *
     * @param ds the OutputStream to use
     * @throws IOException if any file operations goes sideways
     */
    public void getFile(DataOutputStream ds) throws IOException {
        try {
            if (_requestFile.exists() && _requestFile.isFile()) {
                Path file = Paths.get(_absolutePath);
                ds.write(Files.readAllBytes(file));
                ds.writeBytes(System.getProperty("line.separator"));
            }
            else {
                Path errorFile = Paths.get(ROOT_PATH + "/error.html");
                ds.write(Files.readAllBytes(errorFile));
                ds.writeBytes(System.getProperty("line.separator"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
 }
