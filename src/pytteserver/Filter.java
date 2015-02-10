
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pytteserver;

import com.sun.xml.internal.fastinfoset.util.CharArray;
import java.lang.Exception;
import java.lang.Throwable;
import java.util.Arrays;

/**
 *
 * @author Markovic
 */
public class Filter {

    private String _request;
    private String _requestType;
    private boolean _isValid;
    private FileHandler _fh;
    private String _path;

    public Filter(String request) {
        _request = request;
        _isValid = false;

    }

    public void parseRequest() {
        if (validateRequest()) {
            _requestType = _request.split(" ")[0];
            _path = _request.split(" ")[1].split("\\\\")[0];
            System.out.println(_path);
            _fh = new FileHandler(_path);

        } else {

        }

    }

    private boolean validateRequest() {

        if (_request.startsWith("GET") && _request.endsWith("\\r\\n")) {
            _isValid = true;

        } else {
            _isValid = false;

        }

        return _isValid;

    }

    private void requestFile() {

    }

}
