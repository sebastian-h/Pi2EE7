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
package de.pi2ee7.controller;

import de.pi2ee7.business.LedService;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.Future;
import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.faces.bean.SessionScoped;
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

    private Integer reps = 25;
    private Integer rate = 25;

    public Integer getReps() {
        return reps;
    }

    public void setReps(Integer reps) {
        this.reps = reps;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

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

    public String getLedStatusAsTextRed() {
        if (ledService.getState(1)) {
            return "status is: should be ON";
        } else {
            return "status is: should be OFF";
        }
    }

    public String getLedStatusAsTextGreen() {
        if (ledService.getState(2)) {
            return "status is: should be ON";
        } else {
            return "status is: should be OFF";
        }
    }

    /**
     * acts upon form submit (button pressed), changes the state of the led and
     * redirects back to the page
     *
     * @return String The xhtml page to render as the response. (?)
     */
    public String toggleRed() {
        ledService.toggle(1);
        return "index";
    }

    public String blinkRed() {
        ledService.wink(1);
        return "index";
    }

    /**
     * acts upon form submit (button pressed), changes the state of the led and
     * redirects back to the page
     *
     * @return String The xhtml page to render as the response. (?)
     */
    public String toggleGreen() {
        ledService.toggle(2);
        return "index";
    }

    public String blinkGreen() {
        ledService.wink(2);
        return "index";
    }

    public String blinkAsync() {
        FacesContext context = FacesContext.getCurrentInstance();
        String ledColor = context.getExternalContext().getRequestParameterMap().get("ledColor");
        if ((null == this.rate) || (null == this.reps)) {
            context.addMessage("", new FacesMessage("Rate or Repetitions are NULL, no action taken."));
            return "index";
        }
        if (ledColor.equals("RED")) {
            ledService.blink(1, this.rate, this.reps);
            context.addMessage("", new FacesMessage("Blinking started. (led: " + ledColor + ", rate: " + this.rate + ", reps: " + this.reps + ")"));
        } else if (ledColor.equals("GREEN")) {
            ledService.blink(2, this.rate, this.reps);
            context.addMessage("", new FacesMessage("Blinking started. (led: " + ledColor + ", rate: " + this.rate + ", reps: " + this.reps + ")"));
        } else {
            context.addMessage("", new FacesMessage("No color given, no action taken."));
        }
        return "index";
    }

}
