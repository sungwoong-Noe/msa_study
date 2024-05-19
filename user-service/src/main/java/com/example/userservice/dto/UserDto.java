package com.example.userservice.dto;

import com.example.userservice.vo.ResponseOrder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class UserDto {
    private String email;
    private String name;
    private String pwd;
    private String userId;
    private LocalDateTime createAt;

    private String encryptedPwd;

    private List<ResponseOrder> orders;


}
