/*
 * Copyright (C) 2020 Javier Tejedor
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package hundirlaflota.tablero;

import hundirlaflota.tablero.tipocasilla.TipoCasilla;
import java.awt.Color;

/**
 * Clase que define las casillas del tablero
 *
 * @author Javier Tejedor
 */
public class Casilla {

    /**
     * Color de la casilla atacada y es un barco.
     */
    public static final Color COLOR_IMPACTADA = new Color(255, 0, 0);

    /**
     * Color de la casilla atacada pero es agua.
     */
    public static final Color COLOR_ATACADA = new Color(253, 191, 70);

    /**
     * Color de la casilla que contiene el barco1.
     */
    public static final Color COLOR_BARCO1 = new Color(26, 26, 26);

    /**
     * Color de la casilla que contiene el barco2.
     */
    public static final Color COLOR_BARCO2 = new Color(140, 140, 140);

    /**
     * Color de la casilla que contiene el barco3.
     */
    public static final Color COLOR_BARCO3 = new Color(0, 0, 0);

    /**
     * Color de la casilla con agua.
     */
    public static final Color COLOR_AGUA = new Color(102, 207, 255);

    /**
     * Filas máximas permitidas (excl).
     */
    public static int FILA_MAX = 7;
    /**
     * Filas mínimas permitidas (incl).
     */
    public static int FILA_MIN = 0;
    /**
     * Columnas máximas permitidas (excl).
     */
    public static int COLUMNA_MAX = 7;
    /**
     * Columnas mínimas permitidas (incl).
     */
    public static int COLUMNA_MIN = 0;

    private final int fila;
    private final int columna;
    private TipoCasilla tipo;

    /**
     * Construye una casilla a partir de unas coordenadas y un tipo de casilla
     *
     * @param fila Fila
     * @param columna Columna
     * @param tipo TipoCasilla
     */
    public Casilla(int fila, int columna, TipoCasilla tipo) {
        this.fila = fila;
        this.columna = columna;
        this.tipo = tipo;
    }

    /**
     * Devuelve la fila de esta casilla.
     *
     * @return fila
     */
    public int getFila() {
        return fila;
    }

    /**
     * Devuelve la columna de esta casilla.
     *
     * @return columna
     */
    public int getColumna() {
        return columna;
    }

    /**
     * Devuelve el tipo de esta casilla.
     *
     * @return tipo
     */
    public TipoCasilla getTipo() {
        return tipo;
    }

    /**
     * Asigna el tipo de la casilla.
     *
     * @param tipo TipoCasilla
     */
    public void setTipo(TipoCasilla tipo) {
        this.tipo = tipo;
    }

    /**
     * Comprueba si la casilla tiene barco.
     *
     * @return Devuelve verdadero si la casilla tiene barco.
     */
    public boolean tieneBarco() {
        return this.tipo.getTipoIndex() != 0;

    }

    /**
     * Comprueba si una casilla es valida para colocar un barco.
     *
     * @param t El tablero que se quiere comprobar.
     * @return Devuelve verdadero si la casilla es válida.
     */
    public boolean esValida(Tablero t) {
        for (int i = fila - 1 < FILA_MIN ? FILA_MIN : fila - 1; i <= (fila + 1 >= FILA_MAX ? fila : fila + 1); i++) {
            for (int j = columna - 1 < COLUMNA_MIN ? COLUMNA_MIN : columna - 1; j <= (columna + 1 >= COLUMNA_MAX ? columna : columna + 1); j++) {
                if (t.get(new Index(new int[]{i, j})).tieneBarco()) {
                    return false;
                }
            }
        }
        return true;
    }
}
