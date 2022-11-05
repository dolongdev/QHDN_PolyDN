package com.qhdn.poly.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/administration/posts")
public class PostsController {

    @GetMapping("")
    public String getPosts(Model model){
        return "/page-admin/posts";
    }
}
