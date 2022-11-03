package com.qhdn.poly.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/security")
public class SecurityController {

    @GetMapping("/login/form")
    public String loginForm(Model model){
        model.addAttribute("message", "Vui lòng đăng nhập!!");

        return "/security/login";
    }

}
