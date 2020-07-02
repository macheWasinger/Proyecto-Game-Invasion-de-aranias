package juego;

import java.awt.Color;
import java.awt.Font;

import javax.sound.sampled.Clip;

import entorno.Entorno;
import entorno.Herramientas;

public class Exterminador {
	private double x;
	private double y;
	private int altura;
	private int base;
	private double angulo;
	private double velocidad;
	private Color color;
	private boolean estaVivo;
	private boolean noSeDetiene;
	private boolean reproduceAudio;
	private double haPerdido_posX;
	private double haPerdido_posY;
	private int origenDonde_haPerdido;
	private int vidas_oportunidades;
	private java.awt.Image exterminador_Movimiento;
	private java.awt.Image exterminadorAtrapado;
	private java.awt.Image gameOver;
	private Clip muerte;
	
	public Exterminador (double x, double y, int altura, int base, double angulo, Color color) {
		this.x = x;
		this.y = y;
		this.altura = altura;
		this.base = base;
		this.angulo = angulo;
		this.velocidad = velocidad;
		this.color = color;
		this.estaVivo = true;
		this.noSeDetiene = true;
		this.reproduceAudio = true;
		this.vidas_oportunidades = 1;
		this.exterminador_Movimiento = Herramientas.cargarImagen ("exterminador1.png");
		this.exterminadorAtrapado = Herramientas.cargarImagen ("exterminadorAtrapado.png");
		this.gameOver = Herramientas.cargarImagen ("GameOver.gif");
		this.muerte = Herramientas.cargarSonido ("muerte.wav");
	}
	

	public void dibujar(Entorno e){
		e.dibujarTriangulo(this.x, this.y, this.altura, this.base, this.angulo, this.color);
	}
	
	public void dibujarImagenExterminador(Entorno e) {
		if (estaVivo) {
			e.dibujarImagen (this.exterminador_Movimiento, this.x, this.y, this.angulo, 0.19);
		}
		
		else {
			
			if (this.origenDonde_haPerdido == 1) { /*CUANDO ES IGUAL 1, QUIERE DECIR QUE EL EXTERMINADOR FUE ATRAPADO POR UNA ARAÑA */
				
				e.dibujarImagen (this.exterminadorAtrapado, this.haPerdido_posX, this.haPerdido_posY, this.angulo, 0.19);
				
				e.cambiarFont(Font.MONOSPACED + "bold", 30, Color.RED);
				e.escribirTexto("¡ATRAPADO! ", this.haPerdido_posX - 100, (this.haPerdido_posY - 60));	
			}
		}
	}
	
	public void haPerdido (int origenDonde_haPerdido, double haPerdido_posX, double haPerdido_posY) {
		this.origenDonde_haPerdido = origenDonde_haPerdido;
		this.haPerdido_posX = haPerdido_posX;
		this.haPerdido_posY = haPerdido_posY;
		this.estaVivo = false;
	}
	
	public void cargarSonidoMuerte() {
		if (reproduceAudio) {
			this.muerte.start();			
		}
		
		else {
			this.muerte.stop();
		}
	}
	
	public void detenerSonidoMuerte() {
		this.reproduceAudio = false;
	}
	
	public void mover(){
		if (noSeDetiene) {
			this.x = (this.x +(this.velocidad * Math.cos(this.angulo)));
			this.y = (this.y + (this.velocidad * Math.sin(this.angulo)));
		}
		
		else {
			this.frenar();
		}
	}
	
	public void seDetiene() {
		this.noSeDetiene = false;
	}
	
	public void imagenGameOver() {
		this.estaVivo = false;
	}
	
	public void frenar() {
		this.velocidad = 0;
	}
	
	public void cambiarAngulo(){
		this.angulo = this.angulo + Math.PI/2;
	}
	
	int pisaMina (Mina[] m) {
		for (int i = 0; i < m.length; i++) {
			if (m[i] != null) {   //ES PARA VALIDAR SI EN CADA POSICION TENGA UN OBJETO PISTOLA (BALA).
				Mina bombita = m[i];
				
				if (this.x >= (bombita.getX() - bombita.getDiametro()/2) && this.x <= (bombita.getX() + bombita.getDiametro()/2) && 
						this.y >= (bombita.getY() - bombita.getDiametro()/2) && this.y <= (bombita.getY() + bombita.getDiametro()/2)) {
					return i;
				}
			}
			
		}
		return -1;
	
	}
	
