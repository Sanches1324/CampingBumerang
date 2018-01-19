package camping.design;

import camping.dao.CampingDaoFactory;
import camping.dao.ObjednavkaDao;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.StringConverter;

public class ObjednavkyPozemkuController {

    @FXML
    private AnchorPane parentAP;

    @FXML
    private TableView<ObjednavkaFxModel> objednavkyPozemkuTableView;

    @FXML
    private TableColumn<ObjednavkaFxModel, LocalDate> odTableColumn;

    @FXML
    private TableColumn<ObjednavkaFxModel, LocalDate> doTableColumn;

    @FXML
    private TableColumn<ObjednavkaFxModel, Long> pocetDniTableColumn;

    @FXML
    private TableColumn<ObjednavkaFxModel, String> platbaTableColumn;

    @FXML
    private TableColumn<ObjednavkaFxModel, String> menoZakTableColumn;

    @FXML
    private TableColumn<ObjednavkaFxModel, String> telCisloTableColumn;

    @FXML
    private DatePicker odDatePicker;

    private ObjednavkaFxModel objednavkaModel = new ObjednavkaFxModel();
    private ObjednavkaDao objednavkaDao = CampingDaoFactory.INSTANCE.getMySqlObjednavkaDao();
    private ObservableList<ObjednavkaFxModel> objednavky = FXCollections.observableArrayList(objednavkaDao.getAll());
    private Scene scene;

    @FXML
    void initialize(ObservableList<ObjednavkaFxModel> objednavky) {
        objednavkyPozemkuTableView.setItems(objednavky);
        odTableColumn.setCellValueFactory(cellData -> cellData.getValue().datumPrichoduProperty());
        doTableColumn.setCellValueFactory(cellData -> cellData.getValue().datumOdchoduProperty());
        pocetDniTableColumn.setCellValueFactory(cellData -> cellData.getValue().pocetDniProperty().asObject());
        platbaTableColumn.setCellValueFactory(cellData -> cellData.getValue().platbaStringProperty());
        menoZakTableColumn.setCellValueFactory(cellData -> cellData.getValue().menoZakaznikaProperty());
        telCisloTableColumn.setCellValueFactory(cellData -> cellData.getValue().telCisloZakaznikaProperty());
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
        odDatePicker.setConverter(converter);
        FilteredList<ObjednavkaFxModel> filtrovaneObjednavky = new FilteredList<>(objednavky, p -> true);
        odDatePicker.valueProperty().addListener((observable, oldValue, newValue) -> {
            filtrovaneObjednavky.setPredicate(datum -> {
                if (newValue == null) {
                    return true;
                }

                if (datum.getDatumPrichodu().equals(newValue)) {
                    return true;
                } else {
                    return false;
                }
            });
        });
//        SortedList<ObjednavkaFxModel> sortedData = new SortedList<>(filtrovaneObjednavky);
//        sortedData.comparatorProperty().bind(objednavkyPozemkuTableView.comparatorProperty());
        objednavkyPozemkuTableView.setItems(filtrovaneObjednavky);

    }
}
