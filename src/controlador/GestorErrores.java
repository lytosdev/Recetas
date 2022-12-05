package controlador;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputControl;

public class GestorErrores {

  private List<Item> items = new ArrayList<>();

  public void suscribir(TextInputControl textInput, Label textError, TipoError[] tiposError) {

    items.add(new Item(textInput, textError, tiposError));
    textInput.textProperty().addListener(this::textChange);
  }

  public void suscribir(TextInputControl textInput, Label textError, TipoError[] tiposError, int longitud) {

    items.add(new Item(textInput, textError, tiposError, longitud));
    textInput.textProperty().addListener(this::textChange);
  }

  public void desuscribir(TextInputControl textInput) {

    textInput.textProperty().removeListener(this::textChange);
    items.removeIf(x -> x.textInput.equals(textInput));
  }

  public boolean hayErrores() {

    checkErrores();

    for (Item item : items) {
      if (item.mensajesError.size() > 0) {
        return true;
      }
    }

    return false;
  }

  private void checkErrores() {

    for (Item item : items) {
      check(item.textInput);
    }
  }

  private void textChange(ObservableValue<? extends String> observable, String oldValue, String newValue) {

    StringProperty stringProperty = (StringProperty) observable;
    TextInputControl textInput = (TextInputControl) stringProperty.getBean();
    check(textInput);
  }

  private void check(TextInputControl textInput) {

    Item item = items.stream().filter(x -> x.textInput.equals(textInput)).findFirst().get();
    item.clearErrores();
    String text = textInput.getText();
    if (text == null) {
      text = "";
    }

    for (TipoError tipo : item.tiposError) {

      switch (tipo) {
        case LONGITUD:
          if (!validLongitud(text, item.longitud)) {
            item.addError("Máximo " + item.longitud + " caracteres");
          }
          break;
        case EMAIL:
          if (!validEmail(text)) {
            item.addError("Email inválido");
          }
          break;
        case SOLO_NUMEROS:
          if (!validSoloNumeros(text)) {
            item.addError("Número inválido");
          }
          break;
        case NO_SOLO_NUMEROS:
          if (!validNoSoloNumeros(text)) {
            item.addError("Texto inválido");
          }
          break;
        case NO_VACIO:
          if (!validNoVacio(text)) {
            item.addError("Campo requerido");
          }
          break;
      }

    }

    String msjError = item.mensajesError.size() > 0 ? item.mensajesError.get(0) : "";
    item.textError.setText(msjError);
    if (msjError.isEmpty()) {
      textInput.getStyleClass().removeAll("inp-error");
    } else {
      textInput.getStyleClass().add("inp-error");
    }

  }

  private boolean validLongitud(String text, int longitud) {

    if (text.length() > longitud) {
      return false;
    }

    return true;
  }

  private boolean validEmail(String text) {

    Pattern pattern = Pattern.compile("^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,4})+$");
    Matcher matcher = pattern.matcher(text);
    boolean matchFound = matcher.find();

    if (!matchFound) {
      return false;
    }

    return true;
  }

  private boolean validSoloNumeros(String text) {

    Pattern pattern = Pattern.compile("^[0-9]+$");
    Matcher matcher = pattern.matcher(text);
    boolean matchFound = matcher.find();

    if (!matchFound) {
      return false;
    }

    return true;
  }

  private boolean validNoSoloNumeros(String text) {

    Pattern pattern = Pattern.compile("^[^A-Za-z]*[A-Za-z][ -~]*$");
    Matcher matcher = pattern.matcher(text);
    boolean matchFound = matcher.find();

    if (!matchFound) {
      return false;
    }

    return true;
  }

  private boolean validNoVacio(String text) {

    if (text.isBlank()) {
      return false;
    }

    return true;
  }

  private class Item {

    TextInputControl textInput;
    Label textError;
    TipoError[] tiposError;
    int longitud = 5;
    List<String> mensajesError = new ArrayList<>();

    Item(TextInputControl textInput, Label text, TipoError[] tiposError) {
      this.textInput = textInput;
      this.textError = text;
      this.tiposError = tiposError;
    }

    Item(TextInputControl textInput, Label text, TipoError[] tiposError, int longitud) {
      this.textInput = textInput;
      this.textError = text;
      this.tiposError = tiposError;
      this.longitud = longitud;
    }

    void addError(String error) {
      mensajesError.add(error);
    }

    void clearErrores() {
      mensajesError.clear();
    }

  }

  public enum TipoError {
    LONGITUD,
    SOLO_NUMEROS,
    NO_SOLO_NUMEROS,
    EMAIL,
    NO_VACIO
  }

}