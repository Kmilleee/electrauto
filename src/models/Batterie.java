package models;

public class Batterie {
    private String reference;
    private String fabricant;
    private int chargeMax;      // en kwh
    private int chargeActuelle; // en kwh  
    private TypeRecharge typeRecharge;

    public Batterie(String reference, String fabricant, int chargeMax, int chargeActuelle, TypeRecharge typeRecharge) {
        this.reference = reference;
        this.fabricant = fabricant;
        this.chargeMax = chargeMax;
        this.chargeActuelle = chargeActuelle;
        this.typeRecharge = typeRecharge;
    }

    public String getRef() {
        return reference;
    }

    public String getFabricant() {
        return fabricant;
    }

    public int getChargeMax() {
        return chargeMax;
    }

    public int getChargeActuelle() {
        return chargeActuelle;
    }

    public void setChargeActuelle(int chargeActuelle) {
        if (chargeActuelle < 0) {
            throw new IllegalArgumentException("La charge actuelle ne peut pas être négative");
        }
        if (chargeActuelle > chargeMax) {
            throw new IllegalArgumentException("La charge actuelle ne peut pas dépasser la charge maximale");
        }
        this.chargeActuelle = chargeActuelle;
    }

    public TypeRecharge getTypeRecharge() {
        return typeRecharge;
    }

    @Override
    public String toString() {
        return "Batterie{" +
                "reference='" + reference + '\'' +
                ", fabricant='" + fabricant + '\'' +
                ", chargeMax=" + chargeMax + " kWh" +
                ", chargeActuelle=" + chargeActuelle + " kWh" +
                ", typeRecharge=" + typeRecharge +
                '}';
    }
}
