package com.qhdn.poly.controller;

import com.qhdn.poly.model.User;
import com.qhdn.poly.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SecurityController {

    @Autowired
    UserService userService;

    @GetMapping("/login")
    public String login(){
        return "/security/login-form";
    }

    @PostMapping("/login")
    public String processingLogin(@RequestParam String username,
                                  @RequestParam String password){
        User user = this.userService.findByUsername(username);
        if (user != null){
            if (user.getPassword().equals(password)){
                return "/admin/account-list";
            }
        }else {
            return "/security/login-form";
        }
        return username;
    }
}
