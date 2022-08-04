/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAW.arkanoid.modelo;

import javafx.geometry.Point2D;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

/**
 *
 * @author Mateo 
 * La clase Juego tiene un objeto Campo. Un entero que tendrá el
 * numero de nivel actual. Dos enteros estáticos que determinan el ancho y alto
 * del juego. Un objeto Mediaplayer para tener sonido de fondo en el juego. Un
 * boolean para comprobar si hay un powerup activado y otro para mirar si la
 * barra está con el powerup 6 que la hace estar volando. Un entero para saber
 * qué tipo de powerup está activado. Dos objetos nivel, uno que será el nivel
 * actual y otro que será un vector de niveles donde estén todos los demás
 * niveles Un objeto String que tiene la dirección donde está la canción de
 * fondo Un objeto Estadodejuego donde están los enumerados de según que tipo de
 * estadodejuego. A parte, por último esta clase tiene otro enumerado que
 * determina si está pendiente de iniciar y la bola está junto a la barra o
 * iniciado y ya se juega normal y corriente
 *
 */
enum EstadoJuego {
    PENDIETEINICIAR,
    INICIADO
}

public class Juego {

    private Campo campo;
    private int numeronivelactual;
    private boolean poderactivado;
    private boolean volando;
    private int numeropoderactivado;
    private Nivel actual;
    private Nivel niveles[];
    
    public static int ANCHO = 448;
    public static int ALTO = 480;
    private EstadoJuego estadoJuego;

    /**
     * CONSTRUCTOR POR DEFECTO
     */
    public Juego() {
        this.campo = new Campo(Juego.ANCHO, Juego.ALTO);
        this.campo.setVelocidad(1.0f);
        this.campo.getBarra().setAlto(16);
        this.campo.getBarra().setAncho(64);
        this.estadoJuego = EstadoJuego.PENDIETEINICIAR;
        this.numeronivelactual = 1;
        this.poderactivado = false;
        this.volando = false;
        this.initjuego(numeronivelactual);
        
    }

    

    /**
     * ciclo del juego, se mueve la pelota, comprueba colision con barra y
     * ladrillos si es con ladrillos se devuelve true para repintar el fondo.
     *
     * @param pulsados
     * @return
     */
    public EstadoCambiosJuego ciclo(boolean pulsados[]) {
        EstadoCambiosJuego vuelta = EstadoCambiosJuego.NADA;

        if (pulsados[0]) {
            this.moverBarraIzquierda();
        }
        if (pulsados[1]) {
            this.moverBarraDerecha();
        }
        if (volando) {
            if (pulsados[2]) {
                this.moverBarraArriba();
            }
            if (pulsados[3]) {
                this.moverBarraAbajo();
            }
        }

        if (this.DetectarColisionBarra()) {
            this.cambiarAnguloColisionBarra();
            vuelta = EstadoCambiosJuego.TOCABARRA;
        }
        if (this.colisionLadrillos()) {
            vuelta = EstadoCambiosJuego.TOCALADRILLO;
        }
        if (this.colisionPelotaBordes()) {
            vuelta = EstadoCambiosJuego.TOCABORDE;

        }
        if (this.colisionPelotaFondo()) {
            this.campo.perderVida();
            this.setPoderactivado(false);
            this.getCampo().getPelota().setPowerupactivado(false);
            this.getCampo().getBarra().setPosicion(new Point2D(Juego.ANCHO / 2 - 33, Juego.ALTO - 29));
            vuelta = EstadoCambiosJuego.PIERDEVIDA;
            this.volando = false;

        }
        if (this.detectarColisionPowerup()) {
            this.setPoderactivado(true);
            vuelta = EstadoCambiosJuego.ACTIVOPOWERUP;
        }
        if (this.isPoderactivado()) {
            if (this.volando && this.numeropoderactivado != 6) {
                this.getCampo().getBarra().setPosicion(new Point2D(Juego.ANCHO / 2 - 33, Juego.ALTO - 29));
                this.volando = false;
            }
            switch (this.numeropoderactivado) {
                case (0): {
                    this.getCampo().getPelota().setPowerupactivado(false);
                    break;
                }
                case (1): {
                    this.getCampo().getPelota().setPowerupactivado(false);
                    break;
                }
                case (2): {
                    this.getCampo().getPelota().setPowerupactivado(false);
                    break;
                }
                case (3): {
                    this.getCampo().getPelota().setPowerupactivado(false);
                    break;
                }
                case (4): {
                    this.getCampo().getPelota().setPowerupactivado(false);
                    break;
                }
                case (5): {
                    this.getCampo().getPelota().setPowerupactivado(true);
                    break;
                }
                case (6): {
                    this.getCampo().getPelota().setPowerupactivado(false);
                    this.volando = true;
                    break;
                }
            }
        }

        if (this.getNivelActual().detectarSiPasardeNivel()) {
            vuelta = EstadoCambiosJuego.PASODENIVEL;
            this.setEstadoJuego(EstadoJuego.PENDIETEINICIAR);
            this.setPoderactivado(false);
            this.getCampo().getPelota().setPowerupactivado(false);
        }

        if (this.moverPelota()) {
        }
        return vuelta;
    }

