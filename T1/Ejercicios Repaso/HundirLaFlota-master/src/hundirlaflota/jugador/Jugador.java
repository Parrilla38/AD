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
package hundirlaflota.jugador;

import hundirlaflota.tablero.Casilla;
import hundirlaflota.tablero.Index;
import hundirlaflota.tablero.Tablero;

/**
 * Clase principal Jugador
 *
 * @author Javier Tejedor
 */
public class Jugador {

    private Tablero tableroPropio;
    private Tablero tableroDeJuego;

    /**
     * Constructor especificando una instancia de la clase de objeto Tablero.
     *
     * @param tableroPropio Tablero con tus barcos.
     */
    public Jugador(Tablero tableroPropio) {
        this.tableroPropio = tableroPropio;
    }

    /**
     * Constructor sin tablero.
     */
    public Jugador() {
        this.tableroPropio = new Tablero();
    }

    /**
     * Devuelve el tablero de juego.
     *
     * @return Tablero de juego.
     */
    public Tablero getTableroDeJuego() {
        return tableroDeJuego;
    }

    /**
     * Configura el tablero de juego.
     *
     * @param tableroDeJuego El tablero de juego del jugador.
     */
    public void setTableroDeJuego(Tablero tableroDeJuego) {
        this.tableroDeJuego = tableroDeJuego;
    }

    /**
     * Devuelve el tablero propio.
     *
     * @return Tablero propio.
     */
    public Tablero getTableroPropio() {
        return tableroPropio;
    }

    /**
     * Configura el tablero propio.
     *
     * @param tableroPropio El tablero del jugador.
     */
    public void setTableroPropio(Tablero tableroPropio) {
        this.tableroPropio = tableroPropio;
    }

    /**
     * Cambia el estado de una casilla y resta una parte a aquellas casillas
     * cuyo tipo sea igual.
     *
     * @param pos Posición de la casilla.
     */
    public void cambiarEstadoCasilla(int[] pos) {
        this.tableroDeJuego.get(new Index(pos)).getTipo().setTocado(true);
        for (Casilla casilla : this.tableroPropio.values()) {
            if (casilla.getTipo().getTipoIndex() == this.tableroDeJuego.get(new Index(pos)).getTipo().getTipoIndex()) {
                this.tableroDeJuego.get(new Index(pos)).getTipo().restarParte();
            }
        }
    }

}
