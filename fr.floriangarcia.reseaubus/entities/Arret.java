package entities;

public class Arret {

    private String nom;
    private int posX;
    private int posY;

    public Arret(String nom, int posX, int posY) {
        this.nom = nom;
        this.posX = posX;
        this.posY = posY;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }
}
