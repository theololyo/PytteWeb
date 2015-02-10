/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pytteserver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Markovic
 */
public class FileHandler {

    private String _path;
    private String _pattern;
    File[] _fileArray;
    File _rootFolder;
    Path _index;
    byte[] _response;

    public FileHandler(String path) {
        _path = path;
        _rootFolder = new File(System.getProperty("user.dir") + _path);

    }

    public FileHandler() {
        _path = System.getProperty("user.dir");
        _rootFolder = new File(_path);

    }

    public byte[] getIndex() throws IOException {
        _fileArray = _rootFolder.listFiles();

        for (int i = 0; i < _rootFolder.listFiles().length; i++) {
            if (_fileArray[i].getName().startsWith("index.") && (_fileArray[i].getName().endsWith(".html") || _fileArray[i].getName().endsWith(".htm"))) {

                _response = Files.readAllBytes(Paths.get(_fileArray[i].getPath()));
            } else if (_fileArray[i].getName().startsWith("index.")) {
                _response = Files.readAllBytes(Paths.get(_fileArray[i].getPath()));
            } else {
                System.out.println(_path);
                //_response = getDir(_path);

            }

        }

        return _response;
    }

    public byte[] getDir(String path) {
        File dir = new File(_rootFolder + path);
        _fileArray = dir.listFiles();
        String listAllFiles = "";
        for (int j = 0; j < _fileArray.length; j++) {
            listAllFiles = listAllFiles.concat(_fileArray[j].getName() + "\r\n");
        }
        return listAllFiles.getBytes();

    }
    
    
    public byte[] getFile(){
        
        return null;
    }

}
