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
public class Triangulo extends Rectangulo{

    public Triangulo(int base, int altura, int id, double x, double y, Color borde, Color relleno) {
        super(base, altura, id, x, y, borde, relleno);
    }

    
    @Override
    public double area()
    {
        
        return (base * altura) / 2;
        
    }
    
    @Override
    public double perimetro()
    {
        
        double a = Math.pow(this.base, 2);
        double b = Math.pow(this.altura, 2);
        double c = a + b;
        return Math.pow(c, 2);
        
    }
    
    @Override
    public void escalar(int porcentaje)
    {
        
        this.base = (this.base / 100) * porcentaje;
        this.altura= (this.altura / 100) * porcentaje;
    }

    @Override
    public String toString() {
        return "Triangulo: " + "\nX: " + this.origen.getX() + "\nY: " + this.origen.getY() + "\nBase: " + this.base + "\nAltura: " + this.altura + "\nID: " + this.getId() + "\nColor Borde: " + this.borde + "\nColor relleno: " + this.relleno + "\n";
    }
    
    
    
}
