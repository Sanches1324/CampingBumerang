package camping.design;

import camping.dao.CampingDaoFactory;
import camping.dao.ObjednavkaDao;
import camping.dao.PouzivatelDao;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.util.StringConverter;
import javax.swing.JOptionPane;

public class ObjednavkaEditSceneController {

    @FXML
    private Label menoLabel;

    @FXML
    private TableView<ObjednavkaFxModel> objednavkyTableView;

    @FXML
    private TableColumn<ObjednavkaFxModel, Long> idTableColumn;

    @FXML
    private TableColumn<ObjednavkaFxModel, Long> pozemokIdTableColumn;

    @FXML
    private TableColumn<ObjednavkaFxModel, String> pouzivatelIdTableColumn;

    @FXML
    private TableColumn<ObjednavkaFxModel, String> menoZakaznikaColumn;

    @FXML
    private TableColumn<ObjednavkaFxModel, String> telCisloZakaznikaColumn;

    @FXML
    private TableColumn<ObjednavkaFxModel, LocalDate> datumObjednavkyTableColumn;

    @FXML
    private TableColumn<ObjednavkaFxModel, LocalDate> datumPrichoduTableColumn;

    @FXML
    private TableColumn<ObjednavkaFxModel, LocalDate> datumOdchoduTableColumn;

    @FXML
    private TableColumn<ObjednavkaFxModel, Long> pocetDniTableColumn;

    @FXML
    private TableColumn<ObjednavkaFxModel, String> platbaTableColumn;

    @FXML
    private ComboBox<Long> pozemkyComboBox;

    @FXML
    private TextField pocetDniTextField;

    @FXML
    private CheckBox platbaCheckBox;

    @FXML
    private DatePicker datumPrichoduDatePicker;

    @FXML
    private DatePicker datumOdchoduDatePicker;

    @FXML
    private TextField pozemokIdTextField;

    @FXML
    private Button hladatObjednavkuButton;

    @FXML
    private TextField menoZakaznikaTextField;

    @FXML
    private TextField telCisloTextField;

    @FXML
    private Button pridatObjednavkuButton;

    @FXML
    private Button vymazatObjednavkuButton;

    private PozemokFxModel pozemokModel = new PozemokFxModel();
    private PouzivatelFxModel pouzivatelModel = new PouzivatelFxModel();
    private ObjednavkaFxModel objednavkaModel = new ObjednavkaFxModel();
    private ObservableList<ObjednavkaFxModel> objednavky = objednavkaModel.getObjednavky();
    private ObservableList<PozemokFxModel> pozemky = pozemokModel.getPozemky();
    private ObservableList<PouzivatelFxModel> pouzivatelia = pouzivatelModel.getPouzivatelov();
    private ObjednavkaDao objednavkaDao = CampingDaoFactory.INSTANCE.getMySqlObjednavkaDao();
    private PouzivatelDao pouzivatelDao = CampingDaoFactory.INSTANCE.getMySqlPouzivatelDao();
    private ObservableList<PouzivatelFxModel> prihlasenyPouzivatel = FXCollections.observableArrayList(pouzivatelDao.findByPrihlasenie(true));

