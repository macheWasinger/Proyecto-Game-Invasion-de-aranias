package juego;

import java.awt.Color;
import java.util.Random;

import entorno.Entorno;
import entorno.Herramientas;

public class MunicionExtra {
	private double x;
	private double y;
	private double angulo;
	private double diametro;
	private Color color;
	private boolean apareceMunicionExtra;
	private java.awt.Image municionExtra;
	
	public MunicionExtra (double x, double y, double angulo, double diametro, double velocidad, Color color) {
		this.x = x;
		this.y = y;
		this.angulo = angulo;
		this.diametro = diametro;
		this.color = color;
		this.apareceMunicionExtra = true;
		this.municionExtra = Herramientas.cargarImagen ("municionesExtra.png");
	}
	

	public void dibujarMunicion (Entorno e){  //DIBUJA OBJETO CIRCULO
		e.dibujarCirculo(this.x, this.y, this.diametro, Color.GREEN);
	}
	
	public void dibujarMunicionExtra (Entorno e) {  //DIBUJA IMAGEN DE LAS MUNICIONES
		if (this.apareceMunicionExtra) {
			e.dibujarImagen (this.municionExtra, this.x, this.y, 0, 0.09);			
		}
		
		else {
			return;
		}
	}
	
	public void desapareceMunicion() {
		this.apareceMunicionExtra = false;
	}
	
	public void cambiarAngulo(){
		this.angulo = this.angulo + Math.PI/2;
	}
	
	int seSuperponeEdificio (Edificio[] e) {
		for (int i = 0; i < e.length; i++) {
			if (e[i] != null) {   //ES PARA VALIDAR SI EN CADA POSICION TENGA UN OBJETO PISTOLA (BALA).
				Edificio torre = e[i];
				
				if ((this.x + this.diametro/2) >= (torre.getX() - torre.getAncho()/2) && (this.x - this.diametro/2) <= (torre.getX() + torre.getAncho()/2) && 
						(this.y + this.diametro/2) >= (torre.getY() - torre.getAlto()/2) && (this.y - this.diametro/2) <= (torre.getY() + torre.getAlto()/2)) {
					return i;
				}
			}
			
		}
		return -1;
	}
	
	public void separarDelEdificio() {
		this.x = this.x + 50;
		this.y = this.y + 50;
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

	public double getAngulo() {
		return angulo;
	}

	public void setAngulo(double angulo) {
		this.angulo = angulo;
	}

	public double getDiametro() {
		return diametro;
	}

	public void setDiametro(double diametro) {
		this.diametro = diametro;
	}
}
