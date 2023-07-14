package com.bosonit.formacion.block11uploaddownloadfiles.file.domain.dto;

import com.bosonit.formacion.block11uploaddownloadfiles.file.domain.File;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class FileOutputDto {
    int id;
    String fileName;
    Date uploadDate;
    String category;

    public FileOutputDto(File file){
        this.id = file.getId();
        this.fileName = file.getFileName();
        this.uploadDate = file.getUploadDate();
        this.category = file.getCategory();
    }
}
