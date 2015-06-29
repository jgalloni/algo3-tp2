package vista.Actions.accionesEntrenar;

import construcciones.terran.Barraca;
import control.ObservadorDeExcepciones;
import excepciones.construcciones.ExcepcionNoSePuedeEntrenarUnidad;
import vista.sonido.SonidosDelJuego;
import vista.ventanaJugadores.ObservadorEstado;
import vista.ventanaJugadores.ObservadorRecursosSuministros;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ActionEntrenarMarine implements MouseListener {
    private final Barraca barraca;

    public ActionEntrenarMarine(Barraca unidad) {
        this.barraca=unidad;
    }

    @Override
    public void mouseClicked(MouseEvent m) {
        if (m.getButton()!=MouseEvent.BUTTON1) return;

        try {
            this.barraca.entrenarMarine();

            System.out.println("Marine en entrenamiento");
            SonidosDelJuego.getInstance().reproducirUnidadEntrenada();
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
