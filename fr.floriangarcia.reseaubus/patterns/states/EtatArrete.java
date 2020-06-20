package patterns.states;

import entities.Bus;

public class EtatArrete implements EtatBus {

    @Override
    public void partir(Bus bus) {
        bus.setEtat(new EtatDeplacement());
    }

    @Override
    public void arriver(Bus bus) {
        System.out.println("Je suis déjà à l'arrêt !");
    }
}
