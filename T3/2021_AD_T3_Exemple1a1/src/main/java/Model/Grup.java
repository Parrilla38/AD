/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author joange
 */

@Entity
@Table(name="Grupo", catalog="provesHibernate")
public class Grup {
 
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="idGrupo")
    private Long idGrupo;
    
    @Column(name="nivel")
    private String nivel;
    
    @Column(name="curso")
    private int curso;
    
    
    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="tutor",referencedColumnName="idProfe")
    private Professor elTutor;

    public Grup() {
    }

    public Long getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(Long idGrupo) {
        this.idGrupo = idGrupo;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public int getCurso() {
        return curso;
    }

    public void setCurso(int curso) {
        this.curso = curso;
    }

    public Professor getElTutor() {
        return elTutor;
    }

    public void setElTutor(Professor elTutor) {
        this.elTutor = elTutor;
    }

    @Override
    public String toString() {
        return "Grup{" + "idGrupo=" + idGrupo + ", nivel=" + nivel + ", curso=" + curso + ", elTutor=" + elTutor + '}';
    }
    
    
    
}
