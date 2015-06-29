package construcciones.protoss;

import construcciones.CentroDeEntrenamiento;
import construcciones.Construccion;
import excepciones.Mapa.ExcepcionNoSePudoAgregarAlMapa;
import excepciones.construcciones.ExcepcionNecesitaConstruirPortalEstelar;
import excepciones.construcciones.ExcepcionNoSePuedeClonarEdificio;
import excepciones.construcciones.ExcepcionNoSePuedeConstruir;
import excepciones.construcciones.ExcepcionNoSePuedeEntrenarUnidad;
import interfaces.Clonable;
import interfaces.Construible;
import jugabilidad.Jugador;
import jugabilidad.Mapa;
import jugabilidad.auxiliares.Costo;
import jugabilidad.auxiliares.Recursos;
import jugabilidad.utilidadesMapa.Coordenada;
import unidades.protoss.AltoTemplario;
import unidades.protoss.Clon;
import unidades.protoss.ResistenciaProtoss;

import java.util.ArrayList;


public class ArchivosTemplarios extends CentroDeEntrenamiento implements IEdificioProtoss, Clonable {

	public ArchivosTemplarios(){

		resistencia = new ResistenciaProtoss(500,500);
		costo = new Costo(150,200);
		tiempoDeConstruccion = 9;
	}

	public ArchivosTemplarios(Jugador j){

		resistencia = new ResistenciaProtoss(500,500);
		costo = new Costo(150,200);
		tiempoDeConstruccion = 9;
		this. jugador = j;
	}


	public int getEscudo() {
		return ((ResistenciaProtoss)resistencia).getEscudoActual();
	}

	@Override
	public <T extends Construible> void esConstruible(ArrayList<T> cs,Recursos recursosRecolectados, Coordenada coordenada) throws ExcepcionNoSePuedeConstruir{
		boolean construible = false;

		for (T c : cs) {
			if ( ((Construccion)c).habilitaAConstruir(this))
				construible = true;
		}
		
		if(!construible)
				throw new ExcepcionNecesitaConstruirPortalEstelar();

		super.verificarRecursosDisponibles(recursosRecolectados);
		
	}


	public AltoTemplario entrenarAltoTemplario() throws ExcepcionNoSePuedeEntrenarUnidad {
		AltoTemplario altoTemplario = new AltoTemplario(this.jugador.getVisibilidad());

		this.validarCreacionUnidad(altoTemplario);

		this.colaDeEntrenamiento.add(altoTemplario);
		return altoTemplario;
	}

	@Override
	public void moverse(Coordenada hasta, Mapa mapa) throws ExcepcionNoSePudoAgregarAlMapa {

	}

	@Override
	public Clon getClon() throws ExcepcionNoSePuedeClonarEdificio {
		throw new ExcepcionNoSePuedeClonarEdificio();
	}
}

