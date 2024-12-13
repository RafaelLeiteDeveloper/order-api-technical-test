package com.io.order.service;

import com.io.order.model.dto.OrderDto;
import com.io.order.model.dto.ProductDto;
import com.io.order.model.entity.OrderEntity;
import com.io.order.model.entity.OrderProductEntity;
import com.io.order.model.entity.ProductEntity;
import com.io.order.model.internal.OrderInternal;
import com.io.order.model.internal.ProductInternal;
import com.io.order.repository.OrderProductRepository;
import com.io.order.repository.OrderRepository;
import com.io.order.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final OrderProductRepository orderProductRepository;

    @Transactional
    public void saveOrder(OrderDto orderDto) {

        OrderEntity orderEntity = this.orderRepository.saveIfNotExists(OrderEntity.toOrderEntity(orderDto));

        orderDto.getProducts().forEach( productDto -> {
            ProductEntity productEntity = this.productRepository.findByIdOrElseSave(productDto);
            OrderProductEntity orderProductEntity = OrderProductEntity.toOrderProductEntity(productDto.getQuantity(), orderEntity, productEntity);
            this.orderProductRepository.save(orderProductEntity);
        });
    }

    public OrderDto findById(String id) {
        OrderInternal orderDb = orderRepository.findOrderDetailsByOrderId(id).orElseThrow(() -> new NullPointerException("Pedido não encontrado com esse id."));
        List<ProductInternal> productInternalDtoList = orderRepository.findProductsByOrderId(id).orElseGet(Collections::emptyList);
        List<ProductDto> productDtoList = ProductDto.toProductDtoList(productInternalDtoList);
        return OrderDto.toOrderDto(orderDb, productDtoList);
    }

}
