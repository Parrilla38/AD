/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package _AD_Connectors;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author joange
 */
public class ConsultaMetaDades {
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
             
             DatabaseMetaData dbmd = con.getMetaData();
             
             
             System.out.println(dbmd.getDriverName());
             System.out.println(dbmd.getDatabaseProductName());
             System.out.println(dbmd.getURL());
             System.out.println(dbmd.getUserName());
             
             ResultSet rsmd = dbmd.getTables("BDJocs", null, null, null);
            
             while (rsmd.next()) {
                System.out.println(String.format("%-15s %-15s %-15s",
                    rsmd.getString(1), 
                    rsmd.getString(3),
                    rsmd.getString(4)));
               }
             
             rsmd.close();
            
             
             /*
             rsmd=dbmd.getColumns("BDJocs", null, "Genere", null);
             
             System.out.println("Informació de la taula GENERE");
             
            
             while (rsmd.next()) {
                System.out.println(String.format("%-15s %-15s %-15s %-15s",
                    rsmd.getString(3),
                    rsmd.getString(4),
                    rsmd.getString(6),
                    rsmd.getInt(11)
                ));
               }
             
             rsmd.close();
             */
             
             
            ResultSet rspk = dbmd.getPrimaryKeys("BDJocs", null, "Joc");
            ArrayList<String> pks = new ArrayList<String>();
            
            while (rspk.next())
                pks.add(rspk.getString(4));
            
            rspk.close(); // Tanquem el resultset
            
            
             
            ResultSet rsfk = dbmd.getImportedKeys("BDJocs", null, "Joc");
            ArrayList<String> fks = new ArrayList<String>();
            ArrayList<String> fksExt = new ArrayList<String>();
            while (rsfk.next()){
                fks.add(rsfk.getString(8)); // Guarda el camp de la Clau Externa
                fksExt.add(rsfk.getString(3)); // Guarda la taula a que fa referéncia
            }
             
            
            
            ResultSet columnes = dbmd.getColumns("BDJocs",null , "Joc", null);
            
            while (columnes.next()){
                String  columnName=columnes.getString(4);

                if (pks.contains(columnName))
                    columnName+="(PK)";
                
                if (fks.contains(columnName))
                    columnName+= "(FK) -->"+fksExt.get(fks.indexOf(columnName));
                
             
                String tipus=columnes.getString(6);
                String nullable=columnes.getString(18);
                System.out.println(String.format("%-25s %-15s %15s",
                        columnName,
                        tipus,
                        nullable));
            }
         
             
         } catch (ClassNotFoundException ex) {
             Logger.getLogger(ConsultaMetaDades.class.getName()).log(Level.SEVERE, null, ex);
         } catch (SQLException ex) {
             Logger.getLogger(ConsultaMetaDades.class.getName()).log(Level.SEVERE, null, ex);
         }
     }
        
}
