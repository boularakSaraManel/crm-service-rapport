package com.example.rapportservice;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/rapport/secured")
public class securedRapportController {
    @GetMapping("/")
    public String securedEndpoint(){
        return "<h1>Hello from the secured endpoint</h1>";
    }
    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')") //spring will automatically add a prefix ROLE_... ex: ROLE_ADMIN
    public String adminEndpoint(){
        return "<h1>Hello from the admin endpoint</h1>";
    }
    @GetMapping("/delegue")
    @PreAuthorize("hasRole('DELEGUE')")
    public String delegueEndpoint(){
        return "<h1>Hello from the delegue endpoint</h1>";
    }
    @GetMapping("/admin-delegue")
    @PreAuthorize("hasAnyRole('ADMIN', 'DELEGUE')")
    public String adminDelegueEndpoint(){
        return "<h1>Hello from the superviseur-delegue endpoint</h1>";
    }
}
