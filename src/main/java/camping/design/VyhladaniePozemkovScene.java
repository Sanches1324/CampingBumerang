package camping.design;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class VyhladaniePozemkovScene {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button ukazPozemkyButton;

    @FXML
    private Label datumObsadeniaLabel;

    @FXML
    private Label idPozemkuLabel;

    @FXML
    private DatePicker datumObjednavkyDatePicker;

    @FXML
    private TextField idPozemkuTextField;

    @FXML
    private CheckBox neobsadenyPozemokCheckBox;

    @FXML
    private Label poziadavkyVyhladavaniaLabel;

    @FXML
    void initialize() {

    }
}
