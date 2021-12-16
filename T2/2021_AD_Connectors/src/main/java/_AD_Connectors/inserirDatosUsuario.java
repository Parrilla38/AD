/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package _AD_Connectors;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author joange
 */
public class inserirDatosUsuario {
    public static void main(String[] args) {
        try {
            Connection con=null;
            Statement st =null;
            String sentSQL="";
            
            // Ens connectem
            Class.forName("com.mysql.cj.jdbc.Driver");
            String connectionUrl = "jdbc:mysql://localhost:3308/BDJocs?"+
                    "useUnicode=true&characterEncoding=UTF-8&user=root&"+
                    "password=root&allowMultipleQueries=true";
            con = DriverManager.getConnection(connectionUrl);
            
            // Volem inserir  un nou joc però demanant-li al l'usuari
            // les dades a inserir
            
            /*
            
            sentSQL = "INSERT INTO Joc VALUES ("+
                    codi_joc +"," +
                    nom + ","+
                    desc + ","+
                    genere +");";
            
            
            st.executeUpdate(sentSQL);
            */
            
            
            int codi_joc=Leer.leerEntero("Dis-me el codi del joc: ");
            String nom=Leer.leerTexto("Dis-me el nom del Joc: ");
            String desc=Leer.leerTexto("Dis-me la descripció del Joc: ");
            int genere=Leer.leerEntero("Dis-me el genere del joc: ");
            
            
            
            String sentenciaPreparada="INSERT INTO Joc VALUES (?,?,?,?)";
            
                PreparedStatement pst=con.prepareStatement(sentenciaPreparada);
            
            pst.setInt(1, codi_joc);
            pst.setString(2, nom);
            pst.setString(3, desc);
            pst.setInt(4, genere);
            
            int res = pst.executeUpdate();
            
            System.out.println("Inserides " + res + " files.");
            
            
            
            
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(inserirDatosUsuario.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(inserirDatosUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }

}
