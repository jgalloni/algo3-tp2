package vista.Actions;

import vista.CrearJugador;
import vista.VentanaJuego;
import vista.auxiliares.ImagePanel;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class SeleccionarRaza implements java.awt.event.ActionListener {

    private final CrearJugador form;
    private final ImagePanel retrato;
    private VentanaJuego ventanaJuego;
    private String nombreUsado;

    public SeleccionarRaza(CrearJugador crearJugador, ImagePanel retrato, VentanaJuego ventanaJuego,String nombreUsado) {
        this.form=crearJugador;
        this.retrato=retrato;
        this.ventanaJuego=ventanaJuego;
        this.nombreUsado=nombreUsado;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JComboBox c = (JComboBox) e.getSource();
        switch ((String)c.getSelectedItem()){
            case "Protoss":
                form.getAceptarButton().addActionListener(new CrearProtoss(ventanaJuego,form,nombreUsado));
                retrato.setImage(new ImageIcon("src/vista/resourses/retratoProtoss.jpg").getImage());
                retrato.repaint();
                break;
            case "Terran":

                form.getAceptarButton().addActionListener(new CrearTerran(ventanaJuego,form,nombreUsado));
                retrato.setImage(new ImageIcon("src/vista/resourses/retratoTerran.jpg").getImage());
                retrato.repaint();
        }
    }

}
