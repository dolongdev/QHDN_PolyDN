package com.qhdn.poly.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDto {
    private int id;
    @NotEmpty
    @Size(min = 4, message = "Tên tài khoản ít nhất 4 kí tự!!")
    private String name;
    @NotEmpty(message = "Mật khẩu không được để trống")
    @Size(min = 3, max = 10, message = "Mật khẩu phải từ 3 đến 10 kí tự!!")
    private String password;
    @Email(message = "Email không đúng!!")
    private String email;
    @NotEmpty
    private String about;
}
