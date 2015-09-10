/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pytteserver;

/**
 *
 * @author Markovic
 */
public class HttpHeaders {

    private final int _status;
    private final int _contentLength;
    private final int _contentType;
    private final int _httpVersion;
    private String _statusString;
    private String _contentTypeString;
    private String _httpVersionString;

    public HttpHeaders(int status, int cLength, int cType, int httpVersion) {
        _status = status;
        _contentLength = cLength;
        _contentType = cType;
        _httpVersion = httpVersion;
        setContentType(cType);
        setStatusCode(status);
        setHttpVersion(httpVersion);
    }

    public String getHeaders() {
        System.out.println("HTTP/1.1 " + _statusString + "\r\n" + "Content-Type: " + _contentTypeString + "\r\n" + "Content-Length: " + _contentLength + "\r\n\r\n");
        return "HTTP/1.1 " + _statusString + "\r\n" + "Content-Type: " + _contentTypeString + "\r\n" + "Content-Length: " + _contentLength + "\r\n\r\n";
    }

    private void setContentType(int contentType) {
        switch (contentType) {
            case 0:
                _contentTypeString = "text/html";
                break;
            case 1:
                _contentTypeString = "image/gif";
                break;
            case 2:
                _contentTypeString = "image/jpeg";
                break;
            case 3:
                _contentTypeString = "text/plain";
                break;
            default:
                _contentTypeString = "";
        }
    }

    private void setStatusCode(int status) {
        switch (status) {
            case 0:
                _statusString = "200: OK";
                break;
            case 1:
                _statusString = "404: Not Found";
                break;
            case 2:
                _statusString = "400: Bad Request";
                break;
            default:
                _statusString = "";
        }
    }

    private void setHttpVersion(int httpVer) {
        switch (httpVer) {
            case 0:
                _httpVersionString = "HTTP/0.9";
                break;
            case 1:
                _httpVersionString = "HTTP/1.0";
                break;
            case 2:
                _httpVersionString = "HTTP/1.1";
                break;
            default:
                _httpVersionString = "HTTP/1.0";
                break;
        }

    }

}
