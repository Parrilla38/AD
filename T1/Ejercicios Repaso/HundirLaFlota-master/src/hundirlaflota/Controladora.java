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
package hundirlaflota;

import hundirlaflota.interfaz.PantallaSelectorModo;
import hundirlaflota.jugador.Jugador;
import hundirlaflota.jugador.Maquina;
import hundirlaflota.tablero.Tablero;
import hundirlaflota.tablero.tipocasilla.Destructor;
import hundirlaflota.tablero.tipocasilla.Escolta;
import hundirlaflota.tablero.tipocasilla.Portaaviones;

/**
 * Esta clase es la principal y la conecta todos los elementos del programa
 *
 * @author Javier Tejedor
 */
public class Controladora {

    /**
     * Imagen .png vacía
     */
    public static String IMAGEN_VACIA = "src/hundirlaflota/img/vacia.png";

    /**
     * Icono .png de de la aplicacion
     */
    public static String ICONO_APP = "src/hundirlaflota/img/barco.png";
    private Jugador humano;
    private Maquina maquina;

    /**
     * Partes totales de los barcos, se utiliza para calcular si todos los
     * barcos estan hundidos o no.
     */
    public static int partesTotales = (new Escolta().getPartesRestantes() + new Destructor().getPartesRestantes() + new Portaaviones().getPartesRestantes());

    /**
     * El metodo main de la aplicación.
     */
    public static void main(String[] args) {
        Controladora c = new Controladora();
        new PantallaSelectorModo(c).setVisible(true);
    }

    /**
     * El constructor de esta clase
     */
    public Controladora() {
        this.humano = new Jugador(new Tablero());
        this.maquina = new Maquina();
        this.humano.setTableroDeJuego(this.maquina.getTableroPropio());
    }

    /**
     * @return Devuelve el atributo Humano de esta clase.
     */
    public Jugador getHumano() {
        return humano;
    }

    /**
     * @return Devuelve el atributo Maquina de esta clase.
     */
    public Maquina getMaquina() {
        return maquina;
    }

}
