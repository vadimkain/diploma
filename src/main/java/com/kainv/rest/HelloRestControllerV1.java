package com.kainv.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

import static com.kainv.util.RequestMappingPathV1.HELLO;

@Slf4j
@RestController
@RequestMapping(HELLO)
public class HelloRestControllerV1 {
    @GetMapping
    public String sayHello(Principal principal) {
        var result = "";
        if (principal != null) {
            result = "Hello, " + principal.getName();
        } else {
            result = "Hello, my friend!";
        }
        log.info(result);
        return result;
    }
}
