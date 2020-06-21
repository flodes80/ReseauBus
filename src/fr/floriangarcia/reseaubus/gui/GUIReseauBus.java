package fr.floriangarcia.reseaubus.gui;

import com.mxgraph.model.mxCell;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.util.mxEvent;
import com.mxgraph.util.mxEventObject;
import com.mxgraph.util.mxEventSource;
import com.mxgraph.view.mxGraph;
import fr.floriangarcia.reseaubus.Utils;
import fr.floriangarcia.reseaubus.entities.Arret;
import fr.floriangarcia.reseaubus.entities.Bus;
import fr.floriangarcia.reseaubus.entities.LigneBus;
import fr.floriangarcia.reseaubus.entities.ReseauBus;
import fr.floriangarcia.reseaubus.patterns.Observateur;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLOutput;
import java.util.HashMap;

public class GUIReseauBus extends JFrame implements Observateur {
    private static final long serialVersionUID = -8123406571694511514L;

    private static ReseauBus reseauBus;
    private mxGraph graph;
    private mxGraphComponent graphComponent;
    private JButton startButton, stopButton;
    private final int largeurRect = 100;
    private final int hauteurRect = 30;
    private final HashMap<String, Object> arretVertexMap = new HashMap<>(); // Hashmap des rectangles représentants les arrêts. La clé est le nom de l'arrêt
    private final HashMap<String, Object> arretEdgeMap = new HashMap<>(); // Hashmap des liens vers les arrêts. La clé est le nom de l'arrêt vers lequel il pointe

    public GUIReseauBus() {
        super("Reseau de bus");
        graphComponent = new mxGraphComponent(getGraphReseauBus());
        graphComponent.setEnabled(false); // Empêcher modification

        startButton = new JButton("Start");
        startButton.addActionListener(actionEvent -> reseauBus.start());

        stopButton = new JButton("Stop");
        stopButton.addActionListener(actionEvent -> reseauBus.stop());

        getContentPane().add(graphComponent, BorderLayout.CENTER);
        getContentPane().add(startButton, BorderLayout.PAGE_START);
        getContentPane().add(stopButton, BorderLayout.PAGE_END);

        reseauBus.ajouterObservateur(this); // Ajout de sois même en observateur
    }

    /**
     * Obtention du graph du réseau de bus
     * @return le graph dessiné
     */
    private mxGraph getGraphReseauBus() {
        graph = new mxGraph();
        Object parent = graph.getDefaultParent();

        graph.getModel().beginUpdate();
        try {
            drawArrets(graph, parent);
        } finally {
            graph.getModel().endUpdate();
        }

        return graph;
    }

    /**
     * Méthode permettant de dessiner les différents arrêts et de les reliers entre-eux
     * @param graph Graphique utilisé pour dessiner
     * @param parent Parent du graph
     */
    private void drawArrets(mxGraph graph, Object parent) {
        // Parcours de toutes les lignes de bus
        for(LigneBus ligneBus : reseauBus.getLignesBus()){
            // Parcours de tous les arrêts de la ligne de bus
            for(int i = 0; i < ligneBus.getArrets().size(); i++){
                Arret currentArret = ligneBus.getArrets().get(i);   // Récupération arrêt actuel
                Arret lastArret = i > 0 ? ligneBus.getArrets().get(i - 1) : null; // Récupération arrêt précédent si possible
                Object v1;

                // Vérification arrêt pas déjà créé
                if(arretVertexMap.containsKey(currentArret.getNom())){
                    v1 = arretVertexMap.get(currentArret.getNom());
                }
                // Sinon création
                else{
                    v1 = graph.insertVertex(parent, null, currentArret, currentArret.getPosX(), currentArret.getPosY(), largeurRect, hauteurRect);
                    arretVertexMap.put(currentArret.getNom(), v1);
                }


                // S'il existe un arrêt précédent on relie
                if(lastArret != null){
                    Object v2 = arretVertexMap.get(lastArret.getNom());
                    checkAndCreateEdge(currentArret, lastArret, v1, v2, parent);
                }
                // Si aucun arrêt on relie le dernier au premier
                if(i == ligneBus.getArrets().size() - 1){
                    lastArret = ligneBus.getArrets().get(0);
                    Object v2 = arretVertexMap.get(lastArret.getNom());
                    checkAndCreateEdge(currentArret, lastArret, v2, v1, parent);
                }
            }
        }
    }


