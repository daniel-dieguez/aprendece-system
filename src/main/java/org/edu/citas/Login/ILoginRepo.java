package org.edu.citas.Login;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ILoginRepo extends JpaRepository<LoginModel, Integer> {


    @Query("""
        SELECT l
        FROM LoginModel l
        JOIN FETCH l.usuario u
        WHERE l.username = :username
    """)
Optional<LoginModel> findByUsernameWithUsuarioAndTipoUsuario(@Param("username") String username);


}
