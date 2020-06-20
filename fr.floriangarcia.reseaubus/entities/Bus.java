package entities;

import patterns.states.EtatBus;

public class Bus {

    private int numero;
    private EtatBus etat;

    public Bus(int numero) {
        this.numero = numero;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public EtatBus getEtat() {
        return etat;
    }

    public void setEtat(EtatBus etat) {
        this.etat = etat;
    }
}
