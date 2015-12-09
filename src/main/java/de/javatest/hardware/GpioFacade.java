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
