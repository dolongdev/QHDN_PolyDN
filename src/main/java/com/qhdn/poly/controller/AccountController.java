package com.qhdn.poly.controller;

import com.qhdn.poly.model.User;
import com.qhdn.poly.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class AccountController {
    List<User> aList;

    @Autowired
    UserService userService;


    @GetMapping("/accounts")
    public String accountList(Model model){
        return "/administration/account-list";
    }

}
