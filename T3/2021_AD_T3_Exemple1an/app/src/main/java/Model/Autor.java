/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author joange
 */

@Entity
@Table(name="Autor")
public class Autor {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long idAutor;
    
    
    @Column
    private String nom;
    
    @Column
    private String nacionalitat;
    
    
    @OneToMany(mappedBy="elAutor",cascade=CascadeType.ALL)
    private Set<Libro> elsLlibres;
    

    public Long getIdAutor() {
        return idAutor;
    }

    public void setIdAutor(Long idAutor) {
        this.idAutor = idAutor;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNacionalitat() {
        return nacionalitat;
    }

    public void setNacionalitat(String nacionalitat) {
        this.nacionalitat = nacionalitat;
    }

    public Autor(String nom, String nacionalitat) {
        this.nom = nom;
        this.nacionalitat = nacionalitat;
        this.elsLlibres=new HashSet<>();
    }

    public Autor() {
        this.elsLlibres=new HashSet<>();
    }

    public Set<Libro> getElsLlibres() {
        
        return elsLlibres;
    }

    public void setElsLlibres(Set<Libro> elsLlibres) {
        this.elsLlibres = elsLlibres;
    }
    

    public void addLlibre(Libro l){
        
        this.elsLlibres.add(l);
    }
    
    
    
    
}
