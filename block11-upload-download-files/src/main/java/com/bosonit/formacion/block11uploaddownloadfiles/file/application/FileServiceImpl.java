package com.bosonit.formacion.block11uploaddownloadfiles.file.application;

import com.bosonit.formacion.block11uploaddownloadfiles.file.domain.File;
import com.bosonit.formacion.block11uploaddownloadfiles.file.domain.dto.FileInputDto;
import com.bosonit.formacion.block11uploaddownloadfiles.file.domain.dto.FileOutputDto;
import com.bosonit.formacion.block11uploaddownloadfiles.file.repository.FileRepository;
import com.bosonit.formacion.block11uploaddownloadfiles.storage.application.StorageService;
import com.bosonit.formacion.block11uploaddownloadfiles.storage.exception.StorageException;
import com.bosonit.formacion.block11uploaddownloadfiles.storage.exception.StorageFileNotFoundException;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class FileServiceImpl implements FileService {
    @Autowired
    FileRepository fileRepository;

    @Autowired
    StorageService storageService;

    @Override
    public FileOutputDto addFile(MultipartFile file, String type, String category){
        if (!file.getOriginalFilename().split("\\.")[1].equals(type)){
            throw new RuntimeException("El tipo de archivo no es correcto");
        }

        storageService.store(file);

        FileInputDto fileInput = new FileInputDto();
        fileInput.setFileName(file.getOriginalFilename());
        fileInput.setCategory(category);

        File newFile = new File(fileInput);
        fileRepository.save(newFile);

        return new FileOutputDto(newFile);
    }
    @Override
    public Resource getFileById(int id){
        File file = fileRepository.findById(id).orElseThrow(()->
            new StorageFileNotFoundException("El archivo con el id "+id+" no se ha encontrado"));
        return storageService.loadAsResource(file.getFileName());
    }

    @Override
    public List<FileOutputDto> getAllFiles(int pageNumber, int pageSize){
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        return fileRepository.findAll(pageRequest).getContent().stream().map(FileOutputDto::new).toList();
    }

    @Override
    public void setFileStoragePath(String path){
        Path directoryPath = Paths.get(path);

        if(!Files.exists(directoryPath) && !Files.isDirectory(directoryPath)){
            storageService.changeNewRootLocation(directoryPath);
            ResponseEntity.ok().body("El directorio se ha cambiado a "+directoryPath);
        } else {
            storageService.changeExistRootLocation(directoryPath);
        }
    }

    @Override
    public void deleteById(int id){
        File file = fileRepository.findById(id).orElseThrow(()->
                new StorageFileNotFoundException("El archivo con la id "+id+" no se ha encontrado"));
        try{
            Path fileToDelete = storageService.load(file.getFileName()).toAbsolutePath();
            Files.deleteIfExists(fileToDelete); //Elimina el archivo
            fileRepository.deleteById(id);      //Elimina el archivo de la BBDD
        } catch (IOException e){
            throw new StorageException("Error al eliminar el archivo: "+file.getFileName());
        }
    }


}
