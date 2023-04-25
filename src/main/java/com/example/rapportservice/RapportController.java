package com.example.rapportservice;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/rapport")
public class RapportController {
    private final RapportRepository rapportRepository;

    @GetMapping("/list")
    //once connected to apigateway it'll connect to auth and extract the role
    @PreAuthorize("hasAnyRole('SUPERVISEUR', 'DELEGUE')") //spring will automatically add a prefix ROLE_... ex: ROLE_ADMIN
    public List<Rapport> adminEndpoint(){
        return rapportRepository.findAll();
    }

    @PostMapping("/new")
    @PreAuthorize("hasRole('DELEGUE')") //spring will automatically add a prefix ROLE_... ex: ROLE_ADMIN
    public ResponseEntity<String> saveDate(@RequestBody Rapport rapport) {
        try {
            rapportRepository.save(rapport);
            return ResponseEntity.ok("rapport saved successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error saving rapport");
        }
    }
}
