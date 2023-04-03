package com.pplbo.fortic5.model.request;

import com.pplbo.fortic5.model.product.Category;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {

    @NonNull
    private String name;

    @NonNull
    private String description;

    @NonNull
    private Category category;

    @NonNull
    private MultipartFile image;

    @NonNull
    private Integer price;
}
