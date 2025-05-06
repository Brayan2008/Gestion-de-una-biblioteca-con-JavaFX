package sistemas.biblioteca.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Libros {
    
    private String nombre_libro;
    private String image_path;    
    private int codigo_libro;
    private long isb;
    private String nombre_editorial;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fecha;
    
    
    public Libros() {
    }

    public String getImage_path() {
        return image_path;
    }

    public void setImage_path(String image_path) {
        this.image_path = image_path;
    }

    public String getNombre_libro() {
        return nombre_libro;
    }

    public int getCodigo_libro() {
        return codigo_libro;
    }

    public long getIsb() {
        return isb;
    }
    
    public LocalDate getFecha() {
        return fecha;
    }

    public void setNombre_libro(String nombre_libro) {
        this.nombre_libro = nombre_libro;
    }

    public void setCodigo_libro(int codigo_libro) {
        this.codigo_libro = codigo_libro;
    }

    public void setIsb(long isb) {
        this.isb = isb;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getNombre_editorial() {
        return nombre_editorial;
    }

    public void setNombre_editorial(String nombre_editorial) {
        this.nombre_editorial = nombre_editorial;
    }

}
