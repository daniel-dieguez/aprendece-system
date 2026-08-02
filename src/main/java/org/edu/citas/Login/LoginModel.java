package org.edu.citas.Login;


import jakarta.persistence.*;
import lombok.Data;
import org.edu.citas.Models.PersonaModel;
import org.edu.citas.Models.TipoUsuarioModel;

@Entity
@Table(name = "Login", schema = "adm")
@Data
public class LoginModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "username")
    private String username;

    @Column(name = "passwordHash")
    private String passwordHash;

//    @Column(name = "nombre")
//    private String nombre;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usuarioId")
    private PersonaModel usuario;


//
//    @ManyToOne(fetch = FetchType.LAZY) // o EAGER si quieres cargar automáticamente
//    @JoinColumn(name = "tipoUsuarioId")
//    private TipoUsarioModel tipoUsarioModel;



    public LoginModel(Integer id, String username, String passwordHash) {
        this.id = id;
        this.username = username;
        this.passwordHash = passwordHash;
    }

    public LoginModel() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }





}