package camping.design;

import camping.dao.CampingDaoFactory;
import camping.dao.HesloDao;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import camping.dao.MySqlHesloDao;
import camping.entities.Heslo;
import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javax.swing.JOptionPane;
import org.springframework.security.crypto.bcrypt.BCrypt;

public class HesloEditSceneController {

    private MySqlHesloDao hesloDB;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label zadajPovodneHesloLabel;

    @FXML
    private PasswordField povodneHesloPasswordField;

    @FXML
    private Button potvrdZmenuHeslaButton;

    @FXML
    private Label zadajNoveHesloLabel;

    @FXML
    private PasswordField noveHesloPasswordField;

    private HesloFxModel hesloModel = new HesloFxModel();

    public HesloEditSceneController() {
    }

    @FXML
    void initialize(String uzivatel) {

        potvrdZmenuHeslaButton.setOnAction(eh -> {
            String hash = "";
            HesloDao hesloDao = CampingDaoFactory.INSTANCE.getMySqlHesloDao();
            hesloModel = hesloDao.findByUzivatel(uzivatel);

            hash = hesloModel.getHeslo();
            String heslo = povodneHesloPasswordField.getText();
            if (BCrypt.checkpw(heslo, hash)) {
                HesloFxModel noveHeslo = new HesloFxModel();
                noveHeslo.setUzivatel(uzivatel);
                if (noveHesloPasswordField.getText().equals("")) {
                    Alert alert = new Alert(Alert.AlertType.WARNING, "Zadajte nové heslo", ButtonType.OK);
                    Optional<ButtonType> result = alert.showAndWait();
                } else {
                    noveHeslo.setHeslo(noveHesloPasswordField.getText());
                    hesloDao.updateHeslo(noveHeslo);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Heslo bolo zmenené", ButtonType.OK);
                    Optional<ButtonType> result = alert.showAndWait();
                    potvrdZmenuHeslaButton.getScene().getWindow().hide();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Zle zadané pôvodné heslo", ButtonType.OK);
                Optional<ButtonType> result = alert.showAndWait();

            }

        });
    }
}
