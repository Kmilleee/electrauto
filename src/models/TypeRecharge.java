package models;

public class TypeRecharge {

    private String nom; 
    private int puissance; 

    public TypeRecharge(String nom, int puissance) {
        this.nom = nom;
        this.puissance = puissance;
    }

    public String getNom() {
        return nom;
    }

    public int getPuissance() {
        return puissance;
    }

    @Override
    public String toString() {
        return  nom +
                ", puissance : " + puissance + " kW" ;
    }
}
