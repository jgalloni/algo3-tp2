package vista.edificios.protoss;

import construcciones.protoss.EdificioEnInvocacion;
import interfaces.ColocableEnMapa;
import vista.IVista;
import vista.auxiliares.ImagePanel;
import vista.panelesDeEstado.panelesDeConstruccion.PanelEdificioEnConstruccion;
import vista.ventanaJugadores.VentanaJugador;

import javax.swing.*;

public class VistaEdificioEnInvocacion  extends ImagePanel implements IVista {

    private static final int ANCHO = 64;
    private static final int ALTO = 64;
    private final EdificioEnInvocacion edificio;
    private final VentanaJugador ventanaJugador;
    private static String imagePath="images/construcciones/edificioEnConstruccion.png";

    public VistaEdificioEnInvocacion(ColocableEnMapa edificioEnConstruccion, VentanaJugador ventanaJugador) {
        super(ANCHO,ALTO,new ImageIcon(imagePath).getImage().getScaledInstance(ANCHO, ALTO,  java.awt.Image.SCALE_SMOOTH));
        super.setBackground(new ImageIcon("src/vista/paisaje/imagenes/pasto.png").getImage());
        this.edificio= (EdificioEnInvocacion) edificioEnConstruccion;
        this.ventanaJugador=ventanaJugador;
    }

    @Override
    public void actualizarBotonera() {
        ventanaJugador.getPanelAcciones().limpiar();
    }

    @Override
    public void actualizarPanelEstado() {
        PanelEdificioEnConstruccion panelDeConstruccion = new PanelEdificioEnConstruccion();

        ventanaJugador.borrarPanelDeEstadoAnterior();
        this.cargarInfoAlPanelDeEstado(panelDeConstruccion);
        ventanaJugador.mostrarPanelDeEstado(panelDeConstruccion);

    }

    private void cargarInfoAlPanelDeEstado(PanelEdificioEnConstruccion panelDeConstruccion) {
/*
        panelDeConstruccion.cargarNombre(edificio.getEdificioAConvertirse().getClass().getSimpleName());
        panelDeConstruccion.cargarVida(String.valueOf(edificio.getEdificioAConvertirse().getVida()));
        panelDeConstruccion.mostrarTiempoDeConstruccion(edificio.getTiempoDeConstruccionActual(), edificio.getEdificioAConvertirse().getTiempoDeConstruccion());
        //TODO ver como se si tiene escudo o no.
        */
       // panelDeConstruccion.cargarDatosDeEdificioEnConstruccion(edificio);
    }
}
