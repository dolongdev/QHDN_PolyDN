package com.qhdn.poly.controller.admin;

import com.qhdn.poly.dto.UserDto;
import com.qhdn.poly.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/administration/users")
public class UsersController {
    @Autowired
    UserService userService;

    @GetMapping("")
    public String getListUser(Model model, @ModelAttribute("userUpdate") UserDto userUpdate){
        List<UserDto> userDtoList = this.userService.getAllUsers();
        model.addAttribute("users", userDtoList);
        return "page-admin/users";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Integer id){
        this.userService.deleteUser(id);
        return "redirect:/administration/users";
    }

    @PostMapping("/addUser")
    public String addUser(Model model, @ModelAttribute("userUpdate") UserDto userUpdate, BindingResult result){
        if (result.hasErrors()){
            return "/error";
        }
        UserDto user = this.userService.createUser(userUpdate);
        return "redirect:/administration/users";
    }

}
