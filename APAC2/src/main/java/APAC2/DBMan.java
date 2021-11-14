/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package APAC2;

/**
 *
 * @author 34608
 */
public class DBMan {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)  throws Exception{
        // TODO code application logic here
        
        connectionManager cm = new connectionManager();
        
        do
        {
            
            String server = Leer.leerTexto("# Server: ");
            String port = Leer.leerTexto("# Port: ");
            String user = Leer.leerTexto("# Username: ");
            String pass = Leer.leerTexto("# Password: ");

            cm = new connectionManager(server, port, user, pass);
            
        }while (cm.connectDBMS() == null);
        if(cm.connectDBMS() != null)
        {
            
            cm.startShell();
            
        }
        else
        {
            
            System.out.println("Error al conectar con la base de datos");
            
        }
        
        
        
    }
    
}
