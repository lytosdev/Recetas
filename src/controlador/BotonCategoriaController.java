package controlador;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;

public class BotonCategoriaController implements Initializable {

    @FXML
    public Text texto;

    private Consumer<String> accion;

    public BotonCategoriaController(Consumer<String> accion) {
        this.accion = accion;
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

    }

    @FXML
    private void abrir() {
        System.out.println("Cargar categoría");
        accion.accept(texto.getText());
    }

}