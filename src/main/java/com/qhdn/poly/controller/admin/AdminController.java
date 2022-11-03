package com.qhdn.poly.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    @GetMapping("/admin/list")
    public String getAdmin(){
        return "/admin/account-list";
    }


}
