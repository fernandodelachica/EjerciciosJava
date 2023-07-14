package com.bosonit.formacion.block11uploaddownloadfiles.file.domain.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class FIleRequestInputDto {
    private MultipartFile file;
    private String category;
}
