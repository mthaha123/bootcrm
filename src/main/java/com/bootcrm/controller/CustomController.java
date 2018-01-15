package com.bootcrm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/customer")
public class CustomController {

    @RequestMapping("/list")
    public String queryCustomerList(Model model){
        System.out.println("helloAction");
        model.addAttribute("message","再见");
        return "hello";
    }
}
