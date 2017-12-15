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
import javafx.event.EventHandler;
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

    @FXML
    void initialize() {
        
        kategoriaPozemkuChoiceBox.setItems(kategorie);
        pridatPozemokButton.setDefaultButton(true);
        pridatPozemokButton.setOnAction(eh -> {
            PozemokFxModel pozemok = new PozemokFxModel();
            pozemok.setCisloPozemku(Long.parseLong(cisloPozemkuTextField.getText()));
            pozemok.setKategoriaId((long) kategoriaPozemkuChoiceBox.getSelectionModel().getSelectedIndex() + 1);
            pozemok.setCena(Integer.parseInt(cenaPozemkuTextField.getText()));
            if (obsadenostPozemkuCheckBox.selectedProperty().getValue()) {
                pozemok.setObsadenost(true);
            } else {
                pozemok.setObsadenost(false);
            }
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
//            for (PozemokFxModel pozemokFxModel : poz) {
//                if (pozemokFxModel.getCisloPozemku() == Long.parseLong(cisloPozemkuTextField.getText())) {
//                    Alert alert = new Alert(Alert.AlertType.WARNING, "Pozemok z takym cislom uz existuje, chcete jeho aktualizovat?", ButtonType.OK, ButtonType.NO);
//                    Optional<ButtonType> result = alert.showAndWait();
//                    if (result.isPresent() && result.get() == ButtonType.OK) {
//                        pozemokDao.updatePozemok(pozemok);
//                        break;
//                    } else {
//                        pridatPozemokButton.getScene().getWindow().hide();
//                        break;
//                    }
//                } else {
//                    pozemokDao.createPozemok(pozemok);
//                    break;
//                }
//            }

//            for (PozemokFxModel pozemokFxModel : poz) {
//                if (pozemokFxModel.getCisloPozemku() == Long.parseLong(cisloPozemkuTextField.getText())) {
//                    Alert alert = new Alert(Alert.AlertType.WARNING, "Pozemok z takym cislom uz existuje, chcete jeho aktualizovat?", ButtonType.OK, ButtonType.NO);
//                    Optional<ButtonType> result = alert.showAndWait();
//                    if (result.isPresent() && result.get() == ButtonType.OK) {
//                        pozemokDao.updatePozemok(pozemok);
//                        break;
//                    } else {
//                        pridatPozemokButton.getScene().getWindow().hide();
//                        break;
//                    }
//                } else {
//                    pozemokDao.createPozemok(pozemok);
//                    break;
//                }
//            }
            pridatPozemokButton.getScene().getWindow().hide();
            try {
                FXMLLoader loader = new FXMLLoader(
                        getClass().getResource("AdminScene.fxml"));
                Parent parentPane = loader.load();
                Scene scene = new Scene(parentPane);

                Stage stage = new Stage();
                Image logo = new Image("camping\\styles\\logo.png");
                stage.setScene(scene);
                stage.setTitle("Camping Bumerang");
                stage.getIcons().add(logo);
                stage.show();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }
}
