package camping.design;

import camping.dao.CampingDaoFactory;
import camping.dao.ObjednavkaDao;
import camping.dao.PozemokDao;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXDrawersStack;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.transitions.hamburger.HamburgerSlideCloseTransition;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AdminSceneController {

    @FXML
    private AnchorPane mainAnchorPane;

    @FXML
    private JFXButton spravujFinancieButton;

    @FXML
    private JFXButton spravujObjednavkyButton;

    @FXML
    private JFXButton prepniUzivatelaButton;

    @FXML
    private JFXButton pridajPozemokButton;

    @FXML
    private TableView<PozemokFxModel> pozemkyTableView;

    @FXML
    private TableColumn<PozemokFxModel, Long> cisloPozemkuColumn;

    @FXML
    private TableColumn<PozemokFxModel, String> kategoriaPozemkuColumn;

    @FXML
    private TableColumn<PozemokFxModel, Integer> cenaPozemkuColumn;

    @FXML
    private TableColumn<PozemokFxModel, String> obsadenostPozemkuColumn;

    @FXML
    private VBox allVBox;

    @FXML
    private FlowPane pozemkyFlowPane;

    @FXML
    private JFXTextField hladatPozemokTextField;

    @FXML
    private JFXButton vymazatPozemokButton;

    @FXML
    private JFXButton pridatPouzivatelaButton;

    @FXML
    private JFXHamburger adminHamburger;

//    @FXML
//    private JFXDrawer adminDrawer;
    @FXML
    private JFXDrawersStack adminDrawersStack;

//    @FXML
//    private StackPane adminStackPane;
    private PozemokFxModel pozemokModel = new PozemokFxModel();
    private ObservableList<PozemokFxModel> pozemky = FXCollections.observableArrayList(pozemokModel.getPozemky());
    private PozemokDao pozemokDao = CampingDaoFactory.INSTANCE.getMySqlPozemokDao();
    private ObjednavkaDao objednavkaDao = CampingDaoFactory.INSTANCE.getMySqlObjednavkaDao();

    @FXML
    void pridatPouzivatela(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("ZamestnanecEditScene.fxml"));
            Parent parentPane = loader.load();
            Scene scene = new Scene(parentPane);

            Stage stage = new Stage();
            Image logo = new Image("camping\\styles\\logo.png");
            stage.setScene(scene);
            stage.setTitle("Camping Bumerang");
            stage.getIcons().add(logo);
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();

        } catch (IOException ex) {
            Logger.getLogger(ZakaznikSceneController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void prepnutUzivatelaAction(ActionEvent event) {
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
            stage.setMinHeight(450);
            stage.setMinWidth(690);
            prepniUzivatelaButton.getScene().getWindow().hide();
            stage.show();

        } catch (IOException ex) {
            Logger.getLogger(ZakaznikSceneController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void otvoritObjednavky(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("ObjednavkaEditScene.fxml"));
            Parent parentPane = loader.load();
            Scene scene = new Scene(parentPane);

            Stage stage = new Stage();
            Image logo = new Image("camping\\styles\\logo.png");
            stage.setScene(scene);
            stage.setTitle("Camping Bumerang");
            stage.getIcons().add(logo);
            stage.setMinHeight(500);
            stage.setMinWidth(820);

            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    void pridatPozemokAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("PridatPozemokScene.fxml"));
            Parent parentPane = loader.load();
            Scene scene = new Scene(parentPane);

            Stage stage = new Stage();
            Image logo = new Image("camping\\styles\\logo.png");
            stage.setScene(scene);
            stage.setTitle("Camping Bumerang");
            stage.getIcons().add(logo);
            stage.setMinHeight(90);
            stage.setMinWidth(540);
            stage.setResizable(false);
            stage.setOnHidden(eh -> {
                ObservableList<PozemokFxModel> pozemky = FXCollections.observableArrayList(pozemokDao.getAll());
                Collections.sort(pozemky, new Comparator<PozemokFxModel>() {
                    @Override
                    public int compare(PozemokFxModel lp, PozemokFxModel rp) {
                        return (int) (lp.getCisloPozemku() - rp.getCisloPozemku());
                    }
                });
                pozemkyFlowPane.getChildren().clear();
                vytvorPozemok(pozemky);
                zoradPozemky(pozemky);
            });
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    @FXML
    void vymazPozemok(ActionEvent event) {
        try {
            if (pozemkyTableView.getSelectionModel().getSelectedIndex() == -1) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Nie je vybratý žiadný pozemok", ButtonType.OK);
                Optional<ButtonType> result = alert.showAndWait();
            } else {
                int index = pozemkyTableView.getSelectionModel().getSelectedIndex();
                Long vymazat = cisloPozemkuColumn.getCellData(index);
                PozemokDao pozemokDao = CampingDaoFactory.INSTANCE.getMySqlPozemokDao();
                pozemokDao.deletePozemokByCisloPozemku(vymazat);
                ObservableList<PozemokFxModel> pozemky = FXCollections.observableArrayList(pozemokDao.getAll());
                Collections.sort(pozemky, new Comparator<PozemokFxModel>() {
                    @Override
                    public int compare(PozemokFxModel lp, PozemokFxModel rp) {
                        return (int) (lp.getCisloPozemku() - rp.getCisloPozemku());
                    }
                });
                pozemkyFlowPane.getChildren().clear();
                vytvorPozemok(pozemky);
                zoradPozemky(pozemky);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void zoradPozemky(ObservableList<PozemokFxModel> pozemky) {
        if (pozemky.size() > 0) {
            pozemkyTableView.setItems(pozemky);
        } else {
            System.out.println("V liste nie su pozemky");
        }

    }

    @FXML
    void vyhladatTextField(KeyEvent event) {
        hladatPozemokTextField.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (!"0123456789".contains(keyEvent.getCharacter())) {
                    keyEvent.consume();
                }
            }
        });
        PozemokFxModel pozemokModel = new PozemokFxModel();
        ObservableList<PozemokFxModel> pozemky = FXCollections.observableArrayList(pozemokModel.getPozemky());
        Collections.sort(pozemky, new Comparator<PozemokFxModel>() {
            @Override
            public int compare(PozemokFxModel lp, PozemokFxModel rp) {
                return (int) (lp.getCisloPozemku() - rp.getCisloPozemku());
            }
        });
        FilteredList<PozemokFxModel> filtrovanePozemky = new FilteredList<>(pozemky, p -> true);

        hladatPozemokTextField.textProperty()
                .addListener((observable, oldValue, newValue) -> {
                    filtrovanePozemky.setPredicate(pozemok -> {
                        if (newValue == null || newValue.isEmpty()) {
                            return true;
                        }
                        if (pozemok.getCisloPozemku() == Long.parseLong(newValue)) {
                            return true;
                        } else {
                            return false;
                        }
                    });
                }
                );

//        SortedList<PozemokFxModel> sortovanePozemky = new SortedList<>(filtrovanePozemky);
//        sortovanePozemky.comparatorProperty()
//                .bind(pozemkyTableView.comparatorProperty());
        pozemkyTableView.setItems(filtrovanePozemky);
    }

    @FXML
    void initialize() {
        cisloPozemkuColumn.setCellValueFactory(cellData -> cellData.getValue().cisloPozemkuProperty().asObject());
        kategoriaPozemkuColumn.setCellValueFactory(cellData -> cellData.getValue().kategoriaStringProperty());
        cenaPozemkuColumn.setCellValueFactory(cellData -> cellData.getValue().cenaProperty().asObject());
        obsadenostPozemkuColumn.setCellValueFactory(cellData -> cellData.getValue().obsadenostBooleanProperty());
        pozemkyTableView.setItems(pozemky);

        // dynamicke pridavanie Button-ov
        pozemkyFlowPane.setVgap(8);
        pozemkyFlowPane.setHgap(4);
        vytvorPozemok(pozemky);
        // menu
        JFXDrawer adminDrawer = new JFXDrawer();
        prepniUzivatelaButton.setButtonType(JFXButton.ButtonType.RAISED);
        pridajPozemokButton.setButtonType(JFXButton.ButtonType.RAISED);
        pridatPouzivatelaButton.setButtonType(JFXButton.ButtonType.RAISED);
        spravujObjednavkyButton.setButtonType(JFXButton.ButtonType.RAISED);

        prepniUzivatelaButton.setGraphic(new FontAwesomeIconView(FontAwesomeIcon.USERS));
        pridajPozemokButton.setGraphic(new FontAwesomeIconView(FontAwesomeIcon.PLUS_SQUARE_ALT));
        pridatPouzivatelaButton.setGraphic(new FontAwesomeIconView(FontAwesomeIcon.USER_PLUS));
        spravujObjednavkyButton.setGraphic(new FontAwesomeIconView(FontAwesomeIcon.BOOK));

        HBox box = new HBox(20, prepniUzivatelaButton, spravujObjednavkyButton, pridajPozemokButton, pridatPouzivatelaButton);
        StackPane adminDrawerPane = new StackPane(box);
        box.setAlignment(Pos.CENTER);
        adminDrawerPane.setStyle("-fx-background-color: #cccccc");
        adminDrawer.setDirection(JFXDrawer.DrawerDirection.BOTTOM);
        adminDrawer.setDefaultDrawerSize(200);
        adminDrawer.setSidePane(adminDrawerPane);
        adminDrawer.setResizeContent(true);
        adminDrawer.setOverLayVisible(false);
        adminDrawer.setResizableOnDrag(true);
        HamburgerSlideCloseTransition transition = new HamburgerSlideCloseTransition(adminHamburger);
        transition.setRate(-1);
        mainAnchorPane.addEventHandler(MouseEvent.MOUSE_CLICKED, eh -> {
            if (adminDrawer.isShown()) {
                transition.setRate(transition.getRate() * -1);
                transition.play();
                adminDrawersStack.toggle(adminDrawer, false);
            }
        });
        adminHamburger.addEventHandler(MouseEvent.MOUSE_CLICKED, eh -> {
            transition.setRate(transition.getRate() * -1);
            transition.play();
            if (adminDrawer.isShown()) {
                adminDrawersStack.toggle(adminDrawer, false);
            } else {
                adminDrawersStack.toggle(adminDrawer, true);
            }

        });

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
                JFXButton button = new JFXButton(cislo);
                button.setButtonType(JFXButton.ButtonType.RAISED);
                button.setRipplerFill(Paint.valueOf("#66e0ff"));
                button.setMinWidth(30);
                button.setMinHeight(10);
                button.setId("id" + pozemok1.getCisloPozemku() + "Button");
                if (pozemok1.getObsadenostBoolean().equals("voľný")) {
                    button.setStyle("-fx-background-color: #66ff66;");
                } else {
                    button.setStyle("-fx-background-color: #ff6666;");
                }
                button.setOnAction(eh -> {
                    try {
                        FXMLLoader loader = new FXMLLoader(
                                getClass().getResource("objednavkyPozemku.fxml"));
                        Parent parentPane = loader.load();
                        Scene scene = new Scene(parentPane);

                        Stage stage = new Stage();
                        Image logo = new Image("camping\\styles\\logo.png");
                        stage.setScene(scene);
                        stage.setTitle(pozemok1.getCisloPozemku() + "");
                        stage.getIcons().add(logo);
                        stage.setMinHeight(350);
                        stage.setMinWidth(560);
                        ObservableList<ObjednavkaFxModel> objednavkyPozemku = FXCollections.observableArrayList(objednavkaDao.findByPozemokId(pozemok1.getCisloPozemku()));
                        ObjednavkyPozemkuController controller
                                = loader.<ObjednavkyPozemkuController>getController();
                        controller.initialize(objednavkyPozemku);

                        stage.initModality(Modality.APPLICATION_MODAL);
                        stage.showAndWait();

                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                });
                pozemkyFlowPane.getChildren().add(button);
            }
        }
    }
}
