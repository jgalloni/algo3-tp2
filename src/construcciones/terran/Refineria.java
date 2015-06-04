package construcciones.terran;

import interfaces.Construible;

import java.util.ArrayList;

import excepciones.ExcepcionNecesitaConstruirOtroEdificio;
import unidades.Vida;
import jugabilidad.Mapa;
import jugabilidad.auxiliares.Costo;
import jugabilidad.auxiliares.Recursos;
import jugabilidad.utilidadesMapa.Coordenadas;


public class Refineria extends ConstruccionTerran {

	private Recursos recursosDeJugador;
	private final int gasRecolectados = 10;
	
	public Refineria(){};
	public Refineria(Recursos recursos){
		nombre = "Refineria";
		vida = new Vida(750);
		costo = new Costo(100,0);
		tiempoDeConstruccion = 6;
		recursosDeJugador = recursos;
	}


	@Override
	public void agregarse(Mapa mapa, Coordenadas coordenadas) {
		
		mapa.agregarEnTierra(this, coordenadas);
		
	}


	@Override
	public void update() {
		recursosDeJugador.agregarRecursos(0, gasRecolectados);
		
	}
	@Override
	public <T extends Construible> void verificaConstruccionPrevia(
			ArrayList<T> cs) throws ExcepcionNecesitaConstruirOtroEdificio {
		// TODO Auto-generated method stub
		
	}

}
