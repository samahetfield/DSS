package controlador;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;


@ManagedBean(name = "controladorMotor", eager = true)
@SessionScoped
public class ControladorMotor implements Serializable {	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final int APAGADO = 0;
	private final int ENCENDIDO = 1;
	private final int ACELERANDO = 2;
	
	private String TEXTO_APAGADO = "Apagado";
	private String TEXTO_ENCENDIDO = "Encendido";
	private String TEXTO_ACELERANDO = "Acelerando";
	private String TEXTO_ENCENDER = "Encender";
	private String TEXTO_APAGAR = "Apagar";

	private int estado = APAGADO;
    private String textoTitulo;
	private String colorTitulo;
    private String textoBtnOnOff;
    private String colorBtnOnOff;
   	private String colorBtnAcelerar;
	
	public ControladorMotor() {
		System.out.println("Contructor controlador");
	}
	
	private void encender() {
		estado = ENCENDIDO;
		
		textoTitulo = TEXTO_ENCENDIDO;
		textoBtnOnOff = TEXTO_APAGAR;
		colorBtnOnOff = "red";
		
		colorTitulo = "green";
		colorBtnAcelerar = "black";
	}
	
	private void apagar() {
		estado = APAGADO;

		textoTitulo = TEXTO_APAGADO;
		textoBtnOnOff = TEXTO_ENCENDER;

		colorTitulo = "red";
		colorBtnOnOff = "green";
		colorBtnAcelerar = "grey";
	}

	private void acelerar() {
		estado = ACELERANDO;
		
		textoTitulo = TEXTO_ACELERANDO;
		
		colorTitulo = "blue";
	}
	
	public void actualizarBtnOnOff(ActionEvent e) {
		switch (estado) {
		case APAGADO:
			encender();
			break;
		case ENCENDIDO:
			apagar();
			break;
		case ACELERANDO:
			apagar();
			break;
		default:
			break;
		}
	}
	
	public void actualizarBtnAcelerar(ActionEvent e) {
		switch (estado) {
		case APAGADO:
			break;
		case ENCENDIDO:
			acelerar();
			break;
		case ACELERANDO:
			acelerar();
			break;
		default:
			break;
		}
	}
	
	public String getTextoTitulo() {
		if (textoTitulo != null) {
			return textoTitulo;
		} else {
			return TEXTO_APAGADO;
		}
	}

	public void setTextoTitulo(String textoTitulo) {
		this.textoTitulo = textoTitulo;
	}

	public String getColorTitulo() {
		if (colorTitulo != null) {
			return colorTitulo;
		} else {
			return "red";
		}
	}

	public void setColorTitulo(String colorTitulo) {
		this.colorTitulo = colorTitulo;
	}

	public String getTextoBtnOnOff() {
		if (textoBtnOnOff != null) {
			return textoBtnOnOff;
		} else {
			return TEXTO_ENCENDER;
		}
	}

	public void setTextoBtnOnOff(String textoBtnOnOff) {
		this.textoBtnOnOff = textoBtnOnOff;
	}

	public String getColorBtnOnOff() {
		if (colorBtnOnOff != null) {
			return colorBtnOnOff;
		} else {
			return "green";
		}
	}

	public void setColorBtnOnOff(String colorBtnOnOff) {
		this.colorBtnOnOff = colorBtnOnOff;
	}
	
	public String getColorBtnAcelerar() {
		if (colorBtnAcelerar != null) {
			return colorBtnAcelerar;
		} else {
			return "grey";
		}
	}

	public void setColorBtnAcelerar(String colorBtnAcelerar) {
		this.colorBtnAcelerar = colorBtnAcelerar;
	}
}