package controlador;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;

public class VerPasoController implements Initializable {

    @FXML
    private Text txtNumPaso;
    @FXML
    private Text txtPaso;

    private int numPaso;
    private String paso;

    public VerPasoController(int numPaso, String paso) {
        this.numPaso = numPaso;
        this.paso = paso;

    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        txtNumPaso.setText("Paso " + numPaso);
        txtPaso.setText(paso);

    }

}