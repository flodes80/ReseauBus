import java.util.ArrayList;
import java.util.List;

public class LigneBus {
    private String nom;
    private final List<Arret> arrets;

    public LigneBus(String nom) {
        this.nom = nom;
        this.arrets = new ArrayList<>();
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public List<Arret> getArrets() {
        return arrets;
    }
}
