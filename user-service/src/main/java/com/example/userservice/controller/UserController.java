package com.example.userservice.controller;

import com.example.userservice.dto.UserDto;
import com.example.userservice.service.UserService;
import com.example.userservice.vo.Greeting;
import com.example.userservice.vo.RequestUser;
import com.example.userservice.vo.ResponseUser;
import com.netflix.discovery.converters.Auto;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class UserController {

    private Environment env;
    private Greeting greeting;
    private UserService userService;
    private ModelMapper modelMapper;

    @Autowired
    public UserController(Environment env, Greeting greeting, UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.env = env;
        this.greeting = greeting;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/health_check")
    public String status() {

        return "It's Working";
    }

    @GetMapping("/welcome")
    public String welcome() {


//        return env.getProperty("greeting.message");
        return greeting.getMessage();
    }


    @PostMapping("/users")
    public ResponseEntity<ResponseUser> createUser(@RequestBody RequestUser user) {

        UserDto userDto = modelMapper.map(user, UserDto.class);

        ResponseUser responseUser = modelMapper.map(userService.createUser(userDto), ResponseUser.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseUser);
    }
}