	int chocaArania (Arania[] a) {
		for (int i = 0; i < a.length; i++) {
			if (a[i] != null) {   //ES PARA VALIDAR SI EN CADA POSICION TENGA UN OBJETO PISTOLA (BALA).
				Arania spider = a[i];
				
				if (this.x + this.altura/2 >= (spider.getX() - spider.getDiametro()/2) && this.x - this.altura/2 <= (spider.getX() + spider.getDiametro()/2) && 
						this.y + this.altura/2 >= (spider.getY() - spider.getDiametro()/2) && this.y - this.altura/2 <= (spider.getY() + spider.getDiametro()/2)) {
					return i;
				}
			}
			
		}
		return -1;
	
	}
	
	
	public void avanzarExterm(Entorno e) {
		this.velocidad = 2.5;
		if (this.x >= e.ancho() - 30) {  //LE RESTAMOS 30 AL ANCHO PARA QUE SE FRENE EN 770 PORQUE SI ERA EN 800, SE SOBRESALIA UNA PARTE DEL EXTERMINADOR.
			this.velocidad = 0;
			this.x = e.ancho() - 31;  /*LE RESTAMOS 31 AL ANCHO PARA QUE SEA 769 PORQUE SINO 'X' VA A SER IGUAL AL ANCHO PRODUCIENDO QUE EL EXTERMINADOR QUEDE
										QUEDE EN VELOCIDAD CERO.*/
		}
		
		if (this.x <= 30) { //LE RESTAMOS 30 AL ANCHO PARA QUE SE FRENE EN 770 PORQUE SI ERA EN 800, SE SOBRESALIA UNA PARTE DEL EXTERMINADOR.
			this.velocidad = 0;
			this.x = 31;   /*LE RESTAMOS 31 AL ANCHO PARA QUE SEA 769 PORQUE SINO 'X' VA A SER IGUAL AL ANCHO PRODUCIENDO QUE EL EXTERMINADOR QUEDE
							QUEDE EN VELOCIDAD CERO.*/
		}
		
		if (this.y >= e.alto() - 30) { //LE RESTAMOS 30 AL ALTO PARA QUE SE FRENE EN 570 PORQUE SI ERA EN 600, SE SOBRESALIA UNA PARTE DEL EXTERMINADOR.
			this.velocidad = 0;
			this.y = e.alto() - 31;  /*LE RESTAMOS 31 AL ANCHO PARA QUE SEA 569 PORQUE SINO 'Y' VA A SER IGUAL AL ANCHO PRODUCIENDO QUE EL EXTERMINADOR QUEDE
									QUEDE EN VELOCIDAD CERO.*/
		}
		
		if (this.y <= 30) { //LE RESTAMOS 30 AL ALTO PARA QUE SE FRENE EN 570 PORQUE SI ERA EN 600, SE SOBRESALIA UNA PARTE DEL EXTERMINADOR.
			this.velocidad = 0;
			this.y = 31;  /*LE RESTAMOS 31 AL ANCHO PARA QUE SEA 569 PORQUE SINO 'Y' VA A SER IGUAL AL ANCHO PRODUCIENDO QUE EL EXTERMINADOR QUEDE
							QUEDE EN VELOCIDAD CERO.*/
		}
	}
	
	public void aumentarVelocidad (Entorno e) {
		this.velocidad =  5;
	}
	
	boolean chocaEdificio (Edificio[] e) {
		for (int i = 0; i < e.length; i++) {
			if (e[i] != null) {   //ES PARA VALIDAR SI EN CADA POSICION TENGA UN OBJETO PISTOLA (BALA).
				Edificio edif = e[i];
				
				if (this.x + this.altura/2 >= (edif.getX() - edif.getAncho()/2) && this.x - this.altura/2 <= (edif.getX() + edif.getAncho()/2) && 
						this.y + this.altura/2 >= (edif.getY() - edif.getAlto()/2) && this.y - this.altura/2 <= (edif.getY() + edif.getAlto()/2)) {
					return true;
				}
			}
		}
		return false;
	}
	
	boolean agarraMunicionExtra (MunicionExtra[] m) {
		for (int i = 0; i < m.length; i++) {
			if (m[i] != null) {   //ES PARA VALIDAR SI EN CADA POSICION TENGA UN OBJETO PISTOLA (BALA).
				MunicionExtra municion = m[i];
					
				if (this.x + this.altura/2 >= (municion.getX() - municion.getDiametro()/2) && this.x - this.altura/2 <= (municion.getX() + municion.getDiametro()/2) && 
						this.y + this.altura/2 >= (municion.getY() - municion.getDiametro()/2) && this.y - this.altura/2 <= (municion.getY() + municion.getDiametro()/2)) {
					return true;
				}
			}
		}
		return false;
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

	public int getAltura() {
		return altura;
	}

	public void setAltura(int altura) {
		this.altura = altura;
	}

	public int getBase() {
		return base;
	}

	public void setBase(int base) {
		this.base = base;
	}

	public double getAngulo() {
		return angulo;
	}

	public void setAngulo(double angulo) {
		this.angulo = angulo;
	}

	public double getVelocidad() {
		return velocidad;
	}

	public void setVelocidad(double velocidad) {
		this.velocidad = velocidad;
	}

	public int getVidas_oportunidades() {
		return vidas_oportunidades;
	}

	public void setVidas_oportunidades(int vidas_oportunidades) {
		this.vidas_oportunidades = vidas_oportunidades;
	}
}
