package models;

public class Borne {

    private int id;
    private TypeRecharge typeRecharge;
    private Carte carte;

    public Borne(int id, TypeRecharge typeRecharge) {
        this.id = id;
        this.typeRecharge = typeRecharge;
    }

    public void insererCarte(Carte carte) {
        if (carte == null) {
            System.out.println("Carte non insérée");
            return;
        }
        else {
            this.carte = carte;
            System.out.println("Carte insérée");
        }
    }

    public String afficher() {
        StringBuilder info = new StringBuilder();
        info.append("Borne{")
            .append("id=").append(id)
            .append(", typeRecharge=").append(typeRecharge)
            .append("}\n");
        info.append("Infos de la carte :\n")
            .append("nom='").append(carte.getNom()).append("'\n")
            .append("prenom='").append(carte.getPrenom()).append("'\n")
            .append("typeContrat='").append(carte.getTypeContrat()).append("'\n")
            .append("soldeForfait=").append(carte.getSoldeForfait());
        return info.toString();
    }

    public void insererPrise() {
        System.out.println("Prise insérée");
    }

    public void charger() {
        System.out.println("Chargement en cours");
    }
    


}