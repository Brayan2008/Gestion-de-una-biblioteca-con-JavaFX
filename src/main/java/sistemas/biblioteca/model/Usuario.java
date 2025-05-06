package sistemas.biblioteca.model;

import java.util.List;

public class Usuario {
    
   private long id;
   private String email, nombre, password;
   private List<Roles> roles;

    public Usuario() {}

    public long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getNombre() {
        return nombre;
    }

    public String getPassword() {
        return password;
    }

    public List<Roles> getRoles() {
        return roles;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRoles(List<Roles> roles) {
        this.roles = roles;
    }

}



