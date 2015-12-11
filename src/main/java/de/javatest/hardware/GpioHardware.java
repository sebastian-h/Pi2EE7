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

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import javax.annotation.PreDestroy;


/**
 *
 * @author sebastian
 */
public class GpioHardware implements GpioInterface {

    final GpioController gpio;
    final GpioPinDigitalOutput pin;

    public GpioHardware() {
        System.out.println("<--Pi4J--> GPIO Control Example ... started.");

        // create gpio controller
        gpio = GpioFactory.getInstance();

        // provision gpio pin #01 as an output pin and turn on
        pin = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01, "MyLED", PinState.LOW);

        // set shutdown state for this pin
        pin.setShutdownOptions(true, PinState.LOW);

        System.out.println("--> GPIO state should be: OFF");

    }

    @Override
    public boolean isOn() {
        return pin.getState().isHigh();
    }

    @Override
    public String turnOn() {
        pin.high();
        return "LED should be on";
    }

    @Override
    public String turnOff() {
        pin.low();
        return "LED should be off";
    }

    @Override
    public String toggle() {
        pin.toggle();
        return "PIN state should have changed";
    }

    @PreDestroy
    void destroy() {
        // stop all GPIO activity/threads by shutting down the GPIO controller
        // (this method will forcefully shutdown all GPIO monitoring threads and scheduled tasks)
        gpio.shutdown();
    }

}
