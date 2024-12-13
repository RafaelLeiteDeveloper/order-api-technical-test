package com.io.order.controller;

import com.io.order.model.dto.OrderDto;
import com.io.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Objects;

@RestController
@RequestMapping("/v1/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/{id}")
    public ResponseEntity<OrderDto> getOrderById(@PathVariable("id") String id){
        Objects.requireNonNull(id, "O valor de `id` não pode ser nulo");
        OrderDto orderDto = this.orderService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(orderDto);
    }


}
