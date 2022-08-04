/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAW.arkanoid.modelo;

import javafx.geometry.Point2D;

/**
 *
 * @author Pedro La pelota tiene un Point2d para representar la x y la y. Un
 * float para representar su angulo, otro float para representar la velocidad.
 * Un entero que determina el radio de la pelota. Por último un booleano para
 * determinar si tiene su poder especial activado o no.
 * Tiene un método llamado mover que mediante cálculos matemáticos aprovechará
 * el angulo para poner perfectamente la posición de la pelota.
 */
public class Pelota {

    private Point2D posicion;
    private float angulo = 45;
    private float velocidad = 1f;
    private int radio = 8;
    private boolean powerupactivado;

    public Pelota() {
        this.posicion = new Point2D(0, 0);
        this.radio = 8;
        this.powerupactivado = false;
    }

    /**
     * Constructor sobrecargado
     *
     * @param posicion
     * @param radio
     */
    public Pelota(Point2D posicion, int radio) {
        this.posicion = posicion;
        this.powerupactivado = false;
        this.radio = radio;
    }

    public void mover() {
        float x = (float) ((float) getVelocidad() * Math.cos(Math.toRadians(getAngulo())));
        float y = (float) ((float) getVelocidad() * Math.sin(Math.toRadians(getAngulo())));
        this.posicion = this.getPosicion().add(x, y);

    }

    /**
     * @return the angulo
     */
    public float getAngulo() {
        return angulo;
    }

    /**
     * @param angulo the angulo to set
     */
    public void setAngulo(float angulo) {
        this.angulo = angulo;
        if (this.angulo < 0) {
            this.angulo += 360;
        }
        if (this.angulo >= 360) {
            this.angulo = this.angulo % 360;
        }

    }

    public void addAngulo(float angulo) {
        this.angulo += angulo;
        if (this.angulo >= 360) {
            this.angulo = this.angulo % 360;
        }
        if (this.angulo < 0) {
            this.angulo += 360;
        }
    }

    /**
     * @return the radio
     */
    public int getRadio() {
        return radio;
    }

    /**
     * @param radio the radio to set
     */
    public void setRadio(int radio) {
        this.radio = radio;
    }

    /**
     * @return the velocidad
     */
    public float getVelocidad() {
        return velocidad;
    }

    /**
     * @param velocidad the velocidad to set
     */
    public void setVelocidad(float velocidad) {
        this.velocidad = velocidad;
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
     * @return the powerupactivado
     */
    public boolean isPowerupactivado() {
        return powerupactivado;
    }

    /**
     * @param powerupactivado the powerupactivado to set
     */
    public void setPowerupactivado(boolean powerupactivado) {
        this.powerupactivado = powerupactivado;
    }

}
