/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAW.arkanoid.interfaz;

import DAW.arkanoid.modelo.Barra;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 *
 * @author Pedro
 * LA CLASE BarraUI tiene un objeto Barra.
 * Un entero que tendrá el estado actual
 * Un  entero estático que determina la cantidad de estados que tiene la animación. 
 * Un vector de point2d que determinarán la posición de la imagen donde está el powerup en cuestión
 * Un objeto Image que tendrá la imagen de la cual se pintarán los powerups
 * tiene dos longs para hacer un bucle que determina cada cuanto tiempo hace la animación de girar
 * Y por ultimo un entero que se activará si se pierde la partida para hacer una animación especial 
 * 
 * Tiene 4 métodos.
 * tiene un método llamado mapear que hace que según el estado de la animación le de su respectiva posición en la imagen
 * tiene un metodo llamado pintar que tiene el bucle de la animación
 * tiene un método llamado inc que hace que vaya rotando el numero de estado para crear la animación
 * el último método llamado incfinal que hace que vaya rotando el numero de estadofinal para crear la animaciónfinal

 */
public class BarraUI {

    private Barra barra;
    private int estado;
    private int estadofinal;
    private static int NUMESTADOS = 6;
    private Point2D dibujobarras[];
    private Image imagen;
    private long last;
    private long tope = 500;
    /**
     * CONSTRUCTOR SOBRECARGADO
     * @param imagen
     * @param barra 
     */
    public BarraUI(Image imagen, Barra barra) {
        this.estado = 0;
        this.estadofinal = 0;
        this.mapear();
        this.imagen = imagen;
        this.barra = barra;
        this.last = System.currentTimeMillis();
    }

    private void mapear() {
        this.dibujobarras = new Point2D[BarraUI.NUMESTADOS];
        for (int i = 0; i < this.dibujobarras.length; i++) {
            this.dibujobarras[i] = new Point2D(32, i * 8);
        }
    }

    public void pintar(GraphicsContext gc, boolean animacionmuerte) {
        long now=System.currentTimeMillis();
        //System.out.println(this.last+" "+now);
        if(now -this.last>this.tope){
            this.inc();
            this.last=now;
            if (animacionmuerte) {
                this.incfinal();
            }
        }if (animacionmuerte) {
            gc.drawImage(this.imagen, 176, this.dibujobarras[this.estadofinal].getY(), 32, 8, this.barra.getPosicion().getX(), this.barra.getPosicion().getY(), this.barra.getAncho(), this.barra.getAlto());//this.campo.getBarra().getPosicion().getX(),this.campo.getBarra().getPosicion().getY(),280,140);//this.campo.getBarra().getPosicion().getX(),this.campo.getBarra().getPosicion().getY(),23,8,247,240);
            if (estadofinal==5) {
                System.exit(0);

        }
        }else{
        gc.drawImage(this.imagen, this.dibujobarras[this.estado].getX(), this.dibujobarras[this.estado].getY(), 32, 8, this.barra.getPosicion().getX(), this.barra.getPosicion().getY(), this.barra.getAncho(), this.barra.getAlto());//this.campo.getBarra().getPosicion().getX(),this.campo.getBarra().getPosicion().getY(),280,140);//this.campo.getBarra().getPosicion().getX(),this.campo.getBarra().getPosicion().getY(),23,8,247,240);
        }
    }

    public void inc() {
        this.estado++;
        if (this.estado >= BarraUI.NUMESTADOS) {
            this.estado = 0;
        }
    }
    public void incfinal() {
        this.estadofinal++;
        
    }

}
