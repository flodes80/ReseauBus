package entities;

import java.util.ArrayList;
import java.util.List;

public class ReseauBus {
    private final List<LigneBus> lignesBus;
    private final List<Bus> bus;

    public ReseauBus(){
        this.lignesBus = new ArrayList<>();
        this.bus = new ArrayList<>();
    }

    public List<LigneBus> getLignesBus() {
        return lignesBus;
    }

    public List<Bus> getBus() {
        return bus;
    }
}
