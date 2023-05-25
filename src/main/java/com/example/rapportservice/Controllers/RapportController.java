package com.example.rapportservice.Controllers;

import com.example.rapportservice.Entity.Rapport;
import com.example.rapportservice.Repository.RapportRepository;
import com.example.rapportservice.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@RestController
@RequestMapping("/rapport")
public class RapportController {
    private final RapportRepository rapportRepository;
    //private final UserResponseDTO userResponseDTO;

    //private final JwtUtils jwtUtils;


    @GetMapping("/")
    public String greetings(){
        return "hello from rapport microservice";
    }

    @GetMapping("/list")
    //once connected to apigateway it'll connect to auth and extract the role
    //@PreAuthorize("hasAnyRole('SUPERVISEUR', 'DELEGUE')") //spring will automatically add a prefix ROLE_... ex: ROLE_ADMIN
    public List<Rapport> getRapportList(){
        //get jwt mn header w extract it
        //email extracted
        //
        List<Rapport> rapportList= new ArrayList<Rapport>();
        rapportRepository.findAll().forEach(rapport -> rapportList.add(rapport));
        return rapportList;
    }

    @PostMapping("/new")
    public ResponseEntity<String> saveRapport(@RequestParam String title, @RequestParam String content, @RequestParam Long id_delegue) {
        try {
            Optional<Rapport> existingRapport = rapportRepository.findByTitle(title);
            if (existingRapport.isPresent()) {
                return ResponseEntity.badRequest().body("A rapport with this title already exists");
            } else {
                Rapport rapport = new Rapport();
                rapport.setTitle(title);
                rapport.setContent(content);
                rapport.setId_delegue(id_delegue);
                rapportRepository.save(rapport);
                return ResponseEntity.ok("rapport saved successfully");
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error saving rapport");
        }
    }




    /*@GetMapping("/Users/list")
    public Object getUsers(){
        String uri = "http://localhost:8080/api/v1/auth/users/list";
        RestTemplate template = new RestTemplate();
        Object[] objects= new Object[]{template.getForObject(uri,Object.class)};
        return Arrays.asList(objects);
    }*/



    //write a login of using the token's id as the path variable and fix userDTO
   /* @GetMapping("/Users/{id}")
    public UserDTO getUserById(@PathVariable Long id){
        String uri = "http://localhost:8080/api/v1/auth/users/{id}";
        RestTemplate template = new RestTemplate();
        return template.getForObject(uri, UserDTO.class, id);
    }*/


    /*public void jwtDecoder() {
        // Get the current authentication object from the security context
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Extract the JWT token from the authentication object's details
        String jwtToken = (String) authentication.getDetails();

        // Parse the JWT and extract the claims
        Claims claims = Jwts.parser().setSigningKey("357538782F413F4428472B4B6250655367566B59703373367639792442264529").parseClaimsJws(jwtToken).getBody();

        // Extract the individual claims as needed
        String username = claims.get("username", String.class);
        List<String> roles = claims.get("roles", List.class);
        // ...
        }
    }*/



}
