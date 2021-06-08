package com.example.demo.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

@RestController
public class FileController {
@PostMapping("/uploadFile")
public void uploadFile(@RequestParam("file") MultipartFile file){
    // file info
    System.out.println("file name : " + file.getOriginalFilename());
    System.out.println("file size : " + file.getSize());

    try(
        FileOutputStream fos = new FileOutputStream("./temp.jpg");
        InputStream is = file.getInputStream()
    ){
        int readCount;
        byte[] buffer = new byte[1024];

        while((readCount = is.read(buffer)) != -1){
            fos.write(buffer, 0, readCount);
        }
    }catch(Exception e){
        e.printStackTrace();
    }
}

    @GetMapping("/downloadFile")
    public void downloadFile(HttpServletResponse response) {
        String fileName = "temp.jpg";
        File file = new File("./" + fileName);
        String newFileName = "newTemp.jpg";

        try (
            FileInputStream fis = new FileInputStream(file);
            BufferedInputStream bis = new BufferedInputStream(fis);
            OutputStream out = response.getOutputStream()
        ){
            response.addHeader("Content-Disposition", "attachment;filename=\""+newFileName+"\"");
            response.setContentLength((int)file.length());

            int read = 0;
            int myCount = 0;
            while(true) {
                if(myCount%100 == 0){
                    System.out.println(myCount);
                }

//                while((read = bis.read()) != -1) {
                read = bis.read();
                if(read == -1){
                    int read1 = 0;
                    break;
                }
                out.write(read);
                myCount++;
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
