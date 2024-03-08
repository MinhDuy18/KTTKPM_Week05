package vn.edu.iuh.fit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.iuh.fit.entites.Candidate;
import vn.edu.iuh.fit.entites.JwtRequest;
import vn.edu.iuh.fit.entites.JwtResponse;
import vn.edu.iuh.fit.jwt.JwtUtil;
import vn.edu.iuh.fit.service.CandidateService;

@RestController
public class JwtController {
    @Autowired
    private JwtUtil jwtTokenUtil;

    @Autowired
    private CandidateService candidateService;

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
        try {
//            Candidate candidate = candidateService.findByUsername(authenticationRequest.getUsername());
            Candidate candidate = candidateService.findByName(authenticationRequest.getUsername());
            if (candidate == null || !candidate.getEmail().equals(authenticationRequest.getPassword())) {
                throw new Exception("Incorrect username or password");
            }
            final String jwt = jwtTokenUtil.generateToken(candidate.getName());
            return ResponseEntity.ok(new JwtResponse(jwt));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @GetMapping("/user")
    public ResponseEntity<String> getUserInfo(@RequestHeader("Authorization") String authorizationHeader) {
        String jwt = authorizationHeader.substring(7);
        String username = jwtTokenUtil.extractUsername(jwt);
        return ResponseEntity.ok("Authenticated user: " + username);
    }


}
