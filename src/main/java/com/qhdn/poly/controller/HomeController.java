package com.qhdn.poly.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServlet;

@Controller
public class HomeController extends HttpServlet {

    @GetMapping("/home")
    public String home(){
        return "/page/home";
    }

    @GetMapping("/about")
    public String about(){
        return "/page/about";
    }
}
