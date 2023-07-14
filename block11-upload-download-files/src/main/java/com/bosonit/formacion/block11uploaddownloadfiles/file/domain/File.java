package com.bosonit.formacion.block11uploaddownloadfiles.file.domain;

import com.bosonit.formacion.block11uploaddownloadfiles.file.domain.dto.FileInputDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String fileName;
    Date uploadDate;
    String category;

    public File(FileInputDto fileInputDto){
        this.fileName = fileInputDto.getFileName();
        this.uploadDate = new Date();
        this.category = fileInputDto.getCategory();
    }

}
