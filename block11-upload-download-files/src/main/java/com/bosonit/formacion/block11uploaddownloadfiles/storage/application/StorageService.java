package com.bosonit.formacion.block11uploaddownloadfiles.storage.application;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

public interface StorageService {

    void store(MultipartFile file);
    Stream<Path> loadAll();

    Path load(String fileName);

    Resource loadAsResource(String fileName);

    void deleteAll();

    void init();

    void changeNewRootLocation(Path path);

    void changeExistRootLocation(Path path);
}
