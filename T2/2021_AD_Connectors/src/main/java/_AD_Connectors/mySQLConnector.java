/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package _AD_Connectors;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author joange
 */
public class mySQLConnector {
    
    public void provarConnexio(){
        try {
            
            
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            String connectionUrl = "jdbc:mysql://localhost:3308/BDJocs"+
                    "?useUnicode=true&characterEncoding=UTF-8"+
                    "&user=root"+
                    "&password=root";
            
            
            Connection conn = DriverManager.getConnection(connectionUrl);
            
            System.out.println("Ens hem connectat");
            
            ResultSet rs = conn.prepareStatement("show tables").executeQuery();
            
            System.out.println("Taules de la BBDD Jocs");
            
            while (rs.next()){
                String nomTaula=rs.getString(1);    // la primera columna és la 1 i no la 0
                System.out.println("\t"+nomTaula);
            }
            
            
            conn.close();
            
            
            
            
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(mySQLConnector.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(mySQLConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
