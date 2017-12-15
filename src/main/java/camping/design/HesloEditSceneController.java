package camping.design;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import camping.dao.MySqlHesloDao;
import camping.entities.Heslo;
import javax.swing.JOptionPane;

public class HesloEditSceneController {

    /*
    private PouzivatelFxModel uzivatel = new PouzivatelFxModel();
*/
    private HesloFxModel hesloModel;
    
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

    @FXML
    void initialize() {
        //TODO
        potvrdZmenuHeslaButton.setOnAction(eh -> {
            //treba do okna aj uzivatela ak to robíme týmto spôsobom
            //zhoduje sa stare heslo s heslom uzivatela?
            //-----findByUzivatel vracia list------
            if (povodneHesloPasswordField.toString().equals(hesloDB.findByUzivatel(""))) {
                //ak ano robim update
                //CHYBA-updateHeslo chce Heslo pričom Heslo trieda musí obs.viacere
                //položky, my vkladame string
               // hesloDB.updateHeslo(noveHesloPasswordField.toString());
                JOptionPane.showMessageDialog(null, "Heslo obnovené.");
            } else {
                JOptionPane.showMessageDialog(null, "Nesprávne pôvodné heslo používateľa!");
            }
            //ak nie vyhodim chybu
        });
    }
}
