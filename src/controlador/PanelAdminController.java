package controlador;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import modelo.Receta;
import modelo.RecetaUso;

public class PanelAdminController implements Initializable {

    @FXML
    private VBox pnlSolicitudes;
    @FXML
    private HBox pnlCabeza;
    @FXML
    private VBox pnlCuerpo;
    @FXML
    private ComboBox<String> cbxCategorias;

    private GestorErrores gestorErrores = new GestorErrores();
    private NuevaRecetaCabezaController controllerCabeza;
    private NuevaRecetaCuerpoController controllerCuerpo;

    public PanelAdminController() {

    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        // Cargamos ComboBox
        ObservableList<String> options = FXCollections.observableArrayList(
                "Option 1",
                "Option 2",
                "Option 3");
        cbxCategorias.getItems().addAll(options);

        // Cargamos las solicitudes
        for (int i = 0; i < 10; i++) {
            Callback<Class<?>, Object> factory = (Class<?> clazz) -> new SolicitudRecetaController(
                    this::abrirReceta, LocalDate.now(), "micorreo@gmail.com");
            Object[] arr = GestorVistas.cargarVista("/vista/SolicitudReceta.fxml", factory);
            pnlSolicitudes.getChildren().add((Node) arr[0]);
        }

        // Cargamos la cabeza de la nueva receta
        Object[] arrCabeza = GestorVistas.cargarVista("/vista/NuevaRecetaCabeza.fxml");
        Node nodeCabeza = (Node) arrCabeza[0];
        NuevaRecetaCabezaController controllerCabeza = (NuevaRecetaCabezaController) arrCabeza[1];
        pnlCabeza.getChildren().add(nodeCabeza);
        controllerCabeza.suscribirErrores(gestorErrores);

        // Cargamos el cuerpo de la nueva receta
        Object[] arrCuerpo = GestorVistas.cargarVista("/vista/NuevaRecetaCuerpo.fxml");
        Node nodeCuerpo = (Node) arrCuerpo[0];
        NuevaRecetaCuerpoController controllerCuerpo = (NuevaRecetaCuerpoController) arrCuerpo[1];
        pnlCuerpo.getChildren().add(nodeCuerpo);
        controllerCuerpo.suscribirErrores(gestorErrores);

        this.controllerCabeza = controllerCabeza;
        this.controllerCuerpo = controllerCuerpo;

    }

    private void abrirReceta(SolicitudRecetaController solicitudRecetaController) {

        // Cargamos la receta
        Receta r = RecetaUso.getRecetasJson().get(0);
        controllerCabeza.cargar(r.getImagen(), r.getTitulo(), r.getDescripcion(), r.getDificultad(), r.getDuracion(), r.getPersonas());
        controllerCuerpo.cargar(r.getIngredientes(), r.getUtensilios(), r.getPasos());

        // Cargamos la categoría

    }

}