package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame{

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JPanel contentPane;
    private JButton abbrechenButton;
    private JButton OKButton;
    private JLabel messageLabel;

    public Login(){
        setTitle("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        setSize(400, 200);
        setLocationRelativeTo(null);


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

        contentPane.add(panel, BorderLayout.CENTER);
        contentPane.add(messageLabel, BorderLayout.SOUTH);

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

    private boolean authenticate(String username, String password) {
        // Temporäre Authentifizierung, später durch DB-Check ersetzen
        return "admin".equals(username) && "password123".equals(password);
    }

}
