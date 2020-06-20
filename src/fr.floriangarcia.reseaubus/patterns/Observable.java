package fr.floriangarcia.reseaubus.patterns;

import java.util.ArrayList;
import java.util.List;

public class Observable {

    private final List<Observateur> observateurs;

    public Observable(){
        observateurs = new ArrayList<>();
    }

    public void notifierObservateurs(Object object){
        observateurs.forEach(o -> o.notifier(object));
    }

    public void ajouterObservateur(Observateur observateur){
        observateurs.add(observateur);
    }

    public void supprimerObservateur(Observateur observateur){
        observateurs.remove(observateur);
    }
}
