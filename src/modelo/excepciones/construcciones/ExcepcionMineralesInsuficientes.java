package modelo.excepciones.construcciones;

@SuppressWarnings("serial")
public class ExcepcionMineralesInsuficientes extends ExcepcionRecursosInsuficientes {

    @Override
    public String getMessage(){

        return ( "Minerales insuficientes." );

    }

}
