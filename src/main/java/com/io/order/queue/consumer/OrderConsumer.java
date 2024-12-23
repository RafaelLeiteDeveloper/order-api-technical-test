package com.io.order.queue.consumer;

import lombok.extern.slf4j.Slf4j;
import com.io.order.model.dto.request.OrderRequestDto;
import com.io.order.service.OrderService;
import com.rabbitmq.client.Channel;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
@SuppressWarnings("unused")
public class OrderConsumer {

    private final OrderService orderService;

    @RabbitListener(queues = "${spring.rabbitmq.template.queues.consumers.order-queue}", concurrency = "5-10")
    public void listenToProposalQueue(@Valid @Payload OrderRequestDto orderRequestDto, Channel channel, Message message) throws IOException {
        try {
            log.info("Body with {} received: ", orderRequestDto.getOrderId());
            this.orderService.saveOrder(orderRequestDto);
        } catch (Exception e){
            log.error("Exception Type: {} | Message: {}", e.getClass().getSimpleName(), e.getMessage());
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
        }
    }

}