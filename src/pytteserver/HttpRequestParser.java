package pytteserver;

import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Validates the given request and parses it into a path
 *
 * @author Markovic
 */
public class HttpRequestParser {

    private final String _request;
    private String _method;
    private String _path;
    private boolean _isValid;
    private String _protocol;
    private Double _version;
    private String _parsedString;
    private HttpRequest _httpRequest;
    private DataOutputStream _ds;
    private FileHandler _fh;

    /**
     * Constructs a new RequestHandler
     *
     * @param request the request to be processed
     */
    public HttpRequestParser(String request, DataOutputStream ds) {
        _request = request;
        _isValid = false;
        _ds = ds;
        _fh = new FileHandler();
    }

    /**
     * Splits the whole HTTP-request into it's substantial parts
     *
     * @return returns the extracted path from the request
     */
    public HttpRequest parseRequest() throws IOException {
        if (validateRequest()) {
            _method = _request.split(" ")[0];
            _path = _request.split(" ")[1];
            _protocol = _request.split(" ")[0].replaceFirst("/", "");
            _httpRequest = new HttpRequest(_method, _path, _protocol, validateRequest());
            HttpHeaders httpResponse
                    = new HttpHeaders(0,
                            _fh.getContentLengthForFile(_httpRequest.getPath()), _fh.getFileTypeForFile(_httpRequest.getPath()), 2);

            _fh = new FileHandler(_httpRequest.getPath());
            new HttpResponseComposition(httpResponse, _fh).writeComposition(_ds);
        } else {
            _fh = new FileHandler("error.html");
            HttpHeaders httpResponse = new HttpHeaders(2, _fh.getContentLengthForFile("error.html"), 0, 2);
            _ds.writeBytes(httpResponse.getHeaders());
            _ds.write(_fh.getFile());
        }
        return _httpRequest;
    }

    private boolean validateRequest() {
        if (_request.startsWith(" ") || _request == null) {
            return false;
        }
        if (_request.startsWith("GET /") || _request.startsWith("HEAD /")
                && _request.endsWith("HTTP/1.0") || _request.endsWith("HTTP/1.1") || _request.endsWith("HTTP/0.9 ")) {
            _isValid = true;
        }
        return _isValid;

    }
}


//Fixa rätt status, hämtas ur FH
