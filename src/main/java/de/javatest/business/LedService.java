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
