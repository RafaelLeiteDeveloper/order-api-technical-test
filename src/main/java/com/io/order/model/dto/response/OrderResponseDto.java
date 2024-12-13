package com.io.order.model.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.io.order.model.dto.request.OrderRequestDto;
import com.io.order.model.dto.request.ProductRequestDto;
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
public class OrderResponseDto {

    private String orderId;
    private String clientId;
    private List<ProductRequestDto> products;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date orderDate;

    private BigDecimal totalCost;

    public static OrderResponseDto toOrderDto(OrderInternal orderDb, List<ProductRequestDto> productRequestDtoList){
        return OrderResponseDto.builder()
                .orderId(orderDb.getOrderId())
                .clientId(orderDb.getClientId())
                .orderDate(orderDb.getOrderDate())
                .products(productRequestDtoList)
                .totalCost(orderDb.getTotalCost())
                .build();
    }

}
