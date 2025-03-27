import java.awt.*;
import javax.swing.*;
import models.Borne;
import models.Carte;
import models.TypeRecharge;
import utils.ClearScreen;

public class MainSwing extends JFrame {
    private Borne borne;
    private Carte carte;

    public MainSwing() {
        // Création des objets
        //carte = new Carte("Dupont", "Jean", "Forfait", 100.0f, null, null);
        carte = new Carte("Dupont", "Jean", "Abonnement", 100.0f, "2025-01-01", "2025-12-31");
        TypeRecharge typeRecharge = new TypeRecharge("Rapide", 50);
        borne = new Borne(1, typeRecharge);
        borne.insererCarte(carte);

        // Configuration de la fenêtre
        setTitle("Gestion de Borne");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        // Création des boutons
        JButton btnAfficher = new JButton("Afficher infos");
        JButton btnInsererPrise = new JButton("Insérer la prise");
        JButton btnCharger = new JButton("Démarrer chargement");
        JButton btnQuitter = new JButton("Quitter");

        // Ajout des événements
        btnAfficher.addActionListener(e -> {
            ClearScreen.clearScreen();
            String info = borne.afficher();
            JOptionPane.showMessageDialog(this, info, "Informations", JOptionPane.INFORMATION_MESSAGE);
        });
        
        btnInsererPrise.addActionListener(e -> {
            ClearScreen.clearScreen();
            String inserer = borne.insererPrise();
            // borne.insererPrise();
            JOptionPane.showMessageDialog(this, inserer, "Informations",  JOptionPane.INFORMATION_MESSAGE);
        });

        btnCharger.addActionListener(e -> {
            ClearScreen.clearScreen();
            String charge = borne.charger();
            JOptionPane.showMessageDialog(this, charge, "Informations", JOptionPane.INFORMATION_MESSAGE);
        });

        btnQuitter.addActionListener(e -> System.exit(0));

        // Ajout des boutons à la fenêtre
        add(btnAfficher);
        add(btnInsererPrise);
        add(btnCharger);
        add(btnQuitter);

        // Rendre visible
        setVisible(true);
    }

    public static void main(String[] args) {
        new MainSwing();
    }
}
