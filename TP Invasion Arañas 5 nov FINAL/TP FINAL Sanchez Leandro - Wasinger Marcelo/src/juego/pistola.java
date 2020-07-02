package juego;

import java.awt.Color;

import javax.sound.sampled.Clip;

import com.sun.corba.se.pept.transport.ContactInfo;

import entorno.Entorno;
import entorno.Herramientas;

public class pistola {
	private double x;
	private double y;
	private double angulo;
	private double velocidad;
	private double diametro;
	private boolean dispara;
	private boolean saleBala;
	private boolean saleExplosion;
	private boolean tieneBalas;
	private java.awt.Image proyectil;
	private java.awt.Image explosionDisparo;
	private Clip disparo;
	
	
	public pistola (double x, double y, double angulo, double velocidad, double diametro) {
		this.x = x;
		this.y = y;
		this.angulo = angulo;
		this.velocidad = velocidad;
		this.diametro = diametro;
		this.dispara = true;
		this.saleBala = true;
		this.saleExplosion = true;
		this.tieneBalas = true;
		this.proyectil = Herramientas.cargarImagen ("bala1.png");
		this.explosionDisparo = Herramientas.cargarImagen ("explosionDisparo.png");
		this.disparo = Herramientas.cargarSonido ("disparo.wav");
	}
	
	public void dibujarBala (Entorno e){
		e.dibujarCirculo (this.x - 60, this.y, this.diametro, Color.RED);
	}
	
	public void disparar (){
		if (dispara) {
			this.x = (this.x +(this.velocidad * Math.cos (this.angulo)));
			this.y = (this.y + (this.velocidad * Math.sin (this.angulo)));
		}
		
		else {
			this.velocidad = 0; 
		}
	}
	
	public void dibujarProyectil (Entorno e) {
		if (saleBala) {
			e.dibujarImagen (this.proyectil, this.x, this.y, this.angulo, 0.09); //LE RESTO 110 A LA POSICION 'X' PARA QUE LA BALA SALGA DE LA PUNTA DEL ARMA
		}
		
		else {
			return;
		}
	}
	
	public void dibujarExplosionDisparo (Entorno e) {
		if (tieneBalas) {
			e.dibujarImagen (this.explosionDisparo, this.x, this.y, this.angulo, 0.4);			
		}
		
		else {
			return;
		}
	}
	
	public void borrarExplosionDisparo() {
		this.tieneBalas = false;
	}
	
	public void noDispara() {
		this.dispara = false;
	}
	
	public void noSaleBala() {
		this.saleBala = false;
	}
	
	public void noSaleExplosion() {
		this.saleExplosion = false;
	}
	
	public void velocidadTiro () {
		this.velocidad =  velocidad + 0.4;
	}

	//COLISION CON EL BORDE IZQUIERDO
	boolean tocaParedIzquierda (Entorno e) {
		return x <= this.diametro/2;
	}
		
	//COLISION CON EL BORDE DERECHO
	boolean tocaParedDerecha (Entorno e) {
		return x >= e.ancho() - this.diametro/2;
	}
		
	//COLISION CON EL BORDE SUPERIOR
	boolean tocaTecho (Entorno e) {
		return y <= this.diametro/2;
	}
		
	//COLISION CON EL BORDE INFERIOR
	boolean tocaPiso (Entorno e) {
		return y  >= e.alto() - this.diametro/2;
	}
	
	int balazoArania (Arania[] a) {
		for (int i = 0; i < a.length; i++) {
			if (a[i] != null) {   //ES PARA VALIDAR SI EN CADA POSICION TENGA UN OBJETO PISTOLA (BALA).
				Arania spider = a[i];
				
				if (spider.getX() + spider.getDiametro()/2 >= (this.x - this.diametro/2) && spider.getX() - spider.getDiametro()/2 <= (this.x + this.diametro/2) && 
						spider.getY() + spider.getDiametro()/2 >= (this.y - this.diametro/2) && spider.getY() - spider.getDiametro()/2 <= (this.y + this.diametro/2)) {
					
					return i;
				}
			}
			
		}
		return -1;
	}
	

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	//COLISION CON EL BORDE IZQUIERDO DEL EDIFICIO
		boolean edif_bordeIzquierdo (Edificio e) {
			return (this.x - this.diametro/2) <= (e.getAncho()/2) && (this.x + this.diametro/2) >= (e.getAncho()/2);
		}
			
		//COLISION CON EL BORDE DERECHO DEL EDIFICIO
		boolean edif_bordeDerecho (Edificio e) {
			return x >= e.getAncho() - this.diametro/2 || x <= e.getAncho() - this.diametro/2;
		}
			
		//COLISION CON EL BORDE SUPERIOR DEL EDIFICIO
		boolean edif_techo (Edificio e) {
			return y <= this.diametro/2 || y >= this.diametro/2;
		}
			
		//COLISION CON EL BORDE INFERIOR DEL EDIFICIO
		boolean edif_piso (Edificio e) {
			return y  >= e.getAlto() - this.diametro/2 || y  <= e.getAlto() - this.diametro/2;
		}
	
		
		public void sonidoDisparo() {
			this.disparo.start();
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

	
	
	
}
