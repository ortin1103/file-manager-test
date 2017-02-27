package nitro.com.Entity;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.*;

@Component
public class FileManager {
    private static final Logger logger = LoggerFactory.getLogger(FileManager.class);
    private String pathname="/home/denis/Рабочий стол/1";
    private String name;
    private String textfile;

    public void setTextfile(String textfile) {
        this.textfile = textfile;
    }

    public String getTextfile() {
        return textfile;
    }

    public FileManager(String pathname, String name) {
        this.pathname = pathname;
        this.name = name;
    }

    public FileManager() {
    }

    public String getPathname() {
        return pathname;
    }

    private void setPathname(String pathname) {
        this.pathname = pathname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void createFile(String name, String body) throws IOException {
        File file = new File(pathname, name);
        logger.debug(pathname+"/"+ name);
        try{
        if(!file.exists()) {
            file.createNewFile();
        }
            PrintWriter writer=new PrintWriter(file.getAbsoluteFile());
            try {
                writer.print(body);
            } finally {
                writer.close();
            }
            logger.info("create file "+ name);
            return;
        }catch (IOException e) {
            throw new IOException();
        }
    }

    public String[] getList() throws IOException {
        File file =new File(pathname);
        logger.debug("get list locate: "+pathname);
        if(file.exists()) {
            String[] list = file.list();
            logger.info("users get list ");
            logger.trace(String.valueOf(list));
            return list;
        }throw new IOException();
    }

    public String updateFile(String name,String text) throws FileNotFoundException {
        File file = new File(pathname,name);
        logger.debug("Local: "+pathname);
        logger.debug("name file: "+name);

        logger.trace(text);   // не уверен, если много текста - не повиснет ли
        if (file.exists()) {

            PrintWriter writer = new PrintWriter(file.getAbsoluteFile());

            try {

               writer.print(text);
            } finally {

               writer.close();
               logger.info("update file: "+name);
            }

            return "file update";

        } throw new FileNotFoundException();
    }

    public String showFile(String name) throws IOException {
        File file =new File(pathname,name);
        logger.debug("show file: " + pathname + "/" +name);
        StringBuilder sb =new StringBuilder();
        if(file.exists()){
            try{
                BufferedReader in = new BufferedReader(new FileReader(file.getAbsoluteFile()));
                try{
                    String s;
                    while((s=in.readLine()) !=null){
                        sb.append(s);
                        sb.append("\n");
                    }
                }finally {
                    in.close();
                }
            }catch (IOException e){
                logger.error("error ridding file",e);
                throw new RuntimeException(e);

            }
            setTextfile(sb.toString());
            logger.trace("show text "+sb.toString());
            logger.info("show file: "+name);
            return sb.toString();
        }  throw new FileNotFoundException();

    }

    public void deleteFile(String name) throws FileNotFoundException {
        File file = new File(pathname, name);
        logger.debug("delete file "+pathname+"/"+ name);
        if (!file.exists()){
            throw new FileNotFoundException();

        }
        logger.info("delete file ", name);
        file.delete();

    }


}
