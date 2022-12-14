package controlador;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;
import modelo.Categoria;

public class BotonCategoriaController implements Initializable {

    @FXML
    private Text txtTexto;

    private Categoria categoria;
    private Consumer<Categoria> accion;

    public BotonCategoriaController(Categoria categoria, Consumer<Categoria> accion) {
        this.categoria = categoria;
        this.accion = accion;

    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        txtTexto.setText(categoria.getNombre());
    }

    @FXML
    private void abrir() {
        accion.accept(categoria);
    }

}