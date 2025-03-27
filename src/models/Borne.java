package models;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Borne {

    private int id;
    private TypeRecharge typeRecharge;
    private Carte carte;
    private boolean priseInseree;
    private boolean enChargement;

    public Borne(int id, TypeRecharge typeRecharge) {
        this.id = id;
        this.typeRecharge = typeRecharge;
        this.priseInseree = false;
        this.enChargement = false;
    }

    public void insererCarte(Carte carte) {
        if (carte == null) {
            System.out.println("Carte non insérée");
            return;
        }

        if (carte.estUnAbonnement()) {
            String dateActuelle = LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE);
            if (!carte.estAbonnementValide(dateActuelle)) {
                System.out.println("Abonnement expiré ou invalide");
                return;
            }
        }

        this.carte = carte;
        System.out.println("Carte insérée");
    }

    public String afficher( Batterie batterie) {
        StringBuilder info = new StringBuilder();
        info.append("Infos de la borne :").append("\n")
                .append("Numero de la borne : ").append(id).append("\n")
                .append("Types de recharge de la borne : ").append(typeRecharge).append("\n")
                .append("\n");

        if (carte != null) {
            info.append("Infos de la carte :\n")
                    .append("Nom : ").append(carte.getNom()).append("\n")
                    .append("Prenom : ").append(carte.getPrenom()).append("\n");

                    
                    if (carte.estUnForfait()) {
                        info.append("solde : ").append(carte.getSoldeForfait()).append("€\n");
                    } else {
                        info.append("Type : Abonnement\n")
                        .append("Date de début : ").append(carte.getDateDebutAbonnement()).append("\n")
                        .append("Date de fin : ").append(carte.getDateFinAbonnement()).append("\n").append("\n");
                    }
                    info.append("Infos de la batterie :\n")
                            .append("rédéfence : ").append(batterie.getRef()).append("\n")
                            .append("Fabricant : ").append(batterie.getFabricant()).append("\n")
                            .append("Charge maximale : ").append(batterie.getChargeMax()).append("kW\n")
                            .append("Charge actuelle : ").append(batterie.getChargeActuelle()).append("kWh\n")
                            .append("Type de recharge : ").append(batterie.getTypeRecharge()).append("\n");
        } else {
            info.append("Aucune carte insérée : L'abonnement ou le forfait n'est pas valide\n");

        }
        return info.toString();
    }

    public String insererPrise() {
        StringBuilder inserer = new StringBuilder();
        if (carte == null) {
            inserer.append("Impossible d'insérer la prise : aucune carte insérée");
            System.out.println("Impossible d'insérer la prise : aucune carte insérée");
            
        } else {
        priseInseree = true;
        System.out.println("Prise insérée");
        inserer.append("Prise insérée\n");
    }
    
    return inserer.toString();
}

public String charger() {
    StringBuilder charge = new StringBuilder();
    if (!priseInseree) {
        charge.append("Impossible de charger : prise non insérée");
    } else {
        priseInseree = true;
        charge.append("Chargement en cours...\n");
    }
    
    return charge.toString();
}

}