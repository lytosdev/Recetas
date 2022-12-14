package controlador;

import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.function.Consumer;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import modelo.Receta;

public class SolicitudRecetaController implements Initializable {

    @FXML
    private Label lblFecha;
    @FXML
    private Label lblEstado;
    @FXML
    private Label lblTitulo;
    @FXML
    private Label lblEmail;

    private Consumer<Receta> abrirReceta;
    private Receta receta;

    public SolicitudRecetaController(Consumer<Receta> abrirReceta, Receta receta) {
        this.abrirReceta = abrirReceta;
        this.receta = receta;

    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        lblFecha.setText(receta.getFecha().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        String estado = "Estado incorrecto";
        if (receta.getEstado() == Receta.Estado.NO_VALIDA.ordinal()) {
            estado = "No válida";
        } else if (receta.getEstado() == Receta.Estado.PTE_VALIDAR.ordinal()) {
            estado = "Pendiente de validar";
        } else if (receta.getEstado() == Receta.Estado.VALIDA.ordinal()) {
            estado = "Válida";
        }
        lblEstado.setText(estado);
        lblTitulo.setText(receta.getTitulo());
        lblEmail.setText(receta.getAutor());

    }

    @FXML
    private void solicitudRecetaClick() {
        abrirReceta.accept(receta);
    }

}