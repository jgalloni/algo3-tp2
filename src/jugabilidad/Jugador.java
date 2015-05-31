package jugabilidad;

import java.util.ArrayList;

import auxiliares.Recursos;
import construcciones.Construccion;
import construcciones.ProxyConstrucciones;
import excepciones.ExcepcionNecesitaConstruirAcceso;
import excepciones.ExcepcionNecesitaConstruirBarraca;
import excepciones.ExcepcionNecesitaConstruirFabrica;
import excepciones.ExcepcionNecesitaConstruirPortalEstelar;
import excepciones.ExcepcionNecesitaCrearOtraConstruccionPrevia;
import excepciones.ExcepcionRecursosInsuficientes;
import fiuba.algo3.algocraft.unidades.Unidad;

public class Jugador {
	
	private Recursos recursosRecolectados;
	private ArrayList<Construccion> construccionesCreadas = new ArrayList<Construccion>();
	private ArrayList<Unidad> unidadesCreadas = new ArrayList<Unidad>();

	
	
	public void construir(Construccion construccion){
		ProxyConstrucciones proxy = new ProxyConstrucciones();
		
		try{
			proxy.esConstruible(construccion, construccionesCreadas);
		}catch(ExcepcionNecesitaCrearOtraConstruccionPrevia e){
			return; //dentro de las excepciones habria q hacer q aparezca un cartelito q le avise al usuario porque no la puede construir
		}
		
		try {
			recursosRecolectados.gastarRecursos(construccion.getCosto());
		} catch (ExcepcionRecursosInsuficientes e) {
			return; //poner cartelito en la excepcion avisando que no se construye porque no tiene recursos
		}
		
		construccionesCreadas.add(construccion);
	}
	
	public void crearUnidad(Unidad unidad){
		try {
			recursosRecolectados.gastarRecursos(unidad.getCosto());
		} catch (ExcepcionRecursosInsuficientes e) {
			return; //poner cartelito en la excepcion avisando que no se construye porque no tiene recursos
		}
		
		unidadesCreadas.add(unidad);
	}
	
	public Construccion buscarConstruccionCreada(String nombre){
		for(Construccion c : construccionesCreadas){
			if(c.getNombre() == nombre){
					return c;
			}
		}
		return null;
	}
	
}