    @FXML
    void pridatObjednavku(ActionEvent event) {
        boolean pozemokID = false;
//        boolean pouzivatel = false;
        boolean datumOdchodu = false;
        boolean datumPrichodu = false;
        boolean pocetDni = false;
        boolean menoZak = false;
        boolean telZak = false;

        ObjednavkaFxModel objednavka = new ObjednavkaFxModel();
        if (pozemkyComboBox.getValue() == null) {
            pozemkyComboBox.setStyle("-fx-background-color: #FF0000;");
        } else {
            objednavka.setPozemokId(pozemkyComboBox.getValue());
            pozemokID = true;
        }
        objednavka.setPouzivatelId(prihlasenyPouzivatel.get(0).getId());

        objednavka.setDatumObjednavky(LocalDate.now());
        if (datumOdchoduDatePicker.getValue() == null) {
            datumOdchoduDatePicker.setStyle("-fx-background-color: #FF0000;");
        } else {
            objednavka.setDatumOdchodu(datumOdchoduDatePicker.getValue());
            datumOdchodu = true;
        }
        if (datumPrichoduDatePicker.getValue() == null || datumPrichoduDatePicker.getValue().isAfter(datumOdchoduDatePicker.getValue())) {
            datumPrichoduDatePicker.setStyle("-fx-background-color: #FF0000;");
        } else {
            objednavka.setDatumPrichodu(datumPrichoduDatePicker.getValue());
            datumPrichodu = true;
        }

        if (pocetDniTextField.getText().equals("")) {
            pocetDniTextField.setStyle("-fx-background-color: #FF0000;");
        } else {
            objednavka.setPocetDni(Long.parseLong(pocetDniTextField.getText()));
            pocetDni = true;
        }
        if (platbaCheckBox.selectedProperty().getValue()) {
            objednavka.setPlatba(true);
            objednavka.setPlatbaString("zaplatené");
        } else {
            objednavka.setPlatba(false);
            objednavka.setPlatbaString("nezaplatené");
        }
        if (menoZakaznikaTextField.getText().equals("")) {
            menoZakaznikaTextField.setStyle("-fx-background-color: #FF0000;");
        } else {
            objednavka.setMenoZakaznika(menoZakaznikaTextField.getText());
            menoZak = true;
        }
        if (telCisloTextField.getText().equals("")) {
            telCisloTextField.setStyle("-fx-background-color: #FF0000;");
        } else {
            objednavka.setTelCisloZakaznika(telCisloTextField.getText());
            telZak = true;
        }
        if (pozemokID && datumOdchodu && datumPrichodu && pocetDni && menoZak && telZak) {
            pozemkyComboBox.setStyle("");
            datumOdchoduDatePicker.setStyle("");
            datumPrichoduDatePicker.setStyle("");
            pocetDniTextField.setStyle("");
            menoZakaznikaTextField.setStyle("");
            telCisloTextField.setStyle("");

            pozemkyComboBox.getSelectionModel().clearSelection();
            datumOdchoduDatePicker.setValue(null);
            datumPrichoduDatePicker.setValue(null);
            pocetDniTextField.setText("");
            menoZakaznikaTextField.setText("");
            telCisloTextField.setText("");

            objednavkaDao = CampingDaoFactory.INSTANCE.getMySqlObjednavkaDao();
            objednavkaDao.createObjednavku(objednavka);
            ObservableList<ObjednavkaFxModel> noveObjednavky = FXCollections.observableArrayList(objednavkaDao.getAll());
            Collections.sort(noveObjednavky, new Comparator<ObjednavkaFxModel>() {
                @Override
                public int compare(ObjednavkaFxModel lp, ObjednavkaFxModel rp) {
                    return (int) (lp.getPozemokId() - rp.getPozemokId());
                }
            });
            objednavkyTableView.setItems(noveObjednavky);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Vyplňte všetky udaje", ButtonType.OK);
            Optional<ButtonType> result = alert.showAndWait();
        }
    }

