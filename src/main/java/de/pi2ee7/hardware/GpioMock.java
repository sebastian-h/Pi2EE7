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

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author sebastian
 */
public class GpioMock implements GpioInterface {
    
    private Map<Integer, Boolean> pinStates;
    
    GpioMock() {
        pinStates = new HashMap();
        pinStates.put(1, false);
        pinStates.put(2, false);
    }
    
    @Override
    public boolean isOn(Integer pinNumber) {
        boolean status = this.pinStates.get(pinNumber);
        System.out.println("GpioMock: getStatus:" + status + " for Pin Number: " + pinNumber);
        return status;
    }
    
    @Override
    public String toggle(Integer pinNumber) {
        System.out.println("GpioMock: toggle for: " + pinNumber);
        boolean status = this.pinStates.get(pinNumber);
        if (status) {
            this.pinStates.put(pinNumber, false);
            return "turned off pin " + pinNumber;
        } else {
            this.pinStates.put(pinNumber, true);
            return "turned on pin " + pinNumber;
        }
    }
    
    @Override
    public String turnOff(Integer pinNumber) {
        this.pinStates.put(pinNumber, false);
        return "turned off pin " + pinNumber;
    }
    
    @Override
    public String turnOn(Integer pinNumber) {
        this.pinStates.put(pinNumber, true);
        return "turned on pin " + pinNumber;
    }
    
}