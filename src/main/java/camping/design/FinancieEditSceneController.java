package camping.design;

import camping.entities.Pozemok;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class FinancieEditSceneController {

    private Pozemok pozemok;
    
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ListView<?> zoznamVyplatenychListView;

    @FXML
    private Button potvrdPlatbuButton;

    @FXML
    private Label pocetNociLabel;

    @FXML
    private Label idPozemkuLabel;

    @FXML
    private Label celkovaSumaLabel;

    @FXML
    private CheckBox vyplateneCheckBox;

    @FXML
    private TextField pocetNociTextField;

    @FXML
    private TextField idPozemkuTextField;

    @FXML
    private Button hladajVyplatenieButton;

    @FXML
    void initialize() {
        // TODO
        //vypocítanie sumy podla pozemku a poctu dni
        celkovaSumaLabel.setStyle("");
    }
}
