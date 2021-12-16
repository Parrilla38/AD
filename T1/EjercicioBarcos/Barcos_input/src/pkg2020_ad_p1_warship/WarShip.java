package pkg2020_ad_p1_warship;

import Utils.ConsoleColors;
import Utils.Leer;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import static pkg2020_ad_p1_warship.Board.boats;
import static pkg2020_ad_p1_warship.Board.getBOARD_BOATS_COUNT;
import static pkg2020_ad_p1_warship.Board.getBoats;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author joange
 */
public class WarShip {

    /**
     * @return the MAX_JUGADAS
     */
    public static int getMAX_JUGADAS() {
        return MAX_JUGADAS;
    }

    /**
     * @return the r
     */
    public Random getR() {
        return r;
    }

    /**
     * @return the board
     */
    public Board getBoard() {
        return board;
    }

    /**
     * @return the ws
     */
    public WarShip getWs() {
        return ws;
    }

    /**
     * @param args the command line arguments
     */
    private static final int MAX_JUGADAS = 100;

    private Random r;
    private Board board;
    private WarShip ws;

    public WarShip() {
        r = new Random(System.currentTimeMillis());
        board = new Board();
        board.initBoats();
    }

    private void autoPlay() {

        getBoard().paint();
        File oldfile = new File("C:\\archivosprog\\moviments_out.txt");
        File newfile = new File("C:\\archivosprog\\moviments_in.txt");
        
        if (oldfile.renameTo(newfile)) {
            System.out.println("archivo renombrado");
        } else {
            System.out.println("error");
        }
        
        FileReader reader = null;
        BufferedReader buffer = null;
        String [] filatexto = null;
        
        try 
        {
            // fichero fuera
            reader = new FileReader (newfile);
        
            buffer = new BufferedReader (reader);
        
        // Vamos a realizar 50 jugadas aleatorias ...
        
        while (buffer.ready())
        {
            
            
            String lineas = buffer.readLine();
            String [] lineaseparadas = lineas.split(";");
            
            
            System.out.println(ConsoleColors.GREEN_BRIGHT+"JUGADA: " + lineaseparadas[0]);

            int fila, columna;
            do {
                fila = Integer.parseInt(lineaseparadas[1]);
                columna = Integer.parseInt(lineaseparadas[2]);
            } while (getBoard().fired(fila, columna));

            int resultado = getBoard().shot(fila, columna);
            if (resultado != Cell.CELL_WATER) {
                getBoard().paint();
            } else {
                System.out.println("(" + fila + "," + columna + ") --> AGUA");
            }

            if (getBoard().getEnd_Game()) {
                System.out.printf("Joc acabat amb %2d jugades\n", lineaseparadas[0]);
                break;
            }
            
            
        }
        
        buffer.close();
        reader.close(); 
        
        } catch (FileNotFoundException ex) {
            Logger.getLogger(WarShip.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(WarShip.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

    private void play() {
        int num_jugadas = 0;
        boolean rendit = false;

        String jugada;
        int fila = -1, columna = -1;
        do {
            do {
                jugada = Leer.leerTexto("Dime la jugada en dos letras A3, B5... de A0 a J9: ").toUpperCase();
                if (jugada.equalsIgnoreCase("00")) {
                    System.out.println("Jugador rendit");
                    rendit = true;
                    break;
                }
                if (jugada.length() == 0 || jugada.length() > 2) {
                    System.out.println("Format incorrecte.");
                    continue;
                }

                fila = jugada.charAt(0) - 'A';
                columna = jugada.charAt(1) - '0';

            } while (getBoard().fired(fila, columna));

            // acaba el joc
            if (rendit) {
                break;
            }

            num_jugadas++;

            if (getBoard().shot(fila, columna) != Cell.CELL_WATER) {
                getBoard().paintGame();
            } else {
                System.out.println("(" + fila + "," + columna + ") --> AGUA");
            }

            if (getBoard().getEnd_Game()) {
                System.out.printf("Joc acabat amb %2d jugades\n", num_jugadas);
                break;
            }

        } while (num_jugadas < getMAX_JUGADAS());

    }

    public static void main(String[] args) {
        // TODO code application logic here
        WarShip ws = new WarShip();

        int opcio = 0;
        do {
            System.out.println(ConsoleColors.GREEN+"--    Escollir   --");
            System.out.println(ConsoleColors.GREEN+"1. Joc automàtic...");
            System.out.println(ConsoleColors.GREEN+"2. Joc manual......");
            opcio = Leer.leerEntero(ConsoleColors.CYAN+"Indica el tipus de joc que vols: "+ConsoleColors.RESET);
        } while (opcio < 1 || opcio > 2);

        switch (opcio) {
            case 1:
                ws.autoPlay();
                break;
            case 2:
                ws.play();
                break;
        }

    }

}
