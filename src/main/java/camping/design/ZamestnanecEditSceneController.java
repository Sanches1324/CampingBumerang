package camping.design;

import camping.dao.CampingDaoFactory;
import camping.dao.PouzivatelDao;
import camping.dao.PozemokDao;
import camping.design.PouzivatelFxModel;
import java.net.URL;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

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
    private TableColumn<PouzivatelFxModel, String> poziciaColumn;

    @FXML
    private TableColumn<PouzivatelFxModel, LocalDate> datNarColumn;

    @FXML
    private TableColumn<PouzivatelFxModel, String> adresaColumn;

    @FXML
    private TableColumn<PouzivatelFxModel, String> telCisloColumn;

    @FXML
    private TableColumn<PouzivatelFxModel, String> eMailColumn;

    @FXML
    private TableColumn<PouzivatelFxModel, Long> idColumn;

    @FXML
    private Button pridatButton;

    @FXML
    private Button vymazatButton;

    private PouzivatelFxModel pouzivatelModel = new PouzivatelFxModel();
    private PouzivatelDao pouzivatelDao = CampingDaoFactory.INSTANCE.getMySqlPouzivatelDao();
    private ObservableList<PouzivatelFxModel> pouzivatelia = pouzivatelModel.getPouzivatelov();

    @FXML
    void vymazatPouzivatela(ActionEvent event) {
        try {
            if (zamestnanecTableView.getSelectionModel().getSelectedIndex() == -1) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Nie je vybratý žiadný zamestnanec", ButtonType.OK);
                Optional<ButtonType> result = alert.showAndWait();
            } else {
                int index = zamestnanecTableView.getSelectionModel().getSelectedIndex();
                Long vymazat = idColumn.getCellData(index);
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
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("pridatZamestnancaScene.fxml"));
            Parent parentPane = loader.load();
            Scene scene = new Scene(parentPane);

            Stage stage = new Stage();
            Image logo = new Image("camping\\styles\\logo.png");
            stage.setScene(scene);
            stage.setTitle("Camping Bumerang");
            stage.getIcons().add(logo);

            stage.setOnHidden(eh -> {
                ObservableList<PouzivatelFxModel> pouzivatelia = FXCollections.observableArrayList(pouzivatelDao.getAll());
                zamestnanecTableView.getItems().clear();
                if (pouzivatelia.size() > 0) {
                    zamestnanecTableView.setItems(pouzivatelia);
                }

            });
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    void initialize() {
        menoTableColumn.setCellValueFactory(cellData -> cellData.getValue().menoProperty());
        poziciaColumn.setCellValueFactory(cellData -> cellData.getValue().poziciaProperty());
        datNarColumn.setCellValueFactory(cellData -> cellData.getValue().datumNarodeniaProperty());
        adresaColumn.setCellValueFactory(cellData -> cellData.getValue().adresaProperty());
        telCisloColumn.setCellValueFactory(cellData -> cellData.getValue().tel_cisloProperty());
        eMailColumn.setCellValueFactory(cellData -> cellData.getValue().e_mailProperty());
        idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        zamestnanecTableView.setItems(pouzivatelia);
    }
}
