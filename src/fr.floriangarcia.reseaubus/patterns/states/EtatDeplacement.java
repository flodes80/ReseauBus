package fr.floriangarcia.reseaubus.patterns.states;

import fr.floriangarcia.reseaubus.Utils;
import fr.floriangarcia.reseaubus.entities.Bus;

public class EtatDeplacement implements EtatBus {

    @Override
    public void partir(Bus bus) {
        System.out.println(bus + " est déjà en déplacement !");
    }

    @Override
    public void arriver(Bus bus) {
        bus.setEtat(new EtatArrete());
        bus.setArretActuel(bus.getArretSuivant());
        bus.setArretSuivant(null);
        bus.getArretActuel().setBusDansArret(bus);
        bus.getConditionCirculation().setTempsArret(Utils.getRandomIntBetween(5, 10)); // Temps d'arrêt aléatoire
    }
}
