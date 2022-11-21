package controlador;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class NuevoIngredienteController implements Initializable {

    private Consumer<NuevoIngredienteController> accion;

    public NuevoIngredienteController(Consumer<NuevoIngredienteController> accion) {
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