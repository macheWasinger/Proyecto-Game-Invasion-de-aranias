package juego;

import java.awt.Color;

import entorno.Entorno;
import entorno.Herramientas;

import javax.sound.sampled.Clip;

public class Mina {
	private double x;
	private double y;
	private double angulo;
	private double diametro;
	private int pisadas;
	private long horaPisada;
	private boolean bomba;
	private java.awt.Image explosivo;
	private java.awt.Image explosionMina;
	private Clip explosion;
	private boolean imagenExplosion;
	
	public Mina (double x, double y, double angulo, double diametro) {
		this.x = x;
		this.y = y;
		this.angulo = angulo;
		this.diametro = diametro;
		this.imagenExplosion = true;
		this.bomba = true;
		this.pisadas = 2;
		this.horaPisada = 0;
		this.explosivo = Herramientas.cargarImagen ("mina.png");
		this.explosionMina = Herramientas.cargarImagen ("explosionMina.gif");
		this.explosion = Herramientas.cargarSonido ("explosion.wav");
	}
	
	public void dibujar (Entorno e){
		if (bomba) {
			e.dibujarImagen (this.explosivo, this.x, this.y, this.angulo, 0.3);
		}
		
		else {
			e.dibujarImagen (this.explosionMina, this.x, this.y, this.angulo, 1);
		}
	}
	
	public void borraExplosionMina () {
		this.imagenExplosion = false;
	}
	
	public void sonidoExplosion() {
		this.explosion.start();
	}
	
	public void explota() {
		this.bomba = false;
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

	public void setPisadas(int pisadas) {
		this.pisadas = pisadas;
	}

	public int getPisadas() {
		return pisadas;
	}

	public long getHoraPisada() {
		return horaPisada;
	}

	public void setHoraPisada(long l) {
		this.horaPisada = l;
	}
}
