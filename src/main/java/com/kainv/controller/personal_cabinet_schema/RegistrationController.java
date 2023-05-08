package com.kainv.controller.personal_cabinet_schema;

import com.kainv.model.services.personal_cabinet_schema.DirectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("registration")
public class RegistrationController {
    @Autowired
    DirectorService directorService;

    @GetMapping("")
    public String hello() {
        return "Hello. This is registration page";
    }
}
