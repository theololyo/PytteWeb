
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
    private String _parsedString;

    public Filter(String request) {
        _request = request;
        _isValid = false;

    }

    public String parseRequest() {
        if (validateRequest()) {
            _requestType = _request.split(" ")[0];
            _parsedString = _request.split(" ")[1].split("\\\\")[0];
        }
        
        else{
            _parsedString = "Invalid Request";
        }
        return _parsedString;
    }

    private boolean validateRequest() {

        if (_request.startsWith("GET") && _request.endsWith("\\r\\n")) {
            _isValid = true;

        } else {
            _isValid = false;

        }

        return _isValid;

    }
    
    public boolean isValidRequest(){
        return _isValid;
    }


}
