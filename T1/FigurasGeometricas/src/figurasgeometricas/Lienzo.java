/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package figurasgeometricas;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.*;
/**
 *
 * @author 34608
 */



public class Lienzo {

    /**
     * @return the Figuras
     */
    public static ArrayList<Figura> getFiguras() {
        return Figuras;
    }
    
    private static ArrayList<Figura> Figuras = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);
    
    static public void dibujar()
    {
        
        Cuadrado cuadrado1 = new Cuadrado((int) (Math.random() * 50), (int) (Math.random() * 5000),(double) (Math.random() * 30),(double) (Math.random() * 30), Color.BLACK, Color.GREEN);
        getFiguras().add(cuadrado1);
        Cuadrado cuadrado2 = new Cuadrado((int) (Math.random() * 50), (int) (Math.random() * 5000),(double) (Math.random() * 30),(double) (Math.random() * 30),Color.YELLOW, Color.RED);
        getFiguras().add(cuadrado2);
        Circulo circulo1 = new Circulo((int) (Math.random() * 50), (int) (Math.random() * 5000),(double) (Math.random() * 30),(double) (Math.random() * 30), Color.BLUE, Color.CYAN);
        getFiguras().add(circulo1);
        Circulo circulo2 = new Circulo((int) (Math.random() * 50), (int) (Math.random() * 5000),(double) (Math.random() * 30),(double) (Math.random() * 30), Color.BLUE, Color.RED);
        getFiguras().add(circulo2);
        Rectangulo Rectangulo1 = new Rectangulo((int) (Math.random() * 50), (int) (Math.random() * 50), (int) (Math.random() * 5000),(double) (Math.random() * 30),(double) (Math.random() * 30), Color.ORANGE, Color.WHITE);
        getFiguras().add(Rectangulo1);
        Rectangulo Rectangulo2 = new Rectangulo((int) (Math.random() * 50), (int) (Math.random() * 50), (int) (Math.random() * 5000),(double) (Math.random() * 30),(double) (Math.random() * 30), Color.CYAN, Color.BLUE);
        getFiguras().add(Rectangulo2);
        Rectangulo Rectangulo3 = new Rectangulo((int) (Math.random() * 50), (int) (Math.random() * 50), (int) (Math.random() * 5000),(double) (Math.random() * 30),(double) (Math.random() * 30), Color.YELLOW, Color.BLUE);
        getFiguras().add(Rectangulo3);
        Triangulo triangulo1 = new Triangulo((int) (Math.random() * 50), (int) (Math.random() * 50), (int) (Math.random() * 5000),(double) (Math.random() * 30),(double) (Math.random() * 30), Color.CYAN, Color.BLUE);
        getFiguras().add(triangulo1);
        Triangulo triangulo2 = new Triangulo((int) (Math.random() * 50), (int) (Math.random() * 50), (int) (Math.random() * 5000),(double) (Math.random() * 30),(double) (Math.random() * 30), Color.BLACK, Color.WHITE);
        getFiguras().add(triangulo2);
        Triangulo triangulo3 = new Triangulo((int) (Math.random() * 50), (int) (Math.random() * 50), (int) (Math.random() * 5000),(double) (Math.random() * 30),(double) (Math.random() * 30), Color.BLUE, Color.GREEN);
        getFiguras().add(triangulo3);
        
        
        
    }
    
    static public void area()
    {
        
        
        
        System.out.println("Listado de Areas: ");
        ArrayList Areas = new ArrayList();
                
                for(Figura f: getFiguras())
                {
                    
                    double areas = 0;
                    areas = f.area();
                    Areas.add(areas);
                    
                }
                System.out.println(Areas);
                
                
                
        
        
    }
    
    static public void perimetro()
    {
        
        System.out.println("Listado de Perimetros: ");
        ArrayList Perimetros = new ArrayList();
                for(Figura f: getFiguras())
                {
                    
                    double perimetro = 0;
                    perimetro = f.perimetro();
                    Perimetros.add(perimetro);
                    
                }
                System.out.println(Perimetros);
        
        
    }
    
    public static void distancia(int id)
    {
        
        
        ArrayList distancias = new ArrayList();
        double distancia_fig = 0;
        for (Figura f: getFiguras())
            {

                if(f.getId() == id)
                {
                    
                    for (Figura f2: getFiguras())
                    {
                        
                        
                        distancia_fig = f.distancia(f2);
                        distancias.add(distancia_fig);
                        
                        
                    }
                    
                    
                }

            }
        
        System.out.println(distancias);
        
        
    }
    
    static public void listar()
    {
        
        System.out.println("Listado de Figuras: ");
                System.out.println("------------------");
                for(Figura f: getFiguras())
                {
                    
                    System.out.println(f);   
                    
                }
        
    }
    
    static public void escalar(int id, int porcentaje)
    {
        
        for (Figura f: getFiguras())
        {
            
            if (f.getId() == id)
            {
                
                f.escalar(porcentaje);
                
            }

            
        }
        
        
    }
    
    static public void mover(int id, Punto punto)
    {
        
        for (Figura f: getFiguras())
        {
            
            if (f.getId() == id)
            {
                
                f.mover(punto);
                
            }

            
        }
        
    }
    
    static public void desplazarh(int id, int x)
    {
        
        for (Figura f: getFiguras())
        {
            
            if (f.getId() == id)
            {
                
                f.desplazarh(x);
                
            }
        
        
        }   
    }
    
    static public void desplazarv(int id, int y)
    {
        
        for (Figura f: getFiguras())
        {
            
            if (f.getId() == id)
            {
                
                f.desplazarh(y);
                
            }
        
        }
    }
    
}
