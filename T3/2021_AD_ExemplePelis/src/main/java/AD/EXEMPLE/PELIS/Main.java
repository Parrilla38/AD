/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package AD.EXEMPLE.PELIS;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Session;

/**
 *
 * @author joange
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    private static Conexio laconnexio;

    public static void main(String[] args) {
        
        
        //laconnexio = new Conexio();
        //cargaryMostrarPeli(); 
        //leerYGuardarPeli();
        
        Pelicula p=cargarPeli(Long.valueOf(8));
        
        System.out.println("========================================");
        System.out.println(p);
        
        
        

    }

    public static void cargaryMostrarPeli() {
        try {
            Connection con = laconnexio.getConexio();

            String sql = "Select * from Pelis where idPelis=8";

            Statement st = con.createStatement();

            ResultSet rst = st.executeQuery(sql);

            Pelicula p = null;
            if (rst.next()) {
                p = new Pelicula(
                        rst.getLong("idPelis"),
                        rst.getString("Titol"),
                        rst.getInt("Any"),
                        rst.getString("Director"));
            }

            if (p != null) {
                System.out.println(p);
            }
            
            rst.close();
                    
                   

        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    /**
     * 
     * @param id ID de la pelicula
     * @return Una Pelicula
     */
    public static Pelicula cargarPeli(Long id){
        Session laSessio=HibernateUtil.getSessionFactory().getCurrentSession();
        
        laSessio.beginTransaction();
        
        Pelicula p= laSessio.get(Pelicula.class,id);

        laSessio.getTransaction().commit();
        
        return p;
    }
    
    
    
    
    
    
    
    public static void leerYGuardarPeli(){
        
        try {
            // Crear una Pelicula
            
            Pelicula p= new Pelicula(
                    Long.valueOf(Leer.leerEntero("Dime el código: ")),
                    Leer.leerTexto("Dime el título: "),
                    Leer.leerEntero("Dime el año: "),
                    Leer.leerTexto("Dime el director: "));
            
            Connection con = laconnexio.getConexio();
            
            String sql="insert into Pelis (Any,Titol,Director) values (?,?,?)";
            
            PreparedStatement pst=con.prepareStatement(sql);
            
            pst.setInt(1, p.getAny());
            pst.setString(2, p.getTitol());
            pst.setString(3, p.getDirector());
            
            int res=pst.executeUpdate();
            
            if (res==1){
                System.out.println("Inserció correcdta");
            }
            else{
                System.out.println("Error inserint");
            }
            
            pst.close();
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
    }
}
