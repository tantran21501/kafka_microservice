package com.food.ordering.system.order.service.domain;

import com.food.ordering.system.order.service.domain.dto.create.CreateOrderCommand;
import com.food.ordering.system.order.service.domain.dto.create.CreateOrderResponse;
import com.food.ordering.system.order.service.domain.event.OrderCreatedEvent;
import com.food.ordering.system.order.service.domain.mapper.OrderDataMapper;
import com.food.ordering.system.order.service.domain.port.output.message.publisher.payment.OrderCreatedPaymentRequestMessagePublisher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
public class OrderCreateCommandHandler {
    private final OrderCreateHelper orderCreateHelper;
    private final OrderDataMapper orderDataMapper;

    private final OrderCreatedPaymentRequestMessagePublisher orderCreatedPaymentRequestMessagePublisher;

    public OrderCreateCommandHandler(OrderCreateHelper orderCreateHelper,
                                     OrderDataMapper orderDataMapper,
                                     OrderCreatedPaymentRequestMessagePublisher orderCreatedPaymentRequestMessagePublisher) {
        this.orderCreateHelper = orderCreateHelper;
        this.orderDataMapper = orderDataMapper;
        this.orderCreatedPaymentRequestMessagePublisher = orderCreatedPaymentRequestMessagePublisher;
    }

    public CreateOrderResponse createOrder(CreateOrderCommand createOrderCommand) {
        OrderCreatedEvent orderCreatedEvent = orderCreateHelper.persistOrder(createOrderCommand);
        log.info("Order is created with id: {}", orderCreatedEvent.getOrder().getId().getValue());
        orderCreatedPaymentRequestMessagePublisher.publish(orderCreatedEvent);
        return orderDataMapper.orderToCreateOrderResponse(orderCreatedEvent.getOrder());


    }

//    private Restaurant checkRestaurant(CreateOrderCommand createOrderCommand) {
//        Restaurant restaurant = orderDataMapper.createOrderCommandToRestaurant(createOrderCommand);
//        Optional<Restaurant> optionalRestaurant = restaurantRepository.findRestaurantInformation(restaurant);
//        if (optionalRestaurant.isEmpty()) {
//            log.warn("Could not find restaurant with restaurant id: {}", createOrderCommand.getRestaurantId());
//            throw new OrderDomainException("Could not find restaurant with restaurant id: " + createOrderCommand.getRestaurantId());
//        }
//        return optionalRestaurant.get();
//    }
//
//    private void checkCustomer(UUID customerId) {
//        Optional<Customer> customer = customerRepository.findCustomer(customerId);
//        if (customer.isEmpty()) {
//            log.warn("Could not find customer with customer id: {}", customerId);
//            throw new OrderDomainException("Could not find customer with customer id: " + customerId);
//        }
//    }
//
//    private Order saveOrder(Order order) {
//        Order orderResult = orderRepository.save(order);
//        if (orderResult == null) {
//            log.error("Could not save order !");
//            throw new OrderDomainException("Could not save order !");
//        }
//        log.info("Order is saved with it: {}", orderResult.getId().getValue());
//        return orderResult;
//    }

}
