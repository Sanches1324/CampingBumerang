package camping.design;

import camping.dao.CampingDaoFactory;
import camping.dao.PouzivatelDao;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.util.StringConverter;

public class PridatZamestnancaSceneController {

    @FXML
    private JFXTextField menoTextField;

    @FXML
    private JFXTextField eMailTextField;

    @FXML
    private JFXTextField poziciaTextField;

    @FXML
    private JFXTextField adresaTextField;

    @FXML
    private JFXTextField telCisloTextField;

    @FXML
    private JFXDatePicker datumNarDatePicker;

    @FXML
    private JFXButton pridatButton;

    @FXML
    void pridat(ActionEvent event) {
        PouzivatelFxModel novy = new PouzivatelFxModel();
        novy.setMeno(menoTextField.getText());
        novy.setPozicia(poziciaTextField.getText());
        novy.setDatumNarodenia(datumNarDatePicker.getValue());
        novy.setAdresa(adresaTextField.getText());
        novy.setTel_cislo(telCisloTextField.getText());
        novy.setE_mail(eMailTextField.getText());

        PouzivatelDao dao = CampingDaoFactory.INSTANCE.getMySqlPouzivatelDao();
        dao.createPouzivatela(novy);
        menoTextField.getScene().getWindow().hide();
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
