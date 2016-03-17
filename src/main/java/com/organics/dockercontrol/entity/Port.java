package com.organics.dockercontrol.entity;

import java.io.Serializable;

/**
 * Created by organics on 2016/3/13.
 */
public class Port implements Serializable {
    
    private String IP;
    private String PrivatePort;
    private String PublicPort;
    private String Type;

    public String getIP() {
        return IP;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }

    public String getPrivatePort() {
        return PrivatePort;
    }

    public void setPrivatePort(String privatePort) {
        PrivatePort = privatePort;
    }

    public String getPublicPort() {
        return PublicPort;
    }

    public void setPublicPort(String publicPort) {
        PublicPort = publicPort;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }
}
