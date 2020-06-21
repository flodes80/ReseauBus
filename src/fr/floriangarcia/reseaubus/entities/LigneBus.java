package fr.floriangarcia.reseaubus.entities;

import java.util.ArrayList;
import java.util.List;

public class LigneBus {
    private String nom;
    private final List<Arret> arrets;

    public LigneBus(String nom) {
        this.nom = nom;
        this.arrets = new ArrayList<>();
    }

    public String getNom() {
        return nom;
    }

    public void addArret(Arret arret){
        arrets.add(arret);
    }

    public List<Arret> getArrets() {
        return arrets;
    }

    /**
     * Fonction permettant d'obtenir l'arrêt suivant à partir d'un arrêt
     * @param arret Arrêt actuel ou null si aucun
     * @return Prochain arrêt
     */
    public Arret getNextArret(Arret arret){
        if(arret == null){
            return arrets.get(0); // Si début ligne
        }
        else{
            int arretIndex = arrets.indexOf(arret);
            if(arretIndex + 1 >= arrets.size()){
                return arrets.get(0); // Terminus plus aucun autre arrêt, on repart au premier arrêt
            }
            else{
                return arrets.get(arrets.indexOf(arret) + 1);
            }
        }
    }

    /**
     * Fonction permettant d'obtenir l'arrêt précédent à partir d'un arrêt
     * @param arret Arrêt actuel
     * @return Arrêt précédent
     */
    public Arret getPreviousArret(Arret arret){
        int arretIndex = arrets.indexOf(arret);
        return arretIndex - 1 >= 0 ? arrets.get(arretIndex - 1) : arrets.get(arrets.size() - 1);
    }

    @Override
    public String toString() {
        return "Ligne " + nom;
    }
}
