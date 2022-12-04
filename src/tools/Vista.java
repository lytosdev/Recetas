package tools;

import java.security.Principal;
import java.util.Optional;

import controlador.PrincipalController;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcTo;
import javafx.scene.shape.HLineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.VLineTo;
import javafx.stage.StageStyle;

public class Vista {

  public static ImageView getImageView(int altura, int anchura, double topLeft, double topRight, double bottomLeft,
      double bottomRight) {

    ImageView imageView = new ImageView();
    imageView.setFitHeight(altura);
    imageView.setFitWidth(anchura);
    imageView.setClip(getClip(altura, anchura, topLeft, topRight, bottomLeft, bottomRight));

    return imageView;
  }

  public static Pane getPane(int altura, int anchura, double topLeft, double topRight, double bottomLeft,
      double bottomRight) {

    Pane pane = new Pane();
    pane.setPrefHeight(altura);
    pane.setPrefWidth(anchura);
    pane.setClip(getClip(altura, anchura, topLeft, topRight, bottomLeft, bottomRight));
    return pane;
  }

  public static Pane getPane(Image image, int altura, int anchura, double topLeft, double topRight, double bottomLeft,
      double bottomRight) {

    Pane pane = Vista.getPane(altura, anchura, topLeft, topRight, bottomLeft, bottomRight);

    BackgroundRepeat bgRep = BackgroundRepeat.NO_REPEAT;
    BackgroundPosition bgPos = BackgroundPosition.DEFAULT;
    BackgroundSize bgSize = BackgroundSize.DEFAULT;
    BackgroundImage bgImage = new BackgroundImage(image, bgRep, bgRep, bgPos, bgSize);
    Background bg = new Background(bgImage);
    pane.setBackground(bg);

    return pane;
  }

  private static Path getClip(double altura, double anchura, double topLeft, double topRight, double bottomLeft,
      double bottomRight) {
    Path clip;

    double radius1 = altura * topLeft;
    double radius2 = altura * topRight;
    double radius3 = altura * bottomLeft;
    double radius4 = altura * bottomRight;

    clip = new Path(
        new MoveTo(0, radius1),
        new ArcTo(radius1, radius1, 0, radius1, 0, false, true),
        new HLineTo(anchura - radius2),
        new ArcTo(radius2, radius2, 0, anchura, radius2, false, true),
        new VLineTo(altura - radius4),
        new ArcTo(radius4, radius4, 0, anchura - radius4, altura, false, true),
        new HLineTo(radius3),
        new ArcTo(radius3, radius3, 0, 0, altura - radius3, false, true));

    clip.setFill(Color.RED);

    return clip;
  }

  public static Node buscarPadre(Node node, String idPadre) {

    Node padre = node.getParent();

    while (!padre.getId().equals(idPadre)) {
      padre = padre.getParent();
    }

    if (padre.getId().equals(idPadre)) {
      return padre;
    }

    return null;
  }

  public static void alert(Alert.AlertType tipo, String titulo, String mensaje) {
		
		Alert alert = new Alert(tipo);
		alert.initStyle(StageStyle.UTILITY);
		
		alert.setHeaderText(null);
		alert.setTitle(titulo);
		alert.setContentText(mensaje);
	
    //setEstiloAlter(alert);
		alert.showAndWait();
	}
	
	public static void alertConfirmacion(String mensaje) {
		alertAceptar(AlertType.CONFIRMATION, "Confirmación", mensaje);
	}
	
	public static void alertAdvertencia(String mensaje) {
		alertAceptar(AlertType.WARNING, "Advertencia", mensaje);
		
	}
	
	public static void alertError(String mensaje) {
		alertAceptar(AlertType.ERROR, "Error", mensaje);
	}
	
	private static void alertAceptar(Alert.AlertType tipo, String titulo, String mensaje) {
		
		Alert alert = new Alert(tipo);
		alert.initStyle(StageStyle.UTILITY);
		
		alert.setHeaderText(null);
		alert.setTitle(titulo);
		alert.setContentText(mensaje);
		ButtonType boton = new ButtonType("Aceptar", ButtonData.APPLY);
		alert.getButtonTypes().setAll(boton);
		
    setEstiloAlter(alert);
		alert.showAndWait();
	}

  public static void alertCustom() {

    ButtonType foo = new ButtonType("foo", ButtonData.OK_DONE);
    ButtonType bar = new ButtonType("bar", ButtonData.CANCEL_CLOSE);

    Alert alert = new Alert(
        AlertType.WARNING,
        "The format for dates is year.month.day. For example, today is .",
        foo,
        bar);

    alert.setTitle("Date format warning");
    Optional<ButtonType> result = alert.showAndWait();

    if (result.orElse(bar) == foo) {

    }
  }

  private static void setEstiloAlter(Alert alert) {
    DialogPane dialogPane = alert.getDialogPane();
    dialogPane.getStylesheets().add("/vista/css/general.css");
    if (!PrincipalController.modo) {
      dialogPane.getStylesheets().add("/vista/css/modoClaro.css");
    } else {
      dialogPane.getStylesheets().add("/vista/css/modoOscuro.css");
    }
    dialogPane.getStylesheets().add("/vista/css/alert.css");
  }

}
