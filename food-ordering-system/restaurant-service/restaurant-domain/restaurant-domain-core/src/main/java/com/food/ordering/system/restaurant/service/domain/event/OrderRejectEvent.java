package com.food.ordering.system.restaurant.service.domain.event;

import com.food.ordering.system.domain.event.publisher.DomainEventPublisher;
import com.food.ordering.system.domain.valueobject.RestaurantId;
import com.food.ordering.system.restaurant.service.domain.entity.OrderApproval;

import java.time.ZonedDateTime;
import java.util.List;

public class OrderRejectEvent extends OrderApprovalEvent {
    private final DomainEventPublisher<OrderRejectEvent> orderRejectEventDomainEventPublisher;
    public OrderRejectEvent(OrderApproval orderApproval,
                            RestaurantId restaurantId,
                            List<String> failureMessages,
                            ZonedDateTime createdAt,
                            DomainEventPublisher<OrderRejectEvent> orderRejectEventDomainEventPublisher) {
        super(orderApproval, restaurantId, failureMessages, createdAt);
        this.orderRejectEventDomainEventPublisher = orderRejectEventDomainEventPublisher;
    }

    @Override
    public void fire() {
        orderRejectEventDomainEventPublisher.publish(this);
    }
}
