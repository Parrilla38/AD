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
import hundirlaflota.tablero.tipocasilla.Destructor;
import hundirlaflota.tablero.tipocasilla.Escolta;
import hundirlaflota.tablero.tipocasilla.Portaaviones;
import java.util.ArrayList;
import java.util.Random;

/**
 * Clase Máquina que extiende de jugador y contiene toda la logica con la que
 * juega la maquina.
 *
 * @author Javier Tejedor
 */
public class Maquina extends Jugador {

    private ModoJuego modo;
    private int contadorDisparos;
    private ArrayList<Casilla> ultimasCasillas;
    private int[] disparoNuevo;
    private ArrayList<Casilla> casillasDisparadas;
    private int[] ultimoDisparoCertero;

    /**
     * Constructor de la clase Máquina.
     */
    public Maquina() {
        super();
        casillasDisparadas = new ArrayList<>();
        super.setTableroPropio(generarTableroMaquina());
        contadorDisparos = 1;
        ultimasCasillas = new ArrayList<>();
    }

    /**
     * Devuelve el modo con el que está juegando.
     *
     * @return Modo de Juego
     */
    public ModoJuego getModo() {
        return modo;
    }

    /**
     * Configura el modo de juego
     *
     * @param modo Modo de Juego
     */
    public void setModo(ModoJuego modo) {
        this.modo = modo;
    }

    /**
     * Asigna el ultimo disparo certero.
     *
     * @param ultimoDisparoCertero Las coordenadas del disparo que ha dado a
     * algún barco.
     */
    public void setUltimoDisparoCertero(int[] ultimoDisparoCertero) {
        this.ultimoDisparoCertero = ultimoDisparoCertero;
    }

    private int[] disparar(int[] disparo) {
        this.disparoNuevo = disparo;
        super.cambiarEstadoCasilla(disparo);
        return this.casillasDisparadas.add(super.getTableroDeJuego().get(new Index(disparo))) ? disparo : null;
    }

    /**
     * Realiza el disparo, con la lógica correspondiente al modo de juego.
     *
     * @return Devuelve el array de enteros si se ha realizado correctamente el
     * disparo, si no devuelve null.
     */
    public int[] realizarDisparo() {
        ultimasCasillas.clear();
        if (!casillasDisparadas.isEmpty()) {
            for (int i = casillasDisparadas.size() - 5 < 0 ? 0 : casillasDisparadas.size() - 5; i < casillasDisparadas.size(); i++) {
                ultimasCasillas.add(casillasDisparadas.get(i));
            }
        }
        Random rnd = new Random();
        disparoNuevo = new int[]{rnd.nextInt(Casilla.FILA_MAX), rnd.nextInt(Casilla.COLUMNA_MAX)};
        if (modo.equals(ModoJuego.ALEATORIO)) {
            return validarDisparo(disparoNuevo) ? disparar(disparoNuevo) : realizarDisparo();
        } else {

            return modoLogico(disparoNuevo);

        }
    }

