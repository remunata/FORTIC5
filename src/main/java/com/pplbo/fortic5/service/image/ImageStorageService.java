package com.pplbo.fortic5.service.image;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface ImageStorageService {

    void init();

    void save(MultipartFile file, Integer id, String fileName);

    Resource loadImage(String fileName);
}
