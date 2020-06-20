package fr.floriangarcia.reseaubus.patterns.states;

import fr.floriangarcia.reseaubus.Utils;
import fr.floriangarcia.reseaubus.entities.Bus;

public class EtatArrete implements EtatBus {

    @Override
    public void partir(Bus bus) {
        bus.setArretSuivant(bus.getLigneBus().getNextArret(bus.getArretActuel())); // Définition du prochain arrêt
        bus.setArretActuel(null); // Arrêt actuel null car en déplacement
        bus.getConditionCirculation().setTempsTrajet(Utils.getRandomIntBetween(5, 30));
        bus.setEtat(new EtatDeplacement());
    }

    @Override
    public void arriver(Bus bus) {
        System.out.println("Je suis déjà à l'arrêt !");
    }
}
