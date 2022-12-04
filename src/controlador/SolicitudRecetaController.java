package controlador;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.function.Consumer;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class SolicitudRecetaController implements Initializable {

    @FXML
    private Label lblFecha;
    @FXML
    private Label lblEmail;

    private Consumer<SolicitudRecetaController> abrirReceta;
    private LocalDate fecha;
    private String email;

    public SolicitudRecetaController() {

    }

    public SolicitudRecetaController(Consumer<SolicitudRecetaController> abrirReceta, LocalDate fecha, String correo) {
        this.abrirReceta = abrirReceta;
        this.fecha = fecha;
        this.email = correo;
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        lblFecha.setText(fecha.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        lblEmail.setText(email);
    }

    @FXML
    private void solicitudRecetaClick() {
        abrirReceta.accept(this);
    }

}