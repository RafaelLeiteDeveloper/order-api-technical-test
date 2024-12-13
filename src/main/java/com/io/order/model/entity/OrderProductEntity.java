package com.io.order.model.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.io.Serializable;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_order_products")
public class OrderProductEntity {

    @EmbeddedId
    private OrderProductId id;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OrderProductId implements Serializable {
        private String orderId;
        private String productId;
    }

    @ManyToOne
    @MapsId("orderId")
    @JoinColumn(name = "fk_order_id", nullable = false)
    private OrderEntity orderId;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "fk_product_id", nullable = false)
    private ProductEntity productId;

    @Column(name = "it_quantity", nullable = false)
    private int quantity;

    public static OrderProductEntity toOrderProductEntity(
            int quantity,
            OrderEntity orderEntity,
            ProductEntity productEntity
    ) {

        OrderProductEntity.OrderProductId orderProductId =
                new OrderProductEntity.OrderProductId(orderEntity.getOrderId(), productEntity.getProductId());

        return OrderProductEntity.builder()
                .id(orderProductId)
                .orderId(orderEntity)
                .productId(productEntity)
                .quantity(quantity)
                .build();
    }

}
