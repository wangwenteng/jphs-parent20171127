package com.jinpaihushi.jphs.commons.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.jinpaihushi.jphs.system.service.DoPostSmsService;

@Controller
public class DoPostSmsController {

    @Autowired
    private DoPostSmsService doPostSmsService;

    public void sendSms(String mobile, String contentCode, String param) {
        doPostSmsService.sendSms(mobile, contentCode, param);
    }
}
