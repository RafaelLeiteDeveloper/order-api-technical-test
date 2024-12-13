package com.io.order.model.entity;

import com.io.order.model.dto.request.OrderRequestDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_orders")
public class OrderEntity {
    
    @Id
    @Column(name = "pk_order_id", nullable = false, length = 36)
    private String orderId;

    @Column(name = "st_client_id", nullable = false, length = 36)
    private String clientId;

    @Column(name = "dt_order_date")
    @Temporal(TemporalType.DATE)
    private Date date;

    @Column(name = "bd_total_cost")
    private BigDecimal totalCost;

    public static OrderEntity toOrderEntity(OrderRequestDto orderRequestDto) {
        return OrderEntity.builder()
                .orderId(orderRequestDto.getOrderId())
                .clientId(orderRequestDto.getClientId())
                .date(orderRequestDto.getOrderDate())
                .totalCost(orderRequestDto.getTotalPriceProducts())
                .build();
    }
}
