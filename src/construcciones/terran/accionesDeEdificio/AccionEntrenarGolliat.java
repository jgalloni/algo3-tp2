package construcciones.terran.accionesDeEdificio;

import unidades.Unidad;
import unidades.terrran.Golliat;
import excepciones.ExcepcionSuministrosInsuficientes;
import excepciones.ExcepcionRecursosInsuficientes;
import interfaces.AccionDeEdificio;
import jugabilidad.Jugador;

public class AccionEntrenarGolliat implements AccionDeEdificio{

	@Override
	public void accionDeEdificio(Jugador jugador) {
		Unidad golliat = new Golliat();
		//falta Transporte,TiempoDeCosntruccion,Suministro,RangoDeAtaque
		
		try {
			jugador.getRecursos().gastarRecursos(golliat.getCosto());
		} catch (ExcepcionRecursosInsuficientes e) {
			e.printStackTrace();
			return;
		}
		
		try {
			jugador.agregarSuministros(golliat.getSuministro());
		} catch (ExcepcionSuministrosInsuficientes e) {
			e.printStackTrace();
			return;
		}
		
		jugador.agregarUnidad(golliat);
		
	}
}