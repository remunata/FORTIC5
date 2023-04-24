package com.pplbo.fortic5.model.request;

import com.pplbo.fortic5.model.product.Category;
import com.pplbo.fortic5.model.product.Kondisi;
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

    private MultipartFile image;

    @NonNull
    private Integer price;

    @NonNull
    private Integer stock;

    @NonNull
    private String brand;

    @NonNull
    private Kondisi kondisi;
}
