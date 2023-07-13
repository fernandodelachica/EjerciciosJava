package com.bosonit.formacion.block7crudvalidation.config.feign;

import com.bosonit.formacion.block7crudvalidation.model.dto.InstructorOutputDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="instructorFeign", url = "http://localhost:8081")
public interface InstructorFeign {

    @GetMapping("/profesor/{id}")
    InstructorOutputDto getTeacher(@PathVariable int id);
}
