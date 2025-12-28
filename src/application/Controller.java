package application;

import implementation.GestionEmployeDB;
import models.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import java.time.LocalDate;

public class Controller {

    @FXML private TextField txtNom;
    @FXML private TextField txtAge;
    @FXML private DatePicker dpDateEntree;
    @FXML private ComboBox<String> cmbType;
    @FXML private TextField txtSalaire;
    @FXML private Label lblSalaireMoyen;

    @FXML private TableView<Employe> tableEmployes;
    @FXML private TableColumn<Employe, Integer> colId;
    @FXML private TableColumn<Employe, String> colNom;
    @FXML private TableColumn<Employe, Integer> colAge;
    @FXML private TableColumn<Employe, String> colDateEntree;
    @FXML private TableColumn<Employe, Double> colSalaire;

    private ObservableList<Employe> liste = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        cmbType.setItems(FXCollections.observableArrayList("Vendeur", "Representant", "Producteur", "Manutentionnaire"));
        
        colId.setCellValueFactory(d -> new SimpleIntegerProperty(d.getValue().getId()).asObject());
        colNom.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getNom())); 
        colAge.setCellValueFactory(d -> new SimpleIntegerProperty(d.getValue().getAge()).asObject());
        colDateEntree.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getDate()));
        colSalaire.setCellValueFactory(d -> new SimpleDoubleProperty(d.getValue().calculerSalaire()).asObject());

        tableEmployes.setItems(liste);
        afficherAction();
    }

    @FXML
    private void ajouterAction() {
        try {
            String nom = txtNom.getText();
            int age = Integer.parseInt(txtAge.getText());
            String date = (dpDateEntree.getValue() != null) ? dpDateEntree.getValue().toString() : LocalDate.now().toString();
            String type = cmbType.getValue();
            double val = Double.parseDouble(txtSalaire.getText());

            Employe e = null;
            boolean isRisque = false;

            switch (type) {
                case "Vendeur": e = new Vendeur("", nom, age, date, val); break;
                case "Representant": e = new Representant("", nom, age, date, val); break;
                case "Producteur": e = new Producteur("", nom, age, date, (int)val); break;
                case "Manutentionnaire": e = new Manu("", nom, age, date, (int)val); break;
            }

            if (e != null) {
                GestionEmployeDB.addEmployee(e, type, val, isRisque);
                afficherAction();
                clearForm();
            }
        } catch (Exception ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Erreur de saisie: " + ex.getMessage());
            alert.show();
        }
    }

    @FXML
    private void afficherAction() {
        liste.clear();
        liste.addAll(GestionEmployeDB.getAllEmployees());
        salaireMoyenAction();
    }

    @FXML
    private void supprimerAction() {
        Employe emp = tableEmployes.getSelectionModel().getSelectedItem();
        if (emp != null) {
            GestionEmployeDB.deleteEmployee(emp.getId());
            afficherAction();
        }
    }

    @FXML
    private void salaireMoyenAction() {
        double moy = GestionEmployeDB.getMoyenneSalaire();
        lblSalaireMoyen.setText(String.format("Salaire moyen : %.2f Dhs", moy));
    }

    private void clearForm() {
        txtNom.clear(); txtAge.clear(); txtSalaire.clear();
    }
    
    @FXML private void modifierAction() { 
    }
}