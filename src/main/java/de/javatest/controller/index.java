/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.javatest.controller;

import de.javatest.business.LedService;
import java.net.InetAddress;
import java.net.UnknownHostException;
import javax.enterprise.inject.Model;
import javax.inject.Inject;

/**
 *
 * @author sebastian
 */
@Model
public class index {

    @Inject
    LedService ledService;

    public String getHostname() {
        InetAddress inetAddress = null;
        try {
            inetAddress = InetAddress.getLocalHost();
        } catch (UnknownHostException une) {
            System.out.println("Exception: " + une);
            une.printStackTrace();
        }
        String nodeName = inetAddress.getHostName();
        System.out.println("Machine name: " + nodeName);
        return nodeName;
    }

    public String getLedStatus() {
        return "Led status is: " + ledService.getState();
    }

    public String toggle() {
        ledService.toggle();
        return "";
    }

    public String blink() {
        ledService.blink();
        return "";
    }
}
