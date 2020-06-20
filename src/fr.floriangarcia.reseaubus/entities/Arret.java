package fr.floriangarcia.reseaubus.entities;

import fr.floriangarcia.reseaubus.patterns.Observable;

public class Arret extends Observable {

    private final String nom;
    private final int posX;
    private final int posY;
    private Bus busDansArret;

    public Arret(String nom, int posX, int posY) {
        this.nom = nom;
        this.posX = posX;
        this.posY = posY;
        busDansArret = null;
    }

    public String getNom() {
        return nom;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public Bus getBusDansArret() {
        return busDansArret;
    }

    public void setBusDansArret(Bus busDansArret) {
        this.busDansArret = busDansArret;
    }

    public boolean estVide(){
        return busDansArret == null;
    }

    @Override
    public String toString() {
        return "Arrêt " + nom;
    }
}
