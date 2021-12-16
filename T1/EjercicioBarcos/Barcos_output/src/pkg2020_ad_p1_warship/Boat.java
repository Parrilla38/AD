package pkg2020_ad_p1_warship;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import static pkg2020_ad_p1_warship.Board.getBOARD_BOATS_COUNT;
import static pkg2020_ad_p1_warship.Board.getBOARD_DIM;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author joange
 */
public class Boat {
    //Constantes para controlar el estado general del barco
    public final static int BOAT_OK = 0;
    public final static int BOAT_TOUCHED = 1;
    public final static int BOAT_SUNKEN = 2;
    
    //Constantes para controlar su orientación
    public final static int BOAT_DIR_HOR = 0;
    public final static int BOAT_DIR_VER = 1;
    
    // Propiedades
    private int dimension=0;
    private Cell[] cells;
    private int estado = BOAT_OK;
    
    // Constructor
    public Boat(){
        //Vacío, las propiedades se establecen en su iniciaización
    }
    
    // Para consultar el estado de un barco
    public int getBoatState(){return estado;}
   
    // Establece la dimensión del barco y lo coloca en el tablero si cabe
    public void setBoat(int pos, int dim, Board board){
        cells = new Cell[dim];
        this.dimension = dim;
        int k = 0;
        
        
        int fila = new Double((Math.random()*(Board.getBOARD_DIM()))).intValue();
        int columna = new Double(Math.random()*(Board.getBOARD_DIM())).intValue();
        int dir = new Double(Math.random()*(this.BOAT_DIR_VER + 1)).intValue();
        
        
        while (!boatFits(fila, columna, dim, dir, board)){
            fila = new Double((Math.random()*Board.getBOARD_DIM())).intValue();
            columna = new Double(Math.random()*Board.getBOARD_DIM()).intValue();
        } 
       
        if (dir==BOAT_DIR_HOR) {
            for (int i=columna;i<columna+dim;i++) {
                this.cells[i-columna] = board.getCell(fila, i);
                this.cells[i-columna].setBoat(this);
            }
        } else {
            for (int i=fila;i<fila+dim;i++){ //BOAT_DIR_VER
                this.cells[i-fila] = board.getCell(i, columna);
                this.cells[i-fila].setBoat(this);
            }
        }
        
        try{
            
            FileWriter fw = null;
            fw = new FileWriter("C:\\archivosprog\\boat_out.txt", true);
            BufferedWriter bfw = new BufferedWriter(fw);
            
            
            bfw.write(pos + ";" + dim +";" + dir + ";" + fila + ";" + columna + "\n");
            
            
            bfw.close();
            System.out.println("Archivo creado satisfactoriamente..");
        } catch (IOException ex) {
            Logger.getLogger(Boat.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        


        
    }
    
    // Controla si el bote cabe el el tablero
    private boolean boatFits(int fila, int columna, int dimension, int direccion, Board board){
        int min_col = 0, max_col=0, min_row=0, max_row=0;
        
        // los barcos se colocan de fila, columna hacia la derecha o hacia abajo
        // Dependiendo de la orientación calcula el recuadro a controlar
        
        if (direccion == this.BOAT_DIR_HOR) {
            if ((columna + dimension) > Board.getBOARD_DIM()) return false; // El barco no cabe
            
            min_col = board.fitValueToBoard(columna - 1);
            max_col = board.fitValueToBoard(columna + dimension);
            
            min_row = board.fitValueToBoard(fila - 1);
            max_row = board.fitValueToBoard(fila + 1);
        }
        if (direccion == this.BOAT_DIR_VER) {
            if ((fila + dimension) > Board.getBOARD_DIM()) return false; // El barco no cabe
            
            min_col = board.fitValueToBoard(columna - 1);
            max_col = board.fitValueToBoard(columna + 1);
            
            min_row = board.fitValueToBoard(fila - 1);
            max_row = board.fitValueToBoard(fila + dimension);
        }
        
        // Recorre la matriz que contendrá el barco para asegurarse que no hay ninguno
        for (int i=min_row; i<=max_row; i++){
            for (int j=min_col; j<=max_col; j++) {
                if (board.getCell(i, j).getContains()==Cell.CELL_BOAT) return false; // Ya hay un barco
            }
        }
        return true;
    }
    
    // Cuando una shot cae sobre un barco
    public int touchBoat(int fila, int columna){
        int tocados = 0;
        //Si ya está hundido no puede empeorar
        if (estado == BOAT_SUNKEN) return BOAT_SUNKEN; 
        
        // Si no está hundido como mínimo estará tocado
        estado = Boat.BOAT_TOUCHED;
        
        // Comprueba si esta parte del barco aún no habia sido tocada
        // Cuenta los tocados para saber si está hundido
        for (int i = 0; i < dimension; i++) {
            Cell c = cells[i];
            if ((c.getRow() == fila) && (c.getColumn() == columna)) {
                if (c.getContains() == Cell.CELL_BOAT) c.setTouch();
            }
            if (c.getContains() == Cell.CELL_TOUCH)  tocados++;
        }
        // Si todas las partes del barco están tocadas ... Cambiar estado a hundido
        if (tocados == dimension) {
            // Hundido ....
            for (int i=0; i<dimension; i++) {
                Cell c = cells[i];
                c.setSunken();
            }
            estado = BOAT_SUNKEN;
        }
        
        return estado;
    }
    
    // Para mostrar por pantalla las celdas que ocupa un barco
    public void viewCells(){
        System.out.print("Posiciones: {");
        for (int i = 0; i < dimension; i++) {
            Cell c = cells[i];
            System.out.print("(" + c.getRow() + ", " + c.getColumn() + ")");
        }
        System.out.println(" }");
    }
}
