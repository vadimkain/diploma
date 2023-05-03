package com.kainv.controller.personal_cabinet_schema;

import com.kainv.model.entities.personal_cabinet_schema.Role;
import com.kainv.model.repos.personal_cabinet_schema.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("roles")
public class RoleController {
    @Autowired
    private RoleRepository roleRepository;

    @GetMapping("")
    public List<Role> roles() {
        return roleRepository.findAll();
    }
}
