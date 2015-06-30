package vista.Actions.accionesMenu;

import jugabilidad.Juego;
import jugabilidad.Jugador;
import unidades.ProxiDeAtaque;
import unidades.ProxyDeHechizos;
import vista.CrearJugador;
import vista.VentanaJuego;
import vista.ventanaJugadores.VentanaJugador;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AccionJugar implements ActionListener {

    private VentanaJuego ventanaJuego;
    private String nombreUsado;
    private String colorUsado;

    public AccionJugar(VentanaJuego ventanaJuego){
            this.ventanaJuego = ventanaJuego;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        VentanaJugador ventana1;
        VentanaJugador ventana2;

        Juego juego = this.ventanaJuego.getJuego();

        nombreUsado= new String();
        colorUsado= new String();
        JButton c = (JButton) (e.getSource());
        c.setVisible(false);

        ventana1 = this.crearJugador("Crear Jugador 1");
        ventana2 = this.crearJugador("Crear Jugador 2");

        juego.inicializarProxysDeAtaqueYHechizosDeJugadores();

        ventanaJuego.agregarVentana(ventana1);
        ventanaJuego.agregarVentana(ventana2);


    }

    private VentanaJugador crearJugador(String tituloJugador){
        CrearJugador crearJugador = new CrearJugador(ventanaJuego,tituloJugador,nombreUsado,colorUsado);
        crearJugador.mostrar();
        nombreUsado=crearJugador.getNombreJugador();
        colorUsado=crearJugador.getColorJugador();
        return crearJugador.getVentana();
    }
}
