/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAW.arkanoid.interfaz;

import DAW.arkanoid.modelo.Campo;
import DAW.arkanoid.modelo.EstadoCambiosJuego;
import DAW.arkanoid.modelo.Juego;
import DAW.arkanoid.modelo.Ladrillo;
import DAW.arkanoid.modelo.Powerup;
import javafx.geometry.Point2D;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.util.Duration;

/**
 *
 * @author Pedro
 */
public class JuegoUI {

    private String pathintro = "C:/nav.png";
    private Juego juego;
    private Point2D posicion;
    private Image imagen;
    private Image imagen_fondo;
    private Image imagen_barrasypelota;
    private Image imagen_powerups;
    private Image imagen_bloques;
    private BarraUI barraui;
    private PowerupUI[][] powerupsui;
    MediaPlayer sonido_tocabarra, sonido_tocaladrillo, sonido_fallo, sonido_nivel, sonido_powerup, sonido_bordes, sonido_muerte;
    boolean debug;
    private String pathfondos = "fondos.png";
    private String pathbarras = "barras.png";
    private String pathpowerups = "Powerups.png";
    private String pathbloques = "BlocksBackgrounds.png";
    private String pathsonidoladrillo = "sounds/golpe.wav";
    private String pathsonidobarra = "sounds/yunque_2.mp3";
    private String pathsonidopowerup = "sounds/recojopowerup.mp3";
    private String pathsonidobordes = "sounds/rebote_con_eco.mp3";
    private String pathsonidofallo = "sounds/fallo.mp3";
    private String pathsonidomuerte = "sounds/muerte.mp3";
    private String pathsonidonivel = "sounds/nuevonivel.mp3";
    private int colorladrillos[][] = {
        //blanco 0 
        {0, 0},
        //blanco roto 1 
        {16, 0},
        //Blanco roto 2
        {32, 0},
        //blanco roto 3 
        {48, 0},
        //azul  
        {0, 8},
        //azul roto 1   
        {16, 8},
        //azul roto 2  
        {32, 8},
        //azul roto 3   
        {48, 8},
        //amarillo   8
        {0, 16},
        //amarillo roto 1
        {16, 16},
        //amarillo roto 2
        {32, 16},
        //amarillo roto 3 
        {48, 16},
        //verde   
        {0, 24},
        //verde roto 1
        {16, 24},
        //verde roto 2
        {32, 24},
        //verde roto 3 
        {48, 24},
        //Rosa
        {0, 32},
        //Rosa roto 1
        {16, 32},
        //Rosa roto 2
        {32, 32},
        //Rosa roto 3
        {48, 32},
        //Azul
        {0, 40},
        //Azul roto 1
        {16, 40},
        //Azul roto 2
        {32, 40},
        //Azul roto 3
        {48, 40},
        //Naranja
        {0, 48},
        //Naranja roto 1
        {16, 48},
        //Naranja roto 2
        {32, 48},
        //Naranja roto 3
        {48, 48},
        //Negro
        {0, 56},
        //Negro roto 1
        {16, 56},
        //Negro roto 2
        {32, 56},
        //Negro roto 3
        {48, 56},
        //rojo
        {64, 0},
        //rojo roto 1   
        {80, 0},
        //rojo roto 2    
        {96, 0},
        //rojo roto 3   
        {112, 0},
        //naranja
        {64, 8},
        //naranja roto 1   
        {80, 8},
        //naranja roto 2    
        {96, 8},
        //naranja roto 3   
        {112, 8},
        //Gris
        {96, 16},
        //Gris-Morado 1
        {112, 16},
        //Gris-Morado 2
        {96, 24},
        //Morado 3
        {112, 24},
        //Oro
        {64, 32},
        //Oro rot 1
        {80, 32},
        //Oro roto 2
        {96, 32},
        //Oro roto 3
        {112, 32},};

