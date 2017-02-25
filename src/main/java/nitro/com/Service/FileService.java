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


    public void setNameFile(String name){
        fileManager.setName(name);
    }
    public String getNameFile(){
       return fileManager.getName();
    }
    public String getTextFile(){
        return fileManager.getTextfile();
    }


    public void createFile() throws IOException {

        fileManager.createFile();

    }
    public String[] getList() throws IOException {
        return fileManager.getList();
    }
    public String updateFile(String text) throws FileNotFoundException {
        return fileManager.updateFile(text);

    }
    public void showFile() throws IOException {
         fileManager.showFile();
    }
    public void deleteFile() throws FileNotFoundException {
        fileManager.deleteFile();
    }
}
