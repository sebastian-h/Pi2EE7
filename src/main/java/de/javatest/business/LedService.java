/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.javatest.business;

import de.javatest.hardware.GpioFacade;
import de.javatest.hardware.GpioInterface;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author sebastian
 */
@Stateless
public class LedService {

    GpioInterface gpio;

    @Inject
    GpioFacade gpioFacade;

    @PostConstruct
    void init() {
        gpio = gpioFacade.getInstance();
    }

    public String ping() {
        return "Hello at: " + new Date();
    }

    public String getState() {
        if (gpio.isOn()) {
            return "should be ON";
        }
        return "should be OFF";
    }

    public void toggle() {
        gpio.toggle();
    }

    public void wink() {
        for (int i = 1; i < 5; i++) {
            gpio.toggle();
            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                Logger.getLogger(LedService.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }
}
