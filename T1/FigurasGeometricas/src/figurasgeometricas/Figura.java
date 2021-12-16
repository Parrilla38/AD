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
public abstract class Figura implements Dibujable{
    
    
    Punto origen = new Punto();
    private int id;
    Color borde;
    Color relleno;

    public Figura(int id,double x, double y, Color borde, Color relleno) {
        this.id = id;
        this.borde = borde;
        this.relleno = relleno;
        this.origen.setX(x);
        this.origen.setY(y);
    }
    
    
    
    
    abstract double area();
    
    abstract double perimetro();
    
    public double distancia(Figura f)
    {
        
        float dist;
        dist = (float)Math.sqrt((this.origen.getX()*this.origen.getX())+(this.origen.getY()*this.origen.getY()));
        return(dist);
        
        
    }
    
    public abstract void escalar(int porcentaje);
    
    public void mover(Punto p)
    {
        
        this.origen = p;
        
    }
    
    public void desplazarh(double x)
    {
        
        if (x > 0)
        {
            
            origen.setX(origen.getX() + x);
            
        }
        else
        {
            
            origen.setX(origen.getX() - x);
            
        }
        
        
        
    }
    
    public void desplazarv(double y)
    {
        
        if (y > 0)
        {
            
            origen.setY(origen.getY() + y);
            
        }
        else
        {
            
            origen.setY(origen.getY() - y);
            
        }
        
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }
    
    
    

}
