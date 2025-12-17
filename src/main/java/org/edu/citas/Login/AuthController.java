package org.edu.citas.Login;

//import com.example.security.jwt.JwtUtils;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

//@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

   /* @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );
        String token = jwtUtils.generateToken(request.getUsername());
        // Construir respuesta con username y token
        JwtResponse jwtResponse = new JwtResponse(request.getUsername(),token);
        JwtResponse jwtName = new JwtResponse(request.getUsername(),token);

        return ResponseEntity.ok(jwtResponse);
    }*/
   @PostMapping("/login")
   public ResponseEntity<?> login(@RequestBody LoginRequest request) {

       // Validación básica
       if (request.getUsername() == null || request.getPassword() == null) {
           return ResponseEntity
                   .badRequest()
                   .body("Usuario y contraseña son obligatorios");
       }

       try {
           Authentication authentication = authenticationManager.authenticate(
                   new UsernamePasswordAuthenticationToken(
                           request.getUsername(),
                           request.getPassword()
                   )
           );

           String token = jwtUtils.generateToken(authentication.getName());

           JwtResponse response = new JwtResponse(
                   authentication.getName(),
                   token
           );

           return ResponseEntity.ok(response);

       } catch (BadCredentialsException ex) {
           return ResponseEntity
                   .status(HttpStatus.UNAUTHORIZED)
                   .body(new LoginResponse(0, "usuario incorrecto"));

       }
   }

}



