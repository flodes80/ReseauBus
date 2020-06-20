package patterns.states;

import entities.Bus;

public interface EtatBus {
    void partir(Bus bus);
    void arriver(Bus bus);
}
