package control.Actions.accionesEntrenar;

import modelo.construcciones.protoss.Acceso;
import control.ObservadorDeExcepciones;
import modelo.excepciones.construcciones.ExcepcionNoSePuedeEntrenarUnidad;
import vista.auxiliares.jugador.observadores.ObservadorEstado;
import vista.auxiliares.jugador.observadores.ObservadorRecursosSuministros;
import vista.sonido.SonidosDelJuego;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ActionEntrenarDragon implements MouseListener {
    private final Acceso acceso;

    public ActionEntrenarDragon(Acceso unidad) {
        this.acceso= unidad;
    }


    @Override
    public void mouseClicked(MouseEvent m) {
        if (m.getButton()!=MouseEvent.BUTTON1) return;

        try {
            this.acceso.entrenarDragon();

            System.out.println("Dragon en entrenamiento");
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
