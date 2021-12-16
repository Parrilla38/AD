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
package hundirlaflota.tablero.tipocasilla;

/**
 * Clase que define el tipo de casilla Destructor.
 *
 * @author Javier Tejedor
 */
public class Destructor extends TipoCasilla {

    /**
     * Crea una instancia de Destructor.
     */
    public Destructor() {
        super();
        super.setPartesRestantes(3);
    }

}
