package com.app.filemanager.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Service("filesConvertingServiceImpl")
public class FilesToZipServiceImplementation implements FilesConvertingService {

    public StreamingResponseBody zipFiles(MultipartFile[] files, HttpServletResponse response) {

        StreamingResponseBody streamResponseBody = out -> {
            final ZipOutputStream zipOutputStream = new ZipOutputStream(response.getOutputStream());
            ZipEntry zipEntry = null;
            InputStream inputStream = null;

            try {
                for (MultipartFile file : files) {
                    zipEntry = new ZipEntry(file.getName() + new Date());
                    inputStream = file.getInputStream();
                    zipOutputStream.putNextEntry(zipEntry);
                    byte[] bytes = new byte[1024];
                    int length;
                    while ((length = inputStream.read(bytes)) >= 0) {
                        zipOutputStream.write(bytes, 0, length);
                    }
                }
                response.setContentLength((int) (zipEntry != null ? zipEntry.getSize() : 0));
            } catch (IOException e) {
                System.out.println("EXCEPTION " + e.getMessage());
            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (zipOutputStream != null) {
                    zipOutputStream.close();
                }
            }

        };
        return streamResponseBody;


    }
}