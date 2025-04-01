package models;

public class Batterie {
    private String reference;
    private String fabricant;
    private int chargeMax;      
    private int chargeActuelle; 
    private TypeRecharge typeRecharge;
    private long tempsInitialCharge; 

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

    public void mettreAJourCharge(int tempsEnSecondes) {
        if (typeRecharge == null) {
            throw new IllegalStateException("Le type de recharge n'est pas défini");
        }
        
    
        double tempsEnHeures = tempsEnSecondes / 3600.0;
        double chargeAjoutee = typeRecharge.getPuissance() * tempsEnHeures;
        
        int nouvelleCharge = (int) Math.min(chargeMax, chargeActuelle + chargeAjoutee);
        setChargeActuelle(nouvelleCharge);
    }

    public boolean estChargee() {
        return chargeActuelle >= chargeMax;
    }

    public TypeRecharge getTypeRecharge() {
        return typeRecharge;
    }

    public void setTypeRecharge(TypeRecharge typeRecharge) {
        this.typeRecharge = typeRecharge;
    }

    public void setTempsInitialCharge(long tempsInitialCharge) {
        this.tempsInitialCharge = tempsInitialCharge;
    }

    public String calculerTempsRecharge(long tempsActuel) {
        if (typeRecharge == null) {
            throw new IllegalStateException("Le type de recharge n'est pas défini");
        }

        int puissanceRecharge = typeRecharge.getPuissance();
        int chargeRestante = chargeMax - chargeActuelle; 
        double tempsTotalEnSecondes = (chargeRestante * 3600.0) / puissanceRecharge;
        
        long tempsEcoule = tempsActuel - tempsInitialCharge;
        
        long tempsRestant = (long) (tempsTotalEnSecondes - tempsEcoule);
        
        if (tempsRestant <= 0) {
            return "0 secondes";
        }
        
        int heures = (int) (tempsRestant / 3600);
        int minutes = (int) ((tempsRestant % 3600) / 60);
        int secondes = (int) (tempsRestant % 60);
        
        if (heures == 0) {
            if (minutes == 0) {
                return String.format("%d secondes", secondes);
            }
            return String.format("%d minutes et %d secondes", minutes, secondes);
        }
        
        return String.format("%d heures, %d minutes et %d secondes", heures, minutes, secondes);
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
