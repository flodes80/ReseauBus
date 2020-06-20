package patterns;

import java.util.ArrayList;
import java.util.List;

public class Observable {

    private final List<Observateur> observateurs;

    public Observable(){
        observateurs = new ArrayList<>();
    }

    protected void notifierObservateurs(){
        observateurs.forEach(Observateur::notifier);
    }

    protected void ajouterObservateur(Observateur observateur){
        observateurs.add(observateur);
    }

    protected void supprimerObservateur(Observateur observateur){
        observateurs.remove(observateur);
    }
}
