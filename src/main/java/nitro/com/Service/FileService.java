package nitro.com.Service;

import nitro.com.Entity.FileManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.IOException;

@Service
public class FileService {
    private static final Logger logger = LoggerFactory.getLogger(FileManager.class);
    @Autowired
    FileManager fileManager;



    public String getNameFile(){
       return fileManager.getName();
    }
    public String getTextFile(){
        return fileManager.getTextfile();
    }


    public void createFile(String name, String body) throws IOException {

        fileManager.createFile(name,body);

    }
    public String[] getList() throws IOException {
        return fileManager.getList();
    }
    public String updateFile(String name,String text) throws FileNotFoundException {
        return fileManager.updateFile(name,text);

    }
    public String showFile(String name) throws IOException {
         return fileManager.showFile(name);
    }
    public void deleteFile(String name) throws FileNotFoundException {
        fileManager.deleteFile(name);
    }
}
