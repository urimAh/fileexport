package com.app.filemanager.controller;

import com.app.filemanager.service.FilesToZipServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import javax.servlet.http.HttpServletResponse;

@Controller
@CrossOrigin("http://localhost:8080")
@RequestMapping("/convert")
public class FilesToZipController {

    @Autowired
    FilesToZipServiceImplementation filesToZipServiceImplementation;


    @PostMapping("/files-to-zip")
    public ResponseEntity<StreamingResponseBody> uploadFiles(HttpServletResponse response, @RequestParam("files") MultipartFile[] multipartFiles) {
        StreamingResponseBody streamResponseBody = filesToZipServiceImplementation.zipFiles(multipartFiles, response);
        response.setContentType("application/zip");
        response.setHeader("Content-Disposition", "attachment; filename=exported-files.zip");
        return ResponseEntity.ok(streamResponseBody);
    }
}