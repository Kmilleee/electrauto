import java.util.Scanner;

import models.Batterie;
import models.Borne;
import models.Carte;
import models.TypeRecharge;
import utils.ClearScreen;

public class Main {
    private static TypeRecharge typeRecharge;
    
        public static void main(String[] args) {
            // Création d'une carte
            Carte carte = new Carte("Dupont", "Jean", "Forfait", 100.0f, null, null);
    
            // Création d'un type de recharge
            TypeRecharge recharge = new TypeRecharge(TypeRecharge.TypeCharge.Normal);

            
            // Création d'une borne
            Borne borne = new Borne(1, recharge);    

            Batterie batterie = new Batterie("1234", "Tesla", 100, 50, typeRecharge);

        borne.insererCarte(carte);
        Scanner scanner = new Scanner(System.in);
        int choix;

        do {

            System.out.println("Menu :");
            System.out.println("1. Afficher les informations de la borne et de la carte");
            System.out.println("2. Insérer la prise");
            System.out.println("3. Démarrer le chargement");
            System.out.println("4. Quitter");
            choix = scanner.nextInt();
            
            switch (choix) {
                case 1:
                ClearScreen.clearScreen();
                borne.afficher(batterie);
                break;
                case 2:
                ClearScreen.clearScreen();
                borne.insererPrise();
                break;
                case 3:
                ClearScreen.clearScreen();
                borne.charger();
                break;      
                case 4:
                ClearScreen.clearScreen();
                break;
                
                default:
                ClearScreen.clearScreen();
                System.out.println("Saisie incorrecte");
                break;
            }
        } while (choix != 4);
        scanner.close();
    }
}