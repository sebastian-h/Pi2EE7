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
package de.javatest.hardware;

import javax.ejb.Singleton;

/**
 * I guess this is like a factory for selecting the implementation of GPIO bades
 * upon the hardware the app is running on.
 *
 * There is a problem with developing for the raspberry pi locally on a normal
 * computer: the pi4j framework will constantly throw errors, running the
 * software was impossible (for me at least).
 *
 * I have selected the strategy of facade / factory to get the right singleton
 * implementation based upon the hardware architecture: arm / not arm.
 *
 * In case of running on an arm architecture (rpi) this bean always returns the
 * same instance of GpioInterface implementation enabling control of the
 * hardware.
 *
 * In case of running on any other architecture (eg: x86) this bean returns a
 * mock implementation of the GpioInterface doing no more than making some log
 * statements to represent what is going of with the led (like turn on, off,
 * getState and so on.)
 * 
 * This is probably not the best way to accomplish what ii was looking for.
 *
 * @author sebastian
 */
@Singleton
public class GpioFacade {

    private GpioInterface gpioInstance;

    public GpioInterface getInstance() {

        String arch = System.getProperty("os.arch");
        System.out.println(arch);

        if (this.gpioInstance == null) {
            synchronized (this) {
                if (this.gpioInstance == null) {
                    if (arch.toLowerCase().startsWith("arm")) {
                        this.gpioInstance = new GpioHardware();
                    } else {
                        this.gpioInstance = new GpioMock();
                    }

                }
            }
        }
        return this.gpioInstance;
    }

}
