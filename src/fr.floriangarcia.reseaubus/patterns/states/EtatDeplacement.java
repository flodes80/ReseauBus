package fr.floriangarcia.reseaubus.patterns.states;

import fr.floriangarcia.reseaubus.Utils;
import fr.floriangarcia.reseaubus.entities.Bus;

public class EtatDeplacement implements EtatBus {

    @Override
    public void partir(Bus bus) {
        System.out.println("Je me déplace déjà !");
    }

    @Override
    public void arriver(Bus bus) {
        bus.setEtat(new EtatArrete());
        bus.setArretActuel(bus.getArretSuivant());
        bus.setArretSuivant(null);
        bus.getConditionCirculation().setTempsArret(Utils.getRandomIntBetween(5, 30)); // Temps d'arrêt aléatoire
    }
}
