package com.qhdn.poly.controller.admin;

import com.qhdn.poly.dto.UserDto;
import com.qhdn.poly.model.User;
import com.qhdn.poly.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class AdminController {
    private boolean flag;
    @Autowired
    UserService userService;

    @GetMapping("/administration")
    public String getAdmin(Model model){
        List<UserDto> users = this.userService.getAllUsers();
        model.addAttribute("users", users);
        return "/page-admin/index";
    }

//    @GetMapping("/addOrEditAcc")
//    public String addOrEditAcc(Model model){
//        return "administration/addOrEditAcc";
//    }

    @GetMapping("/addOrEditAcc")
    public String addOrEditAcc(Model model){
        UserDto userDto = new UserDto();
        model.addAttribute("userDto", userDto);
        flag = true;
        model.addAttribute("flag", flag);
        return "/administration/addOrEditAcc";
    }

    @GetMapping("/update/{id}")
    public String updateUserForm(Model model, @PathVariable("id") Integer id){
        UserDto userDto = this.userService.getUserById(id);
        model.addAttribute("userDto", userDto);
        flag = false;
        model.addAttribute("flag", flag);
        return "/administration/addOrEditAcc";
    }

//    @PostMapping("/addUser")
//    public String addUser(Model model, @ModelAttribute("userDto") UserDto userDto, BindingResult result){
//        if (result.hasErrors()){
//            return "/error";
//        }
//        UserDto user = this.userService.createUser(userDto);
//        return "redirect:/admin";
//    }

    @PostMapping("/update/{id}")
    public String updateUser(Model model
            , @PathVariable("id") Integer id
            , BindingResult result
            , @ModelAttribute("userDto") UserDto userDto){
        if (result.hasErrors()){
            return "/error";
        }
        UserDto userUpdate = this.userService.updateUser(userDto, id);

        return "redirect:/admin";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id){
        this.userService.deleteUser(id);
        return "redirect:/admin";
    }

}
