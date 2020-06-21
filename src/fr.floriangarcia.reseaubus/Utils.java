package fr.floriangarcia.reseaubus;

import fr.floriangarcia.reseaubus.entities.Arret;
import fr.floriangarcia.reseaubus.entities.Bus;
import fr.floriangarcia.reseaubus.entities.LigneBus;
import fr.floriangarcia.reseaubus.entities.ReseauBus;

import java.util.Random;

public class Utils {

    private static final Random rand = new Random();

    /**
     * Fonction utilitaire permettant d'obtenir un nombre entre deux bornes
     * @param min borne inférieure
     * @param max borne supérieure
     * @return Nombre entier aléatoire entre deux bornes incluses
     */
    public static int getRandomIntBetween(int min, int max){
        return rand.nextInt((max - min) + 1) + min;
    }

    public static ReseauBus getReseauBusExample(){
        // Création de bus
        Bus bus1 = new Bus(1);
        Bus bus2 = new Bus(2);
        Bus bus3 = new Bus(3);
        Bus bus4 = new Bus(4);
        Bus bus5 = new Bus(5);

        // Création des arrêts
        Arret arret1 = new Arret("1", 144, 30);
        Arret arret2 = new Arret("2", 564, 40);
        Arret arret3 = new Arret("3", 1040, 213);
        Arret arret4 = new Arret("4", 842, 624);
        Arret arret5 = new Arret("5", 589, 241);
        Arret arret6 = new Arret("6", 356, 725);
        Arret arret7 = new Arret("7", 620, 620);
        Arret arret8 = new Arret("8", 720, 720);
        Arret arret9 = new Arret("9", 920, 820);
        Arret arret10 = new Arret("10", 120, 920);

        // Création de lignes de bus
        LigneBus ligneBus1 = new LigneBus("1");
        ligneBus1.addArret(arret1);
        ligneBus1.addArret(arret2);
        ligneBus1.addArret(arret3);
        ligneBus1.addArret(arret4);
        ligneBus1.addArret(arret5);
        ligneBus1.addArret(arret6);
        ligneBus1.addArret(arret7);
        ligneBus1.addArret(arret8);
        ligneBus1.addArret(arret9);
        ligneBus1.addArret(arret10);

        // Attribution ligne de bus aux bus
        bus1.setLigneBus(ligneBus1);
        bus2.setLigneBus(ligneBus1);

        // Attribution arrêt initial au bus
        bus1.setArretActuel(arret1);
        bus2.setArretActuel(arret2);

        // Création du réseau de bus
        ReseauBus reseauBus = new ReseauBus();

        // Ajout des bus au réseau de bus
        reseauBus.addBus(bus1);
        reseauBus.addBus(bus2);

        // Ajout des lignes de bus au réseau de bus
        reseauBus.addLigneBus(ligneBus1);
        return reseauBus;
    }
}
