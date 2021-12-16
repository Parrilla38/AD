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

import hundirlaflota.tablero.tipocasilla.Agua;
import hundirlaflota.tablero.tipocasilla.TipoCasilla;
import java.util.TreeMap;

/**
 * Clase que define el tablero genérico.
 *
 * @author Javier Tejedor
 */
public class Tablero extends TreeMap<Index, Casilla> {

    /**
     * Crea un tablero con todas las casillas siendo agua.
     */
    public Tablero() {
        super();
        generarTableroVacio();
    }

    private void generarTableroVacio() {
        for (int i = 0; i < Casilla.FILA_MAX; i++) {
            for (int j = 0; j < Casilla.COLUMNA_MAX; j++) {
                this.put(new Index(new int[]{i, j}), new Casilla(i, j, new Agua()));
            }
        }
    }

    /**
     * Cambia el tipo de casilla, desde una posicion inicial y hasta posicion
     * final inclusive.
     *
     * @param posInicial Posicion Inicial
     * @param posFinal Posicion Final
     * @param tipo Tipo de Casilla
     * @return Devuelve verdadero si todo ha ido correctamente.
     */
    public boolean cambiarTipoCasillas(int[] posInicial, int[] posFinal, TipoCasilla tipo) {

        int deltaEjeX = posFinal[0] - posInicial[0];
        int deltaEjeY = posFinal[1] - posInicial[1];
        boolean casillasValidas = true;
        if (deltaEjeX == 0 && deltaEjeY != 0) {
            if (deltaEjeY < 0) {
                int[] tmp = posFinal.clone();
                posFinal = posInicial.clone();
                posInicial = tmp;
            }
            for (int i = posInicial[1]; i <= posFinal[1]; i++) {
                if (!this.get(navigableKeySet().ceiling(new Index(new int[]{posFinal[0], i}))).esValida(this)) {
                    casillasValidas = false;
                }
            }
            if (Math.abs(deltaEjeY) + 1 == tipo.getPartesRestantes() && casillasValidas) {

                for (int i = posInicial[1]; i <= posFinal[1]; i++) {
                    this.get(new Index(new int[]{posFinal[0], i})).setTipo(tipo);
                }
                return true;
            } else {
                return false;
            }
        } else if (deltaEjeY == 0 && deltaEjeX != 0) {
            if (deltaEjeX < 0) {
                int[] tmp = posFinal.clone();
                posFinal = posInicial.clone();
                posInicial = tmp;
            }
            for (int i = posInicial[0]; i <= posFinal[0]; i++) {
                if (!this.get(navigableKeySet().ceiling(new Index(new int[]{i, posFinal[1]}))).esValida(this)) {
                    casillasValidas = false;
                }
            }
            if (Math.abs(deltaEjeX) + 1 == tipo.getPartesRestantes() && casillasValidas) {

                for (int i = posInicial[0]; i <= posFinal[0]; i++) {
                    this.get(new Index(new int[]{i, posFinal[1]})).setTipo(tipo);
                }
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

}
