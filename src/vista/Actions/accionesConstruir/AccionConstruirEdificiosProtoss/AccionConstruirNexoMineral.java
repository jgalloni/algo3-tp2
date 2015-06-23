package vista.Actions.accionesConstruir.AccionConstruirEdificiosProtoss;

import excepciones.Mapa.ExcepcionNoSePudoAgregarAlMapa;
import excepciones.construicciones.ExcepcionNoSePuedeConstruir;
import jugabilidad.RazaDeJugador.JugadorProtoss;
import jugabilidad.utilidadesMapa.Coordenadas;
import vista.Actions.WraperAccion;
import vista.Actions.accionesConstruir.AccionConstruir;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AccionConstruirNexoMineral implements AccionConstruir,ActionListener {

    private JugadorProtoss jugador;
    private WraperAccion accionConstruirEnEspera;

    public AccionConstruirNexoMineral(JugadorProtoss jugador, WraperAccion accionConstruirEnEspera) {
        this.jugador = jugador;
        this.accionConstruirEnEspera = accionConstruirEnEspera;
        System.out.println("Accion NExo");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        accionConstruirEnEspera.setAccionConstruir(this);
    }

    @Override
    public void construirEn(Coordenadas coordenada) throws ExcepcionNoSePuedeConstruir, ExcepcionNoSePudoAgregarAlMapa {
        jugador.construirNexoMineral(coordenada);
        accionConstruirEnEspera.setAccionConstruir(null);
    }

}