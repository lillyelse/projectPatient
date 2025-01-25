package GUI;

import javax.swing.*;
import java.awt.*;

public class KontaktFormular extends JPanel {

    private JPanel kontaktFormularPanel;
    private JTextField vornameField, nachnameField, geburtsdatumField, strasseField, plzField, ortField,
            bundeslandField, geschlechtField, krankenkasseField, angehoerigerField;


    public KontaktFormular() {
        this.kontaktFormularPanel = new JPanel(new BorderLayout());


        // Initialisiere das Kontaktformular Panel
        JPanel kontaktFormularPanel = new JPanel(new BorderLayout());
        kontaktFormularPanel.setBorder(BorderFactory.createTitledBorder("Kontaktformular"));

        // Panel für das Formular
        JPanel formPanel = new JPanel(new GridLayout(10, 2, 10, 10));

        // Felder für die Eingabe erstellen
        vornameField = new JTextField();
        nachnameField = new JTextField();
        geburtsdatumField = new JTextField();
        strasseField = new JTextField();
        plzField = new JTextField();
        ortField = new JTextField();
        bundeslandField = new JTextField();
        geschlechtField = new JTextField();
        krankenkasseField = new JTextField();
        angehoerigerField = new JTextField();

        // mit formPanel.add Labels und Felder zum Formular hinzugefügt
        formPanel.add(new JLabel("Vorname:"));
        formPanel.add(vornameField);
        formPanel.add(new JLabel("Nachname:"));
        formPanel.add(nachnameField);
        formPanel.add(new JLabel("Geburtsdatum (YYYY-MM-DD):"));
        formPanel.add(geburtsdatumField);
        formPanel.add(new JLabel("Straße:"));
        formPanel.add(strasseField);
        formPanel.add(new JLabel("PLZ:"));
        formPanel.add(plzField);
        formPanel.add(new JLabel("Ort:"));
        formPanel.add(ortField);
        formPanel.add(new JLabel("Bundesland:"));
        formPanel.add(bundeslandField);
        formPanel.add(new JLabel("Geschlecht ID:"));
        formPanel.add(geschlechtField);
        formPanel.add(new JLabel("Krankenkasse:"));
        formPanel.add(krankenkasseField);
        formPanel.add(new JLabel("Angehörige ID:"));
        formPanel.add(angehoerigerField);

        // Kontaktformular Layout zum Panel hinzugefügt
        kontaktFormularPanel.add(formPanel, BorderLayout.CENTER);

        // Button Panel erstellen
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

        // Buttons erstellen
        JButton hinzufuegenButton = new JButton("Hinzufügen");
        JButton bearbeitenButton = new JButton("Bearbeiten");
        JButton loeschenButton = new JButton("Löschen");

    }
}
