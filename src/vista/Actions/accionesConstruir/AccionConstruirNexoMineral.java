package vista.Actions.accionesConstruir;

import excepciones.Mapa.ExcepcionNoSePudoAgregarAlMapa;
import excepciones.construicciones.ExcepcionNoSePuedeConstruir;
import jugabilidad.utilidadesMapa.Coordenadas;
import vista.auxiliares.jugador.BotoneraDeConstruccionesProtoss;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AccionConstruirNexoMineral extends AccionConstruir implements ActionListener {
    BotoneraDeConstruccionesProtoss botonera;

    public AccionConstruirNexoMineral(BotoneraDeConstruccionesProtoss botonera) {
        this.botonera = botonera;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        botonera.setAccionConstruirEnEspera(this);
    }

    @Override
    public void construirEn(Coordenadas coordenada) throws ExcepcionNoSePuedeConstruir, ExcepcionNoSePudoAgregarAlMapa {
        botonera.getJugador().construirNexoMineral(coordenada);
        botonera.setAccionConstruirEnEspera(null);
    }
}
