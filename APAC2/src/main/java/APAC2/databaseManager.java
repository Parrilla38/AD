/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package APAC2;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 34608
 */
public class databaseManager {
    
    
    String server;
    String port;
    String user;
    String pass;
    String dbname;

    public databaseManager(String server, String port, String user, String pass, String dbname) {
        this.server = server;
        this.port = port;
        this.user = user;
        this.pass = pass;
        this.dbname = dbname;
    }
    
    public Connection connectDatabase()
    {
        
        Connection conn = null;
        
        try {
  
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            String connectionUrl = "jdbc:mysql://" + this.server + ":" + this.port + "/" + this.dbname +
                    "?useUnicode=true&characterEncoding=UTF-8"+
                    "&user=" + this.user +
                    "&password=" + this.pass;
            
            conn = DriverManager.getConnection(connectionUrl);
            
        } catch (ClassNotFoundException ex) 
        {
            Logger.getLogger(connectionManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(connectionManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return conn;
        
        
    }
    
    public void showTables()
    {
        
        try {
            
            // Informacion de las Tablas de la Base de datos
            ResultSet rs = connectDatabase().prepareStatement("show tables").executeQuery();
            
            System.out.println("\nTaules de la base de dades:\n");
            System.out.println("-------------------------------");
                
            while(rs.next())
            {

                String s = rs.getString(1);
                System.out.println(s);

            }

            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(connectionManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void insertIntoTable(String table)
    {
        
        try {
            
            ArrayList l_camps = new ArrayList();
            ArrayList l_tipus = new ArrayList();
            ArrayList holders = new ArrayList();
            ArrayList nulls = new ArrayList();
            ArrayList autoincrementable = new ArrayList();
            
            
            DatabaseMetaData dbmd = connectDatabase().getMetaData();
            ResultSet columnes = dbmd.getColumns(this.dbname ,null , table, null);
            
            
            
            while(columnes.next())
            {
                
                l_camps.add(columnes.getString(4));
                l_tipus.add(columnes.getString(6));
                nulls.add(columnes.getString(18));
                autoincrementable.add(columnes.getString(23));
                
                
            }
            
            
            String place_holders = "";
            String campos = "";
            
            for (int i = 0; i < l_camps.size(); i++)
            {
                
                holders.add("?");
                
            }
            for (Object elemento: holders) 
            {
                place_holders += elemento + ", ";
            }
            for (Object elemento: l_camps) 
            {
                campos += elemento + ", ";
            }
            
            String sql = "INSERT INTO " + table + " (" + limpia(campos) + ")" + " VALUES " + "(" + limpia(place_holders) + ")";
            
            PreparedStatement pst = connectDatabase().prepareStatement(sql);
            
            for (int i = 1; i <= l_camps.size(); i++)
            {
                
                if (!"NULL".equals(nulls.get(i - 1)))
                {

                        if ("YES".equals(autoincrementable.get(i - 1)))
                        {

                            System.out.println("El campo " + l_camps.get(i - 1) + " es autoincrementable.");

                        }
                        else
                        {


                            if ("INT".equals(l_tipus.get(i - 1)))
                            {

                                String valor = Leer.leerTexto("Introduce un valor de tipo " + l_tipus.get(i - 1) + " para el campo " + l_camps.get(i - 1) + " :");
                                System.out.println("");
                                System.out.println("----------");
                                pst.setInt(i, Integer.parseInt(valor));

                            }

                            if("DATE".equals(l_tipus.get(i - 1)))
                            {

                                String fecha = Leer.leerTexto("Introduce un valor de tipo " + l_tipus.get(i - 1) + " para el campo " + l_camps.get(i - 1)+ " :");
                                System.out.println("");
                                System.out.println("----------");
                                pst.setDate(i, java.sql.Date.valueOf(fecha));

                            }

                            else
                            {

                                String valor2 = Leer.leerTexto("Introduce un valor de tipo " + l_tipus.get(i - 1) + " para el campo " + l_camps.get(i - 1)+ " :");
                                System.out.println("");
                                System.out.println("----------");
                                pst.setString(i, valor2);

                            }



                        }

                }
                else
                {
                    
                    if ("YES".equals(autoincrementable.get(i - 1)))
                            {

                                System.out.println("El campo " + l_camps.get(i - 1) + " es autoincrementable.");

                            }
                    else
                    {
                        
                        String respuesta = Leer.leerTexto("Deseas insertar algo en la columna " + l_camps.get(i - 1) + " de tipo " + l_tipus.get(i - 1) + "?" + "\nEscribe s|n: ");
                    while(respuesta != "s" || respuesta != "n")
                    {

                       respuesta = Leer.leerTexto("Deseas insertar algo en la columna " + l_camps.get(i - 1) + " de tipo " + l_tipus.get(i - 1) + "?" + "\nEscribe s|n: "); 


                    if ("s".equals(respuesta))
                        {

                            if ("YES".equals(autoincrementable.get(i - 1)))
                            {

                                System.out.println("El campo " + l_camps.get(i - 1) + " es autoincrementable.");

                            }
                            else
                            {


                                if ("INT".equals(l_tipus.get(i - 1)))
                                {

                                    String valor = Leer.leerTexto("Introduce un valor de tipo " + l_tipus.get(i - 1) + " para el campo " + l_camps.get(i - 1) + " :");
                                    System.out.println("");
                                    System.out.println("----------");
                                    pst.setInt(i, Integer.parseInt(valor));

                                }

                                if("DATE".equals(l_tipus.get(i - 1)))
                                {

                                    String fecha = Leer.leerTexto("Introduce un valor de tipo " + l_tipus.get(i - 1) + " para el campo " + l_camps.get(i - 1)+ " :");
                                    System.out.println("");
                                    System.out.println("----------");
                                    pst.setDate(i, java.sql.Date.valueOf(fecha));

                                }

                                else
                                {

                                    String valor2 = Leer.leerTexto("Introduce un valor de tipo " + l_tipus.get(i - 1) + " para el campo " + l_camps.get(i - 1)+ " :");
                                    System.out.println("");
                                    System.out.println("----------");
                                    pst.setString(i, valor2);

                                }



                            }

                        }
                        else if ("n".equals(respuesta))
                        {


                            if ("INT".equals(l_tipus.get(i - 1)))
                                {

                                    pst.setInt(i, Integer.parseInt(""));

                                }

                            else if ("DATE".equals(l_tipus.get(i - 1)))
                            {

                                pst.setDate(i, java.sql.Date.valueOf(""));

                            }

                            else
                            {

                                pst.setString(i, "");

                            }

                        }

                    }
                        
                    }
                    
                    
            }        
        }
                
            

            pst.executeUpdate();
            
            
            
            
            

            
        } catch (SQLException ex) {
            Logger.getLogger(databaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    public void showDescTable(String table)
    {
        
        
        try {
            
            
            String query = "desc " + table + ";";
            Statement stmnt = (Statement) connectDatabase().createStatement();
            ResultSet rs = stmnt.executeQuery(query);
            System.out.println("Descripción de la Tabla " + table);
            System.out.println("---------------------------------");
            
            while(rs.next()) 
            {
                
                String field = rs.getString("Field");
                System.out.println("Field: " + field);
                String type = rs.getString("type");
                System.out.println("Type: " + type);
                String nullinfo = rs.getString("null");
                System.out.println("Null: " + nullinfo);
                String key = rs.getString("key");
                System.out.println("Key:" + key);
                String extra = rs.getString("extra");
                System.out.println("Extra: " + extra);
                System.out.println("");
                System.out.println("----------");
                System.out.println("");
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(databaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void executeSelect(String query)
    {
        
        PreparedStatement ps = null;
        ResultSet rs = null;
        System.out.println(query);
        
        try
        {

            rs = connectDatabase().createStatement().executeQuery(query);

            System.out.println(ConsoleColors.CYAN_BOLD_BRIGHT);
            System.out.println("Contenido de la Tabla");
            System.out.println("---------------------");
            
            ResultSetMetaData rsmdQuery = rs.getMetaData();
            for (int i = 1; i <= rsmdQuery.getColumnCount(); i++)
            System.out.print(String.format("%-25.25s",rsmdQuery.getColumnName(i)));

            System.out.println();
            System.out.println(ConsoleColors.RESET);

            while (rs.next()) 
            {
                
                for (int i = 1; i <= rsmdQuery.getColumnCount(); i++)
                {  
                    
                    System.out.print(String.format("%-25.25s ",rs.getString(i)));
                    
                }
                System.out.println();    
                
            }

        }
        catch(Exception e)
        {
            System.out.println("Error, formato de select incorrecto \n"+e);
        }
        
    }
    
    public void info() {
        
        try {
            
            DatabaseMetaData dbmd = connectDatabase().getMetaData();
            
            // Pintem la capçalera de la taula de resultats
            System.out.println(ConsoleColors.CYAN_BOLD_BRIGHT+"\nInformacio delGBD");
            System.out.println("-------------------\n"+ConsoleColors.RESET);
            System.out.println(ConsoleColors.WHITE_BOLD+"SGBD:\t"+ConsoleColors.RESET + dbmd.getDatabaseProductName());
            System.out.println(ConsoleColors.WHITE_BOLD+"Driver:\t"+ConsoleColors.RESET + dbmd.getDriverName());
            System.out.println(ConsoleColors.WHITE_BOLD+"URL:\t"+ConsoleColors.RESET + dbmd.getURL());
            System.out.println(ConsoleColors.WHITE_BOLD+"Usuari:\t"+ConsoleColors.RESET + dbmd.getUserName());
            System.out.println();
            
        } catch (SQLException ex) {
            Logger.getLogger(databaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void startShell()
    {
        
        String respuesta = "";
        
        do {
        
            respuesta = Leer.leerTexto("# (" + this.user + ")" + " on " + this.server + ":" + this.port + "[" + this.dbname + "] >");
            
            switch (respuesta)
            {
                
                case "help":

                        System.out.println("Ordre                               Descripcio");    
                        System.out.println("----------------------------------------------------------------------------------");
                        System.out.println("show tables o sh tables             Mostra les taules de la base de dades");
                        System.out.println("info                                Mostra informacio sobre el SGBD i la connexio");
                        System.out.println("insert Nom_de_la_Taula              Pregunta al usuari camp per camp de la taula, i inserix un registre a la base de dades");
                        System.out.println("describe Nom_de_la_Taula            Mostra una descripcio dels camps de la taula indicada");
                        System.out.println("select *                            Executara una consulta select qualsevol en la BD");
                        System.out.println("quit                                Torna al mode general");
                        break;
                
                case "sh tables":
                    this.showTables();
                    break;
                    
                case "show tables":
                    this.showTables();
                    break;

                case "quit":
                    connectionManager cm = new connectionManager(server, port, user, pass);
                    cm.startShell();
                    break;
                    
                case "info":
                    this.info();
                    break;

                default:
                // Si no s'ha complert cap dels casos anteriors, dividim l'ordre i analitzem les parts:
                    String[] subcommand= respuesta.toString().split(" ");
                    switch(subcommand[0])
                    {
                        case "describe":
                            this.showDescTable(subcommand[1]);
                            break;
                        case "insert":
                            this.insertIntoTable(subcommand[1]);
                            break;
                        case "select":
                            this.executeSelect(respuesta);
                            break;

                        default:
                            System.out.println("Unknown db option");

                    }
            }
            
            
        } while (respuesta != "quit");
    }
    
    
    // Metodo para quitar los [] de un ArrayList y separarlo por comas
    private static String limpia(String datosArray){
        datosArray = datosArray.trim();
        if (datosArray != null && datosArray.length() > 0 && datosArray.charAt(datosArray.length() - 1) == ',') {           
          datosArray = datosArray.substring(0, datosArray.length() - 1);              
        }
        return datosArray;
   }

    
    
    

}
