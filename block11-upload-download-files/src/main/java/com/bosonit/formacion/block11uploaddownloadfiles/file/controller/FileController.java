package com.bosonit.formacion.block11uploaddownloadfiles.file.controller;

import com.bosonit.formacion.block11uploaddownloadfiles.file.application.FileService;
import com.bosonit.formacion.block11uploaddownloadfiles.file.domain.dto.FileOutputDto;
import com.bosonit.formacion.block11uploaddownloadfiles.storage.exception.StorageFileNotFoundException;
import com.bosonit.formacion.block11uploaddownloadfiles.storage.application.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;



@RestController
public class FileController {

    @Autowired
    FileService fileService;

    @Autowired
    StorageService storageService;


    @PostMapping("/upload/{type}")
    @ResponseStatus(HttpStatus.CREATED)
    public FileOutputDto addFile(@RequestPart MultipartFile file, @PathVariable String type, @RequestPart String category){
        return fileService.addFile(file, type, category);
    }
    @GetMapping("/file")
    public ResponseEntity<Resource> getFile(@RequestParam(required = false) Integer id, @RequestParam(required = false) String fileName){
        Resource file = null;

        if (id != null){
            file = fileService.getFileById(id);
        }
        if (fileName != null && id == null){
            file = storageService.loadAsResource(fileName);
        }
        if (file != null){
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+ file.getFilename() + "\"")
                    .body(file);
        }else {
            return ResponseEntity.badRequest().build();
        }

    }

    @GetMapping("/files/all")
    public Iterable<FileOutputDto> getFilesList(
            @RequestParam(defaultValue = "0", required = false) int pageNumber,
            @RequestParam(defaultValue = "4", required = false) int pageSize){
        return fileService.getAllFiles(pageNumber, pageSize);
    }

    @GetMapping("/files/{fileName}")
    public ResponseEntity<Resource> serveFile(@PathVariable String fileName){
        Resource file = storageService.loadAsResource(fileName);

        return ResponseEntity.ok().header(
                HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\""+file.getFilename() + "\"").body(file);
    }

    @DeleteMapping("/file/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable int id){
        fileService.deleteById(id);
    }

    @PutMapping("/setpath")
    public void setFileStoragePath(@RequestParam String path){
        fileService.setFileStoragePath(path);
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc){
        return ResponseEntity.notFound().build();
    }

}