    /**
     * Méthode permettant la création des liens entre les arrêts en vérifiant qu'ils n'existent pas déjà
     * @param sourceArret Arrêt source
     * @param targetArret Arrêt target
     * @param v1 Arret graphique (Vertex) 1
     * @param v2 Arret graphique (Vertex) 2
     * @param parent Parent (graphique)
     */
    private void checkAndCreateEdge(Arret sourceArret, Arret targetArret, Object v1, Object v2, Object parent){
        if(v1 instanceof mxCell && v2 instanceof mxCell && sourceArret != null && targetArret != null && sourceArret != targetArret){
            mxCell sourceCell = (mxCell) v1;
            mxCell targetCell = (mxCell) v2;
            boolean needCreation = true;

            // Parcours de tous les edges de l'arrêt source
            for(int i = 0; i < sourceCell.getEdgeCount(); i++){
                mxCell currentEdge = (mxCell) sourceCell.getEdgeAt(i);
                // Vérification que l'edge parcouru a comme target notre cellTarget ou non
                if(currentEdge.getTarget() == targetCell){
                    needCreation = false;
                    break;
                }
            }

            // Création nécessaire car edge inexistant
            if(needCreation){
               graph.insertEdge(parent, null, new BusArrayList(), v2, v1);
            }
        }
    }

    /**
     * Méthode permettant de mettre à jour l'edge correspondant à l'emplacement actuel du bus
     * @param bus Bus désiré
     */
    private void updateEdgeForBus(Bus bus){
        mxCell edge = null;
        Arret nextArret = bus.getArretSuivant();
        Arret currentArret = bus.getArretActuel();
        Arret previousArret = nextArret == null ? bus.getLigneBus().getPreviousArret(currentArret) : bus.getLigneBus().getPreviousArret(nextArret);

        // Si il y a un prochain arrêt alors mise à jour edge courant
        if(nextArret != null){
            edge = getEdge(previousArret, nextArret);
            if(edge != null){
                BusArrayList listBus = (BusArrayList) edge.getValue();
                if(!listBus.contains(bus))
                    listBus.add(bus);
            }
        }
        // Sinon clean de l'edge précédent
        else{
            edge = getEdge(previousArret, currentArret);
            if(edge != null)
                ((BusArrayList)edge.getValue()).remove(bus);
        }
    }

    /**
     * Fonction permettant d'obtenir l'edge entre deux arrêt
     * @param arretSource Arrêt de départ
     * @param arretTarget Arrêt de destination
     * @return
     */
    private mxCell getEdge(Arret arretSource, Arret arretTarget){
        mxCell vSource = (mxCell) arretVertexMap.get(arretSource.getNom());
        for(int i = 0; i < vSource.getEdgeCount(); i++){
            mxCell currentEdge = (mxCell) vSource.getEdgeAt(i);
            if(currentEdge.getTarget().getValue() == arretTarget){
                return currentEdge;
            }
        }
        return null;
    }

    /**
     * Méthode permettant la mise à jour du graph en fonction de l'emplacement des bus
     */
    private void updateGraph() {
        graph.getModel().beginUpdate();

        reseauBus.getBus().forEach(b -> updateEdgeForBus(b));

        graph.getModel().endUpdate();
        graph.refresh();
    }

    /**
     * Notification lorsque le réseau est mis à jour --> On met à jour l'affichage
     * @param object Inutilisé ici car référence déjà présente en mémoire
     */
    @Override
    public void notifier(Object object) {
        updateGraph();
    }

    /**
     * Lancement du programme
     * @param args Arguments java
     */
    public static void main(String[] args) {
        reseauBus = Utils.getReseauBusExample();
        GUIReseauBus frame = new GUIReseauBus();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(screenSize.width - 150, screenSize.height - 150);
        frame.setVisible(true);
    }

}
