package org.ogn.gateway.plugin.amqp;
import org.ogn.commons.beacon.AircraftBeacon;
import org.ogn.commons.beacon.AircraftBeaconWithDescriptor;
import org.ogn.commons.beacon.AircraftDescriptor;
import org.ogn.commons.beacon.ReceiverBeacon;
import org.ogn.commons.utils.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Service;

@Service
@ManagedResource(objectName = "org.ogn.gateway.plugin.amqp:name=AmqpSender")
public class AmqpSender implements MsgSender {

	private static final Logger LOG = LoggerFactory.getLogger(AmqpSender.class);

	@Autowired
	private AmqpTemplate rabbitTemplate;

	@Value("${ogn.amqp.queue.aircraft:ogn.beacons.aircraft}")
	private String aircartQueueName;

	@Value("${ogn.amqp.queue.receivers:ogn.beacons.receivers}")
	private String receiversQueueName;
	
	@Override
	public void send(AircraftBeacon beacon, AircraftDescriptor descriptor) {
		LOG.trace("sending aircraft beacon id: {} to queue: {}", beacon.getId(), aircartQueueName);
		rabbitTemplate.convertAndSend(aircartQueueName, JsonUtils.toJson(new AircraftBeaconWithDescriptor(beacon, descriptor)));
	}

	@Override
	public void send(ReceiverBeacon beacon) {
		LOG.trace("sending receiver beacon id: {} to queue: {}", beacon.getId(), receiversQueueName);
		rabbitTemplate.convertAndSend(aircartQueueName, JsonUtils.toJson(beacon));
	}
	

	@ManagedAttribute
	public String getAircartQueueName() {
		return aircartQueueName;
	}

	@ManagedAttribute
	public String getReceiversQueueName() {
		return receiversQueueName;
	}
		
}