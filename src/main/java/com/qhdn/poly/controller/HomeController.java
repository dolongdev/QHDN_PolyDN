package com.qhdn.poly.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {

    @GetMapping("")
    public String home(){
        return "/page/layout-home";
    }

    @GetMapping("/admin/accounts")
    public String admin(){
        return "/admin/account-list";
    }
}
