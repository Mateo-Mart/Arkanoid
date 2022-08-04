/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package DAW.arkanoid.modelo;

/**
 *
 * @author Pedro
 *
 * Un enumerado que determinará el estado del juego, con este se aprovechará
 * para representar los sonidos cuando suceda determinada acción
 */
public enum EstadoCambiosJuego {
    NADA,
    TOCABARRA,
    TOCALADRILLO,
    TOCABORDE,
    PIERDEVIDA,
    MUERTO,
    PASODENIVEL,
    ACTIVOPOWERUP
}
