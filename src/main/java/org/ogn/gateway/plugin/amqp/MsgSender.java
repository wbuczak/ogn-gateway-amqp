/**
 * Copyright (c) 2015 OGN, All Rights Reserved.
 */

package org.ogn.gateway.plugin.amqp;

import java.util.Optional;

import org.ogn.commons.beacon.AircraftBeacon;
import org.ogn.commons.beacon.AircraftDescriptor;
import org.ogn.commons.beacon.ReceiverBeacon;

public interface MsgSender {
	void send(AircraftBeacon beacon, Optional<AircraftDescriptor> descriptor);

	void send(ReceiverBeacon beacon);
}