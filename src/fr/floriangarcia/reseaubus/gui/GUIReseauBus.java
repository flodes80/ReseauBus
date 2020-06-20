package fr.floriangarcia.reseaubus.gui;

import com.mxgraph.model.mxGraphModel;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;
import fr.floriangarcia.reseaubus.Utils;
import fr.floriangarcia.reseaubus.entities.Arret;
import fr.floriangarcia.reseaubus.entities.Bus;
import fr.floriangarcia.reseaubus.entities.LigneBus;
import fr.floriangarcia.reseaubus.entities.ReseauBus;
import fr.floriangarcia.reseaubus.patterns.Observateur;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class GUIReseauBus extends JFrame implements Observateur {
    /** Pour éviter un warning venant du JFrame */
    private static final long serialVersionUID = -8123406571694511514L;

    private static ReseauBus reseauBus;
    private final int largeurRect = 100;
    private final int hauteurRect = 30;
    private HashMap<String, Object> arretVertexMap = new HashMap<>();
    private HashMap<String, Object> arretEdgeMap = new HashMap<>(); // Hashmap des liens vers les arrêts. La clé est le nom de l'arrêt vers lequel il pointe

    public GUIReseauBus() {
        super("Reseau de bus");
        mxGraphComponent graphComponent = new mxGraphComponent(getGraphReseauBus());

        JButton startButton = new JButton("Start");
        startButton.addActionListener(actionEvent -> reseauBus.start());

        JButton stopButton = new JButton("Stop");
        stopButton.addActionListener(actionEvent -> reseauBus.stop());

        getContentPane().add(graphComponent, BorderLayout.CENTER);
        getContentPane().add(startButton, BorderLayout.PAGE_START);
        getContentPane().add(stopButton, BorderLayout.PAGE_END);
    }

    private mxGraph getGraphReseauBus() {
        mxGraph graph = new mxGraph();
        Object parent = graph.getDefaultParent();

        graph.getModel().beginUpdate();
        try {
            drawArrets(graph, parent);
        } finally {
            graph.getModel().endUpdate();
        }

        return graph;
    }

    private void drawArrets(mxGraph graph, Object parent) {
        // Parcours de toutes les lignes de bus
        for(LigneBus ligneBus : reseauBus.getLignesBus()){
            // Parcours de tous les arrêts de la ligne de bus
            for(int i = 0; i < ligneBus.getArrets().size(); i++){
                Arret currentArret = ligneBus.getArrets().get(i);   // Récupération arrêt actuel
                Arret lastArret = i > 0 ? ligneBus.getArrets().get(i - 1) : null; // Récupération arrêt précédent si possible
                Object v1 = graph.insertVertex(parent, null, currentArret, currentArret.getPosX(), currentArret.getPosY(), largeurRect, hauteurRect); // Vertex 1
                arretVertexMap.put(currentArret.getNom(), v1);

                // Relier arrêt courant avec le précédent
                if(lastArret != null){
                    Object v2 = arretVertexMap.get(lastArret.getNom());
                    Object e = graph.insertEdge(parent, null, "Edge", v2, v1);
                    arretEdgeMap.put(currentArret.getNom(), e); // Enregistrement du lien dans la hashmap des liens
                }

                // Si fin des arrêts on relie au premier
                if(i == ligneBus.getArrets().size() - 1){
                    lastArret = ligneBus.getArrets().get(0);
                    Object v2 = arretVertexMap.get(lastArret.getNom());
                    Object e = graph.insertEdge(parent, null, null, v1, v2);
                    arretEdgeMap.put(lastArret.getNom(), e); // Enregistrement du lien dans la hashmap des liens
                }
            }
        }
    }

    private void updateGraph() {
        for(Bus bus : reseauBus.getBus()){
            LigneBus ligneBus = bus.getLigneBus();

        }
    }

    @Override
    public void notifier(Object object) {
        updateGraph();
    }

    public static void main(String[] args) {
        reseauBus = Utils.getReseauBusExample();
        GUIReseauBus frame = new GUIReseauBus();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(screenSize.width - 50, screenSize.height - 50);
        frame.setVisible(true);
    }
}
