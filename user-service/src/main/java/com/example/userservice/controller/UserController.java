package com.example.userservice.controller;

import com.example.userservice.dto.UserDto;
import com.example.userservice.entity.UserEntity;
import com.example.userservice.service.UserService;
import com.example.userservice.vo.Greeting;
import com.example.userservice.vo.RequestUser;
import com.example.userservice.vo.ResponseUser;
import com.netflix.discovery.converters.Auto;
import jakarta.ws.rs.Path;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
//@RequestMapping("/user-service")
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

        @GetMapping("/health-check")
    public String status() {

        return String.format("It's Working in User Service"
        + ", port(local.server.port=" + env.getProperty("local.server.port")
        + ", port(server.port= " + env.getProperty("server.port")
        + ", token secret=" + env.getProperty("token.secret")
        + ", token expiration time= " + env.getProperty("token.expiration_time"));
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

    @GetMapping("/users")
    public ResponseEntity<List<ResponseUser>> getUsers() {
        Iterable<UserEntity> userByAll = userService.getUserByAll();
        List<ResponseUser> result = new ArrayList<>();

        userByAll.forEach(v -> {
            result.add(modelMapper.map(v, ResponseUser.class));
        });

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<ResponseUser> getUser(@PathVariable("userId") String userId) {

        UserDto userByUserId = userService.getUserByUserId(userId);
        ResponseUser result = modelMapper.map(userByUserId, ResponseUser.class);
        return ResponseEntity.ok(result);
    }

}
