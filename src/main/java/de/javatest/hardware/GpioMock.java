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
