package com.pplbo.fortic5.service.image;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class ImageStorageServiceImpl implements ImageStorageService {

    private final Path root = Paths.get("./uploads");

    @Override
    public void init() {
        try {
            Files.createDirectory(root);
        } catch (IOException exception) {
            throw new RuntimeException("Could not initialize root folder");
        }
    }

    @Override
    public void save(MultipartFile file, Integer productId, String fileExtension) {
        try {
            Files.copy(file.getInputStream(), this.root.resolve(productId + fileExtension));
        } catch (Exception exception) {
            throw new RuntimeException("Could not store the file. Error: " + exception.getMessage());
        }
    }

    @Override
    public Resource loadImage(String fileName) {
        try {
            Path file = root.resolve(fileName);
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable())
                return resource;
            else
                throw new RuntimeException("Could not read the file!");
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }
}
