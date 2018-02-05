package camping.design;

import camping.dao.CampingDaoFactory;
import camping.dao.PouzivatelDao;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class PoziciaSceneController {

    @FXML
    private JFXComboBox<String> poziciaComboBox;

    @FXML
    private JFXButton ulozitButton;

    @FXML
    private Label menoLabel;

    @FXML
    private JFXCheckBox povolenyCheckBox;

    @FXML
    void initialize(ObservableList<PouzivatelFxModel> pouzivatel) {
        if (pouzivatel.size() > 0) {
            menoLabel.setText(pouzivatel.get(0).getMeno());
            poziciaComboBox.getSelectionModel().select(pouzivatel.get(0).getPozicia());
            if (pouzivatel.get(0).getPovoleny().equals("povolený")) {
                povolenyCheckBox.selectedProperty().setValue(true);
            } else {
                povolenyCheckBox.selectedProperty().setValue(false);
            }

        }
        ObservableList<String> pozicia = FXCollections.observableArrayList();
        pozicia.add("user");
        pozicia.add("boss");
        poziciaComboBox.setItems(pozicia);

        ulozitButton.setOnAction(eh -> {
            if (pouzivatel.size() > 0) {
                pouzivatel.get(0).setPozicia(poziciaComboBox.getValue());
                if (povolenyCheckBox.selectedProperty().getValue()) {
                    pouzivatel.get(0).setPovoleny("povolený");
                } else {
                    pouzivatel.get(0).setPovoleny("nepovolený");
                }
                PouzivatelDao dao = CampingDaoFactory.INSTANCE.getMySqlPouzivatelDao();
                dao.updatePouzivatela(pouzivatel.get(0));
                menoLabel.getScene().getWindow().hide();
            }

        });
    }

}
