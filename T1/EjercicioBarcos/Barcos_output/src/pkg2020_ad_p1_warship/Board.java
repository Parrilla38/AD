package pkg2020_ad_p1_warship;
import Utils.ConsoleColors;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author joange
 */
public class Board {
    
    // Constantes del tablero
    private static final int BOARD_DIM = 10;
    private static final int BOARD_BOATS_COUNT = 5;
    
    // Propiedades de la clase
    private Cell cells[][];
    static Boat boats[];
    private static int dimensiones[] = {5, 4, 3, 2, 2};
    
    private boolean end_game;
    
    public Board(){
        // Crea la matriz de celdas del tablero
        cells = new Cell[BOARD_DIM][BOARD_DIM];
        // inicializa la matriz a agua
        for (int i=0; i<BOARD_DIM; i++) {
            for (int j=0; j<BOARD_DIM; j++) {
               cells[i][j] = new Cell(i, j);
            }
        }
        
        end_game=false;
    }
    
    // comprueba si ha acabado el juego
    private void testEnd(){
        for (Boat boat : getBoats()) {
            if (boat.getBoatState()!=Boat.BOAT_SUNKEN)
                return;
        }
        end_game=true;
    }
    
    public boolean getEnd_Game(){
        return end_game;
    }
    
    // Crea los botes y los posiciona
    public void initBoats(){
            boats = new Boat[getBOARD_BOATS_COUNT()];
            for (int i=0; i<getBOARD_BOATS_COUNT(); i++)
            {
                boats[i] = new Boat();
                getBoats()[i].setBoat(i, getDimensiones()[i], this);
            }
            
            
        
    }
    
    //Devuelve el objeto Cell que ocupa una fila y coulmna
    public Cell getCell(int fila, int columna){
        return getCells()[fila][columna];
    }
    
    //Devuelve un valor válido dentro del tablero
    public int fitValueToBoard(int value){
        if (value <=0) return 0;
        if (value > getBOARD_DIM()-1) return getBOARD_DIM()-1;
        return value;
    }
    
    
    //El jugador lanza una bomba sobre el tablero
    public int shot(int fila, int columna){
        System.out.print(ConsoleColors.PURPLE+"---- ");
        
        //Sacammos el objeto Boat que hay en la celda bombardeada
        Boat boat = getCells()[fila][columna].getBoat();
        if (boat != null) {
            //Si en la celda hay un barco, llamamos a su método touch (tocado)
            boat.touchBoat(fila, columna);
            testEnd();
            
        }
        else{ // marco la casilla como disparada
            getCells()[fila][columna].setFired();
        }
        System.out.print(ConsoleColors.GREEN+" [" +  fila + "], [" + columna + "] --> " +
                getCells()[fila][columna].getContainsString());
        System.out.println(ConsoleColors.PURPLE+" ----"+ConsoleColors.RESET);
        return getCells()[fila][columna].getContains();
    }
    
    // indica si una cel·la ha estat o no disparada, per no repetir la jugada
    public boolean fired(int fila,int columna){
        if (getCells()[fila][columna].getContains()==Cell.CELL_WATER ||
            getCells()[fila][columna].getContains()==Cell.CELL_BOAT)
            return false;
        else
            return true;
            
    }
    
    // Para mostrar el tablero por pantalla 
    public void paint(){
        // Cabecera ...
        System.out.print("      ");
        for (int k=0; k<Board.getBOARD_DIM(); k++){
            System.out.print(ConsoleColors.BLUE+k + " ");
        }
        System.out.println();
        char c='A';
        for (int i=0; i<Board.getBOARD_DIM(); i++) {
            System.out.print((ConsoleColors.BLUE+c++) + " <-- " +ConsoleColors.RESET);
            for (int j=0; j<Board.getBOARD_DIM(); j++) {
                System.out.print(getCells()[i][j].getContainsString() + " ") ;
            }
            System.out.println(ConsoleColors.BLUE+" -->");
        }
        
        System.out.print("      ");
        for (int k=0; k<Board.getBOARD_DIM(); k++){
            System.out.print(ConsoleColors.BLUE+k + " ");
        }
        System.out.println(ConsoleColors.RESET);
    }
    
    // Para mostrar el tablero por pantalla durante el juego 
    // (sin mostrar los barcos)
    public void paintGame(){
        // Cabecera ...
        System.out.print("  <-- ");
        for (int k=0; k<Board.getBOARD_DIM(); k++){
            System.out.print(k + " ");
        }
        System.out.println(" -->");
        char c='A';
        for (int i=0; i<Board.getBOARD_DIM(); i++) {
            System.out.print((c++) + " <-- ");
            for (int j=0; j<Board.getBOARD_DIM(); j++) {
                if (getCells()[i][j].getContainsString()==Cell.CELL_BOAT_CHAR)
                    System.out.print("_ ") ;
                else
                System.out.print(getCells()[i][j].getContainsString() + " ") ;
            }
            System.out.println(" -->");
        }
    }
    
    public static void crearArchivo()
    {
        
        FileWriter fw = null;
        try{
            
            fw = new FileWriter("C:\\archivosprog\\warship.propierties.txt");
            BufferedWriter bfw = new BufferedWriter(fw);
            bfw.write("board_tam: " + getBOARD_DIM() + "\nnum_boats: " + getBOARD_BOATS_COUNT() + "\nmax_jugadas: " + WarShip.getMAX_JUGADAS());
            bfw.close();
            System.out.println("Archivo creado satisfactoriamente..");
        }
        
        catch (IOException e)
        {
            
            e.printStackTrace();
            
        }
        finally
        {
            
            if (fw != null) 
            {
                try 
                {//cierra el flujo principal
                    
                    fw.close();
                    
                } 
                catch (IOException e) 
                {
                    
                    e.printStackTrace();
                    
                }
            }
            
        }
        
    }

    /**
     * @return the BOARD_DIM
     */
    public static int getBOARD_DIM() {
        return BOARD_DIM;
    }

    /**
     * @return the BOARD_BOATS_COUNT
     */
    public static int getBOARD_BOATS_COUNT() {
        return BOARD_BOATS_COUNT;
    }

    /**
     * @return the cells
     */
    public Cell[][] getCells() {
        return cells;
    }

    /**
     * @return the boats
     */
    public static Boat[] getBoats() {
        return boats;
    }

    /**
     * @return the dimensiones
     */
    public static int[] getDimensiones() {
        return dimensiones;
    }
}
