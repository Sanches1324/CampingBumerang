package camping.design;

import camping.entities.Pouzivatel;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class ZamestnanecEditSceneController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button potvrdPridanieZamestnancaButton;

    @FXML
    private TextField menoZamestnancaTextField;

    @FXML
    private PasswordField hesloZamestnancaTextField;

    @FXML
    private Label menoZamestnancaLabel;

    @FXML
    private Label hesloZamestnancaLabel;

    @FXML
    private ListView<Pouzivatel> zoznamZamestnancovListView;

    @FXML
    private Button OdstranVybratehoButton;

    @FXML
    void initialize() {
    
    }
}
