package juego;
import java.awt.Color;
import java.util.Random;

import entorno.Entorno;
import entorno.Herramientas;

public class Edificio {
	private double x;
	private double y;
	private double ancho;
	private double alto;
	private Color color;

	private java.awt.Image edif;;
	
	public Edificio (double x, double y, double ancho, double alto, Color color) {
		this.x = x;
		this.y = y;
		this.ancho = ancho;
		this.alto = alto;
		this.color = color;
		this.edif = Herramientas.cargarImagen ("techo.png");
	}
	
	public void dibujar (Entorno e) {
		e.dibujarRectangulo(this.x, this.y, this.ancho, this.alto, 0, this.color);
	}
	
	public void dibujarEdificio (Entorno e) {
		e.dibujarImagen (this.edif, this.x, this.y, 0, 0.14);
	}

	int balazoEdificio (pistola[] p) {
		for (int j = 0; j < p.length; j++) {
			if (p[j] != null) {   //ES PARA VALIDAR SI EN CADA POSICION TENGA UN OBJETO PISTOLA (BALA).
				pistola tiro = p[j];
				
				if (tiro.getX() >= (this.x - this.ancho / 2) && tiro.getX() <= (this.x + this.ancho / 2) && 
						tiro.getY() >= (this.y - this.alto / 2) && tiro.getY() <= (this.y + this.alto / 2)) {
					
					return j;
				}
			}
		}
		return -1;
	}
	
	int rebotaArania (Arania[] a) {
		for (int i = 0; i < a.length; i++) {
			if (a[i] != null) {   //ES PARA VALIDAR SI EN CADA POSICION TENGA UN OBJETO PISTOLA (BALA).
				Arania spider = a[i];
				
				if (this.x > (spider.getX() - spider.getDiametro()/2) && this.x < (spider.getX() + spider.getDiametro()/2) && 
						this.y > (spider.getY() + spider.getDiametro()/2) && this.y < (spider.getY() + spider.getDiametro()/2)) {
					return i;
				}
			}
		}
		return -1;
	}
	
	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getAncho() {
		return ancho;
	}

	public void setAncho(double ancho) {
		this.ancho = ancho;
	}

	public double getAlto() {
		return alto;
	}

	public void setAlto(double alto) {
		this.alto = alto;
	}
}
