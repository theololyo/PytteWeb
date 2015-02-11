/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pytteserver;

import com.sun.xml.internal.fastinfoset.util.CharArrayString;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author Markovic
 */
public class FileHandler {

    private String _requestPath;
    private String _pattern;
    private boolean _indexExists;
    File[] _fileArray;
    File _requestFile;
    byte[] _response;
    String _rootPath = System.getProperty("user.dir");

    public FileHandler(String requestPath) throws IOException {
        _requestFile = new File(_rootPath + requestPath);
        _requestPath = _rootPath + requestPath;
        _indexExists = findIndex();
     
    }

    private boolean findIndex() throws IOException {
        boolean indexFound = false;
        if (_requestFile.exists() && !_requestFile.getName().contains(new CharArrayString("index"))) {
            _fileArray = _requestFile.listFiles();

            for (int i = 0; i < _requestFile.listFiles().length; i++) {
                if (_fileArray[i].getName().startsWith("index.") && _fileArray[i].getName().contains(new CharArrayString(".htm"))) {
                    indexFound = true;
                    _response = Files.readAllBytes(Paths.get(_fileArray[i].getPath()));

                } else if (_fileArray[i].getName().startsWith("index.")) {
                    indexFound = true;
                    _response = Files.readAllBytes(Paths.get(_fileArray[i].getPath()));

                }
            }

        } else {

        }

        return indexFound;
    }

    public byte[] getFile() throws IOException {
        String fileResult = "";
        if (_requestFile.exists() && _requestFile.isDirectory()) {
            _fileArray = _requestFile.listFiles();
            if (!_indexExists) {
                for (int j = 0; j < _fileArray.length; j++) {
                    fileResult = fileResult.concat(_fileArray[j].getName() + "\r\n");
                }
            }
            if (_indexExists) {
                return _response;
            }

        } else if (_requestFile.exists() && _requestFile.isFile()) {
            Path file = Paths.get(_requestPath);
            String decode = new String(Files.readAllBytes(file), "UTF-8");
            fileResult = decode;

        } if (!_requestFile.exists()) {
            Path errorFile = Paths.get(_rootPath + "/error.html");
            return Files.readAllBytes(errorFile);
        }
        return fileResult.getBytes();

    }

}
