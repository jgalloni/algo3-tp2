package vista.edificios.protoss;

import modelo.construcciones.protoss.NexoMineral;
import control.BufferImagenes;
import modelo.interfaces.ColocableEnMapa;
import vista.IVista;
import vista.auxiliares.ImagePanel;
import vista.panelesDeEstado.panelesDeConstruccion.PanelConstruccion;
import vista.ventanaJugadores.VentanaJugador;

import java.awt.*;

public class VistaNexoMineral extends ImagePanel implements IVista{

    private static final int ANCHO = 64;
    private static final int ALTO = 64;
    private final NexoMineral edificio;
    private final VentanaJugador ventanaJugador;
    private static final BufferImagenes BUFFERIMAGENES = BufferImagenes.getInstance();

    public VistaNexoMineral(ColocableEnMapa nexoMineral, VentanaJugador ventanaJugador) {
        super(ANCHO, ALTO, BUFFERIMAGENES.obtenerImagen("NexoMineral").getImage().getScaledInstance(ANCHO, ALTO, Image.SCALE_FAST));
        super.setBackground( BUFFERIMAGENES.obtenerImagen("Pasto").getImage());
        this.edificio= (NexoMineral) nexoMineral;
        this.ventanaJugador=ventanaJugador;
    }

    @Override
    public void actualizarBotonera() {
        ventanaJugador.getPanelAcciones().configurarBotones(edificio);
    }

    @Override
    public void actualizarPanelEstado() {
        PanelConstruccion panelDeConstruccion = new PanelConstruccion();

        ventanaJugador.borrarPanelDeEstadoAnterior();
        this.cargarInfoAlPanelDeEstado(panelDeConstruccion);
        ventanaJugador.mostrarPanelDeEstado(panelDeConstruccion);

    }

    private void cargarInfoAlPanelDeEstado(PanelConstruccion panelDeConstruccion) {

        panelDeConstruccion.cargarNombre(edificio.getClass().getSimpleName());
        panelDeConstruccion.cargarVida(String.valueOf(edificio.getVida()));
        panelDeConstruccion.cargarEscudo(String.valueOf(edificio.getEscudo()));
    }
}
