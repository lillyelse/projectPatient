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
    private JLabel passwordlabel;
    private JLabel userlabel;

    /**
     * Konstruktor, der das Login-Fenster erstellt und die Benutzeroberfläche initialisiert.
     * Setzt das Layout, fügt die Komponenten hinzu und definiert das Verhalten der Buttons.
     */
    public Login(){
        setTitle("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(550, 400);
        setLocationRelativeTo(null);

        // Hintergrundbild laden und anzeigen!!
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon backgroundIcon = OptimizedImageLoader.loadImage(
                        "src/Lib/img.png", getWidth(), getHeight()
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
        JPanel panel = new JPanel(new GridLayout(3, 2, 80, 80));
        panel.setOpaque(false);

        // Komponenten hinzufügen
        JLabel userlabel = new JLabel("Benutzername:");
        userlabel.setForeground(Color.WHITE); // Schriftfarbe auf weiss setzen
        userlabel.setFont(new Font("Arial", Font.BOLD, 26)); // Schriftgröße und -stil ändern
        panel.add(userlabel);

        JTextField usernameField = new JTextField(15); // Breite des Textfeldes (15 Zeichen)
        usernameField.setBackground(Color.GRAY); // Hintergrundfarbe
        usernameField.setForeground(Color.WHITE); // Schriftfarbe
        usernameField.setFont(new Font("Arial", Font.PLAIN, 20)); // Schriftgröße ändern
        usernameField.setPreferredSize(new Dimension(150, 25)); // Größe explizit setzen
        panel.add(usernameField);

        JLabel passwordlabel = new JLabel("Passwort:");
        passwordlabel.setForeground(Color.WHITE); // Schriftfarbe auf weiss setzen
        passwordlabel.setFont(new Font("Arial", Font.BOLD, 26)); // Schriftgröße und -stil ändern
        panel.add(passwordlabel);

        JPasswordField passwordField = new JPasswordField(15); // Breite des Textfeldes (15 Zeichen)
        passwordField.setBackground(Color.GRAY); // Hintergrundfarbe
        passwordField.setForeground(Color.WHITE); // Schriftfarbe
        passwordField.setFont(new Font("Arial", Font.PLAIN, 20)); // Schriftgröße ändern
        passwordField.setPreferredSize(new Dimension(150, 25)); // Größe explizit setzen
        panel.add(passwordField);

        OKButton = new JButton("Login");
        abbrechenButton= new JButton("Abbrechen");
        passwordlabel = new JLabel("", SwingConstants.CENTER);

        panel.add(OKButton);
        panel.add(abbrechenButton);

        passwordlabel = new JLabel("", SwingConstants.CENTER);

        backgroundPanel.add(panel, BorderLayout.CENTER); // Login-Panel zum Hintergrund-Panel hinzufügen
        backgroundPanel.add(passwordlabel, BorderLayout.SOUTH);

        // Login-Button Aktion
        JLabel finalPasswordlabel = passwordlabel;
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
                    finalPasswordlabel.setText("Ungültige Anmeldedaten!");
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
