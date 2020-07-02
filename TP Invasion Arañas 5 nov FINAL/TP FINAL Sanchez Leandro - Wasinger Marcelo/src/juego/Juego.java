package juego;

import java.sql.Timestamp;
import java.util.Random;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.sound.sampled.Clip;
import javax.swing.JOptionPane;

import entorno.Entorno;
import entorno.Herramientas;
import entorno.InterfaceJuego;

public class Juego extends InterfaceJuego {

	// El objeto Entorno que controla el tiempo y otros

	// VARIABLES DE INSTANCIAS DE LAS CLASES Y SE INICIALIZAN EN EL CONSTRUCTOR.

	///////////////////////////////////////////////////////////////////////////////////////////////////////////
	/* VARIABLES DE INSTANCIAS DE LAS CLASES Y SE INICIALIZAN EN EL CONSTRUCTOR. */
	
	private Entorno entorno;
	
	private String jugador;
	private Edificio[] edificio;
	private Exterminador exterminator;
	private pistola[] bala;
	private pistola nuevoProyectil;
	private Arania[] arania;
	private Arania araniaMadre;
	private Mina[] bomba;
	private Mina nuevaBomba;
	private MunicionExtra[] municion;
	private MunicionExtra nuevaMunicion;
	
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/* VARIABLES DE INSTANCIAS QUE SIRVEN PARA PODER AÑADIRLE IMAGENES AL JUEGO. */
	private java.awt.Image fondo;
	private java.awt.Image cargador;
	private java.awt.Image pistola;
	private java.awt.Image menu;
	private java.awt.Image loadingGame;
	private java.awt.Image recargando;
	private java.awt.Image sinMunicion;
	private java.awt.Image gameOver;
	
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////
	/* VARIABLES DE INSTANCIAS QUE SIRVEN PARA PODER AÑADIRLE IMAGENES AL JUEGO. */
	private Clip inicio_Menu;
	private Clip botonInicio;
	private Clip sonidoLoading;
	private Clip juegoTerminado;
	private Clip musicaJuego;
	private Clip musicaFondoGameOver;
	private Clip recarga;

	////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/* VARIABLES DE INSTANCIAS DE NUMEROS ENTEROS, FLOTANTES, RANDOM Y BOOLEANOS. */
	private int posX_edificio;
	private int posY_edificio;
	private int cant;
	private int cantAranias;
	private int cant_AraniasMuertas;
	private int contViejas;
	private int cantBomba;
	private int cantBalas;
	private int indice_nuevaBomba;
	private int indice_nuevoProyectil;
	private int indice_nuevaMunicionExtra;
	private int contadorSegundos_imagenRecargando;
	private int contadorSegundos_seQuedoSinMunicion;
	private int cargadorArma;
	private int contador_cargadores;
	private int cantidadBalas_porCargador;
	private int contBalazos_ArañaMadre;
	private int cont_pisasteMina;
	private int cantExpansionExplosion;
	private int contSegundos_gameOver;
	private int contSegundos_atrapado;
	private int posY_arania;
	private int posX_arania;
	private int contSegundosCargandoJuego;
	private int contadorAraniasAniquiladas;
	private int contadorGeneralAraniasMuertas;
	private int municionExtra_posX;
	private int municionExtra_posY;
	private int coordenadas[][];
	private int cant_edificiosCreados;
	private int contador_segundosDisparoTelarania;

	private boolean presionaTecl_izq;
	private boolean presionaTecl_der;
	private boolean disparoTiro;
	private boolean imagenRecargando;
	private boolean imagen_pistola;
	private boolean imagenSinMunicion;
	private boolean imagenCargador;
	private boolean dibujaExplosionDisparo;
	private boolean desapareceCargador;
	private boolean text_contSegundos_explosion;
	private boolean fondoMenu;
	private boolean loading;
	private boolean fondoGameOver; 
	private boolean fondoJuego;
	private boolean cont_AraniasAsesinadas;
	private boolean araniaCambiaTrayectoria;
	private boolean seSuperponen;
	private boolean[] dibujarEdificio;
	private boolean contGeneral_AraniasAsesinadas;
	
	private Random cantidadEdif;
	private Random cantidadAranias;
	
	// Variables y métodos propios de cada grupo
	// ...
	
	////////////////////////////////////////////////////////////////////////////////////////////////////
	private boolean getRandomBoolean() {
		Random r = new Random();
		return r.nextBoolean();
	}
	
	/*ESTE METODO SE ENCARGA DE CREAR LOS OBJETOS QUE REPRESENTAN A LAS ARAÑAS. */
	
