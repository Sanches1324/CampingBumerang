package camping.design;

import com.sun.javafx.iio.ImageLoader;
import com.sun.javafx.scene.control.skin.DatePickerSkin;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class ZakaznikSceneController {

    @FXML
    private AnchorPane zakaznikAnchorPane;

    @FXML
    private Button prepniUzivatelaButton;

    @FXML
    private FlowPane pozemkyFlowPane;

    private PozemokFxModel pozemokModel = new PozemokFxModel();
    private ObservableList<PozemokFxModel> pozemky = FXCollections.observableArrayList(pozemokModel.getPozemky());

    @FXML
    void initialize() {
        prepniUzivatelaButton.setOnAction(eh -> {
            try {
                FXMLLoader loader = new FXMLLoader(
                        getClass().getResource("MainScene.fxml"));
                Parent parentPane = loader.load();
                Scene scene = new Scene(parentPane);

                Stage stage = new Stage();
                Image logo = new Image("camping\\styles\\logo.png");
                stage.setScene(scene);
                stage.setTitle("Camping Bumerang");
                stage.getIcons().add(logo);
                prepniUzivatelaButton.getScene().getWindow().hide();
                stage.show();

            } catch (IOException ex) {
                Logger.getLogger(ZakaznikSceneController.class.getName()).log(Level.SEVERE, null, ex);
            }

        });
        
        pozemkyFlowPane.setVgap(8);
        pozemkyFlowPane.setHgap(4);
        vytvorPozemok(pozemky);

    }

    private void vytvorPozemok(List<PozemokFxModel> pozemok) {
        if (pozemok.size() > 0) {
            Collections.sort(pozemok, new Comparator<PozemokFxModel>() {
                @Override
                public int compare(PozemokFxModel lp, PozemokFxModel rp) {
                    return (int) (lp.getCisloPozemku() - rp.getCisloPozemku());
                }
            });
            for (PozemokFxModel pozemok1 : pozemok) {
                String cislo = Objects.toString(pozemok1.getCisloPozemku(), null);
                Button button = new Button(cislo);
                button.setMinWidth(30);
                button.setMinHeight(10);
                button.setId("id" + pozemok1.getCisloPozemku() + "Button");
                if (pozemok1.getObsadenostBoolean().equals("voľný")) {
                    button.setStyle("-fx-background-color: #04B404;");
                } else {
                    button.setStyle("-fx-background-color: #FF0000;");
                }
                button.setOnAction(eh -> {
                    try {
                        Stage stage = new Stage();
                        Image foto = new Image("camping\\styles\\PozadieMainScene.jpg");
                        ImageView view = new ImageView(foto);
                        StackPane sp = new StackPane();
                        sp.getChildren().add(view);

                        Image logo = new Image("camping\\styles\\logo.png");
                        Scene scene = new Scene(sp);
                        stage.setScene(scene);
                        stage.getIcons().add(logo);
                        stage.setMaxHeight(700);
                        stage.setMaxWidth(900);
                        stage.centerOnScreen();
                        stage.show();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
                pozemkyFlowPane.getChildren().add(button);
            }
        }
    }
}
