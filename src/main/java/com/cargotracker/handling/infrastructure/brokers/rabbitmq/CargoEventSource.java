package com.cargotracker.handling.infrastructure.brokers.rabbitmq;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * Interface depicting all output channels
 */
public interface CargoEventSource {
	String cargoHandlingChannel2 = "cargoHandlingChannel2";

	  @Output(cargoHandlingChannel2) 
	  MessageChannel cargoHandling2();
}