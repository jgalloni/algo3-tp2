package vista.Actions.accionesEntrenar;

import construcciones.protoss.PortalEstelar;
import control.ObservadorDeExcepciones;
import excepciones.construicciones.ExcepcionNoSePuedeEntrenarUnidad;
import vista.ventanaJugadores.ObservadorRecursosSuministros;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ActionEntrenarNaveTransporteProtoss implements MouseListener {

    private PortalEstelar portalEstelar;

    public ActionEntrenarNaveTransporteProtoss(PortalEstelar unidad) {
        this.portalEstelar=unidad;
    }

    @Override
    public void mouseClicked(MouseEvent m) {
        if (m.getButton()!=MouseEvent.BUTTON1) return;

        try {
            this.portalEstelar.entrenarNaveTransporte();

            System.out.println("Nave Transporte en entrenamiento");
            ObservadorRecursosSuministros.getInstance().informarCambios();

        } catch (ExcepcionNoSePuedeEntrenarUnidad e) {
            ObservadorDeExcepciones.getInstance().informarNuevaExcepcion(e);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
