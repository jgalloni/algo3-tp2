package excepciones.construicciones;

public class ExcepcionConstruccionNoRecolectaVolcan extends ExcepcionNoSePuedeConstruir {

    @Override
    public String getMessage(){

        return ( "La construccion debe colocarse sobre un volcan." );

    }

}
