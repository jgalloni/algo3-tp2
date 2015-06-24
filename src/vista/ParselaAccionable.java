package vista;

import control.ObservadorDeExcepciones;
import control.vistaMapa.ObservadorMapa;
import excepciones.Mapa.ExcepcionNoSePudoAgregarAlMapa;
import excepciones.construicciones.ExcepcionNoSePuedeConstruir;
import jugabilidad.utilidadesMapa.Coordenadas;
import vista.auxiliares.jugador.DisplayNotificaciones;
import vista.ventanaJugadores.ObservadorRecursosSuministros;
import vista.ventanaJugadores.VentanaJugador;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ParselaAccionable implements MouseListener {

    VentanaJugador ventana;
    Coordenadas coordenada;
    DisplayNotificaciones displayNotificaciones;
    IVista vista;

    public ParselaAccionable(VentanaJugador ventana, Coordenadas coordenada,IVista vista){
        this.ventana = ventana;
        this.displayNotificaciones = ventana.getDisplayNotificaciones();
        this.coordenada = coordenada;
        this.vista=vista;
    }

    @Override
    public void mouseClicked(MouseEvent m) {

        System.out.println("Parsela clickeada " + coordenada.getX() + " " + coordenada.getY()); //Para test. ya se que funciona

        if(this.verificarConstruccionesEnEspera())
        {
            System.out.println("Edificio creado en el mapa");

        }
        else if(this.verificarAccionEnEspera()){

            System.out.println("Accion de la unidad realizada");

        }
        else{
            vista.actualizarBotonera();
            vista.actualizarPanelEstado();
        }


        ObservadorRecursosSuministros.getInstance().informarCambios();
        ObservadorMapa.getInstance().informarCambiosEnMapa();
    }

    private boolean verificarAccionEnEspera() {
        if(ventana.getAccionActuarEnEspera() !=  null){
            try {
                ventana.getAccionActuarEnEspera().actuarEn(coordenada);
                return true;
            } catch (Throwable e) {
                ObservadorDeExcepciones.getInstance().informarNuevaExcepcion(e);
                return  false;
            }

        }else
            return  false;

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

    private boolean verificarConstruccionesEnEspera() {
        if(ventana.getAccionConstruirEnEspera() !=  null){

            try {
                ventana.getAccionConstruirEnEspera().construirEn(coordenada);
                return true;
            } catch (ExcepcionNoSePuedeConstruir | ExcepcionNoSePudoAgregarAlMapa e) {
                ObservadorDeExcepciones.getInstance().informarNuevaExcepcion(e);
                return  false;
            }

        }else
            return  false;

    }

}
