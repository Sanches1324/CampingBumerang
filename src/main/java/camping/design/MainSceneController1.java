package camping.design;

import camping.dao.CampingDaoFactory;
import camping.dao.HesloDao;
import camping.dao.PouzivatelDao;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
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
import org.springframework.security.crypto.bcrypt.BCrypt;

public class MainSceneController1 {

    @FXML
    private VBox vBoxPane;

    @FXML
    private AnchorPane parentPane;

    @FXML
    private Label hesloLabel;

    @FXML
    private JFXTextField menoPouzivatelaTextField;

    @FXML
    private JFXPasswordField hesloPouzivatelaPasswordField;

    @FXML
    private Button OKButton;

    @FXML
    private Button customerButton;

    @FXML
    private Label typUseraLabel;

    @FXML
    private Button zmenitHesloButton;

    @FXML
    private JFXButton registraciaButton;

    @FXML
    void registrovat(ActionEvent event) {
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
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    private String uzivatel;
    private HesloFxModel hesloModel;
    private boolean administrator = false;
    private boolean zamestnanec = false;

    @FXML
    void initialize() {
        menoPouzivatelaTextField.setStyle("-fx-text-inner-color: white;");
        hesloPouzivatelaPasswordField.setStyle("-fx-text-inner-color: white;");

        OKButton.setDefaultButton(true);
        OKButton.setOnAction(eh -> {
            String menoPouzivatela = menoPouzivatelaTextField.getText();
            String heslo = hesloPouzivatelaPasswordField.getText();
            PouzivatelDao pouzivatelDao = CampingDaoFactory.INSTANCE.getMySqlPouzivatelDao();
            List<PouzivatelFxModel> pouzivatel = pouzivatelDao.findByMeno(menoPouzivatela);
            String pozicia = "";

            if (pouzivatel.size() > 0) {
                pozicia = pouzivatel.get(0).getPozicia();
                if (BCrypt.checkpw(heslo, pouzivatel.get(0).getHeslo())) {
                    if (pozicia.equals("boss")) {
                        try {
                            FXMLLoader loader = new FXMLLoader(
                                    getClass().getResource("AdminScene.fxml"));
                            Parent parentPane = loader.load();
                            Scene scene = new Scene(parentPane);

                            Stage stage = new Stage();
                            Image logo = new Image("camping\\styles\\logo.png");
                            stage.setScene(scene);
                            stage.setTitle("Admin");
                            stage.getIcons().add(logo);
                            stage.setMinHeight(450);
                            stage.setMinWidth(690);
                            customerButton.getScene().getWindow().hide();
                            stage.show();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    } else {
                        try {
                            FXMLLoader loader = new FXMLLoader(
                                    getClass().getResource("ZamestnanecScene.fxml"));
                            Parent parentPane = loader.load();
                            Scene scene = new Scene(parentPane);

                            Stage stage = new Stage();
                            Image logo = new Image("camping\\styles\\logo.png");
                            stage.setScene(scene);
                            stage.setTitle("Admin");
                            stage.getIcons().add(logo);
                            stage.setMinHeight(450);
                            stage.setMinWidth(690);
                            customerButton.getScene().getWindow().hide();
                            stage.show();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Zlé zadané heslo!", ButtonType.OK);
                    Optional<ButtonType> result = alert.showAndWait();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Nenašiel sa použivateľ " + menoPouzivatelaTextField.getText(), ButtonType.OK);
                Optional<ButtonType> result = alert.showAndWait();
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
                stage.setMinHeight(450);
                stage.setMinWidth(690);
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
                stage.setMinHeight(240);
                stage.setMinWidth(260);
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
