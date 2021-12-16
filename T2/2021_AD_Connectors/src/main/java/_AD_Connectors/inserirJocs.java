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
public class inserirJocs {
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
            
            
            st = con.createStatement();
            
            sentSQL = "INSERT INTO Joc VALUES (1, 'Double Dragon',"+
                    "'Dos germans bessons experts en arts marcials s`han de fer"+
                    "camí en un escenari urbà on membres de bandes rivals"+
                    "volen deixar-los fora de combat.', 1)";
                      
            st.executeUpdate(sentSQL);
            
            sentSQL = "INSERT INTO Joc VALUES (2, 'Tetris', 'Tetris és"+
            "un videojoc de tipus trencaclosques inventat per l`"+
            "enginyer informàtic rus Aleksei Pàjitnov l`any 1985" +
            "mentre treballava a l`Académia de Ciéncies de Moscou',4);";
            st.executeUpdate(sentSQL);
            
            
            
            
            
            
            
            
            
            
            
            
            con.close();
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(inserirJocs.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(inserirJocs.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
