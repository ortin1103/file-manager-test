package nitro.com.Controller;

import nitro.com.Entity.FileManager;
import nitro.com.Service.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpSessionRequiredException;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.io.IOException;

@RestController
@RequestMapping("/files")
public class FileController {
    private static final Logger logger = LoggerFactory.getLogger(FileManager.class);

    @Autowired
    FileService fileService;
    @RequestMapping(value = "/{name}",method = RequestMethod.POST)  //создать файл без внесения в него
    public ResponseEntity createFile(@PathVariable String name){

        fileService.setNameFile(name);
        try {
            fileService.createFile();

        } catch (IOException e) {
            logger.error("File already exist ",e);
          return  ResponseEntity.badRequest().body("File already exist");

        } return new ResponseEntity("create "+fileService.getNameFile(), HttpStatus.CREATED);

    }

    @RequestMapping(value = "/",method = RequestMethod.GET)   // получить список файлов в текущей директории
    public ResponseEntity getList(){
        try {
            return new ResponseEntity(fileService.getList(), HttpStatus.ACCEPTED);
        } catch (IOException e) {
            logger.error("local folder is missing ",e);
          return   ResponseEntity.badRequest().body("Local folder is missing....");
        }
    }

    @RequestMapping(value = "/{name}",method = RequestMethod.PUT)   //ивнести изменения в файл
    public ResponseEntity updateFile(@PathVariable String name, @RequestBody String body){
        fileService.setNameFile(name);
        try {
            fileService.updateFile(body);
        } catch (FileNotFoundException e) {
            logger.error("file not found ",e);
           return ResponseEntity.badRequest().body("File not found");
        }
        return new ResponseEntity("File update",HttpStatus.OK);
    }

    @RequestMapping(value = ("/{name}"),method = RequestMethod.GET) //показать файл
    public ResponseEntity showFile(@PathVariable String name){
        fileService.setNameFile(name);
        try {
            fileService.showFile();

        } catch (FileNotFoundException e) {
            logger.error("Get file - file not found ",e);
           return ResponseEntity.badRequest().body("File not found");

        }catch (IOException e){
            logger.error("Error for open file",e);
           return ResponseEntity.badRequest().body("Error for open file");
        }

      return new ResponseEntity(fileService.getTextFile(),HttpStatus.OK);
    }

    @RequestMapping(value = "/{name}")                              //удалить файл
    public ResponseEntity deleteFile(@PathVariable String name){
        fileService.setNameFile(name);
        try {
            fileService.deleteFile();
        } catch (FileNotFoundException e) {
            logger.error("File delete - not found ",e);
         return ResponseEntity.badRequest().body("File not found");
        }
        return new ResponseEntity("Delete "+name,HttpStatus.OK);

    }
}
