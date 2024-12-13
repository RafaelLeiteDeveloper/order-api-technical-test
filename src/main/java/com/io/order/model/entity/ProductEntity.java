package com.io.order.model.entity;

import com.io.order.model.dto.ProductDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_products")
public class ProductEntity {

    @Id
    @Column(name = "pk_product_id", length = 36, nullable = false)
    private String productId;

    @Column(name = "st_name", nullable = false)
    private String name;

    @Column(name = "db_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    public static ProductEntity toProductEntity(ProductDto productDto) {
        return ProductEntity.builder()
                .productId(productDto.getProductId())
                .name(productDto.getName())
                .price(productDto.getPrice())
                .build();
    }

}
