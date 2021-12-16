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
public class compararArea implements Comparator<Figura>{
    
    
    @Override
    public int compare(Figura fig, Figura fig2)
    {

        if (fig.area() > fig2.area())
        {
            
            return 1;
            
        }
        else if (fig.area() < fig2.area())
        {
            
            return -1;
            
        }
        else
        {
            
            return 0;
            
        }
        
    }
    
}
