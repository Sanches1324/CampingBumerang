package camping.design;

import camping.dao.CampingDaoFactory;
import camping.dao.PouzivatelDao;
import camping.dao.PozemokDao;
import camping.design.PouzivatelFxModel;
import java.net.URL;
import java.util.Collections;
import java.util.Comparator;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class ZamestnanecEditSceneController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField menoTextField;

    @FXML
    private TableView<PouzivatelFxModel> zamestnanecTableView;

    @FXML
    private TableColumn<PouzivatelFxModel, String> menoTableColumn;

    @FXML
    private Button pridatButton;

    @FXML
    private Button vymazatButton;

    private PouzivatelFxModel pouzivatelModel = new PouzivatelFxModel();
    private ObservableList<PouzivatelFxModel> pouzivatelia = pouzivatelModel.getPouzivatelov();

    @FXML
    void vymazatPouzivatela(ActionEvent event) {
        try {
            if (zamestnanecTableView.getSelectionModel().getSelectedIndex() == -1) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Nie je vybratý žiadný zamestnanec", ButtonType.OK);
                Optional<ButtonType> result = alert.showAndWait();
            } else {
                int index = zamestnanecTableView.getSelectionModel().getSelectedIndex();
                String vymazat = menoTableColumn.getCellData(index);
                PouzivatelDao pouzivatelDao = CampingDaoFactory.INSTANCE.getMySqlPouzivatelDao();
                pouzivatelDao.deletePouzivatela(vymazat);
                ObservableList<PouzivatelFxModel> pouzivatelia = FXCollections.observableArrayList(pouzivatelDao.getAll());
                zamestnanecTableView.setItems(pouzivatelia);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void pridat(ActionEvent event) {
        boolean menoJe = false;
        PouzivatelFxModel pouzivatel = new PouzivatelFxModel();
        if (menoTextField.getText().equals("")) {
            menoTextField.setStyle("-fx-background-color: #FF0000;");
        } else {
            pouzivatel.setMeno(menoTextField.getText());
            menoJe = true;
        }
        if (menoJe) {
            PouzivatelDao pouzivatelDao = CampingDaoFactory.INSTANCE.getMySqlPouzivatelDao();
            pouzivatelDao.createPouzivatela(pouzivatel);
            ObservableList<PouzivatelFxModel> novePouzivatelia = FXCollections.observableArrayList(pouzivatelDao.getAll());

            zamestnanecTableView.setItems(novePouzivatelia);

        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Vyplňte všetky udaje", ButtonType.OK);
            Optional<ButtonType> result = alert.showAndWait();

        }
    }

    @FXML
    void initialize() {
        menoTableColumn.setCellValueFactory(cellData -> cellData.getValue().menoProperty());
        zamestnanecTableView.setItems(pouzivatelia);
    }
}
