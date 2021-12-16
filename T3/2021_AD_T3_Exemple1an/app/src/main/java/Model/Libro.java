/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author joange
 */

@Entity
@Table(name="Libro")
public class Libro implements Serializable {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long idLibro;
    
    @Column
    private String titol;
    
    @Column
    private String tipus;

    
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="idAutor")
    private Autor elAutor;
    
    
    public Long getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(Long idLibro) {
        this.idLibro = idLibro;
    }

    public String getTitol() {
        return titol;
    }

    public void setTitol(String titol) {
        this.titol = titol;
    }

    public String getTipus() {
        return tipus;
    }

    public void setTipus(String tipus) {
        this.tipus = tipus;
    }

    public Libro(String titol, String tipus) {
        this.titol = titol;
        this.tipus = tipus;
    }

    public Libro(String titol, String tipus, Autor elAutor) {
        this.titol = titol;
        this.tipus = tipus;
        this.elAutor = elAutor;
    }
    
    
    public Libro() {
  
    }

    public Autor getElAutor() {
        return elAutor;
    }

    public void setElAutor(Autor elAutor) {
        this.elAutor = elAutor;
    }
    
    
}
