/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pytteserver;

import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;

/**
 *
 * @author Markovic
 */
public class HttpResponseComposition {

    HttpHeaders _httpHeaders;
    FileHandler _fileHandler;

    public HttpResponseComposition(HttpHeaders httpHeaders, FileHandler fh) {
        _httpHeaders = httpHeaders;
        _fileHandler = fh;
    }

    public void writeComposition(DataOutputStream ds) throws IOException {
        ds.write(_httpHeaders.getHeaders().getBytes(Charset.forName("UTF-8")));
        ds.writeBytes("\r\n\r\n");
        ds.write(_fileHandler.getFile());
    }
}
