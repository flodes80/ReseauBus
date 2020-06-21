package fr.floriangarcia.reseaubus.patterns.states;

import fr.floriangarcia.reseaubus.entities.Bus;

public interface EtatBus {
    void partir(Bus bus);
    void arriver(Bus bus);
}
