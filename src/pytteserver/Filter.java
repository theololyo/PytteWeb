package pytteserver;
/**
 * Validates the given request and parses it into a path
 *
 * @author Markovic
 */
public class Filter {
    private final String _request;
    private String _requestType;
    private boolean _isValid;
    private String _parsedString;
    /**
     * Constructs a new Filter
     *
     * @param request the request to be processed
     */
    public Filter(String request) {
        _request = request;
        _isValid = false;
    }
    /**
     * Splits the whole HTTP-request into it's substantial parts
     *
     * @return returns the extracted path from the request
     */
    public String parseRequest() {
        if (validateRequest()) {
            System.out.println(_request);
            _requestType = _request.split(" ")[0];
            _parsedString = _request.split(" ")[1].split(" ")[0].replaceFirst("/", "");
        }
        return _parsedString;
    }
    private boolean validateRequest() {
        _isValid = _request.startsWith("GET /");
        return _isValid;
    }
}
