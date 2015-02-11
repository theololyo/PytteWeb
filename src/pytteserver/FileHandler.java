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
import java.util.Base64;
import java.util.List;

/**
 *
 * @author Markovic
 */
public class FileHandler {

    private String _path;
    private String _pattern;
    private String _rootPath;
    File[] _fileArray;
    File _rootFolder;
    Path _index;
    byte[] _response;

    public FileHandler() {
        _rootPath = System.getProperty("user.dir");
        _rootFolder = new File(System.getProperty("user.dir") + _path);
    }

    public byte[] getIndex() throws IOException {
        _fileArray = _rootFolder.listFiles();

        for (int i = 0; i < _rootFolder.listFiles().length; i++) {
            if (_fileArray[i].getName().startsWith("index.") && (_fileArray[i].getName().endsWith(".html") || _fileArray[i].getName().endsWith(".htm"))) {

                _response = Files.readAllBytes(Paths.get(_fileArray[i].getPath()));
                
            } else if (_fileArray[i].getName().startsWith("index.")) {
                
                _response = Files.readAllBytes(Paths.get(_fileArray[i].getPath()));
                
            } else {

                _response = getFile(_rootPath);

            }

        }

        return _response;
    }

    public byte[] getFile(String path) throws IOException {
        String fileResult = "";
        if (new File(_rootPath + path).exists() && new File(_rootPath + path).isDirectory()) {
            _fileArray = new File(_rootPath + path).listFiles();
            for (int j = 0; j < _fileArray.length; j++) {
                fileResult = fileResult.concat(_fileArray[j].getName() + "\r\n");
            }
        } else if (new File(_rootPath + path).exists() && new File(_rootPath + path).isFile()) {
            Path file = Paths.get(_rootPath + path);
            String decode = new String(Files.readAllBytes(file), "UTF-8");
            fileResult = decode;

        } else {
            Path errorFile = Paths.get(_rootPath + "/error.html");
            return Files.readAllBytes(errorFile);
        }
        return fileResult.getBytes();

    }

}
