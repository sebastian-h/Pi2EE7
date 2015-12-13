/*
 * Copyright (C) 2015 sebastian
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package de.pi2ee7.business;

import de.pi2ee7.hardware.GpioFacade;
import de.pi2ee7.hardware.GpioInterface;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.AsyncResult;
import javax.ejb.Asynchronous;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author sebastian
 */
/**
 * This is the BusinessService to control a led connected to PIN 1.
 */
@Stateless
public class LedService {

    @Resource
    SessionContext sessionContext;

    GpioInterface gpio;

    @Inject
    GpioFacade gpioFacade;

    @PostConstruct
    void init() {
        gpio = gpioFacade.getInstance();
    }

    /**
     * pings the service to see if it is alive and answering requests
     *
     * @return String a text containing the current time
     */
    public String ping() {
        return "LedService is ready. System time: " + System.currentTimeMillis();
    }

    /**
     * retrieves the current state of illumination the LED.
     *
     * @param Integer pinNumber sets the PinNumber to query
     * @return boolean true if the LED is on or false if it is off at the moment
     * of invocation.
     */
    public boolean getState(Integer pinNumber) {
        if (gpio.isOn(pinNumber)) {
            return true;
        }
        return false;
    }

    /**
     * switches the state of the LED. If it was on it will be turned off, if it
     * was on it will be turned off.
     */
    public void toggle(Integer pinNumber) {
        gpio.toggle(pinNumber);
    }

    /**
     * flashes the led (or "winks" at you).
     */
    public void wink(Integer pinNumber) {
        for (int i = 1; i < 9; i++) {
            gpio.toggle(pinNumber);
            try {
                Thread.sleep(150);
            } catch (InterruptedException ex) {
                Logger.getLogger(LedService.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    @Asynchronous
    public Future<String> blink(Integer pinNumber, Integer rate, Integer iterations) {
        for (int i = 1; i < iterations; i++) {
            if (sessionContext.wasCancelCalled()) {
                break;
            }
            gpio.toggle(pinNumber);
            System.out.println("iteration" + i);
            try {
                Thread.sleep(rate);
            } catch (InterruptedException ex) {
                Logger.getLogger(LedService.class.getName()).log(Level.SEVERE, null, ex);
                break;
            }
        }
        String status = "Finished!";
        return new AsyncResult<>(status);
    }

}
