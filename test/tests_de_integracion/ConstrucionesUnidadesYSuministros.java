package tests_de_integracion;

import construcciones.protoss.Acceso;
import construcciones.protoss.Pilon;
import construcciones.terran.DepositoDeSuministros;
import jugabilidad.RazaDeJugador.JugadorProtoss;
import jugabilidad.RazaDeJugador.JugadorTerran;
import jugabilidad.auxiliares.Recursos;
import jugabilidad.auxiliares.Suministros;
import jugabilidad.utilidadesMapa.Coordenadas;
import org.junit.Assert;
import org.junit.Test;
import unidades.protoss.Dragon;
import unidades.terrran.Marine;

public class ConstrucionesUnidadesYSuministros {
    @Test
    public void NoPuedoEntrenarMasUnidadesPorLlegarALLimiteDeSuministros(){
        JugadorProtoss j = new JugadorProtoss(new Recursos(1000,1000),new Suministros(4,5));// 4 usados, 5 limite
        Acceso a = new Acceso(j);
        Dragon d;

        d = a.entrenarDragon();//+ 2 sumistro

        Assert.assertFalse(j.buscarUnidad(d));

    }

    @Test
     public void CreoVariosPilonesYAumentanLosSuministrosLimiteActuales(){
        Suministros s = new Suministros(0,0);
        JugadorProtoss j = new JugadorProtoss(new Recursos(1000,1000),s);
        Pilon p;

        p = j.construirPilon(new Coordenadas(0,0));
        for (int i = 0; i < p.getTiempoDeConstruccion(); i ++) j.update();
        j.construirPilon(new Coordenadas(0,1));
        for (int i = 0; i < p.getTiempoDeConstruccion(); i ++) j.update();
        j.construirPilon(new Coordenadas(0,2));
        for (int i = 0; i < p.getTiempoDeConstruccion(); i ++) j.update();

        Assert.assertEquals(15,s.getSuministrosLimiteActuales());
    }

    @Test
    public void CreoVariosDepositosDeSuministrosYAumentanLosSuministrosLimiteActuales(){
        Suministros s = new Suministros(0,0);
        JugadorTerran j = new JugadorTerran(new Recursos(1000,1000),s);
        DepositoDeSuministros d;

        d = j.construirDepositoDeSuministros(new Coordenadas(4, 0));
        for (int i = 0; i < d.getTiempoDeConstruccion(); i ++) j.update();
        j.construirDepositoDeSuministros(new Coordenadas(4, 1));
        for (int i = 0; i < d.getTiempoDeConstruccion(); i ++) j.update();
        j.construirDepositoDeSuministros(new Coordenadas(4, 2));
        for (int i = 0; i < d.getTiempoDeConstruccion(); i ++) j.update();

        Assert.assertEquals(15,s.getSuministrosLimiteActuales());
    }

    @Test
    public void CreoPilonesPeroNoPuedoSuperarLos200SuministrosMaximos(){
        Suministros s = new Suministros(0,191);
        JugadorProtoss j = new JugadorProtoss(new Recursos(1000,1000),s);
        Pilon p;

        p = j.construirPilon(new Coordenadas(3,0));
        for (int i = 0; i < p.getTiempoDeConstruccion(); i ++) j.update();
        j.construirPilon(new Coordenadas(3,1));
        for (int i = 0; i < p.getTiempoDeConstruccion(); i ++) j.update();
        j.construirPilon(new Coordenadas(3,2));
        for (int i = 0; i < p.getTiempoDeConstruccion(); i ++) j.update();

        Assert.assertEquals(200,s.getSuministrosLimiteActuales());
    }

    @Test
    public void CreoDepositosDeSuministrosPeroNoPuedoSuperarLos200SuministrosMaximos(){
        Suministros s = new Suministros(0,191);
        JugadorTerran j = new JugadorTerran(new Recursos(1000,1000),s);

        j.construirDepositoDeSuministros(new Coordenadas(1, 0));
        j.construirDepositoDeSuministros(new Coordenadas(1, 1));
        j.construirDepositoDeSuministros(new Coordenadas(1, 2));

        Assert.assertEquals( 200,s.getSuministrosLimiteActuales());
    }

    @Test
    public void SeDestruyeUnDepositoDeSuminisitrosYDisminuyenLosSuministrosDelJugador(){
        Suministros s = new Suministros(0,20);
        JugadorTerran j = new JugadorTerran(new Recursos(1000,1000),s);
        DepositoDeSuministros d;
        Marine m = new Marine();

        d = j.construirDepositoDeSuministros(new Coordenadas(1, 3));
        for (int i = 0; i < d.getTiempoDeConstruccion(); i ++) j.update();

        Assert.assertEquals( 25,s.getSuministrosLimiteActuales());

         //TODO: corregir el codigo para quie pase
        while(d.getVida()== 0) m.atacar(d);

        Assert.assertEquals( 20,s.getSuministrosLimiteActuales());
    }

}