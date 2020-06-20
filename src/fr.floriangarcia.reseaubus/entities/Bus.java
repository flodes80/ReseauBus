package fr.floriangarcia.reseaubus.entities;

import fr.floriangarcia.reseaubus.patterns.Observateur;
import fr.floriangarcia.reseaubus.patterns.states.EtatArrete;
import fr.floriangarcia.reseaubus.patterns.states.EtatBus;

public class Bus implements Observateur {

    private final int numero;
    private EtatBus etat;
    private Arret arretActuel;
    private Arret arretSuivant;
    private LigneBus ligneBus;
    private final ConditionCirculation conditionCirculation;

    public Bus(int numero) {
        this.numero = numero;
        conditionCirculation = new ConditionCirculation(this);
        this.etat = new EtatArrete();
    }

    public void partir(){
        etat.partir(this);
    }

    public void arriver(){
        etat.arriver(this);
    }

    public int getNumero() {
        return numero;
    }

    public EtatBus getEtat() {
        return etat;
    }

    public void setEtat(EtatBus etat) {
        this.etat = etat;
    }

    public Arret getArretActuel() {
        return arretActuel;
    }

    public void setArretActuel(Arret arretActuel) {
        this.arretActuel = arretActuel;
    }

    public Arret getArretSuivant() {
        return arretSuivant;
    }

    public void setArretSuivant(Arret arretSuivant) {
        this.arretSuivant = arretSuivant;
    }

    public ConditionCirculation getConditionCirculation() {
        return conditionCirculation;
    }

    public LigneBus getLigneBus() {
        return ligneBus;
    }

    public void setLigneBus(LigneBus ligneBus) {
        // On supprime des observeurs le bus dans les arrêts de l'ancienne ligne
        if(this.ligneBus != null){
            this.ligneBus.getArrets().forEach(a -> a.supprimerObservateur(this));
        }
        ligneBus.getArrets().forEach(a -> a.ajouterObservateur(this)); // Ajout d'observateurs
        this.ligneBus = ligneBus;
    }

    // Un arrêt vient de se libérer
    @Override
    public void notifier(Object object) {
        if(object instanceof Arret){
            Arret arret = (Arret) object;
            // Check si trajet terminé et si arrêt correspondant
            if(conditionCirculation.getTempsTrajet() == 0 && arretSuivant == arret){
                arriver(); // Entrée à l'arrêt
            }
        }
    }

    @Override
    public String toString() {
        return "Bus N°" + numero;
    }
}
