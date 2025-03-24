package models;

public class Carte {
  private String nom;
  private String prenom;
  private String typeContrat;
  private float soldeForfait;

  public Carte(String nom, String prenom, String typeContrat, float soldeForfait) {
    this.nom = nom;
    this.prenom = prenom;
    this.typeContrat = typeContrat;
    this.soldeForfait = soldeForfait;
  }

  public String getNom() {
    return nom;
  }

  public String getPrenom() {
    return prenom;
  }

  public String getTypeContrat() {
    return typeContrat;
  }

  public float getSoldeForfait() {
    return soldeForfait;
  }

  public boolean estUnForfait() {
    return typeContrat.equals("Forfait");
  }

  public float lireSolde() {
    return soldeForfait;
  }

  public void decompterCharge(float montant) {
    if (!estUnForfait()) {
        throw new UnsupportedOperationException("Impossible de décompter un montant sur un abonnement.");
    }
    if (montant > soldeForfait) {
        throw new IllegalArgumentException("Solde insuffisant pour cette charge.");
    }
    soldeForfait -= montant;
}
   
}