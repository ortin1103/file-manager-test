package nitro.com.Controller;

import nitro.com.Entity.FileManager;
import nitro.com.Service.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.io.IOException;

@RestController
@RequestMapping("/files")
public class FileController {
    private static final Logger logger = LoggerFactory.getLogger(FileManager.class);

    @Autowired
    FileService fileService;
    @RequestMapping(value = "/{name}",method = RequestMethod.POST)  //создать файл
    public ResponseEntity createFile(@PathVariable String name, @RequestBody String body){


        try {
            fileService.createFile(name,body);
            return new ResponseEntity("create "+name, HttpStatus.CREATED);

        } catch (IOException e) {
            logger.error("bad create file ",e);
          return  ResponseEntity.badRequest().body("bad create file");

        }

    }

    @RequestMapping(value = "",method = RequestMethod.GET)   // получить список файлов в текущей директории
    public ResponseEntity getList(){

        try {
            return new ResponseEntity(fileService.getList(), HttpStatus.OK);
        } catch (IOException e) {
            logger.error("local folder is missing ",e);
          return new ResponseEntity("Local folder is missing", HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/{name}",method = RequestMethod.PUT)   //ивнести изменения в файл
    public ResponseEntity updateFile(@PathVariable String name, @RequestBody String body){

        try {
            fileService.updateFile(name,body);
            return new ResponseEntity("File update",HttpStatus.ACCEPTED);
        } catch (FileNotFoundException e) {
            logger.error("file not found ",e);
           return new ResponseEntity("File not found", HttpStatus.NOT_FOUND);
        }

    }

    @RequestMapping(value = ("/{name}"),method = RequestMethod.GET) //показать файл
    public ResponseEntity showFile(@PathVariable String name){

        try {
           return new ResponseEntity(fileService.showFile(name),HttpStatus.OK);

        } catch (FileNotFoundException e) {
            logger.error("Get file - file not found ",e);
           return new ResponseEntity("File not found", HttpStatus.NOT_FOUND);

        }catch (IOException e){
            logger.error("Error for open file",e);
           return new ResponseEntity("error file", HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }

    @RequestMapping(value = "/{name}")                              //удалить файл
    public ResponseEntity deleteFile(@PathVariable String name){

        try {
            fileService.deleteFile(name);
            return new ResponseEntity("Delete "+name,HttpStatus.OK);
        } catch (FileNotFoundException e) {
            logger.error("File delete - not found ",e);
         return new ResponseEntity("File not found", HttpStatus.NOT_FOUND);
        }


    }
}
