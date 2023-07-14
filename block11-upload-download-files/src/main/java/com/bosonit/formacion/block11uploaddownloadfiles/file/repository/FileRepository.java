package com.bosonit.formacion.block11uploaddownloadfiles.file.repository;


import com.bosonit.formacion.block11uploaddownloadfiles.file.domain.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface FileRepository extends JpaRepository<File, Integer>, PagingAndSortingRepository<File, Integer> {
}
