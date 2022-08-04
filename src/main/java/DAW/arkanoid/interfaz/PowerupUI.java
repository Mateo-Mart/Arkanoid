/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAW.arkanoid.interfaz;

import DAW.arkanoid.modelo.Juego;
import DAW.arkanoid.modelo.Powerup;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 *
 * @author Mateo
 * LA CLASE powerupUI tiene un objeto Powerup.
 * un entero que tendrá el estado actual
 * Un  entero estático que determina la cantidad de estados que tiene la animación. 
 * Un vector de point2d que determinarán la posición de la imagen donde está el powerup en cuestión
 * Un objeto Image que tendrá la imagen de la cual se pintarán los powerups
 * tiene dos longs para hacer un bucle que determina cada cuanto tiempo hace la animación de girar
 * Y por último un vector que tiene almacenados la posición donde se encuentra cada tipo de powerup en la imagen
 * 
 * Tiene 4 métodos.
 * tiene un método llamado mapear que hace que según el estado de la animación le de su respectiva posición en la imagen
 * tiene un metodo llamado pintar que tiene el bucle de la animación
 *      cuando se pinta el powerup entonces es cuando se realiza el método gravedad para que caiga el powerup
 * tiene un método llamado colortipoPowerup que es un switch el cual devuelve la posición de Y donde se encuentra ese color de powerup segun su tipo en la imagen para pintarlo
 * el último método llamado inc que hace que vaya rotando el numero de estado para crear la animación
*/
public class PowerupUI {

    private Powerup powerup;
    private int estado;
    private static int NUMESTADOS = 8;
    private Point2D dibujopowerup[];
    private Image imagen;
    private long last;
    private long tope = 70;
    private int colorpowerups[] = {
        //naranja  TIPO 0
        0,
        //verde TIPO 1
        8,
        //rojo  TIPO 2
        16,
        //azul   TIPO 3
        24,
        //celeste  TIPO 4
        32,
        //rosa    TIPO 5
        40,
        //gris    TIPO 6 
        48
    };
/**
 * CONSTRUCTOR SOBRECARGADO
 * se le pasa la imagen y el powerup para pintarlo
 * @param imagen
 * @param powerup 
 */
    public PowerupUI(Image imagen, Powerup powerup) {
        this.estado = 0;
        this.mapear(this.ColorTipoPowerup(powerup));
        this.imagen = imagen;
        this.powerup = powerup;
        this.last = System.currentTimeMillis();
        
    }

    public void mapear(int a) {
        this.dibujopowerup = new Point2D[PowerupUI.NUMESTADOS];
        for (int i = 0; i < this.dibujopowerup.length; i++) {
            this.dibujopowerup[i] = new Point2D(i * 16, a);
        }
    }

    public void pintar(GraphicsContext gc) {
        long now = System.currentTimeMillis();

        if (now - this.last > this.tope) {
            this.inc();
            this.last = now;
            //LE HAGO QUE CAIGA A LA VELOCIDAD DE CADA CICLO DE PINTAR
            if (this.powerup.getPosicion().getY()
                    < Juego.ALTO*1.4 //400
                    &&this.powerup.isCayendo()&&this.powerup.getTipopowerup()!=7) {
                this.powerup.gravedad();
            }
        }
        gc.drawImage(this.imagen, this.dibujopowerup[this.estado].getX(), this.dibujopowerup[this.estado].getY(), 16, 8, this.powerup.getPosicion().getX(), this.powerup.getPosicion().getY(), this.powerup.getAncho(), this.powerup.getAlto());

    }
    public int ColorTipoPowerup(Powerup p) {
        int colordevuelto = 1;
        switch (p.getTipopowerup()) {
            case (0): {
                colordevuelto = this.colorpowerups[0];
                break;
            }
            case (1): {
                colordevuelto = this.colorpowerups[1];

                break;
            }
            case (2): {
                colordevuelto = this.colorpowerups[2];

                break;
            }
            case (3): {
                colordevuelto = this.colorpowerups[3];

                break;
            }
            case (4): {
                colordevuelto = this.colorpowerups[4];

                break;
            }
            case (5): {
                colordevuelto = this.colorpowerups[5];

                break;
            }
            case (6): {
                colordevuelto = this.colorpowerups[6];

                break;
            }
        }
        return colordevuelto;
    }
    
    public void inc() {
        this.estado++;
        if (this.estado >= PowerupUI.NUMESTADOS) {
            this.estado = 0;
        }
    }

}
