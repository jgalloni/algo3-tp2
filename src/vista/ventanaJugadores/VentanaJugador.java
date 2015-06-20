package vista.ventanaJugadores;

import vista.auxiliares.jugador.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;

public abstract class VentanaJugador extends JFrame {

    // Atributos ---------------------

    protected JPanel contenedor;

    private JPanel panelRecursos;
    private JScrollPane panelMapa;
    private JPanel panelLateral;
    protected JPanel panelInferior;



    // Metodos -------------------------

    public VentanaJugador(){

        this.init();

    }

    protected void init(){

        this.crearPaneles();
        this.add(this.contenedor);

    }

    private void crearPaneles(){

        this.crearContenedor();

        this.crearPanelRecursos();
        this.crearPanelMapa();
        this.crearPanelDeNotificaciones();
        this.crearPanelInferior();

        this.agregarAlContenedor();

    }
    private void crearPanelDeNotificaciones() {
        this.panelLateral = new JPanel();
        this.panelLateral.setLayout(new BoxLayout(panelLateral,BoxLayout.Y_AXIS));
        this.panelLateral.add(new DisplayNotificaciones());
        //TODO: aca hay que agregar el panel de estado por eso despues se puede renombrar a PanelLateralNotificacionesEstado o algo asi
    }

    private void crearContenedor(){

        this.contenedor = new JPanel();
        this.contenedor.setLayout(new BorderLayout());

    }

    private void crearPanelRecursos(){

        this.panelRecursos = new JPanel();
        this.panelRecursos.add( new DisplayRecursos() );
        this.panelRecursos.setPreferredSize( new Dimension(700, 35) );

    }

    protected void crearPanelInferior(){

        this.panelInferior = new JPanel( new BorderLayout() );
        this.panelInferior.add(new PanelTerminarTurno(), "East");
        this.panelInferior.add(new PanelAcciones(),"Center");

    }

    private void crearPanelMapa(){

        JPanel contenedor = new JPanel(new GridBagLayout());
        contenedor.add(new DisplayMapa());

        this.panelMapa = new JScrollPane(contenedor);
        this.panelMapa.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        this.panelMapa.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);

        MouseMotionListener doScrollRectToVisible = new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                Rectangle r = new Rectangle(e.getX()-80, e.getY()-80, 120, 120);

                ((JPanel) e.getSource()).scrollRectToVisible(r);

            }

            ///Para que se mueva mas rapido hay que setearle mas grande los limites del rectangulo
            //---------------
            //Se puede hacer con "mouseDragged" y la unica diferencia es que hay que hacer click,mantener apretado
            //y pararte sobre el borde de la imagen y se mueve solo. Para que funcione bien bien va a ver que meterle
            //unos bordes laterales.
        };

        contenedor.addMouseMotionListener(doScrollRectToVisible);
        contenedor.setAutoscrolls(true);

        this.panelMapa.setPreferredSize(new Dimension(700,650));

    }


    private void agregarAlContenedor(){

        this.contenedor.add( this.panelRecursos,"North" );
        this.contenedor.add( this.panelLateral,"West" );
        this.contenedor.add( this.panelMapa ,"Center");
        this.contenedor.add( this.panelInferior,"South");

    }

    public void mostrarPanelDeAcciones(JComponent component,String posicion){
        this.panelInferior.add(component,posicion);
    }
    public void mostrarPanelDeEstado(JPanel panel){
        this.panelLateral.add(panel);
    }

}

