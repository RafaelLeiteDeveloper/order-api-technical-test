package com.io.order.model.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
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
public class OrderRequestDto {

    @JsonProperty("order_id")
    private String orderId;

    @JsonProperty("client_id")
    private String clientId;

    @JsonProperty("products")
    private List<ProductRequestDto> products;

    @JsonProperty("order_date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date orderDate;

    @JsonIgnore
    public BigDecimal getTotalPriceProducts(){
        return products.stream()
                .map(ProductRequestDto::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }


}
