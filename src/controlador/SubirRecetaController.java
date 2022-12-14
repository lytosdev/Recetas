package controlador;

import java.net.URL;
import java.util.ResourceBundle;

import controlador.GestorErrores.TipoError;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import tools.Vista;

public class SubirRecetaController implements Initializable {

    @FXML
    private HBox pnlCabeza;
    @FXML
    private VBox pnlCuerpo;
    @FXML
    private TextField inpEmail;
    @FXML
    private Label lblErrorEmail;

    private GestorErrores gestorErrores = new GestorErrores();

    public SubirRecetaController() {

    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        lblErrorEmail.setText("");

        // Cargamos la cabeza de la nueva receta
        Object[] compCabeza = GestorVistas.cargarVista("/vista/NuevaRecetaCabeza.fxml");
        Node nodeCabeza = (Node) compCabeza[0];
        NuevaRecetaCabezaController nuevaRecetaCabeza = (NuevaRecetaCabezaController) compCabeza[1];
        pnlCabeza.getChildren().add(nodeCabeza);
        nuevaRecetaCabeza.suscribirErrores(gestorErrores);

        // Cargamos el cuerpo de la nueva receta
        Object[] compCuerpo = GestorVistas.cargarVista("/vista/NuevaRecetaCuerpo.fxml");
        Node nodeCuerpo = (Node) compCuerpo[0];
        NuevaRecetaCuerpoController nuevaRecetaCuerpo = (NuevaRecetaCuerpoController) compCuerpo[1];
        pnlCuerpo.getChildren().add(nodeCuerpo);
        nuevaRecetaCuerpo.suscribirErrores(gestorErrores);

        gestorErrores.suscribir(inpEmail, lblErrorEmail, new TipoError[] { TipoError.NO_VACIO, TipoError.EMAIL });

    }

    @FXML
    private void enviar() {

        //Email.enviar();
        if (gestorErrores.hayErrores()) {
            Vista.alertError("No puede enviar la receta porque hay errores en el formulario");
        } else {

        }

    }

}