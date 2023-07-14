package com.bosonit.formacion.block11uploaddownloadfiles.storage.application;

import com.bosonit.formacion.block11uploaddownloadfiles.storage.exception.StorageException;
import com.bosonit.formacion.block11uploaddownloadfiles.storage.exception.StorageFileNotFoundException;
import com.bosonit.formacion.block11uploaddownloadfiles.storage.properties.StorageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;

@Service
public class StorageServiceImpl implements StorageService{

    private Path rootLocation = Path.of("storageDirectory");


    @Override
    public void store(MultipartFile file) {
        try {
            if (file.isEmpty()) {
                throw new StorageException("Error al almacenar el archivo, está vacío");
            }

            Path destinationFile = this.rootLocation.resolve(
                    Paths.get(file.getOriginalFilename())).normalize().toAbsolutePath();

            if (!destinationFile.getParent().equals(this.rootLocation.toAbsolutePath())) {
                throw new StorageException("No se pueden almacenar archivos fuera del directorio actual");
            }

            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (IOException e) {
            throw new StorageException("Error al almacenar el archivo.", e);
        }
    }

    @Override
    public Stream<Path> loadAll() {
        try {
            return Files.walk(this.rootLocation, 1).filter(path -> !path.equals(this.rootLocation)).map(this.rootLocation::relativize);

        } catch (IOException e) {
            throw new StorageException("Error al leer archivos almacenados.", e);
        }
    }

    @Override
    public Path load(String fileName){
        return rootLocation.resolve(fileName);
    }

    @Override
    public Resource loadAsResource(String fileName){
        try{
            Path file = load(fileName);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()){
                return resource;
            } else {
                throw new StorageFileNotFoundException("No se ha podido leer el archivo: "+fileName);
            }
        } catch (MalformedURLException e){
            throw new StorageFileNotFoundException("No se ha podido leer el archivo: "+fileName);
        }
    }

    @Override
    public void deleteAll(){
        FileSystemUtils.deleteRecursively(rootLocation.toFile());
    }

    @Override
    public void init(){
        try{
            Files.createDirectories(rootLocation);
        } catch (IOException e){
            throw new StorageException("No se ha podido inicializar el almacenamiento.", e);
        }
    }

    @Override
    public void changeNewRootLocation(Path path){
        this.rootLocation = path;
        this.init();
    }

    @Override
    public void changeExistRootLocation(Path path){
        this.rootLocation = path;
        try{
            Files.move(rootLocation, path, StandardCopyOption.REPLACE_EXISTING);
        }catch (IOException e) {
            throw new StorageException("No se ha podido inicializar el almacenamiento.", e);
        }
    }

}
