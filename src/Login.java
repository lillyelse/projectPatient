import javax.swing.*;
import java.awt.*;

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
        setContentPane(contentPane);
        pack();

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
        panel.add(messageLabel);

        add(panel, BorderLayout.CENTER);
    }


}
