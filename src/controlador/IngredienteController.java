package controlador;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class IngredienteController implements Initializable {

    private Consumer<IngredienteController> accion;

    public IngredienteController(Consumer<IngredienteController> accion) {
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