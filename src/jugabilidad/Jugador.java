package jugabilidad;

import construcciones.Construccion;
import construcciones.EdificioEnConstruccion;
import excepciones.ExcepcionNoSePuedeConstruir;
import excepciones.ExcepcionSuministrosInsuficientes;
import interfaces.Construible;
import interfaces.Entrenable;
import jugabilidad.auxiliares.Recursos;
import jugabilidad.auxiliares.Suministros;
import jugabilidad.utilidadesMapa.Coordenadas;

import java.util.ArrayList;

public abstract class Jugador {

	protected Recursos recursosRecolectados;
	protected Suministros suministros;
	protected ArrayList<Construible> construccionesCreadas = new ArrayList<>();
	protected ArrayList<Entrenable> unidadesCreadas = new ArrayList<>();
	//protected ArrayList<Construible> colaDeConstruccion = new ArrayList<>();
	protected ArrayList<EdificioEnConstruccion> edificiosEnConstruccion = new ArrayList<>();

	protected void construir(Construible construccionCreada,Coordenadas coordenadas){
		
		try {
			construccionCreada.esConstruible(construccionesCreadas,recursosRecolectados);
		} catch (ExcepcionNoSePuedeConstruir e) {
			e.printStackTrace();
			return;
		}

		EdificioEnConstruccion edificioEnConstruccion = new EdificioEnConstruccion(coordenadas,construccionCreada);
		edificiosEnConstruccion.add(edificioEnConstruccion);
		//colaDeConstruccion.add(construccionCreada);


	}

	public Recursos getRecursos() {
		return recursosRecolectados;
	}
	
	public void agregarUnidad(Entrenable unidad){
		unidadesCreadas.add(unidad);
	}

	public void agregarMinerales(int i) {
		recursosRecolectados.agregarRecursos(i,0);
		
	}

	public void update() {
		Mapa mapa = SingletonMapa.getInstance();

		for (int i = 0; i < edificiosEnConstruccion.size(); i++) {
			EdificioEnConstruccion e = edificiosEnConstruccion.get(i);
			e.disminuirTiempoDeConstruccion();

			if (e.getTiempoDeConstruccion() == 0) {
				Construible t = e.finalizarConstruccion();
				construccionesCreadas.add(t);
				((Construccion) t).agregarse(e.getCoordenada());
				//para evitar casteo hacer que Construible herede de Actualizable
				edificiosEnConstruccion.remove(e);
			}
		}
	}
	
	public boolean buscarConstruccion(Construible c1){
		for(Construible c2: construccionesCreadas){
			if(c1.equals(c2)){
				return true;
			}
		}
		return false;
	}


	public void usarSuministrosDisponibles(int i) throws ExcepcionSuministrosInsuficientes {
		suministros.usarSuministros(i);
		
	}

	/*
	public void aumentarSuministros(int capacidadExtra) {
		suministros.aumentarSuministrosDisponibles(capacidadExtra);
		
	}
	*/

	public boolean buscarUnidad(Entrenable m) {
		for(Entrenable e: unidadesCreadas){
			if(m.equals(e)){
				return true;
			}
		}
		return false;
	}

}
