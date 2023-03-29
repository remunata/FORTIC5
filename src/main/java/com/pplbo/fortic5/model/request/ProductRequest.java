package com.pplbo.fortic5.model.request;

import com.pplbo.fortic5.model.product.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {

    private String name;

    private String description;

    private Category category;

    private MultipartFile images;

    private Integer price;
}
