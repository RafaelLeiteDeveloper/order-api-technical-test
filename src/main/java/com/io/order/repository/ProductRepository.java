package com.io.order.repository;

import com.io.order.model.dto.ProductDto;
import com.io.order.model.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, String> {

    default ProductEntity findByIdOrElseSave(ProductDto productDto){
        return this
                .findById(productDto.getProductId())
                .orElse(this.save(ProductEntity.toProductEntity(productDto)));
    }



}
