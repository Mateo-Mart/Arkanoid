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
 * El ladrillo tiene un Point2d para representar la x y la y.
 * Un entero que determina la dureza del ladrillo.
 * Un boolean que determina si es un ladrillo fijo o se puede romper.
 * Dos enteros estáticos para representar el ancho y alto del ladrillo.
 * 
 */
public class Ladrillo {
    private Point2D posicion;
    private int dureza;
    private boolean fijo;
    public static int ANCHO=32;
    public static int ALTO=16;
    
    public Ladrillo(){
        this.posicion = new Point2D(0, 0);
    }
    /**
     * Constructor Sobrecargado
     * @param posicion
     * @param dureza
     * @param fijo 
     */
    public Ladrillo(Point2D posicion,int dureza, boolean fijo) {
        this.posicion = posicion;
        this.dureza=dureza;
        this.fijo = fijo;
       
        
    }
    /**
     * Método creado para bajar la dureza de el ladrillo en un punto
     */
    public void bajarDureza(){
        this.dureza --;
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
     * @return the dureza
     */
    public int getDureza() {
        return dureza;
    }

    /**
     * @param dureza the dureza to set
     */
    public void setDureza(int dureza) {
        this.dureza = dureza;
    }
    
    /**
     * @return the fijo
     */
    public boolean isFijo() {
        return fijo;
    }

    /**
     * @param fijo the fijo to set
     */
    public void setFijo(boolean fijo) {
        this.fijo = fijo;
    }


    
}
