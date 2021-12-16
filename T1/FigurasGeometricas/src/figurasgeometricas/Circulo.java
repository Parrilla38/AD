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
public class Circulo extends Figura{
    
    int radio;

    public Circulo(int radio, int id, double x, double y, Color borde, Color relleno) {
        super(id, x, y, borde, relleno);
        this.radio = radio;
    }




    
    @Override
    public double area()
    {
        
        return Math.PI * Math.pow(radio, 2); 
        
    }
    
    @Override
    public double perimetro()
    {
        
        int diametro = radio * 2;
        return Math.PI * diametro;
        
    }
    
    @Override
    public void escalar(int porcentaje)
    {
        
        this.radio = (this.radio / 100) * porcentaje;
        
    }

    @Override
    public String toString() {
        return "Circulo: " + "\nX: " + this.origen.getX() + "\nY: " + this.origen.getY() + "\nRadio: " + this.radio + "\nID: " + this.getId() + "\nColor Borde: " + this.borde + "\nColor relleno: " + this.relleno + "\n";
    }
    
    
    
}
