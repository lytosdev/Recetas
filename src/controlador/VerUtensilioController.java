package controlador;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;

public class VerUtensilioController implements Initializable {

    @FXML 
    private Text txtUtensilio;

    private String utensilio;

    public VerUtensilioController(String utensilio) {
      this.utensilio = utensilio;
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        txtUtensilio.setText(utensilio);
    }

}