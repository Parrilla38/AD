/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author joange
 */


@Entity
@Table(name="Professor", catalog="provesHibernate")
public class Professor {
    
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="idProfe")
    private Long idProfe;
    
    
    
    @Column(name="nomProfe")
    private String nomProfe;
    
    @OneToOne(mappedBy="elTutor")
    private Grup elGrup;

    public Grup getElGrup() {
        return elGrup;
    }

    public void setElGrup(Grup elGrup) {
        this.elGrup = elGrup;
    }
    

    public Professor() {
    }

    public Professor(Long idProfe, String nomProfe) {
        this.idProfe = idProfe;
        this.nomProfe = nomProfe;
    }

    public Long getIdProfe() {
        return idProfe;
    }

    public void setIdProfe(Long idProfe) {
        this.idProfe = idProfe;
    }

    public String getNomProfe() {
        return nomProfe;
    }

    public void setNomProfe(String nomProfe) {
        this.nomProfe = nomProfe;
    }

    @Override
    public String toString() {
        return "Professor{" + "idProfe=" + idProfe + ", nomProfe=" + nomProfe +'}';
    }

   
    
}
