package com.hsj.ecommon;

import java.net.InetAddress;

public class ServerListItem {
    private InetAddress address;
    private int port;
    private String name;

    public ServerListItem(InetAddress address, int port, String name) {
        this.address = address;
        this.port = port;
        this.name = name;
    }

    public InetAddress getAddress() {
        return address;
    }

    public void setAddress(InetAddress address) {
        this.address = address;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
