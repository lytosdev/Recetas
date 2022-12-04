package controlador;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;

import controlador.GestorErrores.TipoError;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import tools.Vista;

public class NuevoPasoController implements Initializable {

    @FXML
    private Text txtNumPaso;
    @FXML
    private TextArea inpPaso;
    @FXML
    private Label lblErrorPaso;

    private Consumer<Node> borrarPaso;
    private int numPaso;
    private String paso;
    private GestorErrores gestorErrores;

    public NuevoPasoController(Consumer<Node> borrarPaso) {
        this.borrarPaso = borrarPaso;
    }

    public NuevoPasoController(Consumer<Node> borrarPaso, int numPaso) {
        this.borrarPaso = borrarPaso;
        this.numPaso = numPaso;
    }

    public NuevoPasoController(Consumer<Node> borrarPaso, int numPaso, String paso) {
        this.borrarPaso = borrarPaso;
        this.numPaso = numPaso;
        this.paso = paso;
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        txtNumPaso.setText("Paso " + numPaso);
        inpPaso.setText(paso);
        lblErrorPaso.setText("");

    }

    @FXML
    private void borrar(ActionEvent event) {

        desuscribirErrores();
        
        Node node = (Node) event.getSource();
        borrarPaso.accept(Vista.buscarPadre(node, "nuevoPaso"));
    }

    public void suscribirErrores(GestorErrores gestorErrores) {
        this.gestorErrores = gestorErrores;

        gestorErrores.suscribir(inpPaso, lblErrorPaso,
                new TipoError[] { TipoError.NO_VACIO, TipoError.SOLO_TEXTO, TipoError.LONGITUD }, 300);
    }

    public void setPaso(int numPaso) {
        txtNumPaso.setText("Paso " + numPaso);
    }

    private void desuscribirErrores() {
        if (gestorErrores != null) {
            gestorErrores.desuscribir(inpPaso);
        }
    }

}