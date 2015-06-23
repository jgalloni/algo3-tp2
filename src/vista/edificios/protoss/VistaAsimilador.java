package vista.edificios.protoss;

import construcciones.protoss.Asimilador;
import vista.auxiliares.ImagePanel;
import vista.ventanaJugadores.VentanaJugador;

import javax.swing.*;

public class VistaAsimilador extends ImagePanel{

    private static final int ANCHO = 1;
    private static final int ALTO = 2;
    private final Asimilador edificio;
    private final VentanaJugador ventanaJugador;

    public VistaAsimilador(Asimilador asimilador, VentanaJugador ventanaJugador) {
        super(ANCHO,ALTO,new ImageIcon().getImage());
        this.edificio=asimilador;
        this.ventanaJugador=ventanaJugador;
    }

}