package com.qhdn.poly.controller.rest;

import com.qhdn.poly.dto.ApiResponsive;
import com.qhdn.poly.dto.UserDto;
import com.qhdn.poly.model.User;
import com.qhdn.poly.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountRestController {
    @Autowired
    UserService userService;

    @GetMapping("")
    public List<UserDto> getAllAccount(){
        List<UserDto> users = userService.getAllUsers();
        return users;
    }

    @PostMapping("/add")
    public UserDto add(@Valid @RequestBody UserDto userDto){
        return userService.createUser(userDto);
    }

    @PutMapping("/update/{id}")
    public UserDto update(@Valid @RequestBody UserDto userDto, @PathVariable Integer id){
        return userService.updateUser(userDto, id);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<ApiResponsive> delete(@PathVariable Integer id){
        this.userService.deleteUser(id);
        return new ResponseEntity<ApiResponsive>
                (new ApiResponsive("User Deleted Successfully", true), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getSingleUser(@PathVariable Integer id){
        return ResponseEntity.ok(this.userService.getUserById(id));
    }
}
