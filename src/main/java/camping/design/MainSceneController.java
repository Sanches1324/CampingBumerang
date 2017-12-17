package camping.design;

import camping.dao.CampingDaoFactory;
import camping.dao.HesloDao;
import camping.entities.Heslo;
import java.net.URL;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import org.springframework.security.crypto.bcrypt.BCrypt;

public class MainSceneController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private VBox vBoxPane;

    @FXML
    private AnchorPane parentPane;

    @FXML
    private Label hesloLabel;

    @FXML
    private PasswordField hesloPouzivatelaPasswordField;

    @FXML
    private Button OKButton;

    @FXML
    private Button bossButton;

    @FXML
    private Button userButton;

    @FXML
    private Button customerButton;

    @FXML
    private Label typUseraLabel;

    @FXML
    private Button zmenitHesloButton;

    private String uzivatel;
    private HesloFxModel hesloModel;
    private boolean administrator = false;
    private boolean zamestnanec = false;

    @FXML
    void initialize() {

        bossButton.setOnAction(eh -> {
            typUseraLabel.setText("Zadajte heslo administratora");
            if (zamestnanec) {
                zamestnanec = false;
            }
            administrator = true;
            hesloPouzivatelaPasswordField.setText("");
        });

        userButton.setOnAction(eh -> {
            typUseraLabel.setText("Zadajte heslo zamestnanca");
            if (administrator) {
                administrator = false;
            }
            zamestnanec = true;
            hesloPouzivatelaPasswordField.setText("");

        });
        OKButton.setDefaultButton(true);
        OKButton.setOnAction(eh -> {
            if (!administrator && !zamestnanec) {
                typUseraLabel.setText("Zvolte typ pouzivatela!");
            }

            String hash = "";
            String sol = "";
            if (administrator) {
                HesloDao hesloDao = CampingDaoFactory.INSTANCE.getMySqlHesloDao();
                hesloModel = hesloDao.findByUzivatel("Administrator");

                hash = hesloModel.getHeslo();
                String heslo = hesloPouzivatelaPasswordField.getText();
                if (BCrypt.checkpw(heslo, hash)) {
                    try {
                        FXMLLoader loader = new FXMLLoader(
                                getClass().getResource("AdminScene.fxml"));
                        Parent parentPane = loader.load();
                        Scene scene = new Scene(parentPane);

                        Stage stage = new Stage();
                        Image logo = new Image("camping\\styles\\logo.png");
                        stage.setScene(scene);
                        stage.setTitle("Camping Bumerang");
                        stage.getIcons().add(logo);
                        customerButton.getScene().getWindow().hide();
                        stage.show();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Zlé zadané heslo!", ButtonType.OK);
                    Optional<ButtonType> result = alert.showAndWait();
                }
            } else if (zamestnanec) {
                HesloDao hesloDao = CampingDaoFactory.INSTANCE.getMySqlHesloDao();
                hesloModel = hesloDao.findByUzivatel("Zamestnanec");
                hash = hesloModel.getHeslo();
                String heslo = hesloPouzivatelaPasswordField.getText();
                if (BCrypt.checkpw(heslo, hash)) {
                    try {
                        FXMLLoader loader = new FXMLLoader(
                                getClass().getResource("ZamestnanecScene.fxml"));
                        Parent parentPane = loader.load();
                        Scene scene = new Scene(parentPane);

                        Stage stage = new Stage();
                        Image logo = new Image("camping\\styles\\logo.png");
                        stage.setScene(scene);
                        stage.setTitle("Camping Bumerang");
                        stage.getIcons().add(logo);
                        customerButton.getScene().getWindow().hide();
                        stage.show();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Zlé zadané heslo!", ButtonType.OK);
                    Optional<ButtonType> result = alert.showAndWait();
                }
            }

        });

        customerButton.setOnAction(eh -> {
            try {
                FXMLLoader loader = new FXMLLoader(
                        getClass().getResource("ZakaznikScene.fxml"));
                Parent parentPane = loader.load();
                Scene scene = new Scene(parentPane);

                Stage stage = new Stage();
                Image logo = new Image("camping\\styles\\logo.png");
                stage.setScene(scene);
                stage.setTitle("Camping Bumerang");
                stage.getIcons().add(logo);
                customerButton.getScene().getWindow().hide();
                stage.show();

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        zmenitHesloButton.setOnAction(eh -> {
            try {
                FXMLLoader loader = new FXMLLoader(
                        getClass().getResource("HesloEditScene.fxml"));
                Parent parentPane = loader.load();
                Scene scene = new Scene(parentPane);

                Stage stage = new Stage();
                Image logo = new Image("camping\\styles\\logo.png");
                stage.setScene(scene);
                stage.setTitle("Camping Bumerang");
                stage.getIcons().add(logo);
                HesloEditSceneController controller = loader.<HesloEditSceneController>getController();
                if (administrator) {
                    controller.initialize("Administrator");
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.showAndWait();
                } else if (zamestnanec) {
                    controller.initialize("Zamestnanec");
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.showAndWait();
                } else {
                    Alert alert = new Alert(Alert.AlertType.WARNING, "Vyberte použivateľa, ktorému chcete zmeniť heslo", ButtonType.OK);
                    Optional<ButtonType> result = alert.showAndWait();

                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }

        });

    }
}
