package tests_de_integracion;

import modelo.construcciones.protoss.Acceso;
import modelo.construcciones.protoss.Pilon;
import modelo.construcciones.terran.DepositoDeSuministros;
import modelo.excepciones.ExcepcionNoSePuedeEntrenarUnidadPorSuministrosInsuficientes;
import modelo.excepciones.Mapa.ExcepcionNoSePudoAgregarAlMapa;
import modelo.excepciones.Unidades.ExcepcionDeAccionDeUnidad;
import modelo.excepciones.construcciones.ExcepcionNoSePuedeConstruir;
import modelo.excepciones.construcciones.ExcepcionNoSePuedeEntrenarUnidad;
import modelo.jugabilidad.ProxyMapa;
import modelo.jugabilidad.RazaDeJugador.JugadorProtoss;
import modelo.jugabilidad.RazaDeJugador.JugadorTerran;
import modelo.jugabilidad.auxiliares.Recursos;
import modelo.jugabilidad.auxiliares.Suministros;
import modelo.jugabilidad.auxiliares.Vision;
import modelo.jugabilidad.utilidadesMapa.Coordenada;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import modelo.unidades.ProxiDeAtaque;
import modelo.unidades.ProxyDeHechizos;
import modelo.unidades.terrran.Marine;

public class ConstrucionesUnidadesYSuministros {
    ProxyMapa proxyMapa;
    @Before
    public void resetearProxy(){
        ProxyMapa.resetear();

        proxyMapa = ProxyMapa.getInstance();
        proxyMapa.setCoordenadasMaximas(20, 20);
    }

    @Test(expected = ExcepcionNoSePuedeEntrenarUnidadPorSuministrosInsuficientes.class)
    public void NoPuedoEntrenarMasUnidadesPorLlegarALLimiteDeSuministros() throws ExcepcionNoSePuedeEntrenarUnidad {
        JugadorProtoss j = new JugadorProtoss(new Recursos(1000,1000),new Suministros(4,5));// 4 usados, 5 limite
        Acceso acceso = new Acceso(j);

        acceso.entrenarDragon();//+ 2 sumistro


    }

    @Test
     public void CreoVariosPilonesYAumentanLosSuministrosLimiteActuales() throws ExcepcionNoSePuedeConstruir, ExcepcionNoSePudoAgregarAlMapa {
        Suministros s = new Suministros(0,0);
        JugadorProtoss j = new JugadorProtoss(new Recursos(1000,1000),s);
        j.setVisibilidad(Vision.VisionCompleta(20, 20));
        Pilon p;

        p = j.construirPilon(new Coordenada(1,2));
        for (int i = 0; i < p.getTiempoDeConstruccion(); i ++) j.update();
        j.construirPilon(new Coordenada(1,1));
        for (int i = 0; i < p.getTiempoDeConstruccion(); i ++) j.update();
        j.construirPilon(new Coordenada(5,2));
        for (int i = 0; i < p.getTiempoDeConstruccion(); i ++) j.update();

        Assert.assertEquals(15,s.getSuministrosLimiteActuales());
    }

    @Test
    public void CreoVariosDepositosDeSuministrosYAumentanLosSuministrosLimiteActuales() throws ExcepcionNoSePuedeConstruir, ExcepcionNoSePudoAgregarAlMapa {
        Suministros s = new Suministros(0,0);
        JugadorTerran j = new JugadorTerran(new Recursos(1000,1000),s);
        j.setVisibilidad(Vision.VisionCompleta(20, 20));
        DepositoDeSuministros d;

        d = j.construirDepositoDeSuministros(new Coordenada(4, 6));
        for (int i = 0; i < d.getTiempoDeConstruccion(); i ++) j.update();
        j.construirDepositoDeSuministros(new Coordenada(4, 1));
        for (int i = 0; i < d.getTiempoDeConstruccion(); i ++) j.update();
        j.construirDepositoDeSuministros(new Coordenada(4, 2));
        for (int i = 0; i < d.getTiempoDeConstruccion(); i ++) j.update();

        Assert.assertEquals(15,s.getSuministrosLimiteActuales());
    }

    @Test
    public void CreoPilonesPeroNoPuedoSuperarLos200SuministrosMaximos() throws ExcepcionNoSePuedeConstruir, ExcepcionNoSePudoAgregarAlMapa {
        Suministros s = new Suministros(0,191);
        JugadorProtoss j = new JugadorProtoss(new Recursos(1000,1000),s);
        j.setVisibilidad(Vision.VisionCompleta(20, 20));
        Pilon p;


        p = j.construirPilon(new Coordenada(3,5));
        for (int i = 0; i < p.getTiempoDeConstruccion(); i ++) j.update();
        j.construirPilon(new Coordenada(3,1));
        for (int i = 0; i < p.getTiempoDeConstruccion(); i ++) j.update();
        j.construirPilon(new Coordenada(3,2));
        for (int i = 0; i < p.getTiempoDeConstruccion(); i ++) j.update();

        Assert.assertEquals(200,s.getSuministrosLimiteActuales());
    }

