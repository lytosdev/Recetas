package controlador;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;

import controlador.GestorErrores.TipoError;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import tools.Vista;

public class NuevoIngredienteController implements Initializable {

    @FXML
    private TextField inpCantidad;
    @FXML
    private Label lblErrorCantidad;
    @FXML
    private ComboBox<KeyValuePair> cbxUdMedida;
    @FXML
    private TextField inpNombre;
    @FXML
    private Label lblErrorNombre;

    private Consumer<Node> borrarIngrediente;
    private int cantidad;
    private String udMedida;
    private String nombre;
    private GestorErrores gestorErrores;

    public NuevoIngredienteController(Consumer<Node> borrarIngrediente) {
        this.borrarIngrediente = borrarIngrediente;
    }

    public NuevoIngredienteController(Consumer<Node> borrarIngrediente, int cantidad, String udMedida,
            String nombre) {
        this.borrarIngrediente = borrarIngrediente;
        this.cantidad = cantidad;
        this.udMedida = udMedida;
        this.nombre = nombre;
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        inpCantidad.setText(cantidad == 0 ? "" : Integer.toString(cantidad));
        lblErrorCantidad.setText("");
        // cbxUdMedida.setText(udMedida);
        inpNombre.setText(nombre);
        lblErrorNombre.setText("");

        cbxUdMedida.getItems().add(new KeyValuePair("1", "A"));
        cbxUdMedida.getItems().add(new KeyValuePair("2", "B"));
        cbxUdMedida.getItems().add(new KeyValuePair("3", "C"));
    }

    @FXML
    private void borrar(ActionEvent event) {

        desuscribirErrores();

        Node node = (Node) event.getSource();
        borrarIngrediente.accept(Vista.buscarPadre(node, "nuevoIngrediente"));
    }

    public void suscribirErrores(GestorErrores gestorErrores) {
        this.gestorErrores = gestorErrores;

        gestorErrores.suscribir(inpCantidad, lblErrorCantidad,
                new TipoError[] { TipoError.NO_VACIO, TipoError.SOLO_NUMEROS, TipoError.LONGITUD });
        gestorErrores.suscribir(inpNombre, lblErrorNombre,
                new TipoError[] { TipoError.NO_VACIO, TipoError.SOLO_TEXTO, TipoError.LONGITUD }, 100);
    }

    private void desuscribirErrores() {
        if (gestorErrores != null) {
            gestorErrores.desuscribir(inpCantidad);
            gestorErrores.desuscribir(inpNombre);
        }
    }

    public class KeyValuePair {
        private final String key;
        private final String value;

        public KeyValuePair(String key, String value) {
            this.key = key;
            this.value = value;
        }

        public String getKey() {
            return key;
        }

        public String toString() {
            return value;
        }
    }
}