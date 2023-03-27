package com.pplbo.fortic5.model.request;

import com.pplbo.fortic5.model.product.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CheckoutRequest {

    private Integer PID;

    private Integer quantity = 1;

    private String note;
}
