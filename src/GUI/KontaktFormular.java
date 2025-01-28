package GUI;

import models.Patient;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;
import javax.swing.table.DefaultTableModel;

/**
 * Die Klasse KontaktFormular stellt eine Benutzeroberfläche bereit,
 * um Patientendaten zu erfassen, hinzuzufügen, zu bearbeiten und zu löschen.
 * Die GUI besteht aus einem Formular, in dem Patienteninformationen eingeben werden können.
 * Es gibt auch Buttons zum Hinzufügen, Bearbeiten und Löschen von Patient:innen.
 */
public class KontaktFormular extends JPanel {

    // ATTRIBUTE
    private JPanel kontaktFormularPanel;
    private JTextField vornameField, nachnameField, geburtsdatumField, strasseField, plzField, ortField,
            bundeslandField, geschlechtField, krankenkasseField, angehoerigerField;
    private PatientManager patientManager;
    private Patientendatenbank patientenDatenbank;
    private HauptGUI hauptGUI;
    private DefaultTableModel tableModel;
    /**
     * Konstruktor für die Klasse KontaktFormular
     * Dieser Konstruktor initialisiert das Kontaktformular mit verschiedenen Eingabefeldern
     * und fügt diese zum Formularpanel hinzu.
     * Es werden Buttons für das Hinzufügen, Bearbeiten und Löschen von Patient:innen erstellt
     * und entsprechende ActionListener hinzugefügt.
     */
    public KontaktFormular() {

        this.patientenDatenbank = new Patientendatenbank();

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
        JButton pdfButton = new JButton("PDF Export");
        pdfButton.setCursor(new Cursor(Cursor.HAND_CURSOR));


        // ActionListener für Buttons hinzufügen!!!
        hinzufuegenButton.addActionListener(e -> {
            try {
                // Schritt 1: Patient aus den Eingabefeldern erstellen
                Patient patient = createPatientFromFields();

                if (patient == null) {
                    JOptionPane.showMessageDialog(null, "Fehler bei der Eingabe der Patientendaten. Bitte überprüfen Sie die Felder.",
                            "Eingabefehler", JOptionPane.ERROR_MESSAGE);
                    return;  // Rückgabe, falls die Patientenerstellung fehlgeschlagen ist
                }

                // Schritt 2: Versuchen, den Patienten zur Datenbank hinzuzufügen
                boolean success = (boolean) patientenDatenbank.addPatient(patient);

                if (success) {
                    // Erfolgsmeldung, wenn der Patient erfolgreich hinzugefügt wurde
                    JOptionPane.showMessageDialog(null, "Patient erfolgreich hinzugefügt!");
                    clearFields(); // Eingabefelder leeren
                    hauptGUI.refreshPatientTable(); // Patiententabelle aktualisieren

                } else {
                    // Fehlerfall: Zeige eine Fehlermeldung, wenn das Hinzufügen nicht erfolgreich war
                    JOptionPane.showMessageDialog(null, "Fehler beim Hinzufügen des Patienten. Bitte versuchen Sie es erneut.",
                            "Fehler", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                // Allgemeine Fehlerbehandlung, falls andere unerwartete Fehler auftreten
                JOptionPane.showMessageDialog(null, "Unerwarteter Fehler: " + ex.getMessage(),
                        "Fehler", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        });

        bearbeitenButton.addActionListener(e -> {
            String patientIdStr = JOptionPane.showInputDialog(this, "Geben Sie die PatientID ein, die Sie bearbeiten möchten:");
            if (patientIdStr != null && !patientIdStr.isEmpty()) {
                try {
                    int patientId = Integer.parseInt(patientIdStr);
                    Patient patient = patientenDatenbank.getPatientById(patientId);
                    if (patient != null) {
                        PatientEditDialog dialog = new PatientEditDialog();
                        dialog.setPatient(patient, patientenDatenbank, hauptGUI);
                        dialog.setVisible(true);
                        // Null-Überprüfung, bevor refreshPatientTable aufgerufen wird
                        if (hauptGUI != null) {
                            hauptGUI.refreshPatientTable();
                        } else {
                            System.out.println("Fehler: hauptGUI ist null");
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "Kein Patient mit dieser ID gefunden!");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Ungültige ID eingegeben. Bitte geben Sie eine gültige Zahl ein.");
                }
            }
        });
        loeschenButton.addActionListener(e -> deletePatient());
        pdfButton.addActionListener(e -> exportToPDF());

        // Füge die Buttons zum Button Panel hinzu
        buttonPanel.add(hinzufuegenButton);
        buttonPanel.add(bearbeitenButton);
        buttonPanel.add(loeschenButton);
        buttonPanel.add(pdfButton);
        buttonPanel.add(pdfButton); // Button zum Formular hinzufügen

        // Drei Buttons zum Panel vom Kontaktformular Panel hinzugefügt
        kontaktFormularPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Setze das Kontaktformular Panel als das Hauptpanel des Kontaktformulars
        this.setLayout(new BorderLayout());
        this.add(kontaktFormularPanel, BorderLayout.CENTER);

    }
    private void exportToPDF() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("PDF speichern unter...");
        int userSelection = fileChooser.showSaveDialog(this);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            String pdfFilePath = fileChooser.getSelectedFile().getAbsolutePath();
            if (!pdfFilePath.endsWith(".pdf")) {
                pdfFilePath += ".pdf"; // .pdf hinzufügen, falls nicht vorhanden
            }

            try {
                PdfWriter writer = new PdfWriter(pdfFilePath);
                PdfDocument pdfDoc = new PdfDocument(writer);
                Document document = new Document(pdfDoc);

                // Titel hinzufügen
                document.add(new Paragraph("Patientenbericht")
                        .setBold().setFontSize(18).setTextAlignment(TextAlignment.CENTER)
                        .setMarginBottom(20));

                // Kopfzeilen und Spaltenbreite definieren
                String[] headers = {"ID", "Anrede", "Vorname", "Nachname", "Geburtstag", "Versicherung"}; // Telefon entfernt
                float[] columnWidths = {30, 60, 80, 80, 100, 100}; // Angepasste Breiten
                Table table = new Table(columnWidths);
                table.setWidth(UnitValue.createPercentValue(100));

                // Tabellenkopf hinzufügen
                for (String header : headers) {
                    table.addHeaderCell(new Cell()
                            .add(new Paragraph(header))
                            .setBackgroundColor(ColorConstants.LIGHT_GRAY)
                            .setBold()
                            .setTextAlignment(TextAlignment.CENTER));
                }

                // Tabellendaten hinzufügen
                for (int i = 0; i < tableModel.getRowCount(); i++) {
                    for (int j = 0; j < headers.length; j++) { // Nur relevante Spalten
                        Object cellValue = tableModel.getValueAt(i, j);
                        String cellText = (cellValue != null) ? cellValue.toString() : "-";

                         /*   if (j == 4) { // Spalte Geburtstag
                                cellText = formatDate(cellText); // Nur Datum formatieren
                            }

                            table.addCell(new Cell()
                                    .add(new Paragraph(cellText))
                                    .setTextAlignment(TextAlignment.LEFT)
                                    .setPadding(5));
                          */

                    }
                }

                // Datum des Exports hinzufügen
                document.add(table);
                document.add(new Paragraph("Exportiert am: " + LocalDate.now())
                        .setTextAlignment(TextAlignment.RIGHT).setFontSize(10).setItalic());

                // Dokument schließen
                document.close();
                JOptionPane.showMessageDialog(this, "PDF erfolgreich erstellt: " + pdfFilePath);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Fehler beim Speichern: " + e.getMessage(), "Fehler", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Die Methode löscht einen Patienten aus der Datenbank.
     * Die Methode fordert zur Eingabe einer PatientID auf.
     * Nachdem die ID eingegeben wurde, wird überprüft, ob ein Patient mit dieser ID in der Datenbank vorhanden ist.
     * Wenn der Patient gefunden wird, wird eine Bestätigungsmeldung angezeigt, um den Löschvorgang zu bestätigen.
     * Wenn der Benutzer bestätigt,wird der Patient aus der Datenbank gelöscht.
     * Falls der Patient nicht gefunden wird oder ID ungültig, wird eine entsprechende Fehlermeldung angezeigt.
     * @throws SQLException, wenn ein Fehler bei der Datenbankabfrage auftritt.
     */
    private void deletePatient() {
        // fordert zur Eingabe einer PatientID auf.
        String patientIdStr = JOptionPane.showInputDialog(this, "Geben Sie die PatientID ein, die Sie löschen möchten:");
        if (patientIdStr != null && !patientIdStr.isEmpty()) {
            try {
                int patientId = Integer.parseInt(patientIdStr);
                Patient patient = patientenDatenbank.getPatientById(patientId);
                if (patient != null) {
                    int confirmation = JOptionPane.showConfirmDialog(this,
                            "Möchten Sie den Patienten mit der ID " + patientId + " wirklich löschen?",
                            "Bestätigung", JOptionPane.YES_NO_OPTION);
                    if (confirmation == JOptionPane.YES_OPTION) {
                        if (patientenDatenbank.deletePatient(patientId)) {
                            JOptionPane.showMessageDialog(this, "Patient erfolgreich gelöscht!");
                            hauptGUI.refreshPatientTable();
                        } else {
                            JOptionPane.showMessageDialog(this, "Fehler beim Löschen des Patienten. Bitte versuchen Sie es erneut.");
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Kein Patient mit dieser ID gefunden!");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Ungültige ID eingegeben. Bitte geben Sie eine gültige Zahl ein.");
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Fehler bei der Datenbankabfrage. Bitte versuchen Sie es später erneut.");
            }
        }
    }



    /**
     * Diese Methode bearbeitet die Daten eine:r Patient:in.
     * Die Methode fordert den: die Benutzer:in auf, die PatientenID einzugeben.
     * Anschließend wird der:die entsprechende Patient:in aus der Datenbank abgerufen.
     * Falls Patient:in existiert, wird ein Dialogfenster zum Bearbeiten der Patientendaten geöffnet.
     * Falls der:die Patient:in nicht gefunden wird oder eine ungültige ID eingegeben wird,
     * wird eine Fehlermeldung angezeigt.
     * @throws SQLException wenn ein Fehler beim Abrufen des:der Patient:in aus der Datenbank auftritt.
     */
    private void editPatient() throws SQLException {
        String patientIdStr = JOptionPane.showInputDialog(this, "Geben Sie die PatientID ein, die Sie bearbeiten möchten:");
        if (patientIdStr != null && !patientIdStr.isEmpty()) {
            try {
                int patientId = Integer.parseInt(patientIdStr);
                Patient patient = patientenDatenbank.getPatientById(patientId);
                if (patient != null) {
                    // Öffne das Fenster zum Bearbeiten des Patienten
                    PatientEditDialog dialog = new PatientEditDialog();
                    dialog.setPatient(patient, patientenDatenbank, hauptGUI); // Übergabe der notwendigen Parameter
                    dialog.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(this, "Kein Patient mit dieser ID gefunden!");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Ungültige ID eingegeben. Bitte geben Sie eine gültige Zahl ein.");
            }
        }
    }


    /**
     * Diese Methode erstellt ein Patient-Objekt basierend auf den Werten, die in den Formularfeldern eingegeben wurden.
     * Sie extrahiert die Daten aus den Eingabefeldern des Kontaktformulars, validiert und konvertiert sie
     * und erstellt ein neues Patient-Objekt.
     * Sollte eine der Eingaben ungültig oder fehlerhaft sein,
     * wird eine Fehlermeldung angezeigt und null zurückgegeben.
     * @return Ein neues Patient}-Objekt mit den eingegebenen Werten oder null, wenn die Eingaben fehlerhaft sind.
     */
    public Patient createPatientFromFields() {
        try {
            String vorname = vornameField.getText().trim();
            String nachname = nachnameField.getText().trim();
            String geburtsdatumStr = geburtsdatumField.getText().trim();
            String strasse = strasseField.getText().trim();
            String plz = plzField.getText().trim();
            String ort = ortField.getText().trim();
            String bundesland = bundeslandField.getText().trim();
            int geschlecht = Integer.parseInt(geschlechtField.getText().trim());
            String krankenkasse = krankenkasseField.getText().trim();
            int angehoeriger = Integer.parseInt(angehoerigerField.getText().trim());

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date utilDate = formatter.parse(geburtsdatumStr);
            java.sql.Date geburtsdatum = new java.sql.Date(utilDate.getTime());

            return new Patient(0, vorname, nachname, geburtsdatum, strasse, plz, ort, bundesland, geschlecht, krankenkasse, angehoeriger);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Fehler bei der Eingabe: " + ex.getMessage(), "Fehler", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
    //funktioniert noch nicht, soll mein Kontaktformular leeren, nachdem ich es ausgefüllt hab
    public void clearFields() {
        vornameField.setText("");
        nachnameField.setText("");
        geburtsdatumField.setText("");
        strasseField.setText("");
        plzField.setText("");
        ortField.setText("");
        bundeslandField.setText("");
        geschlechtField.setText("");
        krankenkasseField.setText("");
        angehoerigerField.setText("");
    }
}
