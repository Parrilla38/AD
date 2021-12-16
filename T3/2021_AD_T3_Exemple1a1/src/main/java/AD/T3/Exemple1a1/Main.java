/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package AD.T3.Exemple1a1;

import Model.Grup;
import Model.Professor;
import org.hibernate.Session;

/**
 *
 * @author joange
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        mostrarGrupo(1L);
        
        mostrarProfessor(1L);
        
        guardarGrup();
    }
    
    
    public static void mostrarGrupo(Long idGrupo){
        Session sessio=HibernateUtil.getSessionFactory().getCurrentSession();
        sessio.beginTransaction();
        
        
        // TO - DO
        Grup g=sessio.get(Grup.class, idGrupo);
        System.out.println(g);
        
        sessio.getTransaction().commit();
    }
    
    
    
    public static void mostrarProfessor(Long idProfe){
      Session sessio=HibernateUtil.getSessionFactory().getCurrentSession();
      sessio.beginTransaction();

      Professor p=sessio.get(Professor.class, idProfe);
      System.out.println(p);
      System.out.println(p.getElGrup());

      sessio.getTransaction().commit();
    }
    
    
    public static void guardarGrup(){
        
        Grup g= new Grup();
        
        g.setCurso(2);
        g.setNivel("BTX");
        
        Professor p = new Professor();
        p.setNomProfe("Maria");
        
        g.setElTutor(p);
           
        Session laSessio=HibernateUtil.getSessionFactory().getCurrentSession();
        laSessio.beginTransaction();
        
        laSessio.save(g);
        
        laSessio.getTransaction().commit();
        
    
    }
}
