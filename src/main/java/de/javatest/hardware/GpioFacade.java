/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
