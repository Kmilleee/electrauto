package models;

public class Carte {
  private String nom;
  private String prenom;
  private String typeContrat;
  private float soldeForfait;
  private String dateDebutAbonnement;
  private String dateFinAbonnement;

  public Carte(String nom, String prenom, String typeContrat, float soldeForfait, String dateDebutAbonnement, String dateFinAbonnement) {
    this.nom = nom;
    this.prenom = prenom;
    this.typeContrat = typeContrat;

    if (typeContrat.equals("Forfait")) {
      this.soldeForfait = soldeForfait;
      this.dateDebutAbonnement = null;
      this.dateFinAbonnement = null;
    } else if (typeContrat.equals("Abonnement")) {
      this.soldeForfait = -1; // Pas de solde pour un abonnement
      this.dateDebutAbonnement = dateDebutAbonnement;
      this.dateFinAbonnement = dateFinAbonnement;
    } else {
      throw new IllegalArgumentException("Type de contrat invalide !");
    }
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

  public boolean estUnAbonnement() {
    return typeContrat.equals("Abonnement");
  }

  public float lireSolde() {
    if (!estUnForfait()) {
      throw new UnsupportedOperationException("Les abonnements n'ont pas de solde.");
  }
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

public boolean estAbonnementValide(String dateActuelle) {
  if (estUnForfait()) return false; // Un forfait n'a pas de validité temporelle

  return dateActuelle.compareTo(dateDebutAbonnement) >= 0 && dateActuelle.compareTo(dateFinAbonnement) <= 0;
}
   
}