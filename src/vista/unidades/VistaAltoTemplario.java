package vista.unidades;

import interfaces.ColocableEnMapa;
import unidades.protoss.AltoTemplario;
import vista.Actions.MouseActionObtenerInfoAltoTemplario;
import vista.IVista;
import vista.auxiliares.ImagePanel;
import vista.ventanaJugadores.VentanaJugador;

import javax.swing.*;

public class VistaAltoTemplario extends ImagePanel implements IVista{
    private static final int ANCHO = 1;
    private static final int ALTO = 2;
    private final AltoTemplario unidad;
    private final VentanaJugador ventanaJugador;
    private static String pathImagen="images/unidades/protoss/altoTemplario.png";

    public VistaAltoTemplario(ColocableEnMapa altoTemplario,VentanaJugador ventanaJugador) {
        super(ANCHO,ALTO,new ImageIcon(pathImagen).getImage());
        super.setBackground(new ImageIcon("src/vista/paisaje/imagenes/pasto.png").getImage());
        this.unidad=(AltoTemplario)altoTemplario;
        this.ventanaJugador=ventanaJugador;
        this.addMouseListener(new MouseActionObtenerInfoAltoTemplario(unidad,ventanaJugador));
    }

    @Override
    public void actualizarBotonera() {
        ventanaJugador.getPanelAcciones().configurarBotones(unidad);
    }

    @Override
    public void actualizarPanelEstado() {

    }
}
