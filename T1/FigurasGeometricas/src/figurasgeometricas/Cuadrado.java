/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package figurasgeometricas;

import java.awt.Color;

/**
 *
 * @author 34608
 */
public class Cuadrado extends Figura{
    
    
    int lado;

    public Cuadrado(int lado, int id, double x, double y, Color borde, Color relleno) {
        super(id, x, y, borde, relleno);
        this.lado = lado;
    }


    @Override
    public double area()
    {
        
        return lado * lado;
        
    }
    
    @Override
    public double perimetro()
    {
        
        return lado * 4;
        
    }
    
    @Override
    public void escalar(int porcentaje)
    {
        
        this.lado = (this.lado / 100) * porcentaje;
        
    }
    
    @Override
    public String toString() {
        return "Cuadrado: " + "\nX: " + this.origen.getX() + "\nY: " + this.origen.getY() + "\nLado: " + this.lado + "\nID: " + this.getId() + "\nColor Borde: " + this.borde + "\nColor relleno: " + this.relleno + "\n";
    }
}
