package com.qhdn.poly.service.impl;

import com.qhdn.poly.dto.UserDto;
import com.qhdn.poly.exceptions.ResourceNotFoundException;
import com.qhdn.poly.model.User;
import com.qhdn.poly.repository.UserRepo;
import com.qhdn.poly.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepo userRepo;

    @Autowired
    ModelMapper modelMapper;


    @Override
    public UserDto createUser(UserDto userDto) {
        User user = this.dtoToUser(userDto);

        User saveUser = this.userRepo.save(user);

        return this.userToDto(saveUser);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer userId) {
        User user = this.userRepo.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("User", "id" , userId));

        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAbout(userDto.getAbout());

        User updateUser = this.userRepo.save(user);
        UserDto userDto1 = this.userToDto(updateUser);

        return userDto1;
    }

    @Override
    public UserDto getUserById(Integer userId) {

        User user = this.userRepo.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("User", "id" , userId));

        return this.userToDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {

        List<User> users = this.userRepo.findAll();

        List<UserDto> userDtoList = users.stream().map(user -> this.userToDto(user)).collect(Collectors.toList());

        return userDtoList;
    }

    @Override
    public void deleteUser(Integer userId) {
        User user = this.userRepo.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("User", "id" , userId));
        this.userRepo.delete(user);
    }

    @Override
    public User findByUsername(String username) {
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String username){
        User user = this.userRepo.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found!!"));

        return new org.springframework.security.core.userdetails
                .User(user.getEmail(), user.getPassword(), new ArrayList<>());
    }


    private User dtoToUser(UserDto userDto){
        User user = this.modelMapper.map(userDto, User.class);


//        user.setEmail(userDto.getEmail());
//        user.setPassword(userDto.getPassword());
//        user.setId(userDto.getId());
//        user.setName(userDto.getName());
//        user.setAbout(userDto.getAbout());
        return user;
    }

    public UserDto userToDto(User user){
        UserDto userDto = this.modelMapper.map(user, UserDto.class);


//        userDto.setAbout(user.getAbout());
//        userDto.setName(user.getName());
//        userDto.setId(user.getId());
//        userDto.setPassword(user.getPassword());
//        userDto.setEmail(user.getEmail());

        return userDto;
    }


}
