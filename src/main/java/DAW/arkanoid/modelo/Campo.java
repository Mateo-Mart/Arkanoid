/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAW.arkanoid.modelo;

import javafx.geometry.Point2D;

/**
 *
 * @author Pedro
 * LA CLASE Campo Tiene dos enteros estáticos para definir el ancho y el alto.
 * Un objeto de tipo barra.
 * Un objeto de tipo pelota.
 * Un entero para definir las vidas.
 * Y Un entero estático para definir el borde del campo
 * 
 */
public class Campo {

    private static int width, height;
    private Barra barra;
    //private Powerup powerups[];
    private Pelota pelota;
    private int vidas;
    public static int BORDE = 16;
    public int borde = Campo.BORDE;
    /**
     * CONSTRUCTOR SOBRECARGADO
     * @param anchopixels
     * @param altopixels 
     */
    public Campo(int anchopixels, int altopixels) {
        Campo.width = anchopixels;
        Campo.height = altopixels;
        this.vidas = 3;
        this.barra = new Barra(new Point2D(anchopixels / 2 - 33, altopixels - 29));
        
        //pelota sin modificar"sale por 0/0"  
        this.pelota = new Pelota(new Point2D(this.barra.getPosicion().getX() + this.barra.getAncho(), this.barra.getPosicion().getY() - 12), 10);
        this.pelota.setAngulo(290);
    }
    

    public void setVelocidad(float velocidad) {
        this.pelota.setVelocidad(velocidad);
    }

    public float getVelocidad() {
        return this.pelota.getVelocidad();
    }

    /**
     * @return the barra
     */
    public Barra getBarra() {
        return barra;
    }

    /**
     * @return the pelota
     */
    public Pelota getPelota() {
        return pelota;
    }

    /**
     * @return the borde
     */
    public int getBorde() {
        return borde;
    }

    /**
     * @return the width
     */
    public static int getWidth() {
        return width;
    }

    /**
     * @return the height
     */
    public static int getHeight() {
        return height;
    }

    /**
     * @param aWidth the width to set
     */
    public static void setWidth(int aWidth) {
        width = aWidth;
    }

    /**
     * @param aHeight the height to set
     */
    public static void setHeight(int aHeight) {
        height = aHeight;
    }

    /**
     * @param borde the borde to set
     */
    public void setBorde(int borde) {
        this.borde = borde;
    }

    /**
     * @param barra the barraizquierda to set
     */
    public void setBarra(Barra barra) {
        this.barra = barra;
    }

    /**
     * @return the vidas
     */
    public int getVidas() {
        return vidas;
    }

    /**
     * @param vidas the vidas to set
     */
    public void setVidas(int vidas) {
        this.vidas = vidas;
    }

    public void perderVida() {
        this.vidas--;
    }

    
    
}
