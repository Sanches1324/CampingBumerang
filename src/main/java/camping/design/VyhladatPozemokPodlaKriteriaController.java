package camping.design;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class VyhladatPozemokPodlaKriteriaController {

    @FXML
    private TextField cisloPozemkuTextField;

    @FXML
    private ChoiceBox<String> kategoriaPozemkuChoiceBox;

    @FXML
    private TextField cenaPozemkuTextField;

    @FXML
    private CheckBox obsadenostPozemkuCheckBox;

    @FXML
    private Button vyhladatPozemokButton;

    private KategoriaFxModel kategoriaModel = new KategoriaFxModel();
    private PozemokFxModel pozemokModel = new PozemokFxModel();

    @FXML
    void initialize() {
        kategoriaPozemkuChoiceBox.setItems(FXCollections.observableArrayList(kategoriaModel.getNazvy()));

        vyhladatPozemokButton.setOnAction(eh -> {

        });
    }
    
    
}
