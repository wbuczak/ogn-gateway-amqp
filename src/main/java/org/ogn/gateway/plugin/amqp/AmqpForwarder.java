/**
 * Copyright (c) 2014 OGN, All Rights Reserved.
 */

package org.ogn.gateway.plugin.amqp;

import java.util.Optional;

import org.ogn.commons.beacon.AircraftBeacon;
import org.ogn.commons.beacon.AircraftDescriptor;
import org.ogn.commons.beacon.forwarder.OgnAircraftBeaconForwarder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * RabbitMQ forwarder plug-in for OGN gateway
 * 
 * @author wbuczak
 */
public class AmqpForwarder implements OgnAircraftBeaconForwarder {

	public static final String				SERVICE_NAME	= "RabbitMQ";
	public static final String				VERSION			= "1.0.0";

	private static final Logger				LOG				= LoggerFactory.getLogger(AmqpForwarder.class);

	private MsgSender						forwarder;

	private volatile boolean				initialized		= false;

	private ClassPathXmlApplicationContext	ctx;

	@Override
	public String getName() {
		return SERVICE_NAME + " forwarder";
	}

	@Override
	public String getVersion() {
		return VERSION;
	}

	@Override
	public String getDescription() {
		return "relays OGN aircraft beacons to " + SERVICE_NAME;
	}

	@Override
	public void init() {

		LOG.debug("initializing..");
		if (!initialized) {

			try {
				ctx = new ClassPathXmlApplicationContext("classpath:amqp-application-context.xml");

				ctx.refresh();
				forwarder = ctx.getBean(MsgSender.class);

				initialized = true;
				LOG.debug("initialization done");
			} catch (Exception ex) {
				LOG.error("could not initialize context", ex);
			}
		}
	}

	void init(MsgSender sender) {
		if (!initialized) {
			this.forwarder = sender;
			init();
		}
	}

	@Override
	public void stop() {
		if (ctx != null)
			ctx.close();
	}

	/**
	 * default constructor
	 */
	public AmqpForwarder() {

	}

	@Override
	public void onBeacon(AircraftBeacon beacon, Optional<AircraftDescriptor> descriptor) {
		forwarder.send(beacon, descriptor);
	}
}