    @FXML
    void vymazatObjednavku(ActionEvent event
    ) {
        try {
            if (objednavkyTableView.getSelectionModel().getSelectedIndex() == -1) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Nie je vybratý žiadná objednavka", ButtonType.OK);
                Optional<ButtonType> result = alert.showAndWait();
            } else {
                int index = objednavkyTableView.getSelectionModel().getSelectedIndex();
                Long vymazat = idTableColumn.getCellData(index);
                ObjednavkaDao objednavkaDao = CampingDaoFactory.INSTANCE.getMySqlObjednavkaDao();
                objednavkaDao.deleteObjednavku(vymazat);
                ObservableList<ObjednavkaFxModel> noveObjednavky = FXCollections.observableArrayList(objednavkaDao.getAll());
                Collections.sort(noveObjednavky, new Comparator<ObjednavkaFxModel>() {
                    @Override
                    public int compare(ObjednavkaFxModel lp, ObjednavkaFxModel rp) {
                        return (int) (lp.getPozemokId() - rp.getPozemokId());
                    }
                });

                objednavkyTableView.setItems(noveObjednavky);
            }
        } catch (Exception e) {
            System.out.println("Chyba nacitania z DB" + e);
            e.printStackTrace();
        }
    }

    @FXML
    void vyhladatTextField(KeyEvent event
    ) {
        pozemokIdTextField.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (!"0123456789".contains(keyEvent.getCharacter())) {
                    keyEvent.consume();
                }
            }
        });
        ObjednavkaFxModel objednavkaModel = new ObjednavkaFxModel();
        ObservableList<ObjednavkaFxModel> objednavky = objednavkaModel.getObjednavky();
        Collections.sort(objednavky, new Comparator<ObjednavkaFxModel>() {
            @Override
            public int compare(ObjednavkaFxModel lp, ObjednavkaFxModel rp) {
                return (int) (lp.getPozemokId() - rp.getPozemokId());
            }
        });
        FilteredList<ObjednavkaFxModel> filtrovaneObjednavky = new FilteredList<>(objednavky, p -> true);

        pozemokIdTextField.textProperty()
                .addListener((observable, oldValue, newValue) -> {
                    filtrovaneObjednavky.setPredicate(objednavka -> {
                        if (newValue == null || newValue.isEmpty()) {
                            return true;
                        }
                        if (objednavka.getPozemokId() == Long.parseLong(newValue)) {
                            return true;
                        } else {
                            return false;
                        }
                    });
                }
                );
//        SortedList<ObjednavkaFxModel> sortovanePozemky = new SortedList<>(filtrovaneObjednavky);
//        sortovanePozemky.comparatorProperty()
//                .bind(objednavkyTableView.comparatorProperty());
        objednavkyTableView.setItems(filtrovaneObjednavky);

//        objednavkyTableView.sort();
    }

    @FXML
    void initialize() {
        idTableColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        pozemokIdTableColumn.setCellValueFactory(cellData -> cellData.getValue().pozemokIdProperty().asObject());
        pouzivatelIdTableColumn.setCellValueFactory(cellData -> cellData.getValue().menoPouzivatelaProperty());
        menoZakaznikaColumn.setCellValueFactory(cellData -> cellData.getValue().menoZakaznikaProperty());
        telCisloZakaznikaColumn.setCellValueFactory(cellData -> cellData.getValue().telCisloZakaznikaProperty());
        datumObjednavkyTableColumn.setCellValueFactory(cellData -> cellData.getValue().datumObjednavkyProperty());
        datumPrichoduTableColumn.setCellValueFactory(cellData -> cellData.getValue().datumPrichoduProperty());
        datumOdchoduTableColumn.setCellValueFactory(cellData -> cellData.getValue().datumOdchoduProperty());
        pocetDniTableColumn.setCellValueFactory(cellData -> cellData.getValue().pocetDniProperty().asObject());
        platbaTableColumn.setCellValueFactory(cellData -> cellData.getValue().platbaStringProperty());

        menoLabel.setText(prihlasenyPouzivatel.get(0).getMeno());
        Collections.sort(objednavky, new Comparator<ObjednavkaFxModel>() {
            @Override
            public int compare(ObjednavkaFxModel lp, ObjednavkaFxModel rp) {
                return (int) (lp.getPozemokId() - rp.getPozemokId());
            }
        });
        objednavkyTableView.setItems(objednavky);

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
        datumPrichoduDatePicker.setConverter(converter);
        datumOdchoduDatePicker.setConverter(converter);
        //pozemkyComboBox pridavanie poloziek
        ObservableList<Long> idcka = FXCollections.observableArrayList();
        for (PozemokFxModel pozemokFxModel : pozemky) {
            idcka.add(pozemokFxModel.getCisloPozemku());
        }
        Collections.sort(idcka);
        pozemkyComboBox.setItems(idcka);

//        ObservableList<String> menoPouzivatelov = FXCollections.observableArrayList();
//        for (PouzivatelFxModel pouzivatelFxModel : pouzivatelia) {
//            menoPouzivatelov.add(pouzivatelFxModel.getMeno());
//        }
//        pouzivatelComboBox.setItems(menoPouzivatelov);
        pocetDniTextField.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (!"0123456789".contains(keyEvent.getCharacter())) {
                    keyEvent.consume();
                }
            }
        });
    }
}
