package fr.floriangarcia.reseaubus.entities;

import fr.floriangarcia.reseaubus.patterns.Observable;

import java.util.*;

public class ReseauBus extends Observable {

    private Timer timer;
    private TimerTask task;
    private final List<LigneBus> lignesBus;
    private final List<Bus> bus;
    private boolean started;

    public ReseauBus(){
        this.lignesBus = new ArrayList<>();
        this.bus = new ArrayList<>();
        started = false;
    }

    /**
     * Méthode permettant de lancer la simulation
     */
    public void start(){
        if(!started){
            // Départ de tous les bus
            bus.forEach(Bus::partir);
            createDecrementTask();
            started = true;
        }
        else{
            System.out.println("Simulation déjà en cours !");
        }
    }

    /**
     * Méthode permettant de stopper la simulation
     */
    public void stop(){
        if(started){
            task.cancel();
            timer.cancel();
            started = false;
            System.out.println("Arrêt de la simulation");
        }
        else{
            System.out.println("Simulation déjà arrêtée.");
        }
    }

    public void addLigneBus(LigneBus ligne){
        lignesBus.add(ligne);
    }

    public void addBus(Bus unBus){
        bus.add(unBus);
    }

    public List<LigneBus> getLignesBus() {
        return lignesBus;
    }

    public List<Bus> getBus() {
        return bus;
    }

    /**
     * Méthode décrementant les temps de trajet / arrêt pour chaque bus
     * pour la simulation
     */
    private void createDecrementTask(){
        timer = new Timer();
        task = new TimerTask() {
            @Override
            public void run() {
                bus.forEach(b -> {
                    ConditionCirculation cond = b.getConditionCirculation();
                    cond.setTempsArret(cond.getTempsArret() - 1);
                    cond.setTempsTrajet(cond.getTempsTrajet() - 1);
                });
                // Notification d'update du réseau
                notifierObservateurs(this);
            }
        };
        timer.scheduleAtFixedRate(task, 1000, 1000);
    }
}
