package com.io.order.model.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.io.order.model.internal.ProductInternal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequestDto {

    @JsonProperty("product_id")
    private String productId;

    @JsonProperty("name")
    private String name;

    @JsonProperty("price")
    private BigDecimal price;

    @JsonProperty("quantity")
    private int quantity;

    public static ProductRequestDto toProductDto(ProductInternal internal){
        return ProductRequestDto.builder()
                .name(internal.getName())
                .price(internal.getPrice())
                .productId(internal.getProductId())
                .quantity(internal.getQuantity())
                .build();
    }

    public static List<ProductRequestDto> toProductDtoList(List<ProductInternal> productInternalList){
        return productInternalList.stream()
                                  .map(ProductRequestDto::toProductDto)
                                  .collect(Collectors.toList());
    }
}
