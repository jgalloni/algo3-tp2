package vista.Actions.accionesEntrenar;

import construcciones.terran.PuertoEstelar;
import control.ObservadorDeExcepciones;
import excepciones.construicciones.ExcepcionNoSePuedeEntrenarUnidad;
import vista.ventanaJugadores.ObservadorEstado;
import vista.ventanaJugadores.ObservadorRecursosSuministros;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ActionEntrenarEspectro implements MouseListener {
    private final PuertoEstelar puertoEstelar;

    public ActionEntrenarEspectro(PuertoEstelar unidad) {
        this.puertoEstelar=unidad;
    }

    @Override
    public void mouseClicked(MouseEvent m) {
        if(m.getButton()!=MouseEvent.BUTTON1) return;
        try {
            puertoEstelar.entrenarEspectro();
            System.out.println("Espectro en entrenamiento");
            ObservadorRecursosSuministros.getInstance().informarCambios();
            ObservadorEstado.getInstance().informarCambios();

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