package vista.Actions;

import vista.Actions.accionesUnidades.AccionUnidad;

public class WraperAccionActuar {
    private AccionUnidad accionActuar;

    public void setAccionActuar(AccionUnidad accionActuar) {
        this.accionActuar = accionActuar;
    }

    public AccionUnidad getAccionActuar() {
        return accionActuar;
    }
}
