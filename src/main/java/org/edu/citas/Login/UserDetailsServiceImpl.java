package org.edu.citas.Login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {


    @Autowired
    private ILoginRepo loginRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LoginModel login = loginRepo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));

        return User.builder()
                .username(login.getUsername())
                .password(login.getPasswordHash())
                .roles("USER") // o el rol que necesites
                .build();
    }
}
