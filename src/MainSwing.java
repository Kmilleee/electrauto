import java.awt.*;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import javax.swing.*;
import models.Batterie;
import models.Borne;
import models.Carte;
import models.TypeRecharge;
import utils.ClearScreen;

public class MainSwing extends JFrame {
    private Borne borne;
    private Carte carte;
    private Batterie batterie;
    private JProgressBar progressBar;
    private JLabel tempsLabel;
    private Timer chargeTimer;
    private LocalDateTime debutChargement;
    private JDialog progressDialog;

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
                TypeRecharge nouveauType = new TypeRecharge(choix);
                borne.setTypeRecharge(nouveauType);
                batterie.setTypeRecharge(nouveauType);
            } else {
                JOptionPane.showMessageDialog(this, "Aucun type de charge sélectionné.", "Erreur", JOptionPane.WARNING_MESSAGE);
            }
        });

        btnCharger.addActionListener(e -> {
            ClearScreen.clearScreen();
            if (!borne.isEnChargement()) {
                String charge = borne.charger(batterie);
                if (charge.contains("Chargement en cours")) {
                    afficherFenetreProgression();
                } else {
                    JOptionPane.showMessageDialog(this, charge, "Informations", JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Le chargement est déjà en cours", "Information", JOptionPane.INFORMATION_MESSAGE);
            }
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

    private void afficherFenetreProgression() {
        progressDialog = new JDialog(this, "Progression du chargement", true);
        progressDialog.setLayout(new BorderLayout());
        progressDialog.setSize(300, 150);

        // Barre de progression
        progressBar = new JProgressBar(0, 100);
        progressBar.setStringPainted(true);
        progressBar.setString("0%");

        tempsLabel = new JLabel("Temps restant : ");

        JButton stopButton = new JButton("Arrêter le chargement");
        stopButton.addActionListener(e -> arreterChargement());

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.add(progressBar, BorderLayout.CENTER);
        mainPanel.add(tempsLabel, BorderLayout.NORTH);
        mainPanel.add(stopButton, BorderLayout.SOUTH);

        progressDialog.add(mainPanel);
        progressDialog.setLocationRelativeTo(this);
        progressDialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);

        // Démarrage du timer
        debutChargement = LocalDateTime.now();
        batterie.setTempsInitialCharge(System.currentTimeMillis() / 1000);
        demarrerTimer();

        progressDialog.setVisible(true);
    }

    private void demarrerTimer() {
        chargeTimer = new Timer(1000, e -> {
            if (batterie.estChargee()) {
                arreterChargement();
                return;
            }

            // Mise à jour de la charge
            long tempsEcoule = ChronoUnit.SECONDS.between(debutChargement, LocalDateTime.now());
            batterie.mettreAJourCharge((int) tempsEcoule);

            // Mise à jour de la barre de progression
            int pourcentage = (int) ((batterie.getChargeActuelle() * 100.0) / batterie.getChargeMax());
            progressBar.setValue(pourcentage);
            progressBar.setString(pourcentage + "%");

            // Mise à jour du temps restant
            long tempsActuel = System.currentTimeMillis() / 1000;
            tempsLabel.setText("Temps restant : " + batterie.calculerTempsRecharge(tempsActuel));
        });
        chargeTimer.start();
    }

    private void arreterChargement() {
        if (chargeTimer != null) {
            chargeTimer.stop();
        }
        if (progressDialog != null) {
            progressDialog.dispose();
        }
        borne.setEnChargement(false);
    }

    public static void main(String[] args) {
        new MainSwing();
    }
}
