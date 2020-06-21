package fr.floriangarcia.reseaubus.gui;

import fr.floriangarcia.reseaubus.entities.Bus;

import java.util.ArrayList;

/**
 * Classe utilisée pour l'affichage des bus sur un même trajet
 */
public class BusArrayList extends ArrayList<Bus> {
    @Override
    public String toString() {
        String str = "";
        for(Bus bus : this){
            str += bus + " ";
        }
        return str;
    }
}
