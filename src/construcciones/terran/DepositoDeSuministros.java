package construcciones.terran;

import jugabilidad.Mapa;
import jugabilidad.utilidadesMapa.Coordenadas;
import fiuba.algo3.algocraft.unidades.Vida;
import auxiliares.Costo;



public class DepositoDeSuministros extends ConstruccionTerran{
	
	private final int capacidadExtra = 5;
	protected int pobMaxDeJugador;
	
	public DepositoDeSuministros(int pobMaxDeJugador){
		vida = new Vida(750);
		costo = new Costo(100,0);
		tiempoDeConstruccion = 6;
		this.pobMaxDeJugador = pobMaxDeJugador;
		
		this.aumentarPoblacionMaxima();
	}
	
	public int getCapacidadExtra(){
		return capacidadExtra;
	}

	private void aumentarPoblacionMaxima(){
		pobMaxDeJugador += capacidadExtra;
	}
	
	@Override
	public void agregarse(Mapa mapa, Coordenadas coordenadas) {
		
		mapa.agregarEnTierra(this, coordenadas);
		
	}
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}


}
