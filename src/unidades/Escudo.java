package unidades;

public class Escudo{
    private final int escudoTotal;
    private int escudoActual;

    public Escudo( int escudo) {
        this.escudoActual = this.escudoTotal = escudo;
    }

    public int getEscudoActual() {
        return escudoActual;
    }

    public void regenerar() {
        this.escudoActual = Math.min(escudoTotal, this.escudoActual + this.escudoTotal / 10);
    }

    public int quitar(int cantidad) {
        escudoActual = Math.max(escudoActual - cantidad, 0);
        return Math.max(cantidad - escudoActual, 0);
    }

    public int getEscudoTotal() {
        return escudoTotal;
    }
}