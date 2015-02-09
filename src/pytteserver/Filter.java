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
public class Filter {

    private String[] _request;
    private boolean _valid;
    private FileHandler _fh;
    private String _path;

    public Filter(String[] request) {
        _request = request;
        validateRequest();

    }

    public Byte[] getIndex() {

        return null;
    }

    public boolean validateRequest() throws ArrayIndexOutOfBoundsException {
        System.out.println(_request[0] + " " + _request[1]);
        if (_request[0].equals("GET") && _request[1].contains((CharSequence) "\\r\\n")) {
            _valid = true;
        } else {
            _valid = false;
        }

        return _valid;

    }

}
