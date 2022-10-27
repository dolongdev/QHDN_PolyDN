package com.qhdn.poly.service;

import com.qhdn.poly.dto.UserDto;
import com.qhdn.poly.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {

    UserDto createUser(UserDto userDto);

    UserDto updateUser(UserDto userDto, Integer id);

    UserDto getUserById(Integer id);

    List<UserDto> getAllUsers();

    void deleteUser(Integer id);

    User findByUsername(String username);

    UserDetails loadUserByUsername(String username);
}
