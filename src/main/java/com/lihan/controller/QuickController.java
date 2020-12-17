package com.lihan.controller;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ConfigurationProperties(prefix = "person")
public class QuickController {
    private String name;
    private String addr;

    @RequestMapping("/quick")
    @ResponseBody
    public String quick() {
        return "name: " + name + ", addr: " + addr;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }
}
