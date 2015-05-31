package com.sensorhub.iot.core.mail;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class HostGeneratorImpl implements HostGenerator {
    public String generateLocalAddress() throws UnknownHostException {
        String address = InetAddress.getLocalHost().getHostName() + "/"
                + InetAddress.getLocalHost().getHostAddress();

        return address;
    }
}
