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

/**
 * Clase index, par las claves del tablero.
 *
 * @author Javier Tejedor
 */
public class Index implements Comparable<Index> {

    private int[] i;

    /**
     * Crea una instancia de Index a partir de un array de enteros
     *
     * @param i Array de enteros
     */
    public Index(int[] i) {
        this.i = i;
    }

    private int[] getI() {
        return i;
    }

    /**
     * Compara las coordenadas de los dos index.
     *
     * @param t Index a comparar.
     * @return 0 si son iguales.
     */
    @Override
    public int compareTo(Index t) {

        return (this.i[0] - t.getI()[0]) * 10 - (this.i[1] - t.getI()[1]);
    }

    /**
     * Comprueba si son iguales los objetos a comparar.
     *
     * @param obj Objeto a comparar.
     * @return Verdadero si son iguales.
     */
    @Override
    public boolean equals(Object obj) {
        return (obj instanceof Index) ? this.compareTo((Index) obj) == 0 : false;
    }

}
