package com.organics.dockercontrol.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by organics on 2016/3/13.
 */
public class Container implements Serializable {

    private String Id;
    private String Image;
    private String ImageID;
    private List<Port> Ports;
    private String Status;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getImageID() {
        return ImageID;
    }

    public void setImageID(String imageID) {
        ImageID = imageID;
    }

    public List<Port> getPorts() {
        return Ports;
    }

    public void setPorts(List<Port> ports) {
        Ports = ports;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }
}
