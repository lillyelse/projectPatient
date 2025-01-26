package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Die Login-Klasse erstellt das Login-Fenster, in dem der:die Benutzer:in Name
 * und Passwort eingeben kann, um auf das Hauptfenster zuzugreifen.
 * Sie enthält ein Login-Formular und überprüft die Eingabedaten.
 */
public class Login extends JFrame{

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JPanel contentPane;
    private JButton abbrechenButton;
    private JButton OKButton;
    private JLabel messageLabel;

    /**
     * Konstruktor, der das Login-Fenster erstellt und die Benutzeroberfläche initialisiert.
     * Setzt das Layout, fügt die Komponenten hinzu und definiert das Verhalten der Buttons.
     */
    public Login(){
        setTitle("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        setLocationRelativeTo(null);

        // Hintergrundbild laden und anzeigen!!
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon backgroundIcon = OptimizedImageLoader.loadImage(
                        "src/Lib/DrBerger.png", getWidth(), getHeight()
                );
                if (backgroundIcon != null) {
                    g.drawImage(backgroundIcon.getImage(), 0, 0, getWidth(), getHeight(), this);
                }
            }
        };
        backgroundPanel.setLayout(new BorderLayout());

        // Content Pane und Hintergrund-Panel setzen!!!
        setContentPane(backgroundPanel);

        // Panel für Login-Formular
        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.setOpaque(false);

        // Komponenten hinzufügen
        panel.add(new JLabel("Benutzername:"));
        usernameField = new JTextField();
        panel.add(usernameField);

        panel.add(new JLabel("Passwort:"));
        passwordField = new JPasswordField();
        panel.add(passwordField);

        OKButton = new JButton("Login");
        abbrechenButton= new JButton("Abbrechen");
        messageLabel = new JLabel("", SwingConstants.CENTER);

        panel.add(OKButton);
        panel.add(abbrechenButton);

        messageLabel = new JLabel("", SwingConstants.CENTER);

        backgroundPanel.add(panel, BorderLayout.CENTER); // Login-Panel zum Hintergrund-Panel hinzufügen
        backgroundPanel.add(messageLabel, BorderLayout.SOUTH);

        // Login-Button Aktion
        OKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                if (authenticate(username, password)) {
                    dispose(); // Login-Fenster schließen
                    Patientendatenbank patientenDatenbank = new Patientendatenbank();
                    new HauptGUI(patientenDatenbank);
                } else {
                    messageLabel.setText("Ungültige Anmeldedaten!");
                }
            }
        });
        //wenn man Abbrechen klickt soll sich Login Fenster schließen

        abbrechenButton.addActionListener(e -> dispose());

        setVisible(true);
    }

    /**
     * Die Methode führt die Authentifizierung durch. Sie prüft, ob der Benutzername und das Passwort korrekt sind.
     * @param username Benutzername
     * @param password Passwort
     * @return true, wenn die Authentifizierung erfolgreich ist, sonst false
     */
    private boolean authenticate(String username, String password) {
        // Temporäre Authentifizierung, später durch DB-Check ersetzen
        return "admin".equals(username) && "password123".equals(password);
    }

    /**
     * Diese Klasse lädt ein Bild von der angegebenen Datei und skaliert es auf die gewünschte Größe.
     */
    static class OptimizedImageLoader {
        /**
         * Lädt ein Bild von der angegebenen Datei und skaliert es auf die angegebene Breite und Höhe.
         *
         * @param path  Der Pfad zur Bilddatei.
         * @param width Die gewünschte Breite des Bildes.
         * @param height Die gewünschte Höhe des Bildes.
         *
         */
        public static ImageIcon loadImage(String path, int width, int height) {
            try {
                ImageIcon icon = new ImageIcon(path);
                Image img = icon.getImage();
                Image scaledImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
                return new ImageIcon(scaledImg);
            } catch (Exception e) {
                System.err.println("Fehler beim Laden des Bildes: " + e.getMessage());
                return null;
            }
        }
    }

}
