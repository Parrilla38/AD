/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package figurasgeometricas;

import static figurasgeometricas.Lienzo.sc;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.*;

/**
 *
 * @author 34608
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        // TODO code application logic here
        int respuesta = 0;
        
        
        
        
        do{
        Scanner sc = new Scanner(System.in);
        System.out.println("  ####################################");
        System.out.println("  #               MENÚ               #");
        System.out.println("  ####################################");
        System.out.println("  # 1 - Listar                       #"); // Lista las figuras
        System.out.println("  # 2 - Dibujar                      #"); // Primer paso para dibujar las figuras
        System.out.println("  # 3 - Perímetros                   #"); // Calcula los perimetros
        System.out.println("  # 4 - Areas                        #"); // Calcula las areas
        System.out.println("  # 5 - Escalar                      #"); // Escala la figura
        System.out.println("  # 6 - Mover                        #"); // Mueve una figura a nueva distancia
        System.out.println("  # 7 - Desplazar                    #"); // Desplaza la figura a la posicion indicada
        System.out.println("  # 8 - Ordenar                      #"); // Ordena las figuras
        System.out.println("  # 9 - Distancia                    #"); // Saca una distancia a otra
        System.out.println("  # 10 - Salir                       #"); // Salir del menú
        System.out.println("  ####################################");
        System.out.print("  Introduce la opción que deseas: ");
        respuesta = sc.nextInt();
        
        switch (respuesta) {
            case 1:
                
                
                Lienzo.listar();

                break;
            case 2:
                
                Lienzo.dibujar();
                break;
                
            case 3:
                
                Lienzo.perimetro();
                break;
                
            case 4:
                
                Lienzo.area();
                
                break;
            case 5:
                
                System.out.println("Escribe en número el porcentaje que deseas escalar las figuras:\n --  ");
                int porcentaje_bueno = sc.nextInt();
                System.out.println("Introduce el ID de la figura que quieres escalar: ");
                int id_figura = sc.nextInt();
                Lienzo.escalar(id_figura, porcentaje_bueno);
                
                
                break;
            case 6:
                
                
                int x = 0;
                int y = 0;
                System.out.println("Introduce la nueva X de las coordenadas: ");
                x = sc.nextInt();
                System.out.println("Introduce la nueva Y de las coordenadas: ");
                y = sc.nextInt();
                Punto nuevo_origen = new Punto(x, y);
                System.out.println("Introduce el ID de la figura que quieres: ");
                id_figura = sc.nextInt();
                Lienzo.mover(id_figura, nuevo_origen);
                
                break;
            case 7:
                
                
                System.out.println("Desea desplazar verticalmente o horizontalmente?\n 1 - Horizontal | 2 - Vertical ");
                int respuest1 = sc.nextInt();
                if (respuest1 == 1)
                {
                    
                    System.out.println("Introduce el ID de la figura que quieres: ");
                    id_figura = sc.nextInt();
                    System.out.println("Introduce la nueva coordenada de X para desplazar");
                    int c_x = sc.nextInt();
                    Lienzo.desplazarh(id_figura, c_x);
                    
                }
                else if (respuest1 == 2)
                {
                    
                    System.out.println("Introduce el ID de la figura que quieres: ");
                    id_figura = sc.nextInt();
                    System.out.println("Introduce la nueva coordenada de Y para desplazar");
                    int c_y = sc.nextInt();
                    Lienzo.desplazarh(id_figura, c_y);
                    
                }                
                break;
            case 8:
                
                System.out.println("  ####################################");
                System.out.println("  #             SUBMENÚ              #");
                System.out.println("  ####################################");
                System.out.println("  # 1 - AREA                         #");
                System.out.println("  # 2 - PERÍMETRO                    #");
                System.out.println("  # 3 - POSICIÓN                     #");
                System.out.println("  ####################################");
                System.out.print("  Introduce la opción que deseas: ");
                respuesta = sc.nextInt();
                if (respuesta == 1)
                {
                    
                    System.out.println("  ####################################");
                    System.out.println("  #             SUBMENÚ2             #");
                    System.out.println("  ####################################");
                    System.out.println("  # 1 - ASCENDENTE                    #");
                    System.out.println("  # 2 - DESCENDENTE                  #");
                    System.out.println("  ####################################");
                    System.out.print("  Introduce la opción que deseas: ");
                    int respuesta2 = sc.nextInt();
                    if(respuesta2 == 1)
                    {
                        
                        Collections.sort(Lienzo.getFiguras(), new compararArea());
                        System.out.println(Lienzo.getFiguras());
                        
                    }
                    else if(respuesta2 == 2)
                    {
                        
                        Collections.sort(Lienzo.getFiguras(), new compararArea());
                        Collections.reverse(Lienzo.getFiguras());
                        System.out.println(Lienzo.getFiguras());
                        
                    }
                    
                    
                }
                else if (respuesta == 2)
                {
                    
                    System.out.println("  ####################################");
                    System.out.println("  #             SUBMENÚ2             #");
                    System.out.println("  ####################################");
                    System.out.println("  # 1 - ASCENDENTE                    #");
                    System.out.println("  # 2 - DESCENDENTE                  #");
                    System.out.println("  ####################################");
                    System.out.print("  Introduce la opción que deseas: ");
                    int respuesta2 = sc.nextInt();
                    if(respuesta2 == 1)
                    {
                        
                        Collections.sort(Lienzo.getFiguras(), new compararPerimetro());
                        System.out.println(Lienzo.getFiguras());
                        
                    }
                    else if(respuesta2 == 2)
                    {
                        
                        Collections.sort(Lienzo.getFiguras(), new compararArea());
                        Collections.reverse(Lienzo.getFiguras());
                        System.out.println(Lienzo.getFiguras());
                        
                    }
                    
                    
                }
                else if (respuesta == 3)
                {
                    
                    System.out.println("  ####################################");
                    System.out.println("  #             SUBMENÚ2             #");
                    System.out.println("  ####################################");
                    System.out.println("  # 1 - ASCENDENTE                    #");
                    System.out.println("  # 2 - DESCENDENTE                  #");
                    System.out.println("  ####################################");
                    System.out.print("  Introduce la opción que deseas: ");
                    int respuesta2 = sc.nextInt();
                    if(respuesta2 == 1)
                    {
                        
                        Collections.sort(Lienzo.getFiguras(), new compararPosicion());
                        System.out.println(Lienzo.getFiguras());
                        
                    }
                    else if(respuesta2 == 2)
                    {
                        
                        Collections.sort(Lienzo.getFiguras(), new compararPosicion());
                        Collections.reverse(Lienzo.getFiguras());
                        System.out.println(Lienzo.getFiguras());
                        
                    }
                    
                    
                }
                
                break;
            case 9:
                
                System.out.println("Introduce el ID de la figura que quieres calcular las distancias: ");
                id_figura = sc.nextInt();
                Lienzo.distancia(id_figura);
                break;
            case 10:
                break;
            default:
                System.out.println("Introduce una opción correcta");
                break;
        }
        
        }while(respuesta != 10);
        
        
        
        
        
    }
    
}