    @Test
    public void CreoDepositosDeSuministrosPeroNoPuedoSuperarLos200SuministrosMaximos() throws ExcepcionNoSePuedeConstruir, ExcepcionNoSePudoAgregarAlMapa {
        Suministros s = new Suministros(0,191);
        JugadorTerran j = new JugadorTerran(new Recursos(1000,1000),s);
        j.setVisibilidad(Vision.VisionCompleta(20, 20));
        DepositoDeSuministros d;

        d = j.construirDepositoDeSuministros(new Coordenada(1, 1));
        for (int i = 0; i < d.getTiempoDeConstruccion(); i ++) j.update();
        j.construirDepositoDeSuministros(new Coordenada(1, 3));
        for (int i = 0; i < d.getTiempoDeConstruccion(); i ++) j.update();
        j.construirDepositoDeSuministros(new Coordenada(1, 5));
        for (int i = 0; i < d.getTiempoDeConstruccion(); i ++) j.update();

        Assert.assertEquals( 200,s.getSuministrosLimiteActuales());
    }

    @Test
    public void SeDestruyeUnDepositoDeSuminisitrosYDisminuyenLosSuministrosDelJugador() throws ExcepcionDeAccionDeUnidad, ExcepcionNoSePudoAgregarAlMapa, ExcepcionNoSePuedeConstruir {
        ProxyMapa proxyMapa = ProxyMapa.getInstance();
        proxyMapa.setCoordenadasMaximas(20,20);

        Suministros s = new Suministros(0,20);
        JugadorTerran j1 = new JugadorTerran(new Recursos(1000,1000),s);
        j1.setVisibilidad(Vision.VisionCompleta(20, 20));
        JugadorTerran j2 = new JugadorTerran(new Recursos(200,200),new Suministros(0,20));
        j2.setVisibilidad(Vision.VisionCompleta(20, 20));
        ProxiDeAtaque.inicializar(j1);
        ProxiDeAtaque.inicializar(j2);
        ProxyDeHechizos.inicializar(j1);
        ProxyDeHechizos.inicializar(j2);
        DepositoDeSuministros d;
        Marine m = new Marine(Vision.VisionCompleta(10,10));
        Coordenada coordDeDepot = new Coordenada(5, 6);

        proxyMapa.agregar(m, new Coordenada(5, 5));
        j2.agregarUnidad(m);

        d = j1.construirDepositoDeSuministros(coordDeDepot);

        for (int i = 0; i < d.getTiempoDeConstruccion(); i ++) j1.update();

        Assert.assertEquals( 25,s.getSuministrosLimiteActuales());

        while(d.getVida()!= 0){
            m.atacar(coordDeDepot);
            j2.update();
        }

        j1.update();

        Assert.assertEquals( 20,s.getSuministrosLimiteActuales());
        Assert.assertFalse(j1.buscarConstruccion(d));
        Assert.assertFalse(proxyMapa.posicionTerrestreOcupada(coordDeDepot));
    }

    @Test
    public void SeDestruyeUnPilonYDisminuyenLosSuministrosDelJugador() throws ExcepcionDeAccionDeUnidad, ExcepcionNoSePudoAgregarAlMapa, ExcepcionNoSePuedeConstruir {
        ProxyMapa proxyMapa = ProxyMapa.getInstance();
        proxyMapa.setCoordenadasMaximas(20,20);

        Suministros s = new Suministros(0,20);
        JugadorProtoss j1 = new JugadorProtoss(new Recursos(1000,1000),s);
        j1.setVisibilidad(Vision.VisionCompleta(20, 20));
        JugadorTerran j2 = new JugadorTerran(new Recursos(200,200),new Suministros(1,20));
        j2.setVisibilidad(Vision.VisionCompleta(20, 20));
        ProxiDeAtaque.inicializar(j1);
        ProxiDeAtaque.inicializar(j2);
        ProxyDeHechizos.inicializar(j1);
        ProxyDeHechizos.inicializar(j2);
        Pilon p;
        Marine m = new Marine(Vision.VisionCompleta(10,10));
        Coordenada coordDePilon = new Coordenada(7, 6);

        proxyMapa.agregar(m, new Coordenada(7, 5));
        j2.agregarUnidad(m);

        p = j1.construirPilon(coordDePilon);

        for (int i = 0; i < p.getTiempoDeConstruccion(); i ++) j1.update();

        Assert.assertEquals(25, s.getSuministrosLimiteActuales());

        while(p.getVida()!= 0){
            m.atacar(coordDePilon);
            j2.update();
        }

        j1.update();

        Assert.assertEquals( 20,s.getSuministrosLimiteActuales());
        Assert.assertFalse(j1.buscarConstruccion(p));
        Assert.assertFalse(proxyMapa.posicionTerrestreOcupada(coordDePilon));
    }

}
