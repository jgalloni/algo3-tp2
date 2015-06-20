package vista.edificios;

import construcciones.terran.Barraca;
import vista.auxiliares.ImagePanel;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class VistaBarraca extends ImagePanel implements MouseListener {

    private static final int ANCHO = 1;
    private static final int ALTO = 2;
    private final Barraca edificio;

    public VistaBarraca(Barraca barraca/*TODO aca se pasa el panel del juego*/) {
        super(ANCHO,ALTO,new ImageIcon().getImage());
        this.edificio=barraca;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getButton()!=MouseEvent.BUTTON1) return;
        /*Aca va la accion de
          - setear los botones
            por ejemplo pantallaJuego.getPanelDeInformacion().SetVida(unidad.getVida());
          - setear las propiedades
         */
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}