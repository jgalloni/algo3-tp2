package excepciones.construicciones;

public class ExcepcionNecesitaSerConstruidoSobreUnCristal extends ExcepcionNoSePuedeConstruir {

    @Override
    public String getMessage(){

        return ( "Necesita ser construido sobre un cristal." );

    }

}
