package juego;

import java.awt.Color;

import entorno.Entorno;
import entorno.Herramientas;

public class Arania {
	private double x;
	private double y;
	private double angulo;
	private double velocidad;
	private double diametro;
	private double signo_X = 1;
	private double signo_Y = 1;
	private int vidas;
	private boolean madre;
	private boolean apareceViva;
	private boolean apareceMuerta;
	private boolean enCaceria;
	private java.awt.Image madre_arania;
	private java.awt.Image arania;
	private java.awt.Image sangre;

	
	
	public Arania (double x, double y, double angulo, double velocidad, double diametro) {
		this.x = x;
		this.y = y;
		this.angulo = angulo;
		this.velocidad = 1;
		this.diametro = diametro;
		this.vidas = 1;
		this.madre = false;
		this.apareceViva = true;
		this.apareceMuerta = true;
		this.enCaceria = true;
		this.madre_arania = Herramientas.cargarImagen ("tarantulaMovimiento.gif");
		this.arania = Herramientas.cargarImagen ("arania.gif");
		this.sangre = Herramientas.cargarImagen ("sangre.png");
	}
	
	public void dibujarArania (Entorno e){  //DIBUJA OBJETO CIRCULO
		e.dibujarCirculo(this.x, this.y, this.diametro, Color.GREEN);
	}

	public void dibujarImagenArania (Entorno e) {  //DIBUJA IMAGEN ARAÑA
		if (this.madre) {
			e.dibujarImagen (madre_arania, this.x, this.y, this.angulo, 0.7);
			this.arania = null;  //PONGO NULL PARA QUE NO APAREZCA UNA ARANIA ARRIBA DE LA ARANIA MADRE.
		}
			
		else {
				e.dibujarImagen (arania, this.x, this.y, this.angulo, 0.07);			
		}
	}
	
	public void dibujarSangre (Entorno e) {  //DIBUJA IMAGEN ARAÑA
		e.dibujarImagen (sangre, this.x, this.y, this.angulo, 0.3);
	}
	
	public void velocidadAranias() {
		this.velocidad = velocidad;
	}

	public void cambiarAngulo(){
		this.angulo = this.angulo + Math.PI/2;
	}
	
	public void noPerseguirAlExterminador() {
		this.x = this.x + signo_X * (this.velocidad * Math.cos(angulo));
		this.y = this.y + signo_Y * (this.velocidad * Math.sin(angulo));
	}
	
	public void perseguirExterminador (Exterminador e){
		double angulo = Math.atan2(this.y - e.getY(), this.x - e.getX());
		this.x = this.x + signo_X * (this.velocidad * Math.cos(angulo));
		this.y = this.y + signo_Y * (this.velocidad * Math.sin(angulo));
	}
	
	public void separarAraniasEdificio() {
		this.x = this.x + 25;
		this.y = this.y + 25;
	}
	
	public boolean isMadre() {
		return madre;
	}

	public void setMadre(boolean madre) {
		this.madre = madre;
	}

	public double getSignoX() {
		return signo_X;
	}
	
	public void setSignoX(double multiplox) {
		this.signo_X = multiplox;
	}
	
	public double getSignoY() {
		return signo_Y;
	}
	
	public void setSignoY(double multiploy) {
		this.signo_Y = multiploy;
	}
	
	public void aumentarVelocidadArania() {
		this.velocidad = 1;
	}
	
	public void cambiarTrayectoria() {
		this.angulo = this.angulo + Math.PI/2;
	}
	
	public void cambiarAnguloEnParedIzquierda () {
		this.x = this.diametro / 2;
		this.signo_X = this.signo_X * (-1);
	}
	
	public void cambiarAnguloEnParedDerecha () {
		this.x = 799.9 - this.diametro / 2;  //PUSIMOS 799.9 PORQUE QUISIMOS COLOCAR EL ANCHO() Y NOS DABA ERROR.
		this.signo_X = this.signo_X * (-1);
	}
	
	public void cambiarAnguloEnTecho () {
		this.y = this.diametro / 2;
		this.signo_Y = this.signo_Y * (-1);
	}
	
	public void cambiarAnguloEnPiso () {
		this.y = 599.9 - this.diametro / 2; //PUSIMOS 599.9 PORQUE QUISIMOS COLOCAR EL ALTO() Y NOS DABA ERROR.
		this.signo_Y = this.signo_Y * (-1);
	}
	
	//COLISION CON EL BORDE IZQUIERDO
	boolean tocaParedIzquierda (Entorno e) {
		return this.x <= this.diametro/2;
	}
	
	//COLISION CON EL BORDE DERECHO
	boolean tocaParedDerecha (Entorno e) {
		return this.x >= e.ancho() - this.diametro/2;
	}
	
	//COLISION CON EL BORDE SUPERIOR
	boolean tocaTecho (Entorno e) {
		return this.y <= this.diametro/2;
	}
	
	//COLISION CON EL BORDE INFERIOR
	boolean tocaPiso (Entorno e) {
		return this.y  >= e.alto() - this.diametro/2;
	}
	
	int numMinaPisada (Mina[] m) {
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
	
	int rebotaEdificio (Edificio[] e) {
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
	
	public double getX() {
		
		return x;
	}

	public int getVidas() {
		return vidas;
	}

	public void setVidas(int vidas) {
		this.vidas = vidas;
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

	public double getVelocidad() {
		return velocidad;
	}

	public void setVelocidad(double velocidad) {
		this.velocidad = velocidad;
	}

	public double getDiametro() {
		return diametro;
	}

	public void setDiametro(double diametro) {
		this.diametro = diametro;
	}

	public boolean isEnCaceria() {
		return enCaceria;
	}

	public void setEnCaceria(boolean enCaceria) {
		this.enCaceria = enCaceria;
	}
}
