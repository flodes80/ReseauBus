package patterns.states;

import entities.Bus;

public class EtatDeplacement implements EtatBus {

    @Override
    public void partir(Bus bus) {
        System.out.println("Je me déplace déjà !");
    }

    @Override
    public void arriver(Bus bus) {
        bus.setEtat(new EtatArrete());
    }
}
