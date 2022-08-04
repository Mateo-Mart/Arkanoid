/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAW.arkanoid.modelo;

import javafx.geometry.Point2D;

/**
 *
 * @author Mateo
 * LA clase nivel tiene una matriz de objeto Ladrillo y otra de
 * objeto Powerup. Un booleano que determina si el nivel está terminado o no. Un
 * point2d que determina la posición donde empieza a pintarse el nivel.
 *
 * el método crearladrillos recibe una matriz y una posición para según el
 * numero de la matriz ponerle la dureza al ladrillo y según la posición poner
 * ahí el ladrillo
 *
 * el método crear powerups con el cual TENEMOS UNA ANIMACIÓN DE RUPTURA DE
 * BLOQUES, CON LA CUAL LOS BLOQUES TIENEN 4 ESTADOS DE DUREZA SEGÚN EL COLOR,
 * PARA ELLO //RESERVAMOS LAS CÍFRAS CON MÚLTIPLO DE 5 PARA HACER EL BLOQUE A 0
 * CUANDO SE LLEGUE A ELLAS //SI EL RESTO DE LA DUREZA DEL BLOQUE /5 ES 0, NO SE
 * SE PINTARÁ POWERUP EN ESE BLOQUE //PARA REALIZAR EL QUE NO SE PINTE, A EL
 * POWERUP EN CUESTIÓN LE PONDREMOS DE TIPO 7, //EL TIPO 7 NUNCA SE PINTARÁ NI
 * CAERÁ NI NADA
 *
 * el método detectar si paso de nivel comprueba los ladrillos y si queda alguno
 * devuelve false y si no queda ninguno, pasa de nivel
 *
 */
public class Nivel {

    private boolean finalizado;
    private Ladrillo ladrillos[][];
    private Powerup powerups[][];
    private Point2D inicio;

    /**
     * CONSTRUCTOR SOBRECARGADO Se le pasa una matriz para la dureza de los
     * ladrillos y una posición.
     *
     * @param matriz
     * @param posicion
     */
    public Nivel(int matriz[][], Point2D posicion) {
        this.inicio = posicion;
        this.crearLadrillos(matriz, posicion);
        this.crearPowerups(matriz, posicion);

    }

    public void crearLadrillos(int m[][], Point2D pos) {
        this.setLadrillos(new Ladrillo[m.length][m[1].length]);

        for (int i = 0; i < this.getLadrillos().length; i++) {
            for (int j = 0; j < this.getLadrillos()[i].length; j++) {
                this.getLadrillos()[i][j] = new Ladrillo(new Point2D(pos.getX() + Ladrillo.ANCHO * j, pos.getY() + Ladrillo.ALTO * i), m[i][j], false);
            }

        }
    }

    public void crearPowerups(int m[][], Point2D pos) {
        this.setPowerups(new Powerup[m.length][m[1].length]);
        for (int i = 0; i < this.getPowerups().length ; i++) {
            for (int j = 0; j < this.getPowerups()[0].length ; j++) {
                //el método crear powerups con el cual   TENEMOS UNA ANIMACIÓN DE RUPTURA DE BLOQUES, CON LA CUAL LOS BLOQUES TIENEN 4 ESTADOS DE DUREZA SEGÚN EL COLOR, PARA ELLO
                //RESERVAMOS LAS CÍFRAS CON MÚLTIPLO DE 5 PARA HACER EL BLOQUE A 0 CUANDO SE LLEGUE A ELLAS
                //SI EL RESTO DE LA DUREZA DEL BLOQUE /5 ES 0, NO SE SE PINTARÁ POWERUP EN ESE BLOQUE
                //PARA REALIZAR EL QUE NO SE PINTE, A EL POWERUP EN CUESTIÓN LE PONDREMOS A NULL
                if (m[i][j] % 5 == 0) {
                    this.getPowerups()[i][j] = null;
                } else {
                    //HACEMOS UNA POSIBILIDAD DE UN 25% PARA QUE SE CREE EL POWERUP 
                    if ((int) (Math.random() * 4) == 3) {
                        this.getPowerups()[i][j] = new Powerup(new Point2D(pos.getX() + Ladrillo.ANCHO * j, pos.getY() + Ladrillo.ALTO * i), false);
                        //Y LO DEMÁS, EL 75% NO SE PINTARÁN Y ESTARÁN INACTIVOS
                    } else {

                        this.getPowerups()[i][j]=null;

                    }
                }

            }

        }
    }

    public boolean detectarSiPasardeNivel() {
        boolean pasodenivel = true;

        for (int i = 0; i < this.getLadrillos().length; i++) {
            for (int j = 0; j < this.getLadrillos()[i].length; j++) {
                if (this.getLadrillos()[i][j].getDureza() != 0 || this.getLadrillos()[i][j].isFijo() == true) {
                    //System.out.println("he detectado que quedan ladrillos");
                    pasodenivel = false;
                }

            }
        }
        return pasodenivel;
    }

    /**
     * @return the ladrillo
     */
    public Ladrillo[][] getLadrillos() {
        return ladrillos;
    }

    /**
     * @param ladrillo the ladrillo to set
     */
    public void setLadrillos(Ladrillo[][] ladrillo) {
        this.ladrillos = ladrillo;
    }

    /**
     * @return the inicio
     */
    public Point2D getInicio() {
        return inicio;
    }

    /**
     * @param inicio the inicio to set
     */
    public void setInicio(Point2D inicio) {
        this.inicio = inicio;
    }

    /**
     * @return the finalizado
     */
    public boolean isFinalizado() {
        return finalizado;
    }

    /**
     * @param finalizado the finalizado to set
     */
    public void setFinalizado(boolean finalizado) {
        this.finalizado = finalizado;
    }

    /**
     * @return the powerups
     */
    public Powerup[][] getPowerups() {
        return powerups;
    }

    /**
     * @param powerups the powerups to set
     */
    public void setPowerups(Powerup[][] powerups) {
        this.powerups = powerups;
    }

}
