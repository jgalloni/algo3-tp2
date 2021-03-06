package vista.edificios.protoss;

import modelo.construcciones.protoss.ArchivosTemplarios;
import control.BufferImagenes;
import modelo.interfaces.ColocableEnMapa;
import vista.IVista;
import vista.auxiliares.ImagePanel;
import vista.panelesDeEstado.panelesDeConstruccion.PanelCentroDeEntrenamiento;
import vista.ventanaJugadores.VentanaJugador;

import java.awt.*;

public class VistaArchivosTemplarios extends ImagePanel implements IVista{

    private static final int ANCHO = 64;
    private static final int ALTO = 64;
    private static final BufferImagenes BUFFERIMAGENES = BufferImagenes.getInstance();
    private final ArchivosTemplarios edificio;
    private final VentanaJugador ventanaJugador;


    public VistaArchivosTemplarios(ColocableEnMapa archivosTemplarios, VentanaJugador ventanaJugador) {
        super(ANCHO, ALTO, BUFFERIMAGENES.obtenerImagen("ArchivosTemplarios").getImage().getScaledInstance(ANCHO, ALTO, Image.SCALE_FAST));
        super.setBackground( BUFFERIMAGENES.obtenerImagen("Pasto").getImage());
        this.edificio= (ArchivosTemplarios) archivosTemplarios;
        this.ventanaJugador=ventanaJugador;
    }

    @Override
    public void actualizarBotonera() {
        ventanaJugador.getPanelAcciones().configurarBotones(edificio);
    }

    @Override
    public void actualizarPanelEstado() {
        PanelCentroDeEntrenamiento panelDeConstruccion = new PanelCentroDeEntrenamiento();

        ventanaJugador.borrarPanelDeEstadoAnterior();
        this.cargarInfoAlPanelDeEstado(panelDeConstruccion);
        ventanaJugador.mostrarPanelDeEstado(panelDeConstruccion);

    }

    private void cargarInfoAlPanelDeEstado(PanelCentroDeEntrenamiento panelDeConstruccion) {

        panelDeConstruccion.cargarNombre(edificio.getClass().getSimpleName());
        panelDeConstruccion.cargarVida(String.valueOf(edificio.getVida()));
        panelDeConstruccion.cargarEscudo(String.valueOf(edificio.getEscudo()));
        panelDeConstruccion.cargarDatosDeColaDeEntrenamiento(edificio.getColaDeEntrenamiento());
    }
}
