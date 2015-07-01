package unidades.protoss;

import excepciones.Mapa.ExcepcionNoSePudoAgregarAlMapa;
import excepciones.Unidades.ExcepcionAtacarAUnidadAliada;
import excepciones.Unidades.ExcepcionNoPuedeAtacarAire;
import excepciones.Unidades.ExcepcionObjetivoFueraDeRango;
import excepciones.Unidades.ExcepcionYaActuo;
import interfaces.Cargable;
import interfaces.ColocableEnMapa;
import interfaces.Daniable;
import jugabilidad.Mapa;
import jugabilidad.auxiliares.Costo;
import jugabilidad.auxiliares.Vision;
import jugabilidad.utilidadesMapa.Coordenada;
import unidades.Danio;
import unidades.Terrestre;
import unidades.UnidadGuerrera;

public class Zealot extends UnidadGuerrera implements Cargable {

    public Zealot(Vision visionJugador){
        super(new ResistenciaProtoss(100,60),new Danio(8,0,1,0),7,new Terrestre(),2,new Costo(100,0),4,5,visionJugador,2);
    }

    public Zealot(){
        super(new ResistenciaProtoss(100,60),new Danio(8,0,1,0),7,new Terrestre(),2,new Costo(100,0),4,5);
    }

    public int getEscudo() {
        return ((ResistenciaProtoss)resistencia).getEscudoActual();
    }

    public ColocableEnMapa getClon() {
        ResistenciaProtoss resistenciaProtoss = new ResistenciaProtoss(resistencia.getVidaMaxima(),((ResistenciaProtoss) resistencia).getEscudoActual());

        return new ClonGuerrero(resistenciaProtoss,this.vision,this.ubicacion,this.movilidad,this.transporte,this);
    }

    public void atacarAire(Daniable objetivo) throws ExcepcionObjetivoFueraDeRango, ExcepcionAtacarAUnidadAliada, ExcepcionYaActuo, ExcepcionNoPuedeAtacarAire {
        throw new ExcepcionNoPuedeAtacarAire();
    }

    @Override
    public void moverse(Coordenada hasta, Mapa mapa) throws ExcepcionNoSePudoAgregarAlMapa {

        mapa.moverEnCapaTerrestre(this, hasta);

    }

    @Override
    public  void recibirEMP(){
        this.resistencia.quitar(this.getEscudo());
    }
}
