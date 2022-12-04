package controlador;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import modelo.Receta;
import tools.Vista;

public class PrevisualRecetaController implements Initializable {

    @FXML
    private Pane pnlFoto;
    @FXML
    private Label lblTitulo;
    @FXML
    private Label lblDescripcion;

    private Receta receta;

    public PrevisualRecetaController() {

    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        // imageView = Vista.getImageView(Receta.alturaFoto, Receta.anchuraFoto, 0.1,
        // 0.1, 0, 0);
        // imageView.setImage(new Image("./vista/img/comida.jpg"));

        Image foto = new Image("./vista/img/comida.jpg", Receta.anchuraFoto * 1.2, Receta.alturaFoto * 1.2, true, true);
        Pane paneFoto = Vista.getPane(foto, Receta.alturaFoto, Receta.anchuraFoto, 0.1, 0.1, 0, 0);
        pnlFoto.getChildren().add(paneFoto);

    }

    @FXML
    private void ver() {

        GestorVistas gestorVistas = PrincipalController.gestorVistas;
        String uri = "/vista/VerReceta.fxml";

        gestorVistas.cerrarVista(uri);

        PropsVista propsVista = gestorVistas.abrirVista(uri, "Detalle receta");
        VerRecetaController verReceta = (VerRecetaController) propsVista.getControlador();
        verReceta.cargar(receta);

    }

    public void cargar(Receta receta) {
        this.receta = receta;

        Pane foto = Vista.getPane(receta.getImagen(), Receta.alturaFoto, Receta.anchuraFoto, 0.1, 0.1, 0, 0);

        pnlFoto.getChildren().clear();
        pnlFoto.getChildren().add(foto);
        lblTitulo.setText(receta.getTitulo());
        lblDescripcion.setText(receta.getDescripcion());

    }

}