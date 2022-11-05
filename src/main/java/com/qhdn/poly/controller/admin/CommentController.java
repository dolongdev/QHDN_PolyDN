package com.qhdn.poly.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/administration/comments")
public class CommentController {

    @GetMapping("")
    public String getComments(Model model){
        return "/page-admin/comments";
    }
}
