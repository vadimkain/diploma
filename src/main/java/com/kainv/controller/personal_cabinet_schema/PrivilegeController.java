package com.kainv.controller.personal_cabinet_schema;


import com.kainv.model.entities.personal_cabinet_schema.Privilege;
import com.kainv.model.repos.personal_cabinet_schema.PrivilegeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("privileges")
public class PrivilegeController {
    @Autowired
    private PrivilegeRepository privilegeRepository;

    @GetMapping("")
    public List<Privilege> privileges() {
        return privilegeRepository.findAll();
    }
}