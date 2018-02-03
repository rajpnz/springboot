package nz.co.rajees.springboot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Use this to test JWT stuff
 * Reference <a href="https://auth0.com/blog/securing-spring-boot-with-jwts/">Spring Boot JWT Tutorial</a>
 * May also need to look at the nimbus implementation
 */
@RestController
@RequestMapping("/secure")
public class SecureController {

    @GetMapping
    public String secretStuff(){
        return "You have landed in a secret area.";
    }
}
