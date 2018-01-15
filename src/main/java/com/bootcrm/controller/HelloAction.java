package com.bootcrm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller

public class HelloAction {
    @RequestMapping(value = "/hello.do")
    public String hello(Model model) throws Exception{
        System.out.println("helloAction");
        model.addAttribute("message","再见");
        return "hello";
    }
}
