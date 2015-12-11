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
package de.javatest.controller;

import de.javatest.business.LedService;
import java.net.InetAddress;
import java.net.UnknownHostException;
import javax.enterprise.inject.Model;
import javax.inject.Inject;

/**
 *
 * @author sebastian
 */
@Model
public class index {

    @Inject
    LedService ledService;

    public String getHostname() {
        InetAddress inetAddress = null;
        try {
            inetAddress = InetAddress.getLocalHost();
        } catch (UnknownHostException une) {
            System.out.println("Exception: " + une);
            une.printStackTrace();
        }
        String nodeName = inetAddress.getHostName();
        System.out.println("Machine name: " + nodeName);
        return nodeName;
    }

    public String getLedStatus() {
        return "Led status is: " + ledService.getState();
    }

    public String toggle() {
        ledService.toggle();
        return "";
    }

    public String blink() {
        ledService.wink();
        return "";
    }
}
