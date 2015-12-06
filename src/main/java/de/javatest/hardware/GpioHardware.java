/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
