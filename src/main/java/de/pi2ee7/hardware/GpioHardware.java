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
package de.pi2ee7.hardware;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PreDestroy;

/**
 *
 * @author sebastian
 */
public class GpioHardware implements GpioInterface {

    final GpioController gpio;
    final GpioPinDigitalOutput pin1;
    final GpioPinDigitalOutput pin2;

    final Map<Integer, GpioPinDigitalOutput> pins;

    public GpioHardware() {
        System.out.println("<--Pi4J--> GPIO Control Example ... started.");

        // create gpio controller
        gpio = GpioFactory.getInstance();
        pins = new HashMap();

        // provision gpio pin #01 as an output pin and turn off
        pin1 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01, "MyLED", PinState.LOW);
        pins.put(1, pin1);

        pin2 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_02, "MyLED", PinState.LOW);
        pins.put(2, pin2);

        // set shutdown state for this pin
        pin1.setShutdownOptions(true, PinState.LOW);
        pin2.setShutdownOptions(true, PinState.LOW);

        System.out.println("--> GPIO state should be: OFF");

    }

    @Override
    public boolean isOn(Integer pinNumber) {
        return pins.get(pinNumber).getState().isHigh();
        
    }

    @Override
    public String turnOn(Integer pinNumber) {
        pins.get(pinNumber).high();
        return "LED should be on";
    }

    @Override
    public String turnOff(Integer pinNumber) {
        pins.get(pinNumber).low();
        return "LED should be off";
    }

    @Override
    public String toggle(Integer pinNumber) {
        pins.get(pinNumber).toggle();
        return "PIN state should have changed";
    }

    @PreDestroy
    void destroy() {
        // stop all GPIO activity/threads by shutting down the GPIO controller
        // (this method will forcefully shutdown all GPIO monitoring threads and scheduled tasks)
        gpio.shutdown();
    }

}
