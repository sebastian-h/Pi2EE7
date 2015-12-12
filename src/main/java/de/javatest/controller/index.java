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
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 * This is the controller class for the index.xhtml "landing" page.
 *
 * It enables dynamic content in the Web-UI ands acts upon form submits.
 *
 * @author sebastian
 */
@Model
public class index {
    
    @Inject
    LedService ledService;

    /**
     * lookup the hostname the app is running on
     *
     * @return String the hostname
     */
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
    
    public String getLedStatusRed() {
        return "Led status is: " + ledService.getState(1);
    }
    
    public String getLedStatusGreen() {
        return "Led status is: " + ledService.getState(2);
    }

    /**
     * acts upon form submit (button pressed), changes the state of the led and
     * redirects back to the page
     *
     * @return String The xhtml page to render as the response. (?)
     */
    public String toggle() {
        String value = "";
        value = FacesContext.getCurrentInstance().
                getExternalContext().getRequestParameterMap().get("ledColor");
        if (value.equals("RED")) {
            ledService.toggle(1);
        }
        if (value.equals("GREEN")) {
            ledService.toggle(2);
        }
        return "";
    }
    
    public String blink() {
        String value;
        value = FacesContext.getCurrentInstance().
                getExternalContext().getRequestParameterMap().get("ledColor");
        if (value.equals("RED")) {
            ledService.wink(1);
        }
        if (value.equals("GREEN")) {
            ledService.wink(2);
        }
        return "";
    }
}
