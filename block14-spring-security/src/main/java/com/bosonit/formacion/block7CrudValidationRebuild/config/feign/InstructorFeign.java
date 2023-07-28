package com.bosonit.formacion.block7CrudValidationRebuild.config.feign;

import com.bosonit.formacion.block7CrudValidationRebuild.instructor.domain.dto.InstructorOutputDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "instructorFeign", url = "http://localhost:8081")
public interface InstructorFeign {

    @GetMapping("/instructor/feign/{id}")
    InstructorOutputDto getInstructor(@PathVariable int id);
}
