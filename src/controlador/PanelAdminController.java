package controlador;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import modelo.Categoria;
import modelo.Ingrediente;
import modelo.IngredienteUso;
import modelo.Paso;
import modelo.PasoUso;
import modelo.Receta;
import modelo.RecetaUso;
import modelo.Utensilio;
import modelo.UtensilioUso;

public class PanelAdminController implements Initializable {

    @FXML
    private VBox pnlSolicitudes;
    @FXML
    private HBox pnlCabeza;
    @FXML
    private VBox pnlCuerpo;
    @FXML
    private ComboBox<Categoria> cbxCategorias;

    private GestorErrores gestorErrores = new GestorErrores();
    private NuevaRecetaCabezaController controllerCabeza;
    private NuevaRecetaCuerpoController controllerCuerpo;

    public PanelAdminController() {

    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        // Cargamos combobox con las categoríaas
        cbxCategorias.getItems().addAll(PrincipalController.categorias);

        // Recetas pendiente de validar
        List<Receta> recetas = RecetaUso.selectPteValidar().getObjeto();

        // Cargamos las solicitudes
        for (Receta item : recetas) {
            Callback<Class<?>, Object> factory = (Class<?> clazz) -> new SolicitudRecetaController(this::abrirReceta,
                    item);
            Object[] comp = GestorVistas.cargarVista("/vista/SolicitudReceta.fxml", factory);
            pnlSolicitudes.getChildren().add((Node) comp[0]);
        }

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

        this.controllerCabeza = nuevaRecetaCabeza;
        this.controllerCuerpo = nuevaRecetaCuerpo;

    }

    private void abrirReceta(Receta receta) {

        Receta r = receta;
        List<Ingrediente> ingredientes = IngredienteUso.selectPorReceta(r.getId()).getObjeto();
        List<Utensilio> utensilios = UtensilioUso.selectPorReceta(r.getId()).getObjeto();
        List<Paso> pasos = PasoUso.selectPorReceta(r.getId()).getObjeto();

        // Cargamos la receta
        controllerCabeza.setRecetaCabeza(r.getImagenJfx(), r.getTitulo(), r.getDescripcion(), r.getDificultad(), r.getDuracion(),
                r.getPersonas());
        controllerCuerpo.setRecetaCuerpo(ingredientes, utensilios, pasos);

        // Cargamos la categoría
        Categoria categoria = PrincipalController.categorias.stream()
                .filter(x -> x.getId() == r.getIdCategoria()).findFirst().get();
        cbxCategorias.getSelectionModel().select(categoria);

    }

}