    /**
     * Método creado para iniciar el movimiento de la pelota
     */
    public void RunBall() {
        this.setEstadoJuego(EstadoJuego.INICIADO);
    }

    /**
     * Metodo que inicializa las matrizes de los niveles
     *
     * @param numerodenivel
     */
    public void initjuego(int numerodenivel) {
        this.setNiveles(new Nivel[7]);

        //Bloque de colores
        int matriznivel_0[][] = {
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,},
            {0, 59, 59, 59, 59, 59, 59, 59, 59, 59, 59, 0},
            {0, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 0},
            {0, 44, 44, 44, 44, 44, 44, 44, 44, 44, 44, 0},
            {0, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 0},
            {0, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 0},
            {0, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 0},
            {0, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 0},
            {0, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 0},
            {0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0},
            {0, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
        };

        //Corazon
        int matriznivel_1[][] = {
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 76, 0, 0, 0, 76, 0},
            {0, 0, 0, 76, 71, 0, 0, 0, 71, 76},
            {0, 0, 0, 76, 71, 76, 71, 76, 71, 76},
            {0, 0, 0, 71, 76, 71, 76, 71, 76, 71},
            {0, 0, 0, 0, 71, 76, 71, 76, 71, 0},
            {0, 0, 0, 0, 0, 71, 76, 71, 0, 0},
            {0, 0, 0, 0, 0, 0, 71, 0, 0, 0},};

        //Pokeball
        int matriznivel_2[][] = {
            {0, 0, 0, 0, 39, 39, 39, 39, 0, 0, 0, 0},
            {0, 0, 39, 39, 44, 44, 44, 44, 39, 39, 0, 0},
            {0, 39, 44, 44, 44, 44, 44, 44, 44, 44, 39, 0},
            {0, 39, 44, 44, 44, 44, 44, 44, 44, 44, 39, 0},
            {39, 44, 44, 44, 44, 44, 44, 44, 44, 44, 44, 39},
            {39, 44, 44, 44, 44, 39, 39, 44, 44, 44, 44, 39},
            {39, 39, 44, 44, 39, 54, 54, 39, 44, 44, 44, 39},
            {39, 4, 39, 39, 39, 54, 54, 39, 39, 39, 4, 39},
            {0, 39, 4, 4, 4, 39, 39, 4, 4, 4, 39, 0},
            {0, 39, 4, 4, 4, 4, 4, 4, 4, 4, 39, 0},
            {0, 0, 39, 39, 4, 4, 4, 4, 39, 39, 0, 0},
            {0, 0, 0, 0, 39, 39, 39, 39, 0, 0, 0, 0}

        };

        //Creeper
        int matriznivel_3[][] = {
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 0},
            {0, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 0},
            {0, 19, 19, 39, 39, 19, 19, 39, 39, 19, 19, 0},
            {0, 19, 19, 39, 39, 19, 19, 39, 39, 19, 19, 0},
            {0, 19, 19, 19, 19, 39, 39, 19, 19, 19, 19, 0},
            {0, 19, 19, 19, 39, 39, 39, 39, 19, 19, 19, 0},
            {0, 19, 19, 19, 39, 39, 39, 39, 19, 19, 19, 0},
            {0, 19, 19, 19, 39, 19, 19, 39, 19, 19, 19, 0},
            {0, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 0},
            {0, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}

        };

        //Mario
        int matriznivel_4[][] = {
            {0, 0, 0, 44, 44, 44, 44, 44, 0, 0, 0, 0},
            {0, 0, 44, 44, 44, 44, 44, 44, 44, 44, 44, 0},
            {0, 0, 39, 39, 39, 49, 49, 39, 49, 0, 0, 0},
            {0, 39, 49, 39, 49, 49, 49, 39, 49, 49, 49, 0},
            {0, 39, 49, 39, 39, 49, 49, 49, 39, 49, 49, 49},
            {0, 39, 39, 49, 49, 49, 49, 39, 39, 39, 39, 0},
            {0, 0, 0, 49, 49, 49, 49, 49, 49, 49, 0, 0},
            {0, 0, 39, 39, 44, 39, 39, 39, 0, 0, 0, 0},
            {0, 39, 39, 39, 44, 39, 39, 44, 39, 39, 39, 0},
            {39, 39, 39, 39, 44, 44, 44, 44, 39, 39, 39, 39},
            {49, 49, 39, 44, 14, 44, 44, 14, 44, 39, 49, 49},
            {49, 49, 49, 44, 44, 44, 44, 44, 44, 49, 49, 49},
            {49, 49, 44, 44, 44, 44, 44, 44, 44, 44, 49, 49},
            {0, 0, 44, 44, 44, 0, 0, 44, 44, 44, 0, 0},
            {0, 39, 39, 39, 0, 0, 0, 0, 39, 39, 39, 0},
            {39, 39, 39, 39, 0, 0, 0, 0, 39, 39, 39, 39},};

        //Amongus
        int matriznivel_5[][] = {
            {0, 0, 0, 39, 39, 39, 39, 39, 0, 0, 0, 0},
            {0, 0, 39, 44, 44, 44, 44, 44, 39, 0, 0, 0},
            {0, 39, 44, 44, 44, 44, 44, 44, 44, 39, 0, 0},
            {0, 39, 39, 39, 44, 44, 44, 44, 44, 39, 39, 0},
            {39, 29, 4, 29, 39, 44, 44, 44, 44, 39, 44, 39},
            {39, 9, 9, 9, 39, 44, 44, 44, 44, 39, 44, 39},
            {0, 39, 39, 39, 44, 44, 44, 44, 44, 39, 44, 39},
            {0, 39, 44, 44, 44, 44, 44, 44, 44, 39, 44, 39},
            {0, 39, 44, 44, 44, 39, 44, 44, 44, 39, 39, 0},
            {0, 39, 44, 44, 39, 0, 39, 44, 44, 39, 0, 0},
            {0, 39, 44, 44, 39, 0, 39, 44, 44, 39, 0, 0},
            {0, 0, 39, 39, 0, 0, 0, 39, 39, 0, 0, 0}
        };
        int matriznivel_6[][] = {
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {61, 61, 61, 61, 61, 61, 61},
            {66, 66, 66, 66, 66, 66, 66, 66}
        };
        Point2D inicial = new Point2D(33, 60);

        this.getNiveles()[0] = new Nivel(matriznivel_1, inicial);
        this.getNiveles()[1] = new Nivel(matriznivel_2, inicial);
        this.getNiveles()[2] = new Nivel(matriznivel_3, inicial);
        this.getNiveles()[3] = new Nivel(matriznivel_4, inicial);
        this.getNiveles()[4] = new Nivel(matriznivel_5, inicial);
        this.getNiveles()[5] = new Nivel(matriznivel_5, inicial);

        this.setNivelActual(getNiveles()[numerodenivel]);
    }

    /**
     * Método que detecta la colisión de la pelota con la barra
     *
     * @return true si colisiona y false si no colisiona
     */
    public boolean DetectarColisionBarra() {
        boolean colision = false;
        boolean condicionEjeY = (((int) this.campo.getBarra().getPosicion().getY() - ((int) this.campo.getPelota().getPosicion().getY()
                + this.campo.getPelota().getRadio())) == (int) this.campo.getPelota().getVelocidad());
        boolean condicionEjeXIzq = ((this.campo.getBarra().getPosicion().getX() < this.campo.getPelota().getPosicion().getX())
                || (this.campo.getBarra().getPosicion().getX() < (this.campo.getPelota().getPosicion().getX() + this.campo.getPelota().getRadio())));
        boolean condicionEjeXDer = ((this.campo.getPelota().getPosicion().getX() + this.campo.getPelota().getRadio())
                <= (this.campo.getBarra().getPosicion().getX() + this.campo.getBarra().getAncho())
                || (this.campo.getPelota().getPosicion().getX() <= (this.campo.getBarra().getPosicion().getX() + this.campo.getBarra().getAncho())));

        if (condicionEjeY && condicionEjeXDer && condicionEjeXIzq) {
            colision = true;
        }
        return colision;
    }

    /**
     * Método creado para cambiar el ángulo de la pelota al detectar si ha
     * colisionado con la barra La barra se divide en 8 secciones para así
     * determinar qué tipo de ángulo tendrá según en que lado dé de la barra
     */
    public void cambiarAnguloColisionBarra() {
        float distancia = Math.abs((float) this.campo.getPelota().getPosicion().getX() - (float) this.campo.getBarra().getPosicion().getX());
        int anchoparte = this.campo.getBarra().getAncho() / 8;
        int seccion = (int) distancia / anchoparte;
        int random = (int) (Math.random() * 25);
        //Sección 0 cambia el ángulo a 205 seccion 3 cambia el angulo a 335 y sección 1 y 2 Rebota como si fuese una pared
        //REBOTE ESPECIAL
        switch (seccion) {
            case (0):
                this.campo.getPelota().setAngulo(180 + 20 + random);
                break;
            case (1):
                this.campo.getPelota().setAngulo(180 + 20 + random);
                break;
            case (2):
                this.campo.getPelota().setAngulo(240 + random);
                break;
            case (3):
                this.campo.getPelota().setAngulo(270 - random);
                break;
            case (4):
                this.campo.getPelota().setAngulo(270 + random);
                break;
            case (5):
                this.campo.getPelota().setAngulo(300 + random);
                break;
            case (6):
                this.campo.getPelota().setAngulo(360 - 20 - random);
                break;
            case (7):
                this.campo.getPelota().setAngulo(360 - 20 - random);
                break;
        }

    }

    /**
     * Método creado para detectar la colisión de los ladrillos Teniendo en
     * cuenta los 4 casos comentados en el método Este método también cambia el
     * ángulo de la pelota al detectar colisión, lo hace después de los bucles
     * en un switch para evitar fallos Los ladrillos con múltiplo de 5 se
     * convertirán en dureza 0 para conseguír un efecto de ruptura en el bloque
     * de según el color a la vez, comprueba si la pelota es especial y si es
     * especial destruirá el ladrillo de un golpe y no cambiará el angulo
     *
     * @return true si detecta colisión, false si no la detecta
     */
    public boolean colisionLadrillos() {
        boolean colision = false;
        int tipocolision = 0;
        int pa = (int) this.getCampo().getPelota().getAngulo();
        int py = (int) this.getCampo().getPelota().getPosicion().getY();
        int px = (int) this.getCampo().getPelota().getPosicion().getX();
        int pr = (int) this.getCampo().getPelota().getRadio();
        int lalto = Ladrillo.ALTO;
        int lancho = Ladrillo.ANCHO;
        for (int i = 0; i < this.getNivelActual().getLadrillos().length; i++) {
            for (int j = 0; j < this.getNivelActual().getLadrillos()[0].length; j++) {
                int lx = (int) this.getNivelActual().getLadrillos()[i][j].getPosicion().getX();
                int ly = (int) this.getNivelActual().getLadrillos()[i][j].getPosicion().getY();

                /*CASO 1 
                PAngulo>180
                CON ESTO MIDO LA PARTE DE ABAJO DEL LADRILLO
                py=ly+lalto
                px+pr>lx
                px<lx+lancho
                 */
                if (pa > 180
                        && (py == (ly + lalto))
                        && ((px + pr) > lx)
                        && (px < (lx + lancho))
                        && this.getNivelActual().getLadrillos()[i][j] != null
                        && this.getNivelActual().getLadrillos()[i][j].getDureza() > 0) {
                    colision = true;
                    if (this.getCampo().getPelota().isPowerupactivado()) {
                        this.getNivelActual().getLadrillos()[i][j].setDureza(0);
                    } else {
                        tipocolision = 1;
                        this.getNivelActual().getLadrillos()[i][j].bajarDureza();
                    }
                    if (this.getNivelActual().getLadrillos()[i][j].getDureza() % 5 == 0) {
                        this.getNivelActual().getLadrillos()[i][j].setDureza(0);
                    }
                    /*
                  CASO 2
                PANGULO<180
                CON ESTO MIDO LA PARTE DE ARRIBA DEL LADRILLO
                py+pr=ly
                px+pr>lx
                px<lx+lancho
                     */

                } else if (pa < 180
                        && ((py + pr) == ly)
                        && ((px + pr) > lx)
                        && (px < (lx + lancho))
                        && this.getNivelActual().getLadrillos()[i][j] != null
                        && this.getNivelActual().getLadrillos()[i][j].getDureza() > 0) {
                    colision = true;
                    if (this.getCampo().getPelota().isPowerupactivado()) {
                        this.getNivelActual().getLadrillos()[i][j].setDureza(0);
                    } else {
                        tipocolision = 2;
                        this.getNivelActual().getLadrillos()[i][j].bajarDureza();
                    }
                    if (this.getNivelActual().getLadrillos()[i][j].getDureza() % 5 == 0) {
                        this.getNivelActual().getLadrillos()[i][j].setDureza(0);
                    }

                    /*
                CASO 3
                PANGULO>270
                CON ESTO MIDO LA PARTE DE LA IZQUIERDA
                px+pr=lx
                py<ly+lalto
                py+pr>ly
                     */
                } else if ((pa > 270 || pa < 90)
                        && (((px + pr) == lx) || ((((px + pr) - lx) < 2) && (((px + pr) - lx) > 0)))
                        && (py < (ly + lalto))
                        && ((py + pr) > ly)
                        && this.getNivelActual().getLadrillos()[i] != null
                        && this.getNivelActual().getLadrillos()[i][j].getDureza() > 0) {
                    colision = true;
                    if (this.getCampo().getPelota().isPowerupactivado()) {
                        this.getNivelActual().getLadrillos()[i][j].setDureza(0);
                    } else {
                        tipocolision = 3;
                        this.getNivelActual().getLadrillos()[i][j].bajarDureza();
                    }
                    if (this.getNivelActual().getLadrillos()[i][j].getDureza() % 5 == 0) {
                        this.getNivelActual().getLadrillos()[i][j].setDureza(0);
                    }

                    /*
                CASO 4
                ELSE
                CON ESTO MIDO LA PARTE DE LA DERECHA
                px=lx+lancho
                py<ly+lalto
                py+pr>ly
                
                     */
                } else if (px == (lx + lancho)
                        && (py < (ly + lalto))
                        && ((py + pr) > ly)
                        && this.getNivelActual().getLadrillos()[i][j] != null
                        && this.getNivelActual().getLadrillos()[i][j].getDureza() > 0) {
                    colision = true;
                    if (this.getCampo().getPelota().isPowerupactivado()) {
                        this.getNivelActual().getLadrillos()[i][j].setDureza(0);
                    } else {
                        tipocolision = 4;
                        this.getNivelActual().getLadrillos()[i][j].bajarDureza();
                    }
                    if (this.getNivelActual().getLadrillos()[i][j].getDureza() % 5 == 0) {
                        this.getNivelActual().getLadrillos()[i][j].setDureza(0);
                    }

                }
                if (this.getNivelActual().getLadrillos()[i][j].getDureza() == 0 && colision && this.getNivelActual().getPowerups()[i][j] != null) {

                    this.getNivelActual().getPowerups()[i][j].setCayendo(true);
                }
            }

        }
        switch (tipocolision) {
            case (1): {
                this.getCampo().getPelota().setAngulo(360 - this.getCampo().getPelota().getAngulo());
                break;
            }
            case (2): {
                this.getCampo().getPelota().setAngulo(360 - this.getCampo().getPelota().getAngulo());

                break;
            }
            case (3): {
                this.getCampo().getPelota().setAngulo(180 - this.getCampo().getPelota().getAngulo());
                break;
            }
            case (4): {
                this.getCampo().getPelota().setAngulo(180 - this.getCampo().getPelota().getAngulo());
                break;
            }
        }
        return colision;
    }

    /**
     * Este método detecta la colisión de un powerup con la barra y recogiendo
     * su tipo le manda ese número al juego para activar ese poder en específico
     *
     * @return
     */
    public boolean detectarColisionPowerup() {
        boolean colision = false;

        int Pan = Powerup.ancho;
        int Bx = (int) this.getCampo().getBarra().getPosicion().getX();
        int Ban = this.getCampo().getBarra().getAncho();
        int Pal = Powerup.alto;
        int By = (int) this.getCampo().getBarra().getPosicion().getY();
        int Bal = (int) this.getCampo().getBarra().getAlto();
        for (int i = 0; i < this.getNivelActual().getPowerups().length; i++) {
            for (int j = 0; j < this.getNivelActual().getPowerups()[i].length; j++) {
                if (this.getNivelActual().getPowerups()[i][j] != null) {
                    int Px = (int) this.getNivelActual().getPowerups()[i][j].getPosicion().getX();
                    int Py = (int) this.getNivelActual().getPowerups()[i][j].getPosicion().getY();
                    if (((Px + Pan) > Bx)
                            && (Px < (Bx + Ban))
                            && (Py < (By + Bal))
                            && ((Py + Pal) > By)
                            ) {
                        colision = true;
                        //AL RECOJERLO, SE MANDA PARA DEBAJO DEL MAPA, SE RECIBE DE QUÉ TIPO ERA Y SE MANDA A ACTIVAR, LUEGO ESE PODER SE SOBREESCRIBE A TIPO 7
                        //PARA DEJAR A ESE POWERUP YA INÚTIL DESPUÉS DE HABERLO COGIDO
                        this.getNivelActual().getPowerups()[i][j].setPosicion(new Point2D(this.getNivelActual().getPowerups()[i][j].getPosicion().getX(), 600));
                        this.numeropoderactivado = this.getNivelActual().getPowerups()[i][j].getTipopowerup();
                    }
                }
            }
        }

        return colision;
    }

    //Este es el quinto paso donde hacemos que la pelota pueda rebotar por los bordes de manera correcta
    //Este método hace que se mueva la pelota y consigamos hacer el efecto de que nos siga a la barra hasta que inicie el juego
    public boolean moverPelota() {
        boolean iniciado = false;
        if (this.getEstadoJuego() == estadoJuego.PENDIETEINICIAR) {
            this.campo.getPelota().setPosicion(new Point2D(this.campo.getBarra().getPosicion().getX()
                    + (this.campo.getBarra().getAncho() / 2.3),
                    this.campo.getBarra().getPosicion().getY() - this.campo.getPelota().getRadio() - 2));
            iniciado = false;
        } else {

            this.campo.getPelota().mover();
            iniciado = true;
        }
        return iniciado;
    }

    /**
     * Este método detecta la colisión de la pelota con los bordes para hacer
     * que se mantenga dentro del juego. Tiene 3 condiciones para determinar si
     * rebota por arriba o por los lados.
     *
     * @return
     */
    public boolean colisionPelotaBordes() {
        boolean colision = false;
        //TOCA TECHO
        if (this.campo.getPelota().getPosicion().getY() < (this.campo.borde * 2) - this.campo.getPelota().getRadio() / 2) {
            this.campo.getPelota().setAngulo(360 - this.campo.getPelota().getAngulo());
            colision = true;
        }
        //EN TEORÍA DERECHA
        if ((int) this.campo.getPelota().getPosicion().getX() > (ANCHO - this.campo.borde * 1.5) - (int) this.campo.getPelota().getRadio() / 2) {
            this.campo.getPelota().setAngulo(180 - this.campo.getPelota().getAngulo());
            colision = true;
        }
        //EN TEORÍA IZQUIERDA
        if (this.campo.getPelota().getPosicion().getX() < (this.campo.borde)) {
            this.campo.getPelota().setAngulo(180 - this.campo.getPelota().getAngulo());
            colision = true;
        }

        return colision;
    }

    /**
     * Este método detecta la colisión de la pelota con el fondo para así
     * administrar que se pierda una vida y volver a poner la bola encima de la
     * barra, también desactivará los poderes
     *
     * @return
     */
    public boolean colisionPelotaFondo() {
        boolean tocafondo = false;
        //TOCA SUELO
        if ((this.campo.getPelota().getPosicion().getY() + this.campo.getPelota().getRadio()) > (ALTO)) {
            this.campo.getPelota().setAngulo(290);
            tocafondo = true;
            this.setEstadoJuego(EstadoJuego.PENDIETEINICIAR);
        }
        return tocafondo;
    }

    /**
     * Metodo que hace que podamos mover la barra a la izquierda sin pasarnos
     */
    public void moverBarraIzquierda() {
        //SI LA POSICIÓN POR LA IZQUIERDA DE LA BARRA ES MAYOR AL BORDE ME DEJA MOVER LA BARRA A LA IZQUIERDA
        if (this.campo.getBarra().getPosicion().getX() >= this.campo.borde) {
            this.campo.getBarra().moverIzquierda();
        }

    }

    /**
     * Metodo que hace que podamos mover la barra a la derecha sin pasarnos
     */
    public void moverBarraDerecha() {
        //SI LA POSICIÓN DE LA BARRA POR LA DERECHA ES MENOR AL ANCHO DEL JUEGO PUEDO MOVER LA BARRA A LA DERECHA
        if ((this.campo.getBarra().getPosicion().getX() + this.campo.getBarra().getAncho()) <= (this.ANCHO - this.campo.borde)) {
            this.campo.getBarra().moverDerecha();

        }

    }

    /**
     * Metodo que hace que podamos mover la barra hacia arriba sin pasarnos
     */
    public void moverBarraArriba() {
        //SI LA POSICIÓN POR LA IZQUIERDA DE LA BARRA ES MAYOR AL BORDE ME DEJA MOVER LA BARRA A LA IZQUIERDA
        if (this.campo.getBarra().getPosicion().getY() >= this.ALTO / 1.5) {
            this.campo.getBarra().moverArriba();
        }

    }

    /**
     * Metodo que hace que podamos mover la barra hacia abajo sin pasarnos
     */
    public void moverBarraAbajo() {
        //SI LA POSICIÓN DE LA BARRA POR LA DERECHA ES MENOR AL ANCHO DEL JUEGO PUEDO MOVER LA BARRA A LA DERECHA
        if ((this.campo.getBarra().getPosicion().getY() + this.campo.getBarra().getAlto()) <= (this.ALTO)) {
            this.campo.getBarra().moverAbajo();

        }

    }

    /**
     * @return the campo
     */
    public Campo getCampo() {
        return campo;
    }

    /**
     * @param campo the campo to set
     */
    public void setCampo(Campo campo) {
        this.campo = campo;
    }

    /**
     * @return the estadoJuego
     */
    public EstadoJuego getEstadoJuego() {
        return estadoJuego;
    }

    /**
     * @param estadoJuego the estadoJuego to set
     */
    public void setEstadoJuego(EstadoJuego estadoJuego) {
        this.estadoJuego = estadoJuego;
    }

    /**
     * @return the actual
     */
    public Nivel getNivelActual() {
        return actual;
    }

    /**
     * @param actual the actual to set
     */
    public void setNivelActual(Nivel actual) {
        this.actual = actual;
    }

    /**
     * @return the niveles
     */
    public Nivel[] getNiveles() {
        return niveles;
    }

    /**
     * @param niveles the niveles to set
     */
    public void setNiveles(Nivel[] niveles) {
        this.niveles = niveles;
    }

    /**
     * @return the numeronivelactual
     */
    public int getNumeronivelactual() {
        return numeronivelactual;
    }

    /**
     * @param numeronivelactual the numeronivelactual to set
     */
    public void setNumeronivelactual(int numeronivelactual) {
        this.numeronivelactual = numeronivelactual;
    }

    /**
     * @return the poderactivado
     */
    public boolean isPoderactivado() {
        return poderactivado;
    }

    /**
     * @param poderactivado the poderactivado to set
     */
    public void setPoderactivado(boolean poderactivado) {
        this.poderactivado = poderactivado;
    }
}
