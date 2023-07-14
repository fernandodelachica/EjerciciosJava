package com.bosonit.formacion.block11uploaddownloadfiles.file.application;

import com.bosonit.formacion.block11uploaddownloadfiles.file.domain.dto.FileOutputDto;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


public interface FileService {
    FileOutputDto addFile(MultipartFile file, String type, String category);

    Resource getFileById(int id);

    List<FileOutputDto> getAllFiles(int pageNumber, int pageSize);

    void deleteById(int id);

    void setFileStoragePath(String path);

}
