package vista.panelesDeEstado.panelesDeConstruccion;

import modelo.construcciones.EdificioEnConstruccion;
import vista.sonido.SonidosDelJuego;

import javax.swing.*;
import java.awt.*;

public class PanelEdificioEnConstruccion extends PanelConstruccion {
    private JPanel panelTiempoDeConstruccion;
    private EdificioEnConstruccion edificio;



    public PanelEdificioEnConstruccion(){
        this.inicializarPanelesPadre();
    }


    private void inicializarPanelesPadre(){
        super.crearLabels();
        super.crearPaneles();
        this.add(panelPrincipal);
    }

    private void crearPanelTiempoDeConstruccion() {
        panelTiempoDeConstruccion = new JPanel();
        JLabel tituloTiempoDeConstruccion = new JLabel("Construyendo...");
        this.setearFuenteDeJLabel(tituloTiempoDeConstruccion,false,Font.TRUETYPE_FONT,12);

        panelTiempoDeConstruccion.setLayout(new BoxLayout(panelTiempoDeConstruccion, BoxLayout.X_AXIS));
        panelTiempoDeConstruccion.setOpaque(false);
        panelTiempoDeConstruccion.add(tituloTiempoDeConstruccion);

        panelPrincipal.add(panelTiempoDeConstruccion);
    }

    public void mostrarTiempoDeConstruccion(int tiempoDeConstruccionActual, int tiempoDeConstruccionTotal){
        this.crearPanelTiempoDeConstruccion();

        JProgressBar progressTiempoDeConstruccion = new JProgressBar(0,tiempoDeConstruccionTotal);
        progressTiempoDeConstruccion.setPreferredSize(new Dimension(190,20));
        progressTiempoDeConstruccion.setMaximumSize(new Dimension(190, 20));
        progressTiempoDeConstruccion.setValue(tiempoDeConstruccionTotal-tiempoDeConstruccionActual);
        progressTiempoDeConstruccion.setStringPainted(true);
        progressTiempoDeConstruccion.setVisible(true);

        if(progressTiempoDeConstruccion.getValue() < tiempoDeConstruccionTotal){
            progressTiempoDeConstruccion.setString(String.valueOf(tiempoDeConstruccionActual)+" turnos para construirse");
        }else{
            progressTiempoDeConstruccion.setString("edificio construido");
            SonidosDelJuego.getInstance().reproducirEdificioConstruido();
        }

        panelPrincipal.add(Box.createRigidArea(new Dimension(10, 10)));
        panelPrincipal.add(progressTiempoDeConstruccion);
    }

    public void cargarDatosDeEdificioEnConstruccion(EdificioEnConstruccion edificioEnConstruccion){
        this.edificio = edificioEnConstruccion;
        this.cargarNombre(edificio.getEdificioAConvertirse().getClass().getSimpleName());
        this.cargarVida(String.valueOf(edificio.getEdificioAConvertirse().getVida()));
        this.mostrarTiempoDeConstruccion(edificio.getTiempoDeConstruccionActual(), edificio.getEdificioAConvertirse().getTiempoDeConstruccion());

    }

    @Override
    public void repaint(){
        if(edificio!= null) {
            this.panelPrincipal.removeAll();
            this.crearLabels();
            this.crearPaneles();
            this.add(panelPrincipal);
            this.cargarDatosDeEdificioEnConstruccion(this.edificio);
        }
    }

}