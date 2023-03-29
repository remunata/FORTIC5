package com.pplbo.fortic5.service.image;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

@Service
public class ImageStorageServiceImpl implements ImageStorageService {

    private final Path root = Paths.get("src/main/resources/static/assets/products");

    @Override
    public void init() {
        try {
            Files.createDirectory(root);
        } catch (IOException exception) {
            throw new RuntimeException("Could not initialize root folder");
        }
    }

    @Override
    public void save(MultipartFile file, Integer productId) {
        try {
            Files.copy(file.getInputStream(), this.root.resolve(productId + "/" + file.getOriginalFilename()));
        } catch (Exception exception) {
            throw new RuntimeException("Could not store the file. Error: " + exception.getMessage());
        }
    }

    @Override
    public Stream<Path> loadImages(Integer id) {
        try (Stream<Path> pathStream = Files.walk(this.root.resolve(String.valueOf(id)), 1)) {
            return pathStream
                    .filter(Files::isRegularFile)
                    .map(this.root::relativize);
        } catch (IOException exception) {
            throw new RuntimeException("Could not load the file");
        }
    }
}
