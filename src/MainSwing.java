import java.awt.*;
import javax.swing.*;
import models.Borne;
import models.Carte;
import models.Batterie;
import models.TypeRecharge;
import utils.ClearScreen;

public class MainSwing extends JFrame {
    private Borne borne;
    private Carte carte;
    private Batterie batterie;

    public MainSwing() {
        // Création des objets
        //carte = new Carte("Dupont", "Jean", "Forfait", 100.0f, null, null);
        carte = new Carte("Dupont", "Jean", "Abonnement", 100.0f, "2025-01-01", "2025-12-31");
        TypeRecharge typeRecharge = new TypeRecharge(TypeRecharge.TypeCharge.Normal);
        borne = new Borne(1, typeRecharge);
        batterie = new Batterie("1234", "Tesla", 100, 50, typeRecharge);
        borne.insererCarte(carte);

        // Configuration de la fenêtre
        setTitle("Gestion de Borne");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        // Création des boutons
        JButton btnAfficher = new JButton("Afficher infos");
        JButton btnInsererPrise = new JButton("Insérer la prise");
        JButton btnSelectionCharge = new JButton("Selectionner le type de charge souhaité");
        JButton btnCharger = new JButton("Démarrer chargement");
        JButton btnQuitter = new JButton("Quitter");

        // Ajout des événements
        btnAfficher.addActionListener(e -> {
            ClearScreen.clearScreen();
            String info = borne.afficher(batterie);
            JOptionPane.showMessageDialog(this, info, "Informations", JOptionPane.INFORMATION_MESSAGE);
        });
        
        btnInsererPrise.addActionListener(e -> {
            ClearScreen.clearScreen();
            String inserer = borne.insererPrise();
            // borne.insererPrise();
            JOptionPane.showMessageDialog(this, inserer, "Informations",  JOptionPane.INFORMATION_MESSAGE);
        });

        btnSelectionCharge.addActionListener(e -> {
            // Options disponibles dans l'énumération TypeCharge
            TypeRecharge.TypeCharge[] options = TypeRecharge.TypeCharge.values();
        
            // Afficher une boîte de dialogue avec une liste déroulante
            TypeRecharge.TypeCharge choix = (TypeRecharge.TypeCharge) JOptionPane.showInputDialog(
                    this,
                    "Sélectionnez le type de charge :",
                    "Type de Charge",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[0] // Option par défaut
            );
        
            // Vérifier si l'utilisateur a fait un choix
            if (choix != null) {
                borne.setTypeRecharge(new TypeRecharge(choix)); // Mettre à jour le type de charge de la borne
                JOptionPane.showMessageDialog(this, "Vous avez sélectionné : " + choix.name(), "Confirmation", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Aucun type de charge sélectionné.", "Erreur", JOptionPane.WARNING_MESSAGE);
            }
        });



        btnCharger.addActionListener(e -> {
            ClearScreen.clearScreen();
            String charge = borne.charger(batterie);
            JOptionPane.showMessageDialog(this, charge, "Informations", JOptionPane.INFORMATION_MESSAGE);
        });

        btnQuitter.addActionListener(e -> System.exit(0));

        // Ajout des boutons à la fenêtre
        add(btnAfficher);
        add(btnInsererPrise);
        add(btnSelectionCharge);
        add(btnCharger);
        add(btnQuitter);

        // Rendre visible
        setVisible(true);
    }

    public static void main(String[] args) {
        new MainSwing();
    }
}