    private int[] modoLogico(int[] disparo) {

        contadorDisparos = 0;
        int nCasillasAcertadas = 0;
        if (!ultimasCasillas.isEmpty()) {
            nCasillasAcertadas = ultimasCasillas.stream().map((ultimasCasilla) -> ultimasCasilla.tieneBarco() ? 1 : 0).reduce(nCasillasAcertadas, Integer::sum);

        }

        int[] proximoDisparo = new int[2];

        if (nCasillasAcertadas >= 2) {
            int deltaEjeX = ultimasCasillas.get(ultimasCasillas.size() - 1).getFila() - ultimasCasillas.get(ultimasCasillas.size() - 2).getFila();
            int deltaEjeY = ultimasCasillas.get(ultimasCasillas.size() - 1).getColumna() - ultimasCasillas.get(ultimasCasillas.size() - 2).getColumna();
            if (deltaEjeX == 0) {
                int disparoY = ultimasCasillas.get(ultimasCasillas.size() - 1).getColumna();
                do {
                    disparoY++;
                    if (disparoY >= Casilla.COLUMNA_MAX) {
                        disparoY -= Casilla.COLUMNA_MAX;
                    }
                    proximoDisparo = new int[]{ultimoDisparoCertero[0], disparoY};
                    contadorDisparos++;
                    if (contadorDisparos >= Casilla.COLUMNA_MAX) {
                        return validarDisparo(disparo) ? disparar(disparo) : realizarDisparo();
                    }
                } while (!validarDisparo(proximoDisparo));
                return disparar(proximoDisparo);
            } else if (deltaEjeY == 0) {
                int disparoX = ultimasCasillas.get(ultimasCasillas.size() - 1).getFila();
                do {
                    disparoX++;
                    if (disparoX >= Casilla.FILA_MAX) {
                        disparoX -= Casilla.FILA_MAX;
                    }
                    proximoDisparo = new int[]{disparoX, ultimoDisparoCertero[1]};
                    contadorDisparos++;
                    if (contadorDisparos >= Casilla.FILA_MAX) {
                        return validarDisparo(disparo) ? disparar(disparo) : realizarDisparo();
                    }
                } while (!validarDisparo(proximoDisparo));
                return disparar(proximoDisparo);
            }
        } else if (nCasillasAcertadas == 1
                && (ultimasCasillas.get(ultimasCasillas.size() - 1).getFila() == ultimoDisparoCertero[0] || ultimasCasillas.get(ultimasCasillas.size() - 1).getColumna() == ultimoDisparoCertero[1])) {
            contadorDisparos++;
            do {
                switch (contadorDisparos) {
                    case 1:
                        proximoDisparo = new int[]{(ultimoDisparoCertero[0] + 1) >= Casilla.FILA_MAX ? Casilla.FILA_MIN : ultimoDisparoCertero[0] + 1, ultimoDisparoCertero[1]};
                        break;
                    case 2:
                        proximoDisparo = new int[]{(ultimoDisparoCertero[0] - 1) < Casilla.FILA_MIN ? Casilla.FILA_MAX - 1 : ultimoDisparoCertero[0] - 1, ultimoDisparoCertero[1]};
                        break;
                    case 3:
                        proximoDisparo = new int[]{ultimoDisparoCertero[0], (ultimoDisparoCertero[1] + 1) >= Casilla.COLUMNA_MAX ? Casilla.COLUMNA_MIN : ultimoDisparoCertero[1] + 1};
                        break;
                    case 4:
                        proximoDisparo = new int[]{ultimoDisparoCertero[0], (ultimoDisparoCertero[1] - 1) < Casilla.COLUMNA_MIN ? Casilla.COLUMNA_MAX - 1 : ultimoDisparoCertero[1] - 1};
                        break;
                    default:
                        return validarDisparo(disparo) && contadorDisparos >= 5 ? disparar(disparo) : realizarDisparo();
                }
                contadorDisparos++;
            } while (!validarDisparo(proximoDisparo));
            return disparar(proximoDisparo);
        }
        return validarDisparo(disparo) ? disparar(disparo) : realizarDisparo();
    }

    private boolean validarDisparo(int[] disparo) {
        return !casillasDisparadas.contains(super.getTableroDeJuego().get(new Index(disparo)))
                && disparo[0] < Casilla.FILA_MAX
                && disparo[0] >= Casilla.FILA_MIN
                && disparo[1] < Casilla.COLUMNA_MAX
                && disparo[1] >= Casilla.COLUMNA_MIN;
    }

    private static Tablero generarTableroMaquina() {
        Tablero tablero = new Tablero();
        boolean correcto = false;
        Random random = new Random();
        while (!correcto) {
            correcto = tablero.cambiarTipoCasillas(
                    new int[]{random.nextInt(Casilla.FILA_MAX), random.nextInt(Casilla.COLUMNA_MAX)},
                    new int[]{random.nextInt(Casilla.FILA_MAX), random.nextInt(Casilla.COLUMNA_MAX)},
                    new Escolta()
            );
        }
        correcto = false;
        while (!correcto) {
            correcto = tablero.cambiarTipoCasillas(
                    new int[]{random.nextInt(Casilla.FILA_MAX), random.nextInt(Casilla.COLUMNA_MAX)},
                    new int[]{random.nextInt(Casilla.FILA_MAX), random.nextInt(Casilla.COLUMNA_MAX)},
                    new Destructor()
            );
        }
        correcto = false;
        while (!correcto) {
            correcto = tablero.cambiarTipoCasillas(
                    new int[]{random.nextInt(Casilla.FILA_MAX), random.nextInt(Casilla.COLUMNA_MAX)},
                    new int[]{random.nextInt(Casilla.FILA_MAX), random.nextInt(Casilla.COLUMNA_MAX)},
                    new Portaaviones()
            );
        }
        return tablero;
    }

}
