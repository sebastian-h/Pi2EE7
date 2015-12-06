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
public class GpioMock implements GpioInterface {

    private boolean status;

    GpioMock() {
        status = false;
    }

    @Override
    public boolean isOn() {
        System.out.println("GpioMock: status:" + this.status);
        return status;
    }

    @Override
    public String toggle() {
         System.out.println("GpioMock: toggle");
        if (this.status) {
            this.status = false;
            return "turned off";
        } else {
            this.status = true;
            return "turned on";
        }
    }

    @Override
    public String turnOff() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String turnOn() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
