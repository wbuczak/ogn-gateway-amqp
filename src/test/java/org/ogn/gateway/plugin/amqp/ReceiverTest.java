package org.ogn.gateway.plugin.amqp;

import static org.junit.Assert.assertEquals;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ogn.commons.beacon.impl.AircraftDescriptorImpl;
import org.ogn.commons.beacon.impl.aprs.AprsAircraftBeacon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:amqp-application-context.xml", "classpath:rabbit-context-subscriber.xml" })
public class ReceiverTest {

	@Autowired
	MsgSender sender;

	@Autowired
	AmqpReceiver receiver;

	@Test
	public void test() throws Exception {

//		Path p = Paths.get(getClass().getResource("/test-aircraft-beacons.txt").toURI());
//		List<String> aprsSentences = Files.readAllLines(p, Charset.defaultCharset());
//
//		Files.lines(p).forEach(l -> {
//			sender.send(new AprsAircraftBeacon(l), AircraftDescriptorImpl.UNKNOWN_AIRCRAFT_DESCRIPTOR);
//		});
//
//		Thread.sleep(5000);

		//assertEquals(aprsSentences.size(), receiver.getCounter());
		
		Thread.sleep(Long.MAX_VALUE);
	}

}