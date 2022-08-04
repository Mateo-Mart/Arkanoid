/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAW.arkanoid.modelo;

import javafx.geometry.Point2D;
import javax.swing.text.Position;

/**
 *
 * @author Mateo 
 * LA CLASE BARRA TIENE UN Point2d para representar la X y la Y.
 * UN Entero para representar el ANCHO y otro para representar el ALTO 
 * Y Entero que determina el INCREMENTO de su posición al mover la barra
 * Tiene 4 métodos, uno para mover la barra a cada uno de los puntos cardinales
 */
public class Barra {

    private Point2D posicion;
    private int ancho;
    private int alto;
    private int incremento = 2;
    
    public Barra() {
        this.posicion = new Point2D(0, 0);
    }
    /**
     * Constructor Sobrecargado 
     * @param posicion 
     */
    public Barra(Point2D posicion) {
        this.posicion = posicion;
    }
    
    public void moverDerecha() {
        this.posicion = this.posicion.add(this.incremento, 0);

    }

    public void moverIzquierda() {
        this.posicion = this.posicion.add(-this.incremento, 0);

    }

    public void moverArriba() {
        this.posicion = this.posicion.add(0, -this.incremento);

    }

    public void moverAbajo() {
        this.posicion = this.posicion.add(0, this.incremento);

    }

    /**
     * @return the posicion
     */
    public Point2D getPosicion() {
        return posicion;
    }

    /**
     * @return the ancho
     */
    public int getAncho() {
        return ancho;
    }

    /**
     * @return the alto
     */
    public int getAlto() {
        return alto;
    }

    /**
     * @param posicion the posicion to set
     */
    public void setPosicion(Point2D posicion) {
        this.posicion = posicion;
    }

    /**
     * @param ancho the ancho to set
     */
    public void setAncho(int ancho) {
        this.ancho = ancho;
    }

    /**
     * @param alto the alto to set
     */
    public void setAlto(int alto) {
        this.alto = alto;
    }

}
