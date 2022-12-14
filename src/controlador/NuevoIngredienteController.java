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
import modelo.Ingrediente;
import modelo.UnidadMedida;
import tools.Vista;

public class NuevoIngredienteController implements Initializable {

    @FXML
    private TextField inpCantidad;
    @FXML
    private Label lblErrorCantidad;
    @FXML
    private ComboBox<UnidadMedida> cbxUdMedida;
    @FXML
    private TextField inpNombre;
    @FXML
    private Label lblErrorNombre;

    private Consumer<Node> borrarIngrediente;
    private Ingrediente ingrediente;
    private GestorErrores gestorErrores;

    public NuevoIngredienteController(Consumer<Node> borrarIngrediente) {
        this.borrarIngrediente = borrarIngrediente;

    }

    public NuevoIngredienteController(Consumer<Node> borrarIngrediente, Ingrediente ingrediente) {
        this.borrarIngrediente = borrarIngrediente;
        this.ingrediente = ingrediente;

    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        // Cargamos combobox con las unidades de medida
        cbxUdMedida.getItems().addAll(PrincipalController.udsMedida);

        lblErrorCantidad.setText("");
        lblErrorNombre.setText("");

        // Si tenemos un ingrediente para abrir lo cargamos
        if (ingrediente != null) {
            inpCantidad.setText(Integer.toString(ingrediente.getCantidad()));
            UnidadMedida udMedida = PrincipalController.udsMedida.stream()
                    .filter(x -> x.getId() == ingrediente.getIdUdMedida()).findFirst().get();
            cbxUdMedida.getSelectionModel().select(udMedida);
            inpNombre.setText(ingrediente.getNombre());
        } else {
            cbxUdMedida.getSelectionModel().selectFirst();
        }

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
                new TipoError[] { TipoError.NO_VACIO, TipoError.NO_SOLO_NUMEROS, TipoError.LONGITUD }, 100);

    }

    private void desuscribirErrores() {

        if (gestorErrores != null) {
            gestorErrores.desuscribir(inpCantidad);
            gestorErrores.desuscribir(inpNombre);
        }

    }

}