package construcciones.terran;

import construcciones.CentroDeEntrenamiento;
import construcciones.Construccion;
import excepciones.ExcepcionNecesitaConstruirBarraca;
import excepciones.ExcepcionNoSePuedeConstruir;
import interfaces.Construible;
import interfaces.Entrenable;
import jugabilidad.Jugador;
import jugabilidad.Mapa;
import jugabilidad.auxiliares.Costo;
import jugabilidad.auxiliares.Recursos;
import jugabilidad.utilidadesMapa.Coordenadas;
import unidades.Vida;
import unidades.terrran.Golliat;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;


public class Fabrica extends CentroDeEntrenamiento{
	
	//necesita que la Barraca haya sido construida para poder crearse

	Queue<Entrenable> colaDeEntrenamiento = new LinkedList<Entrenable>();
	ArrayList<Entrenable> unidadesCreadas = new ArrayList<Entrenable>();
	Jugador jugador;


	public Fabrica(){
		nombre = "Fabrica";
		vida = new Vida(1250);
		costo = new Costo(200,100);
		tiempoDeConstruccion = 12;

	}
	public Fabrica(Jugador j){
		nombre = "Fabrica";
		vida = new Vida(1250);
		costo = new Costo(200,100);
		tiempoDeConstruccion = 12;
		this.jugador = j;
	}
	
	@Override
	public void agregarse(Mapa mapa, Coordenadas coordenadas) {
		
		mapa.agregarEnTierra(this, coordenadas);
		
	}

	@Override
	public void update() {

		super.update();
		if(!colaDeEntrenamiento.isEmpty()){
			Entrenable unidad= colaDeEntrenamiento.peek();

			unidad.disminuirTiempoDeEntrenamiento();

			if(unidad.getTiempoDeEntrenamiento() == 0){
				this.crearUnidad(unidad);
			}
		}
	}

	public void entrenarGolliat(Golliat g){
		colaDeEntrenamiento.add(g);
	}

	@Override
	public void recibirDanio(int danioParcial){

		vida.quitar(danioParcial);

	}

	@Override
	public <T extends Construible> void esConstruible(ArrayList<T> cs,Recursos recursosRecolectados) throws ExcepcionNoSePuedeConstruir{
		boolean construible = false;
		
		for (Iterator<T> iterator = cs.iterator(); iterator.hasNext();) {
			T c = iterator.next();
			if(((Construccion)c).habilitaAConstruir(this))
				construible = true;
		}
		
		if(!construible)
				throw new ExcepcionNecesitaConstruirBarraca();
		
		super.verificarRecursosDisponibles(recursosRecolectados);
		
	}
	
	public boolean habilitaAConstruir(PuertoEstelar t) {
		return true;
	}

}
