package fr.floriangarcia.reseaubus.entities;

public class ConditionCirculation {
    private int tempsTrajet;
    private int tempsArret;
    private final Bus bus;

    public ConditionCirculation(Bus bus) {
        this.bus = bus;
    }

    public int getTempsTrajet() {
        return tempsTrajet;
    }

    public void setTempsTrajet(int tempsTrajet) {
        // On simule le trajet
        if(tempsTrajet >= 0){
            this.tempsTrajet = tempsTrajet;
        }
        // On entre à l'arrêt seulement si l'arrêt est vide
        else if(bus.getArretSuivant() != null && bus.getArretSuivant().estVide()){
            bus.arriver();
        }
        // Sinon on attend
    }

    public int getTempsArret() {
        return tempsArret;
    }

    public void setTempsArret(int tempsArret) {
        // On attend
        if(tempsArret >= 0){
            this.tempsArret = tempsArret;
        }
        // On part
        else if(bus.getArretActuel() != null){
            bus.partir();
        }
    }
}
