package com.example.rapportservice.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/rapport/public")
public class publicRapportController {
    @GetMapping("/")
    public String publicEndpoint(){
        return "<h1>Hello from the public endpoint</h1>";
    }
}