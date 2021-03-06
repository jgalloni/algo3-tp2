package modelo.unidades;

import modelo.excepciones.Unidades.ExcepcionDeAccionDeUnidad;
import modelo.excepciones.Unidades.ExcepcionYaActuo;
import modelo.jugabilidad.auxiliares.Costo;
import modelo.jugabilidad.auxiliares.Vision;
import modelo.jugabilidad.utilidadesMapa.Coordenada;

public abstract class UnidadGuerrera extends Unidad {

    protected Danio danio;

    public UnidadGuerrera(Resistencia resistencia,Danio danio,int vision,Ubicacion ubicacion,int suministros,Costo costo,int tiempoDeEntrenamiento,int movilidad,Vision visionJugador,int transporte){
        super(resistencia,vision,ubicacion,suministros,costo,tiempoDeEntrenamiento,movilidad,visionJugador,transporte);
        this.danio=danio;
    }

    public UnidadGuerrera(Resistencia resistencia, Danio danio, int vision, Ubicacion ubicacion, int suministro, Costo costo, int tiempoDeEntrenamiento, int movilidad) {
        super(resistencia,vision,ubicacion,suministro,costo,tiempoDeEntrenamiento,movilidad);
        this.danio=danio;
    }

    public int getRangoAereo(){
        return this.danio.getRangoAereo();
    }

    public int getRangoTerrestre(){
        return this.danio.getRangoTerrestre();
    }

    public int getDanioTerrestre() {
        return danio.getDanioTierra();
    }

    public int getDanioAereo() {
        return danio.getDanioAire();
    }

    public void atacar(Coordenada coordenada) throws ExcepcionDeAccionDeUnidad {
        if(!this.accion.puedoActuar()) throw new ExcepcionYaActuo();
        ProxiDeAtaque.atacar(this,coordenada);
        this.accion=this.accion.actuo();
    }
}
