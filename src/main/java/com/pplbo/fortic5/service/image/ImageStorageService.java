package com.pplbo.fortic5.service.image;

import jakarta.annotation.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

public interface ImageStorageService {

    void init();

    void save(MultipartFile file, Integer id);

    Stream<Path> loadImages(Integer id);
}
