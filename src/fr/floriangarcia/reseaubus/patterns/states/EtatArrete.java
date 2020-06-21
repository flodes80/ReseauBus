package fr.floriangarcia.reseaubus.patterns.states;

import fr.floriangarcia.reseaubus.Utils;
import fr.floriangarcia.reseaubus.entities.Bus;

public class EtatArrete implements EtatBus {

    @Override
    public void partir(Bus bus) {
        // Définition du prochain arrêt
        bus.setArretSuivant(bus.getLigneBus().getNextArret(bus.getArretActuel()));
        // Suppression du bus dans l'arrêt actuel
        bus.getArretActuel().setBusDansArret(null);
        // Arrêt actuel null car en déplacement
        bus.setArretActuel(null);
        // Génération d'un temps fictif de trajet entre deux valeurs
        bus.getConditionCirculation().setTempsTrajet(Utils.getRandomIntBetween(5, 10));
        // Changement d'état du bus
        bus.setEtat(new EtatDeplacement());
    }

    @Override
    public void arriver(Bus bus) {
        System.out.println(bus + " est déjà à l'arrêt !");
    }
}
