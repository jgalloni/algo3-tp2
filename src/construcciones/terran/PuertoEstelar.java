package construcciones.terran;

import construcciones.CentroDeEntrenamiento;
import construcciones.Construccion;
import excepciones.Mapa.ExcepcionNoSePudoAgregarAlMapa;
import excepciones.construicciones.ExcepcionNecesitaConstruirFabrica;
import excepciones.construicciones.ExcepcionNoSePuedeConstruir;
import excepciones.construicciones.ExcepcionNoSePuedeEntrenarUnidad;
import interfaces.Construible;
import jugabilidad.Jugador;
import jugabilidad.Mapa;
import jugabilidad.auxiliares.Costo;
import jugabilidad.auxiliares.Recursos;
import jugabilidad.utilidadesMapa.Coordenadas;
import unidades.terrran.Espectro;
import unidades.terrran.NaveCiencia;
import unidades.terrran.NaveTransporteTerran;
import unidades.terrran.ResistenciaTerran;

import java.util.ArrayList;


public class PuertoEstelar extends CentroDeEntrenamiento{

	//necesita que la Fabrica haya sido construida para poder crearse

	
	public PuertoEstelar(){
		resistencia = new ResistenciaTerran(1300);
		costo = new Costo(150,100);
		tiempoDeConstruccion = 10;

	}
	public PuertoEstelar(Jugador j){
		resistencia = new ResistenciaTerran(1300);
		costo = new Costo(150,100);
		tiempoDeConstruccion = 10;
		this.jugador = j;
	}


	@Override
	public <T extends Construible> void esConstruible(ArrayList<T> cs,Recursos recursosRecolectados,Coordenadas coordenadas) throws ExcepcionNoSePuedeConstruir{
		boolean construible = false;

		for (T c : cs) {
			if (((Construccion) c).habilitaAConstruir(this))
				construible = true;
		}
		
		if(!construible)
				throw new ExcepcionNecesitaConstruirFabrica();
		
		super.verificarRecursosDisponibles(recursosRecolectados);
		
	}


	public Espectro entrenarEspectro() {
		Espectro espectro = new Espectro(this.jugador.getVisibilidad());
		try {
			this.validarCreacionUnidad(espectro);
		} catch (ExcepcionNoSePuedeEntrenarUnidad e) {
			e.printStackTrace();
			return espectro;
		}
		this.colaDeEntrenamiento.add(espectro);
		return espectro;
	}

	public NaveCiencia entrenarNaveCiencia() {
		NaveCiencia naveCiencia = new NaveCiencia(this.jugador.getVisibilidad());
		try {
			this.validarCreacionUnidad(naveCiencia);
		} catch (ExcepcionNoSePuedeEntrenarUnidad e) {
			e.printStackTrace();
			return naveCiencia;
		}
		this.colaDeEntrenamiento.add(naveCiencia);
		return naveCiencia;
	}

	public NaveTransporteTerran entrenarNaveTransporte() {
		NaveTransporteTerran naveTransporte = new NaveTransporteTerran(this.jugador.getVisibilidad());
		try {
			this.validarCreacionUnidad(naveTransporte);
		} catch (ExcepcionNoSePuedeEntrenarUnidad e) {
			e.printStackTrace();
			return naveTransporte;
		}
		this.colaDeEntrenamiento.add(naveTransporte);
		return naveTransporte;
	}

	@Override
	public void moverse(Coordenadas hasta, Mapa mapa) throws ExcepcionNoSePudoAgregarAlMapa {

	}

}

