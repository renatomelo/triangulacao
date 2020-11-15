
/**
 * Representa um triângulo. A parte de fazer referência para os triângulos
 * adjacentes está incompleta.
 * 
 * @author renato
 *
 */

public class Triangulo {
	Ponto a, b, c;
	Triangulo adjAB, adjAC, adjBC;

	/**
	 * Inicializa o triângulo. Altera a ordem dos vertices para deixa-los em
	 * sentido anti horario.
	 * 
	 * @param a
	 * @param b
	 * @param c
	 */
	public Triangulo(Ponto a, Ponto b, Ponto c) {
		this.a = a;
		this.b = b;
		this.c = c;

		adjAB = adjAC = adjBC = null;

		// sentido anti-horario
		double soma = (b.getX() - a.getX()) * (b.getY() + a.getY()) + (c.getX() - b.getX()) * (c.getY() + b.getY())
				+ (a.getX() - c.getX()) * (a.getY() + c.getY());

		if (soma > 0) {
			Ponto aux = a;
			a = b;
			b = aux;
		}
	}

	public Ponto getA() {
		return a;
	}

	public Ponto getB() {
		return b;
	}

	public Ponto getC() {
		return c;
	}

	/**
	 * Determina se um ponto se encontra dentro de um triângulo
	 * 
	 * @param ponto
	 * @return true se o ponto está no triângulo,false caso contrario.
	 */
	public boolean ehPontoInterno(Ponto ponto) {
		return ehPontoInterno(ponto, true);
	}

	private boolean ehPontoInterno(Ponto ponto, boolean estrito) {
		if (estrito) {

			boolean l1 = Ponto.ehCurvaEsquerda(a, b, ponto);
			boolean l2 = Ponto.ehCurvaEsquerda(b, c, ponto);
			boolean l3 = Ponto.ehCurvaEsquerda(c, a, ponto);

			if (l1 && l2 && l3) {
				return true;
			}

			boolean r1 = Ponto.ehCurvaDireita(a, b, ponto);
			boolean r2 = Ponto.ehCurvaDireita(b, c, ponto);
			boolean r3 = Ponto.ehCurvaDireita(c, a, ponto);

			if (r1 && r2 && r3) {
				return true;
			}

			return false;
		} else {
			boolean l1 = !Ponto.ehCurvaDireita(a, b, ponto);
			boolean l2 = !Ponto.ehCurvaDireita(b, c, ponto);
			boolean l3 = !Ponto.ehCurvaDireita(c, a, ponto);

			if (l1 && l2 && l3) {
				return true;
			}

			boolean r1 = !Ponto.ehCurvaEsquerda(a, b, ponto);
			boolean r2 = !Ponto.ehCurvaEsquerda(b, c, ponto);
			boolean r3 = !Ponto.ehCurvaEsquerda(c, a, ponto);

			if (r1 && r2 && r3) {
				return true;
			}

			return false;
		}
	}

	public String toString() {
		String saida;
		saida = a.toString() + " " + b.toString() + " " + c.toString();

		return saida;
	}

	public enum Lado {
		AB(0), AC(1), BC(2);

		int i;

		Lado(int i) {
			this.i = i;
		}

		public static Lado valueOf(int i) {
			switch (i) {
			case 0:
				return AB;
			case 1:
				return AC;
			case 2:
				return BC;
			default:
				return null;
			}

		}
	}

	/**
	 * Retorna um triangulo adjacente
	 * 
	 * @param l
	 *            o lado ao qual o triangulo esta
	 * @return o tringulo adjacente ao lado l
	 */
	public Triangulo getTrianguloAdjacente(Lado l) {
		Triangulo adj;
		switch (l) {
		case AB:
			adj = adjAB;
			break;
		case AC:
			adj = adjAC;
			break;
		case BC:
			adj = adjBC;
			break;
		default:
			adj = null;
			break;
		}
		return adj;
	}

	public void setTrianguloAdjacente(Lado l, Triangulo t) {
		switch (l) {
		case AB:
			adjAB = t;
			break;
		case AC:
			adjAC = t;
			break;
		case BC:
			adjBC = t;
			break;
		}
	}

	public boolean ehAdjacente(Triangulo t, Lado l) {
		return (this.getTrianguloAdjacente(l) == t);
	}

//	/**
//	 * Atualiza a referencia aos triangulos adjacentes
//	 * 
//	 * @param t
//	 * @return
//	 */
//	public boolean atualizaAdjacentes(Triangulo t) {
//		if (t == null)
//			return false;
//
//		Segmento seg1, seg2;
//		for (int i = 0; i < 3; i++) {
//			for (int j = 0; j < 3; j++) {
//				seg1 = new Segmento(this.getVertice(i), this.getVertice((i + 1) % 3));
//				seg2 = new Segmento(t.getVertice(j), t.getVertice((j + 1) + 1));
//
//				if (seg1.equals(seg2)) {
//					Lado esteLado = Lado.valueOf(i);
//					Lado tLado = Lado.valueOf(j);
//
//					this.setTrianguloAdjacente(esteLado, t);
//					t.setTrianguloAdjacente(tLado, this);
//
//					return true;
//				}
//			}
//		}
//
//		return false;
//	}

	private Ponto getVertice(int i) {

		switch (i) {
		case 0:
			return a;
		case 1:
			return b;
		case 2:
			return c;
		default:
			return null;
		}
	}
}
