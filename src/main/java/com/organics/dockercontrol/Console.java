package com.organics.dockercontrol;


import com.organics.dockercontrol.controller.Controller;

/**
 * Created by organics on 2016/3/12.
 */
public class Console {
    public static void main(String[] args) {
        Controller c = new Controller();
        c.searchStats();
    }
}
