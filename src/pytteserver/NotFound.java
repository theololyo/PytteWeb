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
public class NotFound extends Response {

    private String _message;
    private final int RESPONSE_CODE;

    public NotFound(String message, int RESPONSE_CODE) {
        this._message = message;
        this.RESPONSE_CODE = RESPONSE_CODE;
    }
    
     public NotFound(String path) {
         this.RESPONSE_CODE = 404;
    }

    public String getResponseMessage() {
        return _message;
    }

    @Override
    public void setResponseMessage(String response) {
        this._message = response;
    }

    @Override
    public int getResponseCode() {
        return RESPONSE_CODE;
    }

    public byte[] getResponseFile(String path) {
        
      return null;  
    }

}
