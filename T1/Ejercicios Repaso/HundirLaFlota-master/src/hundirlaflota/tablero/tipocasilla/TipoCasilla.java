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
 * Clase que define el tipo de casilla génerico.
 *
 * @author Javier Tejedor
 */
public abstract class TipoCasilla {

    private int partesRestantes;
    private boolean tocado;
    private final int tipoIndex;

    /**
     * Genera una casilla vacía
     */
    public TipoCasilla() {
        tocado = false;
        if (this instanceof Escolta) {
            tipoIndex = 1;
        } else if (this instanceof Destructor) {
            tipoIndex = 2;
        } else if (this instanceof Portaaviones) {
            tipoIndex = 3;
        } else {
            tipoIndex = 0;
        }
    }

    /**
     * Configura las partes restantes/iniciales del casilla.
     *
     * @param partesRestantes Partes restantes
     */
    public void setPartesRestantes(int partesRestantes) {
        this.partesRestantes = partesRestantes;
    }

    /**
     * Devuelve las partes restantes del tipo de casilla
     *
     * @return Devuelve las partes restantes.
     */
    public int getPartesRestantes() {
        return partesRestantes;
    }

    /**
     * @return Devuelve el index del tipo.
     */
    public int getTipoIndex() {

        return tipoIndex;
    }

    /**
     * Resta una parte a las restantes.
     */
    public void restarParte() {

        this.partesRestantes--;
    }

    /**
     * Comprueba si está tocado.
     *
     * @return Devuelve verdadero si está tocado.
     */
    public boolean isTocado() {
        return tocado;
    }

    /**
     * Cambia el valor del atributo tocado.
     *
     * @param tocado Boolean.
     */
    public void setTocado(boolean tocado) {
        this.tocado = tocado;
    }

}
