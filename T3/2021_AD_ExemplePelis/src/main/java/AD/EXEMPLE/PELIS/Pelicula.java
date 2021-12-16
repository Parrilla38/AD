/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AD.EXEMPLE.PELIS;

/**
 *
 * @author joange
 */
public class Pelicula {
    
    // indicar ací a quina columna de la BBDD fa referencia
    private Long idPeli;
    private String titol;
    private int any;
    private String Director;

    public Long getIdPeli() {
        return idPeli;
    }

    public String getTitol() {
        return titol;
    }

    public int getAny() {
        return any;
    }

    public String getDirector() {
        return Director;
    }

    public void setIdPeli(Long idPeli) {
        this.idPeli = idPeli;
    }

    public void setTitol(String titol) {
        this.titol = titol;
    }

    public void setAny(int any) {
        this.any = any;
    }

    public void setDirector(String director) {
        this.Director = director;
    }

    public Pelicula(Long idPeli, String titol, int any, String director) {
        this.idPeli = idPeli;
        this.titol = titol;
        this.any = any;
        this.Director = director;
    }

    public Pelicula() {
    }

    @Override
    public String toString() {
        return "Pelicula{" + "idPeli=" + idPeli + ", titol=" + titol + ", any=" + any + ", director=" + Director + '}';
    }
    
    
}
