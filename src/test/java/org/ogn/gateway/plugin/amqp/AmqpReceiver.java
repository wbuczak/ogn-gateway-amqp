package org.ogn.gateway.plugin.amqp;

import java.util.concurrent.atomic.AtomicInteger;

import org.ogn.commons.beacon.AircraftBeaconWithDescriptor;
import org.ogn.commons.utils.JsonUtils;

public class AmqpReceiver {

	AtomicInteger counter = new AtomicInteger();

	public void onMessage(String message) {
		System.out.println("Received <" + message + ">");
		JsonUtils.fromJson(message, AircraftBeaconWithDescriptor.class);
		counter.incrementAndGet();
	}

	public int getCounter() {
		return counter.get();
	}

}