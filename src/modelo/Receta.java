package modelo;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

public class Receta {

  public static enum Estado {
    VALIDA,
    NO_VALIDA,
    PTE_VALIDAR
  }

  public static int alturaFoto = 180;
  public static int anchuraFoto = 250;

  private int id;
  private String titulo;
  private String descripcion;
  private Image imagenJfx;
  private int duracion;
  private int personas;
  private int estado;
  private String dificultad;
  private LocalDate fecha;
  private String autor;
  private int idCategoria;

  public Receta() {

  }

  public Receta(String titulo, String descripcion, Image imagenJfx, int duracion, int personas, int estado,
      String dificultad, LocalDate fecha, String autor, int idCategoria) {
    this.titulo = titulo;
    this.descripcion = descripcion;
    this.imagenJfx = imagenJfx;
    this.duracion = duracion;
    this.personas = personas;
    this.estado = estado;
    this.dificultad = dificultad;
    this.fecha = fecha;
    this.autor = autor;
    this.idCategoria = idCategoria;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getTitulo() {
    return titulo;
  }

  public void setTitulo(String titulo) {
    this.titulo = titulo;
  }

  public String getDescripcion() {
    return descripcion;
  }

  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }

  public byte[] getImagen() {

    BufferedImage bi = SwingFXUtils.fromFXImage(imagenJfx, null);
    ByteArrayOutputStream os = new ByteArrayOutputStream();
    try {
      ImageIO.write(bi, "jpeg", os);
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    return os.toByteArray();
  }

  public void setImagen(byte[] imagen) {
    this.imagenJfx = new Image(new ByteArrayInputStream(imagen));
  }

  public Image getImagenJfx() {
    return imagenJfx;
  }

  public void setImagenJfx(Image imagenJfx) {
    this.imagenJfx = imagenJfx;
  }

  public int getDuracion() {
    return duracion;
  }

  public void setDuracion(int duracion) {
    this.duracion = duracion;
  }

  public int getPersonas() {
    return personas;
  }

  public void setPersonas(int personas) {
    this.personas = personas;
  }

  public int getEstado() {
    return estado;
  }

  public void setEstado(int estado) {
    this.estado = estado;
  }

  public String getDificultad() {
    return dificultad;
  }

  public void setDificultad(String dificultad) {
    this.dificultad = dificultad;
  }

  public LocalDate getFecha() {
    return fecha;
  }

  public void setFecha(LocalDate fecha) {
    this.fecha = fecha;
  }

  public String getAutor() {
    return autor;
  }

  public void setAutor(String autor) {
    this.autor = autor;
  }

  public int getIdCategoria() {
    return idCategoria;
  }

  public void setIdCategoria(int idCategoria) {
    this.idCategoria = idCategoria;
  }

}
