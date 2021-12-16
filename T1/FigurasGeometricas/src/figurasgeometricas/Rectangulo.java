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
public class Rectangulo extends Figura{
    
    int base;
    int altura;

    public Rectangulo(int base, int altura, int id, double x, double y, Color borde, Color relleno) {
        super(id, x, y, borde, relleno);
        this.base = base;
        this.altura = altura;
    }


    
    
    @Override
    public double area()
    {
        
        return base * altura;
        
    }
    
    @Override
    public double perimetro()
    {
        
        int operacion = (base * 2) + (altura * 2);
        return operacion;
        
    }
    
    @Override
    public void escalar(int porcentaje)
    {
        
        this.base = (this.base / 100) * porcentaje;
        this.altura= (this.altura / 100) * porcentaje;
    }

    @Override
    public String toString() {
        return "Rectangulo: " + "\nX: " + this.origen.getX() + "\nY: " + this.origen.getY() + "\nBase: " + this.base + "\nAltura: " + this.altura + "\nID: " + this.getId() + "\nColor Borde: " + this.borde + "\nColor relleno: " + this.relleno + "\n";
    }
    
    
    
}
