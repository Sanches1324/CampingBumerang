package camping.design;

import camping.dao.CampingDaoFactory;
import camping.dao.PouzivatelDao;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.util.StringConverter;

public class PridatZamestnancaSceneController {

    @FXML
    private JFXTextField menoTextField;

    @FXML
    private JFXTextField prihlMenoTextField;

    @FXML
    private JFXDatePicker datumNarDatePicker;

    @FXML
    private JFXTextField adresaTextField;

    @FXML
    private JFXTextField telCisloTextField;

    @FXML
    private JFXTextField eMailTextField;

    @FXML
    private JFXPasswordField hesloPasswordField;

//    @FXML
//    private JFXPasswordField ptvHesloPasswordField;
    @FXML
    private JFXButton pridatButton;

    @FXML
    void pridat(ActionEvent event) {
        if (menoTextField.getText().equals("") || prihlMenoTextField.getText().equals("") || datumNarDatePicker.getValue() == null || adresaTextField.getText().equals("") || telCisloTextField.getText().equals("") || eMailTextField.getText().equals("") || hesloPasswordField.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Vyplňte všetky udaje", ButtonType.OK);
            Optional<ButtonType> result = alert.showAndWait();
        } else {
            PouzivatelFxModel novy = new PouzivatelFxModel();
            novy.setMeno(menoTextField.getText());
            novy.setPozicia("user");
            novy.setPrihl_Meno(prihlMenoTextField.getText());
            novy.setDatumNarodenia(datumNarDatePicker.getValue());
            novy.setAdresa(adresaTextField.getText());
            novy.setTel_cislo(telCisloTextField.getText());
            novy.setE_mail(eMailTextField.getText());
            novy.setHeslo(hesloPasswordField.getText());

            PouzivatelDao dao = CampingDaoFactory.INSTANCE.getMySqlPouzivatelDao();
            int pocet = dao.getAll().size();
            dao.createPouzivatela(novy);
            int pocet2 = dao.getAll().size();
            System.out.println(pocet + "         " + pocet2);
            if (pocet2 == pocet + 1) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Registrácia prebehla úspešne", ButtonType.OK);
                Optional<ButtonType> result = alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Pri registácií sa vyskytla chyba", ButtonType.OK);
                Optional<ButtonType> result = alert.showAndWait();
            }
            menoTextField.getScene().getWindow().hide();
        }

    }

    @FXML
    void initialize() {
        StringConverter converter = new StringConverter<LocalDate>() {
            DateTimeFormatter dateFormatter
                    = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            @Override
            public String toString(LocalDate localDate) {
                if (localDate == null) {
                    return "";
                }
                return dateFormatter.format(localDate);
            }

            @Override
            public LocalDate fromString(String dateString) {
                if (dateString == null || dateString.trim().isEmpty()) {
                    return null;
                }
                return LocalDate.parse(dateString, dateFormatter);
            }

        };
        datumNarDatePicker.setConverter(converter);
    }
}
