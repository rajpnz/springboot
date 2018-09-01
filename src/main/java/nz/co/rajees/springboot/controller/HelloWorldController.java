package nz.co.rajees.springboot.controller;

import nz.co.rajees.springboot.dto.User;
import nz.co.rajees.springboot.features.FeatureNotEnabledException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.togglz.core.manager.FeatureManager;

import static nz.co.rajees.springboot.features.AppFeature.HOME_FEATURE;

@RestController
@RequestMapping("/")
public class HelloWorldController {

    private final FeatureManager featureManager;

    public HelloWorldController(FeatureManager featureManager) {
        this.featureManager = featureManager;
    }

    @GetMapping
    public String home(){
        if(HOME_FEATURE.isActive()) {
            return "Hello, Welcome to Raj's Spring Boot Application";
        } else {
            throw new FeatureNotEnabledException();
        }
    }

    @PostMapping(path = "/users")
    public String createUser(@Validated @RequestBody User user) {
        return user.getName() + " " + user.getBirthdayAsStringAsDate();
    }
}
