package com.qhdn.poly.repository;

import com.qhdn.poly.dto.UserDto;
import com.qhdn.poly.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String username);
}
