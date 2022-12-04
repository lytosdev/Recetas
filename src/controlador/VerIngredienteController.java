package controlador;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;

public class VerIngredienteController implements Initializable {

    @FXML
    private Text txtIngrediente;

    private String ingrediente;

    public VerIngredienteController(String ingrediente) {
        this.ingrediente = ingrediente;
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        txtIngrediente.setText(ingrediente);
    }

}