    /**
     *
     * @param j
     */
    public JuegoUI(Juego jue) {
        this.juego = jue;
        ClassLoader classLoader = getClass().getClassLoader();

        this.debug = true;

        this.initimagenes(classLoader);
        this.barraui = new BarraUI(this.imagen_barrasypelota, this.juego.getCampo().getBarra());
        this.initpowerupui();
        this.initsonidos(classLoader);
    }

    //ME CREO UNA MATRIZ DE POWERUPS UI PARA PODER REPRESENTAR CADA UNO DE LOS POWERUPS DE CADA NIVEL
    private void initpowerupui() {
        this.setPowerupsui(new PowerupUI[this.juego.getNivelActual().getPowerups().length][this.juego.getNivelActual().getPowerups()[0].length]);

        for (int i = 0; i < this.juego.getNivelActual().getPowerups().length; i++) {
            for (int j = 0; j < this.juego.getNivelActual().getPowerups()[i].length; j++) {
                if (this.juego.getNivelActual().getPowerups()[i][j] != null) {
                    this.getPowerupsui()[i][j] = new PowerupUI(this.imagen_powerups, this.juego.getNivelActual().getPowerups()[i][j]);

                }

            }
        }
    }

    private void initimagenes(ClassLoader classLoader) {

        this.imagen_fondo = new Image(classLoader.getResource(this.pathfondos).toString());

        this.imagen_bloques = new Image(classLoader.getResource(this.pathbloques).toString());
        this.imagen_barrasypelota = new Image(classLoader.getResource(this.pathbarras).toString());
        this.imagen_powerups = new Image(classLoader.getResource(this.pathpowerups).toString());
    }

    private void initsonidos(ClassLoader classLoader) {
        System.out.println(classLoader.getResource(this.pathsonidoladrillo).toString());
        this.sonido_tocaladrillo = new MediaPlayer(new Media(classLoader.getResource(this.pathsonidoladrillo).toString()));
        this.sonido_tocabarra = new MediaPlayer(new Media(classLoader.getResource(this.pathsonidobarra).toString()));
        this.sonido_bordes = new MediaPlayer(new Media(classLoader.getResource(this.pathsonidobordes).toString()));
        this.sonido_fallo = new MediaPlayer(new Media(classLoader.getResource(this.pathsonidofallo).toString()));
        this.sonido_muerte = new MediaPlayer(new Media(classLoader.getResource(this.pathsonidomuerte).toString()));
        this.sonido_nivel = new MediaPlayer(new Media(classLoader.getResource(this.pathsonidonivel).toString()));
        this.sonido_powerup = new MediaPlayer(new Media(classLoader.getResource(this.pathsonidopowerup).toString()));

    }

    /**
     *
     * @param principal
     * @param fondo
     * @param estado
     */
    public void paint(GraphicsContext principal, GraphicsContext fondo, EstadoCambiosJuego estado) {
        this.paintPrincipal(principal);

        if (estado == EstadoCambiosJuego.TOCALADRILLO) {
            this.paintBackground(fondo);
            this.sonido_tocaladrillo.setVolume(1.0d);
            this.sonido_tocaladrillo.seek(Duration.ZERO);
            this.sonido_tocaladrillo.play();

        }
        if (estado == EstadoCambiosJuego.TOCABARRA) {
            this.sonido_tocabarra.setVolume(1.00d);
            this.sonido_tocabarra.seek(Duration.ZERO);
            this.sonido_tocabarra.play();

        }
        if (estado == EstadoCambiosJuego.TOCABORDE) {
            this.sonido_bordes.setVolume(1.00d);
            this.sonido_bordes.seek(Duration.ZERO);
            this.sonido_bordes.play();

        }
        if (estado == EstadoCambiosJuego.ACTIVOPOWERUP) {
            this.sonido_powerup.setVolume(1.00d);
            this.sonido_powerup.seek(Duration.ZERO);
            this.sonido_powerup.play();

        }
        if (estado == EstadoCambiosJuego.PIERDEVIDA) {
            this.paintBackground(fondo);
            if (this.juego.getCampo().getVidas() >= 0) {
                this.sonido_fallo.setVolume(0.50d);
                this.sonido_fallo.seek(Duration.ZERO);
                this.sonido_fallo.play();
            } else {
                this.sonido_muerte.setVolume(0.50d);
                this.sonido_muerte.seek(Duration.ZERO);
                this.sonido_muerte.play();
            }

        }
        if (estado == EstadoCambiosJuego.PASODENIVEL) {
            this.juego.setNumeronivelactual(this.juego.getNumeronivelactual() + 1);
            this.juego.initjuego(this.juego.getNumeronivelactual());
            System.out.println("He intentado cambiar el nivel al nivel ->  " + this.juego.getNumeronivelactual());
            this.paintBackground(fondo);
            this.paintPrincipal(principal);
            this.sonido_nivel.setVolume(0.50d);
            this.sonido_nivel.seek(Duration.ZERO);
            this.sonido_nivel.play();

        }

    }

    private void paintPrincipal(GraphicsContext gc) {
        int color[];
        Powerup p;
        gc.clearRect(0, 0, Campo.getWidth(), Campo.getHeight());
        gc.setFill(Color.RED);
        if (this.juego != null) {
            //se dibuja la barra
            if (this.juego.getCampo().getBarra() != null) {
                if (this.juego.getCampo().getVidas() >= 0) {
                    this.barraui.pintar(gc, false);
                } else {
                    this.barraui.pintar(gc, true);
                }
                if (this.juego.getCampo().getPelota().isPowerupactivado()) {
                    gc.drawImage(this.imagen_barrasypelota, 5, 40, 5, 5, this.juego.getCampo().getPelota().getPosicion().getX(), this.juego.getCampo().getPelota().getPosicion().getY(), this.juego.getCampo().getPelota().getRadio(), this.juego.getCampo().getPelota().getRadio());//this.campo.getBarra().getPosicion().getX(),this.campo.getBarra().getPosicion().getY(),280,140);//this.campo.getBarra().getPosicion().getX(),this.campo.getBarra().getPosicion().getY(),23,8,247,240);

                } else {

                    gc.drawImage(this.imagen_barrasypelota, 0, 40, 5, 5, this.juego.getCampo().getPelota().getPosicion().getX(), this.juego.getCampo().getPelota().getPosicion().getY(), this.juego.getCampo().getPelota().getRadio(), this.juego.getCampo().getPelota().getRadio());//this.campo.getBarra().getPosicion().getX(),this.campo.getBarra().getPosicion().getY(),280,140);//this.campo.getBarra().getPosicion().getX(),this.campo.getBarra().getPosicion().getY(),23,8,247,240);
                }

            }
            for (int i = 0; i < this.getPowerupsui().length; i++) {
                for (int j = 0; j < this.getPowerupsui()[i].length; j++) {
                    p = this.juego.getNivelActual().getPowerups()[i][j];
                    if (p != null && p.isCayendo() == true && this.powerupsui[i][j] != null) {
                        this.powerupsui[i][j].pintar(gc);

                    }
                }
            }
        }
    }

