package org.edu.citas.Login;

public interface ILoginDto {

    Integer getIdUsuario();
    String getUserName();
    String getPasswordHash();
    String getNombre();
    Integer getTipoUsuario();
    String getNameTipo();
}
