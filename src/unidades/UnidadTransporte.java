package unidades;

import excepciones.Unidades.ExcepcionCargaSuperada;
import excepciones.Unidades.ExcepcionCargarUnidadEnemiga;
import excepciones.Mapa.ExcepcionNoSePudoAgregarAlMapa;
import excepciones.Unidades.ExcepcionYaActuo;
import interfaces.Cargable;
import interfaces.ColocableEnMapa;
import jugabilidad.ProxyMapa;
import jugabilidad.auxiliares.Costo;
import jugabilidad.auxiliares.Vision;
import jugabilidad.utilidadesMapa.Coordenadas;

import java.util.LinkedList;
import java.util.Queue;

public abstract class UnidadTransporte extends Unidad {

    private static int tranporteMax = 8;
    private Queue<Cargable> unidades;

    public UnidadTransporte(Resistencia resistencia,int vision, Ubicacion ubicacion, int suministros, Costo costo, int tiempoDeEntrenamiento,int movilidad, Vision visionJugador,int transporte) {
        super(resistencia,vision, ubicacion, suministros, costo, tiempoDeEntrenamiento,movilidad,visionJugador,transporte);
        this.unidades= new LinkedList<>();
    }

    public UnidadTransporte(Resistencia resistencia, int vision, Ubicacion ubicacion, int suministros, Costo costo, int tiempoDeEntrenamiento, int movilidad) {
        super(resistencia,vision, ubicacion, suministros, costo, tiempoDeEntrenamiento,movilidad);
        this.unidades= new LinkedList<>();
    }

    public void cargar(Cargable unidad) throws ExcepcionCargaSuperada, ExcepcionYaActuo, ExcepcionCargarUnidadEnemiga {
        if(!this.accion.puedoActuar()) throw new ExcepcionYaActuo();
        if(ProxyDeHechizos.esEnemigo(this,unidad)) throw new ExcepcionCargarUnidadEnemiga();
        int cargaTotal=0;
        for(Cargable a:unidades){
            cargaTotal+=a.getTransporte();
        }
        cargaTotal+=unidad.getTransporte();
        if(cargaTotal>tranporteMax) throw new ExcepcionCargaSuperada();
        unidades.add(unidad);
        ProxyMapa.getInstance().quitar((Unidad) unidad);
        this.accion.actuo();
    }

    public void descargar(Coordenadas coordenadas) throws ExcepcionNoSePudoAgregarAlMapa, ExcepcionYaActuo {
        if(!this.accion.puedoActuar()) throw new ExcepcionYaActuo();
        ProxyMapa proxy = ProxyMapa.getInstance();
        proxy.agregar((ColocableEnMapa) unidades.remove(), coordenadas);
        this.accion.actuo();
    }

    public int getTransporteOcupado() {
        int cargaTotal=0;
        for(Cargable a:unidades){
            cargaTotal+=a.getTransporte();
        }
        return cargaTotal;
    }

    @Override
    protected void matar() {
        if(this.resistencia.estaMuerto()){
            ProxyMapa mapa = ProxyMapa.getInstance();
            mapa.quitar(this);
            for (Cargable u:unidades){//mata a todas la unidades q carga
                ((Unidad)u).recibirDanio(500);
            }
        }
    }
}
