package com.food.ordering.system.restaurant.service.domain.ports.output.message.publisher;

import com.food.ordering.system.domain.event.publisher.DomainEventPublisher;
import com.food.ordering.system.restaurant.service.domain.event.OrderApprovalEvent;
import com.food.ordering.system.restaurant.service.domain.event.OrderApprovedEvent;
import com.food.ordering.system.restaurant.service.domain.event.OrderRejectEvent;

public interface OrderRejectedMessagePublisher extends DomainEventPublisher<OrderRejectEvent> {
}
