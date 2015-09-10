package pytteserver;

/**
 *
 * @author Theo
 */
public class HttpRequest {

    private final String _method;
    private final String _path;
    private final String _protocol;
    private final boolean _isValid;

    public HttpRequest(String method, String path, String protocol, boolean isValid) {
        _method = method;
        _path = path;
        _protocol = protocol;
        _isValid = isValid;
    }

    public String getMethod() {
        return _method;
    }

    public String getPath() {
        return _path;
    }

    public String getProtocol() {
        return _protocol;
    }

    public boolean isValid() {
        return _isValid;
    }
}
