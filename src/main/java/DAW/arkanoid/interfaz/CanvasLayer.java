/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAW.arkanoid.interfaz;

import DAW.arkanoid.modelo.Campo;
import DAW.arkanoid.modelo.Juego;

import javafx.geometry.Point2D;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;


/**
 *
 * @author Pedro
 */
public class CanvasLayer {

    private Canvas principal;
    private Canvas fondo;
  


   

    public CanvasLayer(int ancho, int alto, Juego j) {
         this.principal = new Canvas(ancho, alto);
        this.fondo = new Canvas(ancho, alto);
       
             
    }

    public void clear(){
        this.fondo.getGraphicsContext2D().clearRect(0, 0, this.fondo.getWidth(), this.fondo.getHeight());
        this.principal.getGraphicsContext2D().clearRect(0, 0, this.principal.getWidth(), this.principal.getHeight());
        
    }    


   

    /**
     * @return the principal
     */
    public Canvas getPrincipal() {
        return principal;
    }

    /**
     * @return the fondo
     */
    public Canvas getFondo() {
        return fondo;
    }

    

   
}
