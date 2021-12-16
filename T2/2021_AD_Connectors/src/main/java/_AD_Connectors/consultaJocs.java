/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package _AD_Connectors;

import com.mysql.cj.protocol.Resultset;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author joange
 */
public class consultaJocs {
    public static final String limit=" LIMIT 10";
    public static void main(String[] args) {

        try {
            Connection con=null;
            Statement st =null;
            String sentSQL="";
            
            // Ens connectem
            Class.forName("com.mysql.cj.jdbc.Driver");
            String connectionUrl = "jdbc:mysql://localhost:3308/BDJocs?"+
                    "useUnicode=true&characterEncoding=UTF-8&user=root&"+
                    "password=root";
            con = DriverManager.getConnection(connectionUrl);
            
            sentSQL="Select * from Joc"+limit;
            
            st=con.createStatement();
            
            ResultSet rs=st.executeQuery(sentSQL);
            
            
            while(rs.next()){
                System.out.print("Joc: " + rs.getString("nom")+"\t");
                System.out.println("Genere: " + rs.getInt("Genere_id"));
            }
            
            
            rs.close();
            con.close();
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(consultaJocs.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(consultaJocs.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
