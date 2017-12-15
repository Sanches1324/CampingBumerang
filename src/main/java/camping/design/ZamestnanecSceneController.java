package camping.design;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ZamestnanecSceneController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Font x1;

    @FXML
    private Color x2;

    @FXML
    private Button spravujFinancieButton;

    @FXML
    private Button prepniUzivatelaButton;

    @FXML
    private Button spravujObjednavkyButton;

    @FXML
    private ListView<?> udajeOPozemkuListView;

    @FXML
    private Font x3;

    @FXML
    private Color x4;

    @FXML
    void initialize() {
        spravujObjednavkyButton.setOnAction(eh -> {
            ObjednavkaEditSceneController controller = new ObjednavkaEditSceneController();
            showObjednavkaEditWindow(controller);
        });
        prepniUzivatelaButton.setOnAction(eh -> {
            MainSceneController controller = new MainSceneController();
            showMainWindow(controller);
        });
    }

    private void showObjednavkaEditWindow(ObjednavkaEditSceneController controller) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("ObjednavkaEditScene.fxml"));
            loader.setController(controller);

            Parent parentPane = loader.load();
            Scene scene = new Scene(parentPane);

            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Správa objednávok");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        } catch (IOException iOException) {
            iOException.printStackTrace();
        }
    }

    private void showMainWindow(MainSceneController controller) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("MainScene.fxml"));
            loader.setController(controller);

            Parent parentPane = loader.load();
            Scene scene = new Scene(parentPane);

            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Camping Bumerang: prihlásenie");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        } catch (IOException iOException) {
            iOException.printStackTrace();
        }
    }
}
