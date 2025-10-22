package org.edu.citas.Login;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ILoginRepo extends JpaRepository<LoginModel, Integer> {

    Optional<LoginModel> findByUsername(String username);
}
