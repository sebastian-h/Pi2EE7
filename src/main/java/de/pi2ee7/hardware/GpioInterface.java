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

/**
 * actual interface to the led functionality.
 * 
 * business functionality for turning the led on / off, changing state and so on.
 *
 * should have javadoc for the methods, but should be self-explanatory..
 *
 * @author sebastian
 */
public interface GpioInterface {

    boolean isOn(Integer gpioNumber);

    String toggle(Integer gpioNumber);

    String turnOff(Integer gpioNumber);

    String turnOn(Integer gpioNumber);
   
}
