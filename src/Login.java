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

        contentPane = new JPanel(new BorderLayout());
        setContentPane(contentPane);
        setSize(400, 200);
        setLocationRelativeTo(null);

        // Layout für das Fenster
        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));

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

                    // Instanz der PatientenDatenbank hier erstellen


                    // HauptGUI


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