	private void crearObjetosAranias() {
		cantidadAranias = new Random();
		cantAranias = 10 + cantidadAranias.nextInt(1);
		this.arania = new Arania[cantAranias];
		Random random = new Random();
		Random r = new Random();
		for (int i = 0; i < arania.length; i++) {
			if (getRandomBoolean()) {
				posX_arania = 0;
				posY_arania = r.nextInt(600);
			}
			
			else {
				posX_arania = 750;
				posY_arania = r.nextInt(600);
			}
			
			int ang = random.nextInt(360);
			this.arania[i] = new Arania(posX_arania, posY_arania, ang, 0.0, 45);
		}
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////
	/*ESTE METODO SE ENCARGA DE CREAR LOS OBJETOS QUE REPRESENTAN A LAS NUEVAS MUNICIONES. */
	private void actualizarBalas() {
		this.cantBalas = 70;
		this.contador_cargadores = 2;
		this.cantidadBalas_porCargador = this.cantBalas / 2;
		this.bala = new pistola[cantBalas];
		this.indice_nuevoProyectil = 0;
		this.cargadorArma = this.cantidadBalas_porCargador;
	}	

	/////////////////////////////////////////////////////////////////////////////////////////////////////////
	private boolean getRandomBoolean_crearEdificio() {
		Random aleatorio = new Random();
		return aleatorio.nextBoolean();
	}

	/*ESTE METODO SE ENCARGA DE CREAR LOS OBJETOS QUE REPRESENTAN A LOS EDIFICIOS Y LOS UBICA EN DIFERENTES
	 * COORDENADAS PARA QUE NO SE SUPERPONGAN ENTRE SI. */
	private void armador_ArregloEdificios() {
		this.coordenadas = new int [2][8];
		this.coordenadas[0][0] = 200;
		this.coordenadas[0][1] = 280;
		this.coordenadas[0][2] = 500;
		this.coordenadas[0][3] = 650;
		this.coordenadas[0][4] = 200;
		this.coordenadas[0][5] = 280;
		this.coordenadas[0][6] = 500;
		this.coordenadas[0][7] = 650;
		this.coordenadas[1][0] = 100;
		this.coordenadas[1][1] = 180;
		this.coordenadas[1][2] = 180;
		this.coordenadas[1][3] = 100;
		this.coordenadas[1][4] = 450;
		this.coordenadas[1][5] = 550;
		this.coordenadas[1][6] = 550;
		this.coordenadas[1][7] = 450;
		cant = 8;
		this.edificio = new Edificio[cant];
		
		for (int i = 0; i < cant; i++) {
			edificio[i] = new Edificio (this.coordenadas[0][i], this.coordenadas[1][i], 85, 70, Color.blue);
		}

		dibujarEdificio = new boolean [8];
		this.cant_edificiosCreados = 0;
		while (this.cant_edificiosCreados < 4) {
			for (int i = 0; i < 8; i++) {
				boolean band = getRandomBoolean();
				if (band == true) {
					dibujarEdificio[i] = true;
					cant_edificiosCreados++;
				}
			}
		}
	}
	
	Juego() {
		// Inicializa el objeto entorno
		this.entorno = new Entorno(this,
				"TP FINAL P1 Sanchez - Wasinger", 800, 600);
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		/* CREO EL OBJETO EXTERMINADOR Y AÑADO IMAGENES Y SONIDOS AL JUEGO. */
		this.exterminator = new Exterminador(entorno.ancho()/2, entorno.alto()/2, 60, 50, 5 * Math.PI / 6, Color.RED);
		this.fondo = Herramientas.cargarImagen ("fondoJuego.png");
		this.menu = Herramientas.cargarImagen ("fondoMenu.gif");
		this.loadingGame = Herramientas.cargarImagen ("loading.gif");
		this.cargador = Herramientas.cargarImagen ("cargador.png");
		this.pistola = Herramientas.cargarImagen ("pistola.png");
		this.recargando = Herramientas.cargarImagen ("recargando.png");
		this.sinMunicion = Herramientas.cargarImagen ("sinMunicion.png");
		this.gameOver = Herramientas.cargarImagen ("GameOver.gif");
		
		this.inicio_Menu = Herramientas.cargarSonido ("inicio.wav");
		this.sonidoLoading = Herramientas.cargarSonido ("loadingGame.wav");
		this.juegoTerminado = Herramientas.cargarSonido ("Game Over.wav");
		this.musicaJuego = Herramientas.cargarSonido ("sonidoJuego.wav");
		this.musicaFondoGameOver = Herramientas.cargarSonido ("GameOverResidentEvil4.wav");
		this.botonInicio = Herramientas.cargarSonido ("sonidoBotonInicio.wav");
		this.recarga = Herramientas.cargarSonido ("recargar.wav");
		
		////////////////////////////////////////////////////////////////////////////////////////////////////////////
		/* CONSTRUCTOR DE LOS OBJETOS DE CADA CLASE. */
		this.cantBalas = 70;
		this.contador_cargadores = 2;
		this.cantidadBalas_porCargador = this.cantBalas / 2;
		this.bala = new pistola[cantBalas];
		this.indice_nuevoProyectil = 0;
		this.cargadorArma = this.cantidadBalas_porCargador;
	
		this.municion = new MunicionExtra[5];
		this.indice_nuevaMunicionExtra = 0;
		
		this.cantBomba = 20;
		this.bomba = new Mina[cantBomba];
		this.indice_nuevaBomba = 0;
		this.crearObjetosAranias();
		this.armador_ArregloEdificios();

		////////////////////////////////////////////////////////////////////////////////////////////////////
		/* LE INICIALIZO EL VALOR A CADA VARIABLE INSTANCIA (INT, FLOAT Y BOOLEAN) QUE CREE ANTES DEL CONSTRUCTOR. */
		this.contadorSegundos_imagenRecargando = 120;
		this.contadorSegundos_seQuedoSinMunicion = 70;
		this.cant_AraniasMuertas = 0;
		this.contBalazos_ArañaMadre = 0;
		this.cont_pisasteMina = 0;
		this.contSegundos_gameOver = 200;
		this.contSegundos_atrapado = 60;
		this.contSegundosCargandoJuego = 1680;
		this.contadorAraniasAniquiladas = 0;
		this.contadorGeneralAraniasMuertas = 0;
		this.contador_segundosDisparoTelarania = 190;
	
		this.presionaTecl_izq = true;
		this.presionaTecl_der = true;
		this.disparoTiro = false;
		this.imagen_pistola = true;
		this.imagenRecargando = false;
		this.imagenSinMunicion = false;
		this.dibujaExplosionDisparo = false;
		this.imagenCargador = true;
		this.text_contSegundos_explosion = false;
		this.fondoMenu = true;
		this.loading = false;
		this.fondoGameOver = false;
		this.fondoJuego = false;
		this.cont_AraniasAsesinadas = false;
		this.contGeneral_AraniasAsesinadas = false;
		
		// Inicializar lo que haga falta para el juego
		// ...

		// Inicia el juego!
		this.entorno.iniciar();
	}

	/**
	 * Durante el juego, el método tick() será ejecutado en cada instante y por
	 * lo tanto es el método más importante de esta clase. Aquí se debe
	 * actualizar el estado interno del juego para simular el paso del tiempo
	 * (ver el enunciado del TP para mayor detalle).
	 */
	public void tick() {
		if (this.fondoJuego) { /* el boolean "fondoJuego comienza en FALSE y cuando aprieto ENTER, se vuelve TRUE y se dibuja el juego. "*/
			this.entorno.dibujarImagen (this.fondo, this.entorno.ancho()/2, this.entorno.alto()/2, 0, 1.018);
			for (int i = 0; i < this.edificio.length; i++) {
				if (this.dibujarEdificio[i] == true) {      /* Si cada edificio es true, lo dibuja. */
					this.edificio[i].dibujarEdificio(this.entorno);	
				}
				
				else {
					this.edificio[i] = null;		/* Si cada edificio es false, no lo dibuja. */
				}
			}
				
			for (int i = 0; i < edificio.length; i++) {
				if (this.edificio[i] != null) {
					this.edificio[i].dibujarEdificio(this.entorno);
					int numBalaImpactada = this.edificio[i].balazoEdificio(this.bala);  /* Devuelve el indice del edificio
					 																que recibio el impacto de bala*/
					
					if (numBalaImpactada >= 0) {  /* Si el indice del edificio que fue impactado 
													es mayor o igual a 0, se elimina al objeto BALA.*/
						this.bala[numBalaImpactada] = null;
					}
				}
			}
			
			for (int b = 0; b < this.bomba.length; b++) {
				if (this.bomba[b] != null) {
					if (this.bomba[b].getHoraPisada() == 0) {  /* SE FIJA SI TIENE HORA DE PISADA. */
						this.bomba[b].dibujar(this.entorno);
					}
					
					else {
						Timestamp horaActual = new Timestamp(System.currentTimeMillis()); //CAPTURA LA HORA ACTUAL REAL EN MILISEGUNDOS.
						long diferencia = horaActual.getTime() - this.bomba[b].getHoraPisada(); /*CALCULA LA DIFERENCIA ENTRE LA HORA PISADA
						 								(EN MILISEGUNDOS) - LA HORA (TAMBIEN EN MILISEGUNDOS) EN LA QUE LA ARAÑA PISO LA BOMBA*/
																								
						if (diferencia < 3100){  /*SI EL TIEMPO QUE DURA EL GIF DE EXPLOSION ES MENOR A 3,1 SEGUNDOS, SE EJECUTA 
													UNA SOLA VEZ PORQUE SINO SE REPRODUCE VARIAS VECES. */
							this.bomba[b].dibujar(this.entorno);
							this.bomba[b].sonidoExplosion();
						}
						
						else {
							this.bomba[b] = null;  //SI EL TIEMPO DEL GIF ES MAYOR A 3.1 SEGUNDOS, LO BORRA.
						}
					}
					
					if (this.exterminator != null) {
						int posicion = this.exterminator.pisaMina(this.bomba);
						if (posicion >= 0) {
							this.exterminator.haPerdido (2, this.exterminator.getX(), this.exterminator.getY()); /* CUANDO ES 2, SIGNIFICA 
																											QUE EL EXTERMINADOR PISO LA BOMBA. */ 
							
							this.exterminator.setVidas_oportunidades(this.exterminator.getVidas_oportunidades()-1);
							
							if (this.bomba[posicion].getPisadas() == 1) {  /* Si es igual a 1, es porque la bomba ya fue pisada una vez. */
								this.bomba[posicion].explota();
								this.bomba[posicion].sonidoExplosion();
								this.exterminator.cargarSonidoMuerte();
								this.bomba[posicion] = null;
								this.exterminator = null;	
							}
							
							else {
								this.bomba[posicion].setPisadas(this.bomba[posicion].getPisadas() - 1); /* Como es la primera vez que se pisa,
								 														entonces le resta una pisada. */
								this.exterminator.cargarSonidoMuerte();
								this.exterminator = null;
								this.bomba[posicion].explota();
								this.bomba[posicion].sonidoExplosion();
								this.bomba[posicion].setDiametro(200);  /* Al pisar la mina por primera vez, se agranda el diametro de
								la misma, se ejecuta el gif de explosion, pero no se elimina el objeto. Por lo cual, va a quedar nula
								cuando la pisen por segunda vez al hacer contacto con el objeto que se encuentre a su alrededor. */
								Timestamp timestamp = new Timestamp(System.currentTimeMillis()); /*  */
								this.bomba[posicion].setHoraPisada(timestamp.getTime());
							}
						}
					}
					
				}
			}
			
			for (int i = 0; i < arania.length; i++) {
				if (this.arania[i] != null) { // CORROBORO QUE HAYA UN OBJETO ARAÑA EN CADA POSICION DEL ARREGLO ARAÑA.
					int numeroDeBombaPisada = this.arania[i].numMinaPisada(this.bomba); /* Devuelve el indice de la bomba
					 																que fue pisada por la araña.*/
									
					if (numeroDeBombaPisada >= 0) {  /* Si el indice de la bomba que fue pisada es 
													mayor o igual a 0, se activa el gif del explosion y empiezan a sumarse la cantidad
													de arañas aniquiladas y el contador general de arañas muertas.*/
						this.bomba[numeroDeBombaPisada].explota();
						this.contadorAraniasAniquiladas++;
						this.contadorGeneralAraniasMuertas++;
											
						if (this.bomba[numeroDeBombaPisada].getPisadas() == 1) { /* Si es igual a 1, es porque la bomba ya fue pisada una vez.*/
							this.bomba[numeroDeBombaPisada].sonidoExplosion();
							this.bomba[numeroDeBombaPisada] = null;
							this.arania[i] = null;
							this.cant_AraniasMuertas++;
							
						}
											
						else {
							this.bomba[numeroDeBombaPisada].setPisadas(this.bomba[numeroDeBombaPisada].getPisadas() - 1); /* Como es la 
																					primera vez que se pisa, entonces le resta una pisada. */
							this.arania[i] = null;
							this.cant_AraniasMuertas++;
							this.bomba[numeroDeBombaPisada].explota();
							this.bomba[numeroDeBombaPisada].setDiametro(200); /* Al pisar la mina por primera vez, se agranda el diametro de
							la misma, se ejecuta el gif de explosion, pero no se elimina el objeto. Por lo cual, va a quedar nula
							cuando la pisen por segunda vez al hacer contacto con el objeto que se encuentre a su alrededor. */
							
							Timestamp timestamp = new Timestamp(System.currentTimeMillis()); /* Captura la hora actual real en milisegundos. */			
							this.bomba[numeroDeBombaPisada].setHoraPisada(timestamp.getTime());	
						}

						if (this.cant_AraniasMuertas == this.arania.length) { /* INDICA QUE LAS ARAÑAS ESTAN TODAS MUERTAS. */																			// 
							this.arania[0] = new Arania(0, 300 , 5, 0.0, 90); /* El primer indice del objeto araña, va a aumentar su diametro
							 													para convertirse en la araña madre. */
							this.araniaMadre = this.arania[0];
							this.araniaMadre.setVidas(1);
							this.araniaMadre.setMadre(true); 
							this.araniaMadre.setEnCaceria(true); /* Al ser TRUE, la araña madre persigue al exterminador. */
							this.cant_AraniasMuertas = 0; /* REINICIO CONTADOR DE ARAÑAS MUERTAS (COMO QUE NO HAY ARAÑAS MUERTAS). */
						}
					}							
				}
			}
	
			for (int p = 0; p < this.bala.length; p++) {
				if (this.bala[p] != null) {
					this.bala[p].sonidoDisparo();
					this.bala[p].dibujarExplosionDisparo(this.entorno);
					
					if (this.bala[p].getVelocidad() > 0) {
						this.bala[p].borrarExplosionDisparo();
					}
					
					this.bala[p].velocidadTiro();
					this.bala[p].disparar();
					this.bala[p].dibujarProyectil(this.entorno);
					
					int indice = this.bala[p].balazoArania(this.arania); /* CUANDO SE COLISIONAN, RETORNA LA POSICION DE LA ARAÑA MUERTA. */
					if (indice >= 0) { 	/* Si el indice de la araña que recibio el balazo es mayor o igual a 0, se ejecutan las demás cosas.*/
						this.arania[indice].dibujarSangre(this.entorno);
						this.bala[p] = null;
						
						if (this.arania[indice].getVidas() == 1) { /* Si la araña que recibio el balazo, tiene una sola vida, se ejecutan los contadores
						 											y se elimina el objeto. */
							this.cant_AraniasMuertas++;
							this.contadorAraniasAniquiladas++;
							this.contadorGeneralAraniasMuertas++;
							
							Arania spider = this.arania[indice];
							this.arania[indice] = null; /* MATAMOS A LA ARAÑA (CON EL NULL BORRO EL OBJETO).*/
		
							if (spider.isMadre()) { /* Si murio la araña madre, se ejecuta el metodo de crear arañas. */
								this.crearObjetosAranias();
							}	
						}

						else {
								this.arania[indice].setVidas(this.arania[indice].getVidas() - 1); /* Si la araña tiene mas de una vida, significa
						que es la araña madre, por ende le resta una vida porque tiene 10. Entonces, pasa al IF para preguntar si se cumple que tiene una vida,
						y como no se cumple vuelve al ELSE y le resta otra vida. Y cuando la araña madre tenga una sola vida, se activa el IF, 
						elimina a la araña madre y se activan los contadores.*/
						}
						
						if (this.cant_AraniasMuertas == this.arania.length) { /* Indica que todas las arañas estan muertas. */																			// 
							this.arania[0] = new Arania(0, 300 , 5, 0.0, 90); /* El primer indice del objeto araña, va a aumentar su diametro
																				para convertirse en la araña madre. */
							this.araniaMadre = this.arania[0];
							this.araniaMadre.setVidas(10);
							this.araniaMadre.setMadre(true);
							this.araniaMadre.setEnCaceria(true);  /* Al ser TRUE, la araña madre persigue al exterminador. */
							this.cant_AraniasMuertas = 0; /* REINICIO CONTADOR DE ARAÑAS MUERTAS (COMO QUE NO HAY ARAÑAS MUERTAS). */
						}
					}
				}
			}
			
			if (this.exterminator != null) {
				this.exterminator.dibujarImagenExterminador(this.entorno);
				if (this.exterminator.getVidas_oportunidades() > 0) { /* Si el exterminador esta vivo, se ejecuta todo lo demas. */	
					if (this.cantidadBalas_porCargador > 0) {
						if (this.entorno.sePresiono(this.entorno.TECLA_ESPACIO)) {
							
							if (this.indice_nuevoProyectil < this.bala.length) {
								this.nuevoProyectil = new pistola(this.exterminator.getX(), this.exterminator.getY(), this.exterminator.getAngulo(), 0.0, 20);
								this.bala[this.indice_nuevoProyectil] = nuevoProyectil;
								this.indice_nuevoProyectil++;
								this.cantBalas--;
								this.cantidadBalas_porCargador--;
								
								if (this.cantidadBalas_porCargador == 0) {
									this.contador_cargadores--;
								}
							}
						}
					}
				}
				
				else {
						this.exterminator.seDetiene();
				}
					
					for (int i = 0; i < this.municion.length; i++) {
						if (this.municion[i] != null) {
							if (this.contadorAraniasAniquiladas == 0 || this.contadorAraniasAniquiladas >= 1) { /* Si el contador de 
							arañas muertas es igual a cero o mayor a 1, van a dibujarse las municiones extras y van a quedar en la pantalla. */
								this.municion[i].dibujarMunicionExtra(this.entorno);
								
								int indiceEdificio = this.municion[i].seSuperponeEdificio(this.edificio);
								if (indiceEdificio >= 0) {
									this.municion[i].separarDelEdificio(); /* Si la municion extra aparece en la misma posicion de algun 
							edificio (ya que tiene coordenadas random), lo muevo a un costado aumentadole unos pixeles a sus coordenadas. */
								}							
							}
							
							if (this.exterminator.agarraMunicionExtra(this.municion)) {
								this.municion[i] = null;
								this.actualizarBalas();  /* Si el exterminador agarra la municion extra, devuelve las balas que ha gastado
								o devuelve lo o los cargadores que uso (LAS BALAS Y/O CARGADORES NO SON ACUMULABLES). */
							}	
						}
					}
			}
			
			if (this.contadorAraniasAniquiladas == 10) { /* Cuando el el exterminador elimine a cierta cantidad arañas (en este caso a 10),
			 											el contador se reinicia a cero y crea una municion extra.*/
				this.contadorAraniasAniquiladas = 0;
				if (this.indice_nuevaMunicionExtra < this.municion.length) {
					Random aleatorio = new Random();
					municionExtra_posX = aleatorio.nextInt(700);
					municionExtra_posY = aleatorio.nextInt(500);
					this.nuevaMunicion = new MunicionExtra (municionExtra_posX, municionExtra_posY, 0, 0.0, 100, Color.GREEN);	
					this.municion[this.indice_nuevaMunicionExtra] = this.nuevaMunicion;
					this.indice_nuevaMunicionExtra++;
				}
			}
			
				if (this.exterminator != null) {
					int numAraniaQueChocaConExterm = this.exterminator.chocaArania(this.arania);
					if (numAraniaQueChocaConExterm >= 0) {	
						this.exterminator.haPerdido(1, this.exterminator.getX(), this.exterminator.getY()); /*CUANDO ES IGUAL 1, QUIERE DECIR 
																	QUE EL EXTERMINADOR FUE ATRAPADO POR UNA ARAÑA. */
						this.recarga.stop();
						this.exterminator.cargarSonidoMuerte();
						this.exterminator.setVidas_oportunidades(this.exterminator.getVidas_oportunidades()-1);
					}
								
					if (this.exterminator.getVidas_oportunidades() < 1) { 
						/*Cuando el exterminador tiene una vida y es atrapado, se le resta una y como la vida
					 	es menor a 1, se borra el objeto exterminador y se activa el game over. */
						this.contSegundos_atrapado--;
						if (this.contSegundos_atrapado <= 0) { /* Cuando el contador de milisegundos es menor o igual a cero, se borra al 		
					 											exterminador y se activa el game over. */
						this.exterminator = null;
						}						
					}	
				}
				
				if (this.exterminator != null) {
					if (this.exterminator.chocaEdificio(this.edificio)) {
						this.exterminator.cambiarAngulo();
					}
					
					for (int i = 0; i < this.arania.length; i++) {
						if (this.arania[i] != null) {
							this.arania[i].dibujarImagenArania(this.entorno);
								
								if (this.arania[i].isEnCaceria()) { /* Comienza como true. */
									this.arania[i].perseguirExterminador(this.exterminator);
								}
								
								else {
									this.arania[i].noPerseguirAlExterminador();
								}
								
								int numeroEdificioChocado = this.arania[i].rebotaEdificio(this.edificio); /* Devuelve el indice del edificio
								que fue colisionado por la araña. */
								if (numeroEdificioChocado >= 0) { /* Si el indice del edificio colisionado es mayor o igual a cero,
								 										se ejecuta todo lo demas. */
									if (this.arania[i].getX() > 0) {
										this.arania[i].setX(this.arania[i].getX() - 15);
									}
									
									else {
										this.arania[i].setX(this.arania[i].getX() + 15);
									}
									
									if (this.arania[i].getY() > 0) {
										this.arania[i].setY(this.arania[i].getY() - 15);
									}
									
									else {
										this.arania[i].setY(this.arania[i].getY() + 15);
									}
									
									/* Cuando rebota en el edificio, deja de perseguir al exterminador hasta chocarse con el entorno. */
									this.arania[i].setEnCaceria(false);
									
									this.arania[i].cambiarTrayectoria();	
								}
								
								/* Una vez que la araña se haya chocado con el entorno, vuelve a activarse el booleano 
								 * para que la misma empiece a perseguirlo otra vez. */
								if (this.arania[i].tocaParedIzquierda(entorno)) {
									this.arania[i].cambiarAnguloEnParedIzquierda();
									this.arania[i].setEnCaceria(true);
								}

								if (this.arania[i].tocaParedDerecha(entorno)) {
									this.arania[i].cambiarAnguloEnParedDerecha();
									this.arania[i].setEnCaceria(true);
								}

								if (this.arania[i].tocaTecho(entorno)) {
									this.arania[i].cambiarAnguloEnTecho();
									this.arania[i].setEnCaceria(true);
								}

								if (this.arania[i].tocaPiso(entorno)) {
									this.arania[i].cambiarAnguloEnPiso();
									this.arania[i].setEnCaceria(true);
								}		
						}
					}
				}
				
			// Procesamiento de un instante de tiempo
			// ...

			if (this.exterminator != null) {
				if (presionaTecl_izq) {
					if (entorno.estaPresionada(entorno.TECLA_DERECHA)) {  /* El exterminador gira hacia la derecha. */
						this.exterminator.setAngulo(this.exterminator.getAngulo() + Math.PI / 60);
					}
				}
		
				if (presionaTecl_der) {
					if (entorno.estaPresionada(entorno.TECLA_IZQUIERDA)) {  /* El exterminador gira hacia la izquierda. */
						this.exterminator.setAngulo(this.exterminator.getAngulo() - Math.PI / 60);
					}
				}
		
				if (entorno.estaPresionada(entorno.TECLA_ARRIBA)) {  /* /* El exterminador avanza. */
					this.exterminator.avanzarExterm(this.entorno);
				}
				
				if (entorno.estaPresionada(entorno.TECLA_SHIFT) && entorno.estaPresionada(entorno.TECLA_ARRIBA)) { /* El exterminador corre. */
					this.exterminator.aumentarVelocidad(this.entorno);
				}
		
				if (!(entorno.estaPresionada(entorno.TECLA_ARRIBA))) { /* Si dejo de apretar la tecla ARRIBA, el exterminador frena su marcha. */
					this.exterminator.frenar();
				}
		
				if (entorno.sePresiono('m')) {  /* Si aprieto la tecla M, el exterminador lanza una bomba al suelo. */
					if (this.indice_nuevaBomba < this.bomba.length) {
						this.nuevaBomba = new Mina(this.exterminator.getX() - 85, this.exterminator.getY(), this.exterminator.getAngulo(), 90);
						this.bomba[this.indice_nuevaBomba] = nuevaBomba;
						this.indice_nuevaBomba++;
					}
				}
				
				this.exterminator.mover();	
			}
			
			if (this.imagenRecargando) { /* ES UN BOOLEANO QUE AFIRMA QUE EL TEXTO "RECARGANDO MUNICION" QUE APARECE EN EL JUEGO ES FALSE.*/
				this.entorno.dibujarImagen (this.recargando, 650, 90, 0, 0.27);
			}
					
			if (this.imagen_pistola) { /* ES UN BOOLEANO QUE AFIRMA QUE EL TEXTO "TIROS" QUE APARECE EN EL JUEGO ES TRUE. */
				this.entorno.cambiarFont(Font.MONOSPACED + " bold", 50, Color.BLACK);
				this.entorno.escribirTexto("" + this.cantidadBalas_porCargador, 750, 40);
				this.entorno.dibujarImagen (this.pistola, 700, 30, 0, 0.11);
			}
					
			if (this.imagenCargador) {
				this.entorno.cambiarFont(Font.MONOSPACED + " bold", 60, Color.BLACK);
				this.entorno.escribirTexto("" + this.contador_cargadores, 55, 60);
				this.entorno.dibujarImagen (this.cargador, 35, 40, 0, 0.12);
			}
					
			if (this.imagenSinMunicion) {
				this.entorno.dibujarImagen (this.sinMunicion, 650, 90, 0, 0.27);
			}	
			
			if (this.cantidadBalas_porCargador == 0) { /* CUANDO GASTE 35 BALAS, SE CUMPLE LA CONDICION. */
				this.contadorSegundos_seQuedoSinMunicion--;  /* EMPIEZA A CORRER EL TIEMPO EN MILISEGUNDOS. */
						
				if (this.contadorSegundos_seQuedoSinMunicion <= 0) { /*CUANDO EL CONTADOR DE SEGUNDOS LLEGA A CERO, El TEXTO "TIROS" SE VUELVE FALSE PARA DESAPARECER DEL JUEGO Y
														 EL TEXTO "RECARGANDO MUNICION" SE VUELVE TRUE PARA APARECER */
					this.contadorSegundos_seQuedoSinMunicion = 0;
					this.imagen_pistola = false;
					this.imagenRecargando = true;
					this.recarga.start();
					this.contadorSegundos_imagenRecargando--;
					
					if (this.contadorSegundos_imagenRecargando <= 0) { /* CUANDO EL CONTADOR DE SEGUNDOS LLEGA A CERO, EL TEXTO "RECARGANDO
					 MUNICION" SE VUELVE FALSE PARA DESAPARECER DEL JUEGO Y EL TEXTO "TIROS" VUELVE A SER TRUE PARA APARECER NUEVAMENTE. */
						this.contadorSegundos_imagenRecargando = 0;
						this.imagenRecargando = false;
						this.imagen_pistola = true;
						this.cantidadBalas_porCargador = 35;
					}				
				}
			}
					
			if (this.contador_cargadores == 0) {
				this.contadorSegundos_seQuedoSinMunicion--; 
						
				if (this.contadorSegundos_seQuedoSinMunicion <= 0) {
					this.imagen_pistola = false;
					this.imagenSinMunicion = true;
				}
			}

			if (this.fondoGameOver) {   /* Comienza en false para no aparecer en pantalla. */
				this.entorno.dibujarImagenConCentro(this.gameOver, 0, 0, 0, 0, 0, 1.018);
			}
			
			if (this.exterminator == null) { /* Cuando muera el exterminador, se detiene el sonido de recargar pistola.
			 								Esta condicion la hice para que Java no me tire errores.*/
				this.recarga.stop();
				this.contSegundos_gameOver--;	/* Cuando muere el exterminador, se activa el contador de segundos.*/
			}

			if (this.contSegundos_gameOver <= -1) { /* Cuando el contador sea menor o igual a -1, se detiene la musica del juego,
			se activan la imagen y el sonido de Game Over, y desaparecen los contadores que estan dentro del juego.  */
				this.musicaJuego.stop();
				this.fondoGameOver = true;
				this.contSegundos_gameOver = 0;
				this.cont_AraniasAsesinadas = false;
				this.contGeneral_AraniasAsesinadas = false;
				this.musicaFondoGameOver.loop(2);
				this.juegoTerminado.start();
			}
			
			if (entorno.sePresiono(entorno.TECLA_FIN)) {   /*Al apretar la tecla FIN, se cierra la ventana del juego. */
				System.exit(0);
			}
		}
		
		if (this.loading) { /* Es un booleano que contiene la imagen de cargando juego. Y al activarse, se ejecuta todo lo demas.*/
			this.entorno.dibujarImagenConCentro(this.loadingGame, 0, 0, 0, 0, 0, 1.018);
			this.sonidoLoading.start();
			this.contSegundosCargandoJuego--;
		}
	
		if (this.fondoMenu) {  	/*BOOLEANO QUE PREGUNTA SI EL MENU ESTA ACTIVADO. */
			this.entorno.dibujarImagenConCentro (this.menu, 0, 0, 0, 0, 0, 1.018);
			this.inicio_Menu.loop(3); /* Como el sonido es corto, hago que se reproduzca 3 veces. */
			
			if (entorno.sePresiono(entorno.TECLA_ENTER)) {
				this.botonInicio.start();
				this.fondoMenu = false;
				this.inicio_Menu.stop();
				}		
			
			if (entorno.sePresiono(entorno.TECLA_FIN)) {  /*Al apretar la tecla FIN, se cierra la ventana del juego. */ 
				System.exit(0);
			}
		}
		
		if (this.fondoMenu == false) { /* Si desaparece la imagen del menu, se activa la imagen de CARGANDO JUEGO. */
			this.loading = true;
		}	
	
		////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		/* IMPRESIONES EN LA PANTALLA DEL JUEGO. */
		if (this.cont_AraniasAsesinadas) {
			this.entorno.cambiarFont(Font.MONOSPACED + " bold", 35, Color.white);
			this.entorno.escribirTexto("cant. objetivo", 130, 30);
		}
		
		if (this.cont_AraniasAsesinadas) {
			this.entorno.cambiarFont(Font.MONOSPACED + " bold", 50, Color.white);
			this.entorno.escribirTexto("" + this.contadorAraniasAniquiladas, 280, 85);
		}
		
		if (this.contGeneral_AraniasAsesinadas) {
			this.entorno.cambiarFont(Font.MONOSPACED + " bold", 35, Color.YELLOW);
			this.entorno.escribirTexto("cant. muertas", 445, 30);
		}
		
		if (this.contGeneral_AraniasAsesinadas) {
			this.entorno.cambiarFont(Font.MONOSPACED + " bold", 50, Color.YELLOW);
			this.entorno.escribirTexto("" + this.contadorGeneralAraniasMuertas, 460, 85);
		}
		
		if (this.contSegundosCargandoJuego <= 0) {
			this.loading = false;
			this.sonidoLoading.stop();
			this.contSegundosCargandoJuego = 0;
			this.fondoJuego = true;
			this.musicaJuego.loop(3);
			this.cont_AraniasAsesinadas = true;
			this.contGeneral_AraniasAsesinadas = true;
		}
	}

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Juego juego = new Juego();
	}
}
