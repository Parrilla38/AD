/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package APAC2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class connectionManager {
    
    String server;
    String port;
    String user;
    String pass;

    public connectionManager() {
    }
    
    
    public connectionManager(String server, String port, String user, String pass) {
        this.server = server;
        this.port = port;
        this.user = user;
        this.pass = pass;
    }
    
    public Connection connectDBMS()
    {
        
        Connection conn = null;
        
        try {
  
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            String connectionUrl = "jdbc:mysql://" + this.server + ":" + this.port +
                    "?useUnicode=true&characterEncoding=UTF-8"+
                    "&user=" + this.user +
                    "&password=" + this.pass +
                    "&allowMultiQueries=true";
            
            conn = DriverManager.getConnection(connectionUrl);
            
            
        } catch (ClassNotFoundException ex) 
        {
            Logger.getLogger(connectionManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(connectionManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return conn;
        
    }
    
    
    public void showInfo()
    {
        
        try {
            
            DatabaseMetaData dbmd = connectDBMS().getMetaData();
            
            // Informacion de la Base de datos
            
            System.out.println("Gestor de BBDD:" + dbmd.getDatabaseProductName());
            
            System.out.println("Version del Gestor:" + dbmd.getDatabaseProductVersion());
            
            System.out.println("Nombre del driver:" + dbmd.getDriverName());
            
            System.out.println("Version del driver:" + dbmd.getDriverVersion());
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(connectionManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void showDatabases()
    {
        
        try {
            
            DatabaseMetaData dbmd = connectDBMS().getMetaData();
            
            ResultSet result = dbmd.getCatalogs();
         
            while (result.next()) {
                String aDBName = result.getString(1);
                System.out.println(aDBName);
            }      
            
            
        } catch (SQLException ex) {
            Logger.getLogger(connectionManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }
    
    
    public void importScript(String script2) 
    {
        
        File script = new File(script2);
        System.out.println("Executant l'script "+ script.getName());

        // Convertim el fitxer a una cadena


        BufferedReader br=null;
        try{
            
            br=new BufferedReader(new FileReader(script));
            
        } catch(FileNotFoundException e)
        {
            
            System.out.println("Error: L'script no existeix.");
            
        };

        String line = null;
        StringBuilder sb= new StringBuilder();

        // Obtenim el símbol del salt de línia segons el sistema
        String breakLine=System.getProperty("line.separator");

        try
        {
            while ((line=br.readLine())!=null) 
            {
                
                sb.append(line);
                sb.append(breakLine);
                
            }
        } catch (IOException e)
        {
            System.out.println ("ERROR d'E/s");
        }

        // Convertim el stringBuilder en cadena:
        String query = sb.toString();

        System.out.println("Executant consulta: \n" + query);
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Error en carregar el driver");
        }

        try{
        
            Statement st= connectDBMS().createStatement();
            int result=st.executeUpdate(query);

            System.out.println("Script Executat amb éxit. Eixida: "+
            result);

            st.close();
            connectDBMS().close();

        } catch (SQLException e){
        System.out.println("Error en l'script "+ e);
        }

    }
    
    public void startShell()
    {
        
        String respuesta = "";
            
            
            do {
                
                respuesta = Leer.leerTexto("# (" + this.user + ")" + " on " + this.server + ":" + this.port + ">");
                
                switch (respuesta)
                {

                    case "help":

                        System.out.println("Ordre                               Descripcio");    
                        System.out.println("----------------------------------------------------------------------------------");
                        System.out.println("show databases o sh db              Mostra una llista amb les diferents bases de dades del sistema");
                        System.out.println("info                                Muestra informacion del Servidor");
                        System.out.println("import Nom_del_script               Permetra executar un script sql indicant la ubicació del fitxer");
                        System.out.println("use Nom_de_la_BD                    Canvia al mode de connexio a la base de dades");
                        System.out.println("quit                                Ix de l'aplicacio");
                        break;
                        
                    case "sh db":
                        this.showDatabases();
                        break;
                        
                    case "show databases":
                        this.showDatabases();
                        break;
                        
                    case "info":
                        this.showInfo();
                        break;

                    case "quit":
                        break;


                    default:
                    // Si no s'ha complert cap dels casos anteriors, dividim l'ordre i analitzem les parts:
                        String[] subcommand=respuesta.split(" ");
                        switch(subcommand[0])
                        {
                            case "import":
                                this.importScript(subcommand[1]);
                                break;
                            
                            case "use":
                                databaseManager dm = new databaseManager(this.server, this.port, this.user, this.pass, subcommand[1]);
                                dm.startShell();
                                break;

                            default:
                                System.out.println("Unknown db option");
                                break;

                        }


                }
                
            } while(!"quit".equals(respuesta));
            
    }
    
}
