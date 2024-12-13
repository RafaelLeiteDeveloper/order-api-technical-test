package com.io.order.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.io.order.model.internal.OrderInternal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {

    @JsonProperty("order_id")
    private String orderId;

    @JsonProperty("client_id")
    private String clientId;

    @JsonProperty("products")
    private List<ProductDto> products;

    @JsonProperty("order_date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date orderDate;

    @JsonIgnore
    public BigDecimal getTotalPriceProducts(){
        return products.stream()
                .map(ProductDto::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public static OrderDto toOrderDto(OrderInternal orderDb, List<ProductDto> productDtoList){
        return OrderDto.builder()
                .orderId(orderDb.getOrderId())
                .clientId(orderDb.getClientId())
                .orderDate(orderDb.getOrderDate())
                .products(productDtoList)
                .build();
    }

}
