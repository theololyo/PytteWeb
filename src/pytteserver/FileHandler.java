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
    private File _requestFile;
    private byte[] _response;
    private final String ROOT_PATH = System.getProperty("user.dir") + "\\";
    private String _requestPath;
    private HttpRequest _httpRequest;

    /**
     * Constructs a new File Handler with a given workingpath Looks for any type
     * of file named index
     *
     * @param requestPath the requested path
     * @throws IOException if any file operations goes sideways
     */
    public FileHandler(HttpRequest httpRequest) throws IOException {
        _httpRequest = httpRequest;
        _requestFile = new File(ROOT_PATH + httpRequest.getPath());
        _requestPath = httpRequest.getPath();
        _absolutePath = ROOT_PATH + httpRequest.getPath();
    }

    public FileHandler(String path) {
        _requestFile = new File(ROOT_PATH + path);
    }

    public FileHandler() {
    }

    /**
     * Searches the working directory for the the requested file or directory If
     * the requested resource is missing returns an error-page
     *
     * @param ds the OutputStream to use
     * @throws IOException if any file operations goes sideways
     */
    public byte[] getFile() throws IOException {
        byte[] readBytes = null;
        try {
            if (_requestFile.exists() && _requestFile.isFile()) {
                Path file = Paths.get(_requestFile.toString());
                readBytes = Files.readAllBytes(file);
            } else {
                Path errorFile = Paths.get(ROOT_PATH + "/error.html");
                readBytes = Files.readAllBytes(errorFile);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return readBytes;
    }

    public void writeFile(DataOutputStream ds, byte[] bytesToWrite) throws IOException {
        ds.write(bytesToWrite);
        ds.writeBytes(System.getProperty("line.separator"));
    }

    public int getContentLengthForFile(String fileName) throws IOException {
        int cLength;
        _requestFile = new File(ROOT_PATH + fileName);
        if (_requestFile.exists() && _requestFile.isFile()) {
            cLength = (int) _requestFile.length();
        } else {
            cLength = (int) _requestFile.length();
        }
        return cLength;
    }

    public int getFileTypeForFile(String fileName) {
        int fileType = 4;
        _requestFile = new File(ROOT_PATH + fileName);
        if (_requestFile.exists() && _requestFile.isFile()) {
            if (fileName.endsWith(".html") || fileName.endsWith(".html")) {
                fileType = 0;
            } else if (fileName.endsWith(".gif")) {
                fileType = 1;
            } else if (fileName.endsWith(".jpg") || fileName.endsWith(".jpeg")) {
                fileType = 2;
            } else if (fileName.endsWith(".txt")) {
                fileType = 3;
            }
        }
        return fileType;
    }
}
