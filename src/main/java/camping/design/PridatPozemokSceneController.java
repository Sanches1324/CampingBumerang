package camping.design;

import camping.dao.CampingDaoFactory;
import camping.dao.PozemokDao;
import java.net.URL;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;
import javax.swing.JOptionPane;

public class PridatPozemokSceneController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label cisloPozemkuLabel;

    @FXML
    private TextField cisloPozemkuTextField;

    @FXML
    private ChoiceBox<String> kategoriaPozemkuChoiceBox;

    @FXML
    private TextField cenaPozemkuTextField;

    @FXML
    private CheckBox obsadenostPozemkuCheckBox;

    @FXML
    private Button pridatPozemokButton;
    private KategoriaFxModel kategoriaModel = new KategoriaFxModel();
    private ObservableList<String> kategorie = kategoriaModel.getNazvy();
    private PozemokFxModel pozemokModel = new PozemokFxModel();
    private ObservableList<PozemokFxModel> pozemky = FXCollections.observableArrayList(pozemokModel.getPozemky());
    private boolean cislo = false;
    private boolean kategoria = false;
    private boolean cena = false;
    private boolean splneneVsetko = false;

    @FXML
    void initialize() {
        int posledny = pozemky.size() + 1;
        cisloPozemkuTextField.setText(posledny + "");
        kategoriaPozemkuChoiceBox.setItems(kategorie);
        pridatPozemokButton.setDefaultButton(true);
        pridatPozemokButton.setOnAction(eh -> {
            PozemokFxModel pozemok = new PozemokFxModel();
            if (cisloPozemkuTextField.getText().equals("") || Long.parseLong(cisloPozemkuTextField.getText()) < 1 || Long.parseLong(cisloPozemkuTextField.getText()) > 100) {
                cisloPozemkuTextField.setStyle("-fx-background-color: #FF0000;");
            } else {
                pozemok.setCisloPozemku(Long.parseLong(cisloPozemkuTextField.getText()));
                cislo = true;
            }
            if (kategoriaPozemkuChoiceBox.getSelectionModel().getSelectedIndex() == -1) {
                kategoriaPozemkuChoiceBox.setStyle("-fx-background-color: #FF0000;");
            } else {
                pozemok.setKategoriaId((long) kategoriaPozemkuChoiceBox.getSelectionModel().getSelectedIndex() + 1);
                kategoria = true;
            }
            if (cenaPozemkuTextField.getText().equals("")) {
                cenaPozemkuTextField.setStyle("-fx-background-color: #FF0000;");
            } else {
                pozemok.setCena(Integer.parseInt(cenaPozemkuTextField.getText()));
                cena = true;
            }

            if (obsadenostPozemkuCheckBox.selectedProperty().getValue()) {
                pozemok.setObsadenost(true);
                pozemok.setObsadenostBoolean("obsadený");
            } else {
                pozemok.setObsadenost(false);
                pozemok.setObsadenostBoolean("voľný");
            }

            if (cislo == true && kategoria == true && cena == true) {
                PozemokDao pozemokDao = CampingDaoFactory.INSTANCE.getMySqlPozemokDao();
                List<PozemokFxModel> pozemky = pozemokDao.getAll();
                ObservableList<PozemokFxModel> poz = FXCollections.observableArrayList(pozemky);
                try {
                    pozemokDao.createPozemok(pozemok);
                } catch (Exception e) {
                    Alert alert = new Alert(Alert.AlertType.WARNING, "Pozemok z takym cislom uz existuje, chcete jeho aktualizovat?", ButtonType.OK, ButtonType.NO);
                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.isPresent() && result.get() == ButtonType.OK) {
                        pozemokDao.updatePozemok(pozemok);
                    } else {
                        pridatPozemokButton.getScene().getWindow().hide();
                    }
                }
                pridatPozemokButton.getScene().getWindow().hide();
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Vyplňte všetky udaje", ButtonType.OK);
                Optional<ButtonType> result = alert.showAndWait();

            }
        }
        );
    }
}
