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
        // Passage à l'état Arreté
        bus.setEtat(new EtatArrete());
        // On set l'arrêt suivant comme étant l'arrêt actuel
        bus.setArretActuel(bus.getArretSuivant());
        // Aucun arrêt suivant avant départ
        bus.setArretSuivant(null);
        // On signale à l'arrêt qu'on est chez lui
        bus.getArretActuel().setBusDansArret(bus);
        // Génération d'un temps aléatoire d'arrêt
        bus.getConditionCirculation().setTempsArret(Utils.getRandomIntBetween(5, 10)); // Temps d'arrêt aléatoire
    }
}
