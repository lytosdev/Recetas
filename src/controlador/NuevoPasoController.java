package controlador;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;

public class NuevoPasoController implements Initializable {

    @FXML
    public Text txtNumPaso;
    
    private Consumer<NuevoPasoController> accion;

    public NuevoPasoController(Consumer<NuevoPasoController> accion) {
        this.accion = accion;
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

    }

    @FXML
    private void borrar() {
        accion.accept(this);
    }

}