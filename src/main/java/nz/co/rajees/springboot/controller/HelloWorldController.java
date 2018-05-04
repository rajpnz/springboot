package nz.co.rajees.springboot.controller;

import nz.co.rajees.springboot.dto.User;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class HelloWorldController {

    @GetMapping
    public String home(){
        return "Hello, Welcome to Raj's Spring Boot Application";
    }

    @PostMapping(path = "/users")
    public String createUser(@Validated @RequestBody User user) {
        return user.getName();
    }
}
