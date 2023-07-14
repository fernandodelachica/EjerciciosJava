package com.bosonit.formacion.block11uploaddownloadfiles.file.domain.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class FileInputDto {
    String fileName;
    Date uploadDate;
    String category;
}
