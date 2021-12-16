/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package figurasgeometricas;

import java.util.Comparator;

/**
 *
 * @author 34608
 */
public class compararPosicion implements Comparator<Figura>{
    

    @Override
    public int compare(Figura fig, Figura fig2)
    {
        
        
        if (fig.origen.getX() > fig2.origen.getX() && fig.origen.getY() > fig2.origen.getY())
        {
            
            return 1;
            
        }
        else if (fig.origen.getX() < fig2.origen.getX() && fig.origen.getY() < fig2.origen.getY())
        {
            
            return -1;
            
        }
        else
        {
            
            return 0;
            
        }
        
    }
    
}
