import java.util.ArrayList;
import java.util.StringTokenizer;

public class Principal {

	public static void main(String[] args) {
		try {

			String entrada = "31 41 59 26 57";

			System.out.println("Entrada: " + entrada);
			String resultado = ejecucion(entrada);
			System.out.println("Salida: " + resultado);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static String ejecucion(String entrada) throws Exception {
		StringTokenizer tokenizer = new StringTokenizer(entrada);

		// Chequeo de que existan específicamente 4 espacios o 5 elementos en el string.
		if (tokenizer.countTokens() != 5)
			throw new Exception("Se requieren 5 componentes numéricos, se encontraron " + tokenizer.countTokens());

		// Almaceno todas las unidades en su valor de onzas.
		Onza onzas = new Onza(0);
		onzas.setValor(onzas.getValor() + new Galon(Double.parseDouble(tokenizer.nextToken())).toOnza().getValor());
		onzas.setValor(onzas.getValor() + new Cuarta(Double.parseDouble(tokenizer.nextToken())).toOnza().getValor());
		onzas.setValor(onzas.getValor() + new Pinta(Double.parseDouble(tokenizer.nextToken())).toOnza().getValor());
		onzas.setValor(onzas.getValor() + new Taza(Double.parseDouble(tokenizer.nextToken())).toOnza().getValor());
		onzas.setValor(onzas.getValor() + new Onza(Double.parseDouble(tokenizer.nextToken())).getValor());
		while (tokenizer.hasMoreTokens()) {
			String token = tokenizer.nextToken();

		}

		// Distribuyo las onzas totales de mayor unidad a menor unidad buscando unidades
		// totales.
		// Voy quitango el número de Unidades Respectivas del total de onzas en cada
		// paso.
		Galon galones = new Galon((int) onzas.toGalon().getValor());

		onzas.setValor(onzas.getValor() - galones.toOnza().getValor());

		Cuarta cuartas = new Cuarta((int) onzas.toCuarta().getValor());

		onzas.setValor(onzas.getValor() - cuartas.toOnza().getValor());

		Pinta pintas = new Pinta((int) onzas.toPinta().getValor());

		onzas.setValor(onzas.getValor() - pintas.toOnza().getValor());

		Taza tazas = new Taza((int) onzas.toTaza().getValor());

		onzas.setValor(onzas.getValor() - tazas.toOnza().getValor());

		// Regreso los valores finales como un String.
		String ret = "";
		ret += (int) galones.getValor() + " ";
		ret += (int) cuartas.getValor() + " ";
		ret += (int) pintas.getValor() + " ";
		ret += (int) tazas.getValor() + " ";
		ret += (int) onzas.getValor() + " ";

		return ret;
	};

}

abstract class UnidadBritanica {
	public static final double GALON_A_CUARTAS = 4.0;
	public static final double CUARTA_A_PINTAS = 2.0;
	public static final double PINTA_A_TAZAS = 2.0;
	public static final double TAZA_A_ONZAS = 8.0;

	public static final double GALON_A_PINTAS = GALON_A_CUARTAS * CUARTA_A_PINTAS;
	public static final double GALON_A_TAZAS = GALON_A_PINTAS * PINTA_A_TAZAS;
	public static final double GALON_A_ONZAS = GALON_A_TAZAS * TAZA_A_ONZAS;

	public static final double CUARTA_A_GALONES = 1.0 / GALON_A_CUARTAS;
	public static final double CUARTA_A_TAZAS = CUARTA_A_PINTAS * PINTA_A_TAZAS;
	public static final double CUARTA_A_ONZAS = CUARTA_A_TAZAS * TAZA_A_ONZAS;

	public static final double PINTA_A_GALONES = 1.0 / GALON_A_PINTAS;
	public static final double PINTA_A_CUARTAS = 1.0 / CUARTA_A_PINTAS;
	public static final double PINTA_A_ONZAS = PINTA_A_TAZAS * TAZA_A_ONZAS;

	public static final double TAZA_A_GALONES = 1.0 / GALON_A_TAZAS;
	public static final double TAZA_A_CUARTAS = 1.0 / CUARTA_A_TAZAS;
	public static final double TAZA_A_PINTAS = 1.0 / PINTA_A_TAZAS;

	public static final double ONZA_A_GALONES = 1.0 / GALON_A_ONZAS;
	public static final double ONZA_A_CUARTAS = 1.0 / CUARTA_A_ONZAS;
	public static final double ONZA_A_PINTAS = 1.0 / PINTA_A_ONZAS;
	public static final double ONZA_A_TAZAS = 1.0 / TAZA_A_ONZAS;

	private double valor;

	public UnidadBritanica(double valor) throws Exception {
		excepcionValorMenorACero(valor);
		this.valor = valor;
	}

	public Galon toGalon() {
		String tipo = this.getClass().getName();
		try {
			switch (tipo) {
			case "Galon":
				return new Galon(this.valor);
			case "Cuarta":
				return new Galon(this.valor * CUARTA_A_GALONES);
			case "Pinta":
				return new Galon(this.valor * PINTA_A_GALONES);
			case "Taza":
				return new Galon(this.valor * TAZA_A_GALONES);
			case "Onza":
				return new Galon(this.valor * ONZA_A_GALONES);
			default:
				throw new Exception("Tipo de dato no reconocido. - toGalon()");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public Cuarta toCuarta() {
		String tipo = this.getClass().getName();
		try {
			switch (tipo) {
			case "Galon":
				return new Cuarta(this.valor * GALON_A_CUARTAS);
			case "Cuarta":
				return new Cuarta(this.valor);
			case "Pinta":
				return new Cuarta(this.valor * PINTA_A_CUARTAS);
			case "Taza":
				return new Cuarta(this.valor * TAZA_A_CUARTAS);
			case "Onza":
				return new Cuarta(this.valor * ONZA_A_CUARTAS);
			default:
				throw new Exception("Tipo de dato no reconocido. - toCuarta()");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public Pinta toPinta() {
		String tipo = this.getClass().getName();
		try {
			switch (tipo) {
			case "Galon":
				return new Pinta(this.valor * GALON_A_PINTAS);
			case "Cuarta":
				return new Pinta(this.valor * CUARTA_A_PINTAS);
			case "Pinta":
				return new Pinta(this.valor);
			case "Taza":
				return new Pinta(this.valor * TAZA_A_PINTAS);
			case "Onza":
				return new Pinta(this.valor * ONZA_A_PINTAS);
			default:
				throw new Exception("Tipo de dato no reconocido. - toPinta()");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public Taza toTaza() {
		String tipo = this.getClass().getName();
		try {
			switch (tipo) {
			case "Galon":
				return new Taza(this.valor * GALON_A_TAZAS);
			case "Cuarta":
				return new Taza(this.valor * CUARTA_A_TAZAS);
			case "Pinta":
				return new Taza(this.valor * PINTA_A_TAZAS);
			case "Taza":
				return new Taza(this.valor);
			case "Onza":
				return new Taza(this.valor * ONZA_A_TAZAS);
			default:
				throw new Exception("Tipo de dato no reconocido. - toTaza()");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public Onza toOnza() {
		String tipo = this.getClass().getName();
		try {
			switch (tipo) {
			case "Galon":
				return new Onza(this.valor * GALON_A_ONZAS);
			case "Cuarta":
				return new Onza(this.valor * CUARTA_A_ONZAS);
			case "Pinta":
				return new Onza(this.valor * PINTA_A_ONZAS);
			case "Taza":
				return new Onza(this.valor * TAZA_A_ONZAS);
			case "Onza":
				return new Onza(this.valor);
			default:
				throw new Exception("Tipo de dato no reconocido. - toOnza()");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public double getValor() {
		return this.valor;
	}

	public void setValor(double valor) throws Exception {
		excepcionValorMenorACero(valor);
		this.valor = valor;
	}

	private void excepcionValorMenorACero(double valor) throws Exception {
		if (valor < 0)
			throw new Exception("Valor debe ser mayor o igual a cero.");
	}

	public String toString() {
		return "" + this.valor + " " + this.getClass().getName();
	}

}

class Galon extends UnidadBritanica {

	public Galon(double valor) throws Exception {
		super(valor);
		// TODO Auto-generated constructor stub
	}

}

class Cuarta extends UnidadBritanica {

	public Cuarta(double valor) throws Exception {
		super(valor);
		// TODO Auto-generated constructor stub
	}

}

class Pinta extends UnidadBritanica {

	public Pinta(double valor) throws Exception {
		super(valor);
		// TODO Auto-generated constructor stub
	}

}

class Taza extends UnidadBritanica {

	public Taza(double valor) throws Exception {
		super(valor);
		// TODO Auto-generated constructor stub
	}

}

class Onza extends UnidadBritanica {

	public Onza(double valor) throws Exception {
		super(valor);
		// TODO Auto-generated constructor stub
	}

}
