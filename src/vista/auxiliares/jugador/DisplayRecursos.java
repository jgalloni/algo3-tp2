package vista.auxiliares.jugador;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class DisplayRecursos extends JFrame {

    private BufferedImage[] imagenes;
    private JPanel panel;

    public static void main(String[] args){

        DisplayRecursos displayRecursos = new DisplayRecursos();

        displayRecursos.pack();
        displayRecursos.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        displayRecursos.setVisible(true);

    }

    // Metodos ------------------------------

    public DisplayRecursos(){

        this.init();

    }

    private void init(){

        this.panel = new JPanel( new GridLayout(1,3,0,3));
        this.cargarImagenes();

        this.agregarIndicadores();
        this.add(this.panel);

    }

    private void cargarImagenes(){

        try{

            this.imagenes = new BufferedImage[3];
            this.imagenes[0] = ImageIO.read(getClass().getResource("imagenesRecursos/cristal.jpg"));
            this.imagenes[1] = ImageIO.read(getClass().getResource("imagenesRecursos/volcan.jpg"));
            this.imagenes[2] = ImageIO.read(getClass().getResource("imagenesRecursos/man.jpg"));

        } catch ( IOException e){
            e.printStackTrace();
        }

    }

    private void agregarIndicadores(){

        this.agregarIndicadorMinerales();
        this.agregarIndicadorGas();
        this.agregarIndicadorSuministros();

    }

    private void agregarIndicadorMinerales(){

        JLabel cristal = new JLabel(new ImageIcon( imagenes[0] ) );
        cristal.setText("Cristal: <Cantidad>");
        this.panel.add(cristal);

    }

    private void agregarIndicadorGas(){

        JLabel gasVespeno = new JLabel(new ImageIcon( imagenes[1] ) );
        gasVespeno.setText("Gas vespeno: <Cantidad>");
        this.panel.add(gasVespeno);

    }

    private void agregarIndicadorSuministros(){

        JLabel suministros = new JLabel(new ImageIcon( imagenes[2] ) );
        suministros.setText("Suministros: <Usado>/<Disponible>");
        this.panel.add(suministros);

    }

}