    /**
     *
     * @param gc
     */
    public void paintBackground(GraphicsContext gc) {
        int borde = 10;
        int lugarXSiguientefondo = 232;
        int lugarYSiguientefondo = 256;
        Ladrillo l;
        if (this.juego.getNumeronivelactual() < 5) {

            gc.drawImage(this.imagen_fondo, lugarXSiguientefondo * this.juego.getNumeronivelactual(), 0, 224, 240, 0, 0, this.juego.ANCHO, this.juego.ALTO);

        } else {
            switch (this.juego.getNumeronivelactual()) {
                case (5): {
                    gc.drawImage(this.imagen_fondo, lugarXSiguientefondo * 0, 0, 224, 240, 0, 0, this.juego.ANCHO, this.juego.ALTO);

                    break;
                }
                case (6): {
                    gc.drawImage(this.imagen_fondo, lugarXSiguientefondo * 1, 0, this.juego.ANCHO, this.juego.ALTO);

                    break;
                }
                case (7): {
                    gc.drawImage(this.imagen_fondo, lugarXSiguientefondo * 2, 0, 224, 240, 0, 0, this.juego.ANCHO, this.juego.ALTO);

                    break;
                }
                case (8): {
                    gc.drawImage(this.imagen_fondo, lugarXSiguientefondo * 3, 0, 224, 240, 0, 0, this.juego.ANCHO, this.juego.ALTO);

                    break;
                }
                case (9): {
                    gc.drawImage(this.imagen_fondo, lugarXSiguientefondo * 4, 0, 224, 240, 0, 0, this.juego.ANCHO, this.juego.ALTO);

                    break;
                }
            }
        }
        if (this.juego != null) {
            //pintar el ladrillo
            for (int i = 0; i < this.juego.getNivelActual().getLadrillos().length; i++) {
                for (int j = 0; j < this.juego.getNivelActual().getLadrillos()[i].length; j++) {
                    l = this.juego.getNivelActual().getLadrillos()[i][j];

                    if (l != null && l.getDureza() > 0) {
                        gc.drawImage(this.imagen_bloques, this.cordenadaDureza(l).getX(), this.cordenadaDureza(l).getY(), 16, 8,
                                l.getPosicion().getX(), //Lx
                                l.getPosicion().getY(), //Ly
                                32, 16);
                    }
                }
            }
            //i=1 para que la posicion de x*i sea superior a 0

            for (int i = 1; i <= this.juego.getCampo().getVidas(); i++) {
                gc.drawImage(this.imagen_fondo,/*Posicion X imagen*/ 8, /*Posicion Y imagen*/ 240, /*Tamaño x objeto*/ 15,/*Tamaño Y objeto*/ 7,/*Posicion donde pinto el objeto*/ 20 * i * 2, 470, /*Pintamos el objeto el doble de grande tanto en X como en Y*/ 30, 14);

            }
        }
    }

    /**
     * Con este método conseguiemos sacar el color a pintar del ladrilo que le
     * pasamos según su dureza Vamos a evitar utilizar los múltiplos de 5 ya que
     * cualquier ladrillo el cual su dureza sea multiplo de 5 va a ser eliminado
     *
     * @param l Se le pasa un ladrillo para sacar su color basado en su dureza
     * @return Devuelve un Objeto Point2D con el cual tendremos los datos de X e
     * Y para pintar
     */
    private Point2D cordenadaDureza(Ladrillo l) {

        this.posicion = new Point2D(0, 0);

        switch (l.getDureza()) {
            case (1): {
                //Blanco roto 3
                this.setPosicion(new Point2D(this.colorladrillos[3][0], this.colorladrillos[3][1]));
                break;
            }
            case (2): {
                //Blanco roto 2
                this.setPosicion(new Point2D(this.colorladrillos[2][0], this.colorladrillos[2][1]));
                break;
            }
            case (3): {
                //Blanco roto 1
                this.setPosicion(new Point2D(this.colorladrillos[1][0], this.colorladrillos[1][1]));
                break;
            }
            case (4): {
                //Blanco
                this.setPosicion(new Point2D(this.colorladrillos[0][0], this.colorladrillos[0][1]));
                break;
            }
            //ESTO ES PARA SEPARARLOS

            case (6): {
                //Azul roto 3
                this.setPosicion(new Point2D(this.colorladrillos[7][0], this.colorladrillos[7][1]));
                break;
            }
            case (7): {
                //Azul roto 2
                this.setPosicion(new Point2D(this.colorladrillos[6][0], this.colorladrillos[6][1]));
                break;
            }
            case (8): {
                //Azul roto 1
                this.setPosicion(new Point2D(this.colorladrillos[5][0], this.colorladrillos[5][1]));
                break;
            }
            case (9): {
                //Amarillo 3
                this.setPosicion(new Point2D(this.colorladrillos[4][0], this.colorladrillos[4][1]));
                break;
            }

            //ESTO ES PARA SEPARARLOS
            case (11): {
                //Amarillo 3
                this.setPosicion(new Point2D(this.colorladrillos[11][0], this.colorladrillos[11][1]));
                break;
            }
            case (12): {
                //Amarillo roto 2
                this.setPosicion(new Point2D(this.colorladrillos[10][0], this.colorladrillos[10][1]));
                break;
            }
            case (13): {
                //Amarillo roto 1
                this.setPosicion(new Point2D(this.colorladrillos[9][0], this.colorladrillos[9][1]));
                break;
            }
            case (14): {
                //Amarillo
                this.setPosicion(new Point2D(this.colorladrillos[8][0], this.colorladrillos[8][1]));
                break;
            }

            //ESTO ES PARA SEPARARLOS
            case (16): {
                //Verde roto 3
                this.setPosicion(new Point2D(this.colorladrillos[15][0], this.colorladrillos[15][1]));
                break;
            }
            case (17): {
                //Verde roto 2
                this.setPosicion(new Point2D(this.colorladrillos[14][0], this.colorladrillos[14][1]));
                break;
            }
            case (18): {
                //Verde roto 1
                this.setPosicion(new Point2D(this.colorladrillos[13][0], this.colorladrillos[13][1]));
                break;
            }
            case (19): {
                //Verde
                this.setPosicion(new Point2D(this.colorladrillos[12][0], this.colorladrillos[12][1]));
                break;
            }

            //ESTO ES PARA SEPARARLOS
            case (21): {
                //Rosa roto 3
                this.setPosicion(new Point2D(this.colorladrillos[19][0], this.colorladrillos[19][1]));
                break;
            }
            case (22): {
                //Rosa roto 2
                this.setPosicion(new Point2D(this.colorladrillos[18][0], this.colorladrillos[18][1]));
                break;
            }
            case (23): {
                //Rosa roto 1
                this.setPosicion(new Point2D(this.colorladrillos[17][0], this.colorladrillos[17][1]));
                break;
            }
            case (24): {
                //Rosa
                this.setPosicion(new Point2D(this.colorladrillos[16][0], this.colorladrillos[16][1]));
                break;
            }

            //ESTO ES PARA SEPARARLOS
            case (26): {
                //Azul claro roto 3
                this.setPosicion(new Point2D(this.colorladrillos[23][0], this.colorladrillos[23][1]));
                break;
            }
            case (27): {
                //Azul claro roto 2
                this.setPosicion(new Point2D(this.colorladrillos[22][0], this.colorladrillos[22][1]));
                break;
            }
            case (28): {
                //Azul claro roto 1
                this.setPosicion(new Point2D(this.colorladrillos[21][0], this.colorladrillos[21][1]));
                break;
            }
            case (29): {
                //Azul
                this.setPosicion(new Point2D(this.colorladrillos[20][0], this.colorladrillos[20][1]));
                break;
            }

            //ESTO ES PARA SEPARARLOS
            case (31): {
                //naranja roto 3
                this.setPosicion(new Point2D(this.colorladrillos[27][0], this.colorladrillos[27][1]));
                break;
            }
            case (32): {
                //Naranja roto 2
                this.setPosicion(new Point2D(this.colorladrillos[26][0], this.colorladrillos[26][1]));
                break;
            }
            case (33): {
                //Naranja roto 1
                this.setPosicion(new Point2D(this.colorladrillos[25][0], this.colorladrillos[25][1]));
                break;
            }
            case (34): {
                //Naranja
                this.setPosicion(new Point2D(this.colorladrillos[24][0], this.colorladrillos[24][1]));
                break;
            }

            //ESTO ES PARA SEPARARLOS
            case (36): {
                //Negro roto 3
                this.setPosicion(new Point2D(this.colorladrillos[31][0], this.colorladrillos[31][1]));
                break;
            }
            case (37): {
                //Negro roto 2
                this.setPosicion(new Point2D(this.colorladrillos[30][0], this.colorladrillos[30][1]));
                break;
            }
            case (38): {
                //Negro roto 1
                this.setPosicion(new Point2D(this.colorladrillos[29][0], this.colorladrillos[29][1]));
                break;
            }
            case (39): {
                //Negro
                this.setPosicion(new Point2D(this.colorladrillos[28][0], this.colorladrillos[28][1]));
                break;
            }

            //ESTO ES PARA SEPARARLOS
            case (41): {
                //Rojo roto 3
                this.setPosicion(new Point2D(this.colorladrillos[35][0], this.colorladrillos[35][1]));
                break;
            }
            case (42): {
                //Rojo roto 2
                this.setPosicion(new Point2D(this.colorladrillos[34][0], this.colorladrillos[34][1]));
                break;
            }
            case (43): {
                //Rojo roto 1
                this.setPosicion(new Point2D(this.colorladrillos[33][0], this.colorladrillos[33][1]));
                break;
            }
            case (44): {
                //Rojo
                this.setPosicion(new Point2D(this.colorladrillos[32][0], this.colorladrillos[32][1]));
                break;
            }

            //ESTO ES PARA SEPARARLOS
            case (46): {
                //Naranja claro roto 3
                this.setPosicion(new Point2D(this.colorladrillos[39][0], this.colorladrillos[39][1]));
                break;
            }
            case (47): {
                //Naranja claro roto 2
                this.setPosicion(new Point2D(this.colorladrillos[38][0], this.colorladrillos[38][1]));
                break;
            }
            case (48): {
                //Naranja claro roto 1
                this.setPosicion(new Point2D(this.colorladrillos[37][0], this.colorladrillos[37][1]));
                break;
            }
            case (49): {
                //Naranja claro
                this.setPosicion(new Point2D(this.colorladrillos[36][0], this.colorladrillos[36][1]));
                break;
            }

            //ESTO ES PARA SEPARARLOS
            case (51): {
                //Morado roto 3
                this.setPosicion(new Point2D(this.colorladrillos[43][0], this.colorladrillos[43][1]));
                break;
            }
            case (52): {
                //Morado roto 2
                this.setPosicion(new Point2D(this.colorladrillos[42][0], this.colorladrillos[42][1]));
                break;
            }
            case (53): {
                //Morado roto 1
                this.setPosicion(new Point2D(this.colorladrillos[41][0], this.colorladrillos[41][1]));
                break;
            }
            case (54): {
                //Gris
                this.setPosicion(new Point2D(this.colorladrillos[40][0], this.colorladrillos[40][1]));
                break;
            }

            //ESTO ES PARA SEPARARLOS
            case (56): {
                //Morado
                this.setPosicion(new Point2D(this.colorladrillos[47][0], this.colorladrillos[47][1]));
                break;
            }
            case (57): {
                //Gris morado 2
                this.setPosicion(new Point2D(this.colorladrillos[46][0], this.colorladrillos[46][1]));
                break;
            }
            case (58): {
                //Gris morado 1
                this.setPosicion(new Point2D(this.colorladrillos[45][0], this.colorladrillos[45][1]));
                break;
            }
            case (59): {
                //Gris
                this.setPosicion(new Point2D(this.colorladrillos[44][0], this.colorladrillos[44][1]));
                break;
            }
            case (61): {
                //Gris
                this.setPosicion(new Point2D(this.colorladrillos[20][0], this.colorladrillos[20][1]));
                break;
            }
            case (66): {
                //Gris
                this.setPosicion(new Point2D(this.colorladrillos[12][0], this.colorladrillos[12][1]));
                break;
            }
            case (71): {
                //Gris
                this.setPosicion(new Point2D(this.colorladrillos[32][0], this.colorladrillos[32][1]));
                break;
            }
            case (76): {
                //Gris
                this.setPosicion(new Point2D(this.colorladrillos[28][0], this.colorladrillos[28][1]));
                break;
            }
        }

        return posicion;

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
     * @return the powerupsui
     */
    public PowerupUI[][] getPowerupsui() {
        return powerupsui;
    }

    /**
     * @param powerupsui the powerupsui to set
     */
    public void setPowerupsui(PowerupUI[][] powerupsui) {
        this.powerupsui = powerupsui;
    }
}
