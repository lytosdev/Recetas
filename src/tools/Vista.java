package tools;

import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcTo;
import javafx.scene.shape.HLineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.VLineTo;

public class Vista {

  public static ImageView getImage(int altura, int anchura, double topLeft, double topRight, double bottomLeft, double bottomRight) {

    ImageView imageView = new ImageView();
    imageView.setFitHeight(altura);
    imageView.setFitWidth(anchura);
    imageView.setClip(getClip(imageView, topLeft, topRight, bottomLeft, bottomRight));

    return imageView;
  }

  private static Path getClip(ImageView imageView, double topLeft, double topRight, double bottomLeft, double bottomRight) {
    Path clip;

    double height = imageView.getFitHeight();
    double width = imageView.getFitWidth();
    double radius1 = height * topLeft;
    double radius2 = height * topRight;
    double radius3 = height * bottomLeft;
    double radius4 = height * bottomRight;

    clip = new Path(
        new MoveTo(0, radius1),
        new ArcTo(radius1, radius1, 0, radius1, 0, false, true),
        new HLineTo(width - radius2),
        new ArcTo(radius2, radius2, 0, width, radius2, false, true),
        new VLineTo(height - radius4),
        new ArcTo(radius4, radius4, 0, width - radius4, height, false, true),
        new HLineTo(radius3),
        new ArcTo(radius3, radius3, 0, 0, height - radius3, false, true));

    clip.setFill(Color.RED);

    return clip;
  }

}
