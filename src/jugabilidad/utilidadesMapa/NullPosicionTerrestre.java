package jugabilidad.utilidadesMapa;

import excepciones.Mapa.ExcepcionNoSePudoAgregarAlMapa;
import interfaces.ColocableEnMapa;
import jugabilidad.Mapa;
import jugabilidad.ProxyMapa;

public class NullPosicionTerrestre implements ColocableEnMapa {


    @Override
    public void agregarse(Mapa mapa, Coordenadas coordenadas) throws ExcepcionNoSePudoAgregarAlMapa {

        mapa.agregarEnCapaTerrestre(this, coordenadas);

    }
}
