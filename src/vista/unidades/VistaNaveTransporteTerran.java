package vista.unidades;

import unidades.terrran.NaveTransporteTerran;
import vista.auxiliares.ImagePanel;
import vista.ventanaJugadores.VentanaJugador;

import javax.swing.*;

public class VistaNaveTransporteTerran extends ImagePanel{

    private static final int ANCHO = 1;
    private static final int ALTO = 2;
    private final NaveTransporteTerran unidad;
    private static String pathImagen="images/unidades/terrran/naveTRansporte.png";
    private final VentanaJugador ventanaJugador;

    public VistaNaveTransporteTerran(NaveTransporteTerran nave,VentanaJugador ventanaJugador) {
        super(ANCHO,ALTO,new ImageIcon(pathImagen).getImage());
        this.unidad=nave;
        this.ventanaJugador=ventanaJugador;
    }

}
