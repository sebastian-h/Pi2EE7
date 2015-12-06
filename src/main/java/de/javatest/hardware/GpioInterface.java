/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.javatest.hardware;

/**
 *
 * @author sebastian
 */
public interface GpioInterface {

    boolean isOn();

    String toggle();

    String turnOff();

    String turnOn();
    
}
