package vista.panelesDeEstado.panelesDeConstruccion;

import javax.swing.*;
import java.awt.*;
import java.awt.font.TextAttribute;
import java.util.Map;

public class PanelConstruccion extends JPanel {
    protected JPanel panelPrincipal;

    protected  JLabel nombre;
    protected  JLabel vida;
    protected  JLabel escudo;


    public PanelConstruccion(){
        panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));
        panelPrincipal.setOpaque(false);
    }


    protected void crearLabels() {
        nombre = new JLabel();
        vida = new JLabel();
        escudo = new JLabel();

    }

    protected void crearPaneles() {

        JPanel panelNombre = new JPanel();
        JPanel panelvida = new JPanel();
        JPanel panelescudo = new JPanel();

        panelNombre.setLayout(new BoxLayout(panelNombre, BoxLayout.X_AXIS));
        panelNombre.setOpaque(false);
        panelvida.setLayout(new BoxLayout(panelvida, BoxLayout.X_AXIS));
        panelvida.setOpaque(false);
        panelescudo.setLayout(new BoxLayout(panelescudo, BoxLayout.X_AXIS));
        panelescudo.setOpaque(false);

        Font font = new Font("Verdana", Font.BOLD, 12);
        Map attributes = font.getAttributes();
        attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        nombre.setFont(font.deriveFont(attributes));

        panelNombre.add(nombre);
        panelvida.add(new JLabel("Vida:  "));
        panelvida.add(vida);
        panelescudo.add(new JLabel("Escudo:  "));
        panelescudo.add(escudo);

        panelPrincipal.add(panelNombre);
        panelPrincipal.add(Box.createRigidArea(new Dimension(10, 10)));
        panelPrincipal.add(Box.createRigidArea(new Dimension(10, 10)));
        panelPrincipal.add(panelvida);
        panelPrincipal.add(Box.createRigidArea(new Dimension(10, 10)));
        panelPrincipal.add(panelescudo);

        panelescudo.setVisible(false);


    }

    public void cargarNombre(String string){
        nombre.setText(string);
    }

    public void cargarVida(String string){
        vida.setText(string);
    }

    public void cargarEscudo(String string){
        escudo.setText(string);
        escudo.setVisible(true);
    }


}
