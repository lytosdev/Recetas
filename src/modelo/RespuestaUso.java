package modelo;

public class RespuestaUso<T> {

	public final static int EXITO = 1;
	public final static int ADVERTENCIA = 2;
	public final static int ERROR = 3;
	
	private String mensaje;
	private int tipo;
	private T objeto;

	public RespuestaUso(String mensaje, int tipo) {
		this.mensaje = mensaje;
		this.tipo = tipo;

	}

	public RespuestaUso(String mensaje, int tipo, T objeto) {
		this.mensaje = mensaje;
		this.tipo = tipo;
		this.objeto = objeto;

	}

	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public int getTipo() {
		return tipo;
	}
	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public T getObjeto() {
		return objeto;
	}
	public void setObjeto(T objeto) {
		this.objeto = objeto;
	}

}