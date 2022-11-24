package modelo;

import javafx.scene.image.Image;

public class Receta {

  private Image imagen;
  private String titulo;
  private String descripcion;
  private String categoria;
  private String dificultad;
  private int duracion;
  private int personas;
  private Ingrediente[] ingredientes;
  private String[] utensilios;
  private String[] pasos;

  public Receta(Image imagen, String titulo, String descripcion, String categoria, String dificultad,
      int duracion, int personas, Ingrediente[] ingredientes, String[] utensilios, String[] pasos) {
    this.imagen = imagen;
    this.titulo = titulo;
    this.descripcion = descripcion;
    this.categoria = categoria;
    this.dificultad = dificultad;
    this.duracion = duracion;
    this.personas = personas;
    this.ingredientes = ingredientes;
    this.utensilios = utensilios;
    this.pasos = pasos;

  }

  public Image getImagen() {
    return imagen;
  }

  public String getTitulo() {
    return titulo;
  }

  public String getDescripcion() {
    return descripcion;
  }

  public String getCategoria() {
    return categoria;
  }

  public String getDificultad() {
    return dificultad;
  }

  public int getDuracion() {
    return duracion;
  }

  public int getPersonas() {
    return personas;
  }

  public Ingrediente[] getIngredientes() {
    return ingredientes;
  }

  public String[] getUtensilios() {
    return utensilios;
  }

  public String[] getPasos() {
    return pasos;
  }

}
