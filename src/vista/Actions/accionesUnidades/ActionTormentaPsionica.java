package vista.Actions.accionesUnidades;

import excepciones.Unidades.ExcepcionObjetivoFueraDeRango;
import excepciones.Unidades.ExcepcionYaActuo;
import jugabilidad.RazaDeJugador.JugadorProtoss;
import jugabilidad.utilidadesMapa.Coordenadas;
import unidades.ProxyDeHechizos;
import unidades.protoss.AltoTemplario;
import vista.Actions.WraperAccionActuar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionTormentaPsionica implements ActionListener, AccionUnidad {
    private AltoTemplario unidad;
    private WraperAccionActuar wraperAccionActuar;

    public ActionTormentaPsionica(AltoTemplario unidad, WraperAccionActuar wraperAccionActuar) {
        this.unidad = unidad;
        this.wraperAccionActuar = wraperAccionActuar;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

    @Override
    public void actuarEn(Coordenadas coordenada) throws ExcepcionObjetivoFueraDeRango, ExcepcionYaActuo {
        unidad.tormentaPsionica(coordenada, (JugadorProtoss) ProxyDeHechizos.obtenerDuenio(unidad));
        wraperAccionActuar.setAccionActuar(null);
    }
}
