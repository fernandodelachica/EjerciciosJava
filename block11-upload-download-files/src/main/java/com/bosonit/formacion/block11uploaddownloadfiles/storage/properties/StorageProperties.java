package com.bosonit.formacion.block11uploaddownloadfiles.storage.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties("storage")
@Getter
@Setter
public class StorageProperties {

    private String location = "storageDirectory";

}
