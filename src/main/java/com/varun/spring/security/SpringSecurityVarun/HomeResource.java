package com.varun.spring.security.SpringSecurityVarun;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeResource {
    
    @GetMapping("/")
    public String home()
    {
        return ("<h1>Welcome varun jaiswal  page</h1>");
    }
}
