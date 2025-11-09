package org.edu.citas.Login;

//import com.example.security.jwt.JwtUtils;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

//        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtUtils.generateToken(request.getUsername());
//        String token = jwtUtils.generateToken(request.getName());

        // Construir respuesta con username y token
        JwtResponse jwtResponse = new JwtResponse(request.getUsername(),token);
        JwtResponse jwtName = new JwtResponse(request.getUsername(),token);



        return ResponseEntity.ok(jwtResponse);
    }
}



