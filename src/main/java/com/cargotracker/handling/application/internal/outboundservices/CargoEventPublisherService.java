package com.cargotracker.handling.application.internal.outboundservices;

import com.cargotracker.handling.infrastructure.brokers.rabbitmq.CargoEventSource;
import com.cargotracker.shareddomain.events.CargoHandledEvent;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionalEventListener;
import org.springframework.util.MimeTypeUtils;

/**
 *
 */
@Service
@EnableBinding(CargoEventSource.class)
public class CargoEventPublisherService {

    CargoEventSource cargoEventSource;

    public CargoEventPublisherService(CargoEventSource cargoEventSource){
        this.cargoEventSource = cargoEventSource;
    }

    @TransactionalEventListener
	public void handleCargoHandledEvent(CargoHandledEvent cargoHandledEvent) {

		System.out.println("before sending messages");
		MessageChannel messageChannel = cargoEventSource.cargoHandling2();
		messageChannel.send(MessageBuilder.withPayload(cargoHandledEvent)
				.setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON).build());
	}
}