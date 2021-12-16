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

/**
 * Los distintos modos de juego.
 *
 * @author Javier Tejedor
 */
public enum ModoJuego {

    /**
     * Disparos aleatorios.
     */
    ALEATORIO,
    /**
     * Disparos aleatorios hasta que encuentra una parte de un barco. Entonces
     * dispara al rededor de esta casilla hasta que encuentra la siguiente parte
     * del barco, en ese momento dispara en el eje donde estan las dos casillas
     * disparadas.
     */
    LOGICO
}
