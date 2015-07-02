package vista.recursos;

import modelo.interfaces.ColocableEnMapa;
import modelo.recursos.Cristal;
import vista.IVista;
import vista.auxiliares.ImagePanel;
import vista.ventanaJugadores.VentanaJugador;

import javax.swing.*;

public class VistaCristales extends ImagePanel implements IVista {

    private static final int ANCHO = 64;
    private static final int ALTO = 64;
    private final Cristal recurso;
    private static String pathImagen="src/vista/recursos/imagenes/cristal.png";
    private final VentanaJugador ventanaJugador;

    public VistaCristales(ColocableEnMapa cristal,VentanaJugador ventanaJugador) {
        super(ANCHO,ALTO,new ImageIcon(pathImagen).getImage());
        this.recurso=(Cristal)cristal;
        this.ventanaJugador=ventanaJugador;
    }

    @Override
    public void actualizarBotonera() {
        ventanaJugador.getPanelAcciones().configurarBotones(recurso);
    }

    @Override
    public void actualizarPanelEstado() {
    }
}
