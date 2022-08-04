/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAW.arkanoid.modelo;

import javafx.geometry.Point2D;

/**
 *
 * @author Mateo y Fran LA CLASE powerup tiene un Point2d para representar la X
 * y la Y. UN Entero estático para representar el ANCHO y otro para representar
 * el ALTO Un entero que determina el INCREMENTO de su posición conseguir que
 * caiga el powerup Un entero que se incializa entre 0 y 6 para crear
 * aleatoriamente el tipo de powerup, si es 7 el powerup está creado pero
 * inactivo Y por último un booleano que determina si está cayendo o parado
 * esperando a ser activado. Tiene 1 método, que hará que cada vez que se llame
 * bajará la posición del powerup según el incremento, para así lograr que caiga
 */
public class Powerup {

    private Point2D posicion;
    public static int ancho = 22;
    public static int alto = 11;
    private int tipopowerup;
    private boolean cayendo;
    private int incremento = 3;

    /**
     * CONSTRUCTOR POR DEFECTO
     */
    public Powerup() {
        this.posicion = new Point2D(0, 0);
    }

    /**
     * CONSTRUCTOR SOBRECARGADO se le pasa la posición de point2d y un booleano
     * que determina si está cayendo o no
     *
     * @param posicion
     * @param cayendo
     */
    public Powerup(Point2D posicion, boolean cayendo) {
        this.posicion = posicion;
        this.cayendo = cayendo;
        this.tipopowerup = (int) (Math.random() * 7);

    }

    public void gravedad() {
        this.setPosicion(this.getPosicion().add(0, this.incremento));

    }

    /**
     * @return the posicion
     */
    public Point2D getPosicion() {
        return posicion;
    }

    /**
     * @param posicion the posicion to set
     */
    public void setPosicion(Point2D posicion) {
        this.posicion = posicion;
    }

    /**
     * @return the ancho
     */
    public int getAncho() {
        return ancho;
    }

    /**
     * @param ancho the ancho to set
     */
    public void setAncho(int ancho) {
        this.ancho = ancho;
    }

    /**
     * @return the alto
     */
    public int getAlto() {
        return alto;
    }

    /**
     * @param alto the alto to set
     */
    public void setAlto(int alto) {
        this.alto = alto;
    }

    /**
     * @return the tipopowerup
     */
    public int getTipopowerup() {
        return tipopowerup;
    }

    /**
     * @param tipopowerup the tipopowerup to set
     */
    public void setTipopowerup(int tipopowerup) {
        this.tipopowerup = tipopowerup;
    }

    /**
     * @return the cayendo
     */
    public boolean isCayendo() {
        return cayendo;
    }

    /**
     * @param cayendo the cayendo to set
     */
    public void setCayendo(boolean cayendo) {
        this.cayendo = cayendo;
    }
}
