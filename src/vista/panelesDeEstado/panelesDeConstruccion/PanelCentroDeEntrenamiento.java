package vista.panelesDeEstado.panelesDeConstruccion;

import interfaces.Entrenable;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.util.Queue;

public class PanelCentroDeEntrenamiento extends PanelConstruccion {
    private  JTree treeColaDeEntrenamiento;
    private DefaultMutableTreeNode root;
    private JPanel panelTiempoDeEntrenamientoDeUnidad;
    private Queue<Entrenable> colaDeEntrenamiento;
    private Container contenedorColaYTiempo;


    public PanelCentroDeEntrenamiento(){
        super.crearLabels();
        super.crearPaneles();

        this.add(panelPrincipal);

        contenedorColaYTiempo = new Container();
        contenedorColaYTiempo.setLayout(new BoxLayout(contenedorColaYTiempo, BoxLayout.Y_AXIS));
        this.panelPrincipal.add(contenedorColaYTiempo);
    }

    public void mostrarColaDeEntrenamiento(Queue<Entrenable> colaDeEntrenamiento) {
        if(colaDeEntrenamiento.size() == 0) return;

        this.crearTreeColaDeEntrenamiento(colaDeEntrenamiento);
        this.mostrarTiempoDeEntrenamiento(colaDeEntrenamiento.peek());

        for(Entrenable unidad: colaDeEntrenamiento){
            root.add(new DefaultMutableTreeNode(unidad.getClass().getSimpleName()));
        }
    }

    private void crearTreeColaDeEntrenamiento(Queue<Entrenable> colaDeEntrenamiento) {
        root = new DefaultMutableTreeNode(String.valueOf(colaDeEntrenamiento.size())+" unidad/s en Cola de Entrenamiento");
        treeColaDeEntrenamiento = new JTree(root);
        treeColaDeEntrenamiento.setOpaque(false);
        treeColaDeEntrenamiento.setPreferredSize(new Dimension(100,20));


        JScrollPane panelColaDeEntrenamiento = new JScrollPane(treeColaDeEntrenamiento);
        panelColaDeEntrenamiento.setPreferredSize(new Dimension(170,60));
       // this.panelPrincipal.add(panelColaDeEntrenamiento);
        this.contenedorColaYTiempo.add(panelColaDeEntrenamiento);


        //TODO no logr� hacer que sea scrolleable
    }

    private void mostrarTiempoDeEntrenamiento(Entrenable unidad){
        int tiempoDeEntrenamientoTotal = unidad.getTiempoDeEntrenamientoTotal();
        int tiempoDeEntrenamientoActual = unidad.getTiempoDeEntrenamientoActual();

        this.crearLabelDeUnidadEnEntrenamiento(unidad.getClass().getSimpleName());

        JProgressBar progressTiempoDeEntrenamiento = new JProgressBar(0,tiempoDeEntrenamientoTotal);
        progressTiempoDeEntrenamiento.setPreferredSize(new Dimension(190, 20));
        progressTiempoDeEntrenamiento.setMaximumSize(new Dimension(190, 20));
        progressTiempoDeEntrenamiento.setValue(tiempoDeEntrenamientoTotal - tiempoDeEntrenamientoActual);
        progressTiempoDeEntrenamiento.setStringPainted(true);
        progressTiempoDeEntrenamiento.setVisible(true);

        progressTiempoDeEntrenamiento.setString(String.valueOf(tiempoDeEntrenamientoActual) + " turnos para finalizar");
       // this.panelPrincipal.add(Box.createRigidArea(new Dimension(10, 10)));
        // this.panelPrincipal.add(progressTiempoDeEntrenamiento);
        this.contenedorColaYTiempo.add(Box.createRigidArea(new Dimension(10, 10)));
        this.contenedorColaYTiempo.add(progressTiempoDeEntrenamiento);

    }


    private void crearLabelDeUnidadEnEntrenamiento(String nombreDeUnidad) {
        this.panelTiempoDeEntrenamientoDeUnidad = new JPanel();

        this.panelTiempoDeEntrenamientoDeUnidad.setLayout(new BoxLayout(panelTiempoDeEntrenamientoDeUnidad, BoxLayout.X_AXIS));
        this.panelTiempoDeEntrenamientoDeUnidad.add(new JLabel("Entrenando "+nombreDeUnidad+"..."));

      //  this.panelPrincipal.add(Box.createRigidArea(new Dimension(10, 10)));
      //  this.panelPrincipal.add(this.panelTiempoDeEntrenamientoDeUnidad);
        this.contenedorColaYTiempo.add(Box.createRigidArea(new Dimension(10, 10)));
        this.contenedorColaYTiempo.add(this.panelTiempoDeEntrenamientoDeUnidad);

    }

    @Override
    public void repaint(){
        if(this.colaDeEntrenamiento!= null) {
            this.contenedorColaYTiempo.removeAll();
            this.cargarDatosDeColaDeEntrenamiento(this.colaDeEntrenamiento);
        }
    }


    public void cargarDatosDeColaDeEntrenamiento(Queue<Entrenable> colaDeEntrenamiento) {
        this.colaDeEntrenamiento = colaDeEntrenamiento;

        this.mostrarColaDeEntrenamiento(colaDeEntrenamiento);
    }
}
