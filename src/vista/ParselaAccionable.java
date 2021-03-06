package vista;

import control.ObservadorDeExcepciones;
import control.vistaMapa.ObservadorMapa;
import modelo.excepciones.Mapa.ExcepcionNoSePudoAgregarAlMapa;
import modelo.excepciones.construcciones.ExcepcionNoSePuedeConstruir;
import modelo.interfaces.ColocableEnMapa;
import modelo.jugabilidad.ProxyMapa;
import modelo.jugabilidad.utilidadesMapa.Coordenada;
import modelo.unidades.ProxyDeHechizos;
import vista.auxiliares.jugador.displays.DisplayNotificaciones;
import vista.auxiliares.jugador.observadores.ObservadorEstado;
import vista.auxiliares.jugador.observadores.ObservadorRecursosSuministros;
import vista.sonido.SonidosDelJuego;
import vista.ventanaJugadores.VentanaJugador;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ParselaAccionable implements MouseListener {

    VentanaJugador ventana;
    Coordenada coordenada;
    DisplayNotificaciones displayNotificaciones;
    IVista vista;

    public ParselaAccionable(VentanaJugador ventana, Coordenada coordenada,IVista vista){
        this.ventana = ventana;
        this.displayNotificaciones = ventana.getDisplayNotificaciones();
        this.coordenada = coordenada;
        this.vista=vista;
    }

    @Override
    public void mouseClicked(MouseEvent m) {

        System.out.println("Parsela clickeada " + coordenada.getX() + " " + coordenada.getY()); //Para test. ya se que funciona
        ObservadorMapa.getInstance().informarCambios();

        if (m.getButton() == MouseEvent.BUTTON3) {

            if(this.verificarConstruccionesEnEspera()) {
                System.out.println("Edificio creado en el mapa");
                SonidosDelJuego.getInstance().reproducirEdificioEnConstruccion();

                ObservadorRecursosSuministros.getInstance().informarCambios();
                ObservadorMapa.getInstance().informarCambios();
                ObservadorDeExcepciones.getInstance().borrarDisplay();
            }

            if(this.verificarAccionEnEspera()){
                System.out.println("Accion de la unidad realizada");
                ObservadorRecursosSuministros.getInstance().informarCambios();
                ObservadorEstado.getInstance().informarCambios();
                ObservadorMapa.getInstance().informarCambios();
                ObservadorDeExcepciones.getInstance().borrarDisplay();
            }

        }

        if(m.getButton() == MouseEvent.BUTTON1){
            ColocableEnMapa seleccionado = ProxyMapa.getInstance().obtenerDeCapaAerea(coordenada);
            if(seleccionado==null)
                seleccionado=ProxyMapa.getInstance().obtenerDeCapaTerrestre(coordenada);
            if(this.ventana.obtenerJugador()== ProxyDeHechizos.obtenerDuenio(seleccionado)) {
                vista.actualizarBotonera();
            }
            else{
                this.ventana.getPanelAcciones().limpiar();
            }

            ObservadorDeExcepciones.getInstance().borrarDisplay();

            this.actualizarPanelesDeEstado();

        }

    }

    private void actualizarPanelesDeEstado() {
        ventana.borrarPanelDeEstadoAnterior(); // el tema es que caundo tocas el pasto no se activa la VistaPasto entonces hay q borrar manualmente
        vista.actualizarPanelEstado();
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
