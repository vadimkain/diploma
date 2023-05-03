package com.kainv.controller.personal_cabinet_schema;

import com.kainv.model.entities.personal_cabinet_schema.User.User;
import com.kainv.model.repos.personal_cabinet_schema.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("")
    public List<User> usersList() {
        return userRepository.findAll();
    }
}
