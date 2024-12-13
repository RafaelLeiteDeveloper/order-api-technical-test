package com.io.order.service;

import com.io.order.model.dto.request.OrderRequestDto;
import com.io.order.model.dto.request.ProductRequestDto;
import com.io.order.model.dto.response.OrderResponseDto;
import com.io.order.model.entity.OrderEntity;
import com.io.order.model.entity.OrderProductEntity;
import com.io.order.model.entity.ProductEntity;
import com.io.order.model.internal.OrderInternal;
import com.io.order.model.internal.ProductInternal;
import com.io.order.repository.OrderProductRepository;
import com.io.order.repository.OrderRepository;
import com.io.order.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Collections;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final OrderProductRepository orderProductRepository;

    @Transactional
    public void saveOrder(OrderRequestDto orderRequestDto) {

        log.info("Starting the Save Order Process in Bank...");
        OrderEntity orderEntity = this.orderRepository.saveIfNotExists(OrderEntity.toOrderEntity(orderRequestDto));

        orderRequestDto.getProducts().forEach(productDto -> {

            log.info("starting the process of registering the products in the bank, if there is none...");
            ProductEntity productEntity = this.productRepository.findByIdOrElseSave(productDto);

            log.info("starting the process of searching for a product in the bank if it exists, if it does not exist, populating...");
            OrderProductEntity orderProductEntity = OrderProductEntity.toOrderProductEntity(productDto.getQuantity(), orderEntity, productEntity);
            this.orderProductRepository.save(orderProductEntity);
        });

        log.info("Order successfully saved in the bank.");
    }

    public OrderResponseDto findById(String id) {
        log.info("Starting the process of searching for an order in the bank by id...");
        OrderInternal orderDb = orderRepository.findOrderDetailsByOrderId(id).orElseThrow(() -> new NullPointerException("Pedido não encontrado com esse id."));
        List<ProductInternal> productInternalDtoList = orderRepository.findProductsByOrderId(id).orElseGet(Collections::emptyList);
        List<ProductRequestDto> productRequestDtoList = ProductRequestDto.toProductDtoList(productInternalDtoList);
        log.info("order and your products found, returning.");
        return OrderResponseDto.toOrderDto(orderDb, productRequestDtoList);
    }

}
