import java.util.ArrayList;

public class Poligono {

	ArrayList<Ponto> vertices;
	private boolean ehSentidoHorario;

	public Poligono() {
		this.vertices = new ArrayList<Ponto>();
	}

	public void adiciona(Ponto p) {
		if (p != null)
			vertices.add(p);
	}

	/**
	 * Verifica se a ordem dos vertices na lista está em sentido horario.
	 * 
	 * @return true se está no sentido horario, false caso contrario.
	 */
	public boolean ehSentidoHorario() {
		double soma = 0, s1, s2;

		for (int i = 0; i < tamanho(); i++) {
			s1 = vertices.get((i + 1) % tamanho()).getX() - vertices.get(i).getX();
			s2 = vertices.get((i + 1) % tamanho()).getY() + vertices.get(i).getY();

			soma += (s1 * s2);
		}
		ehSentidoHorario = (soma > 0);
		return ehSentidoHorario;
	}

	public int tamanho() {
		return vertices.size();
	}

	public Ponto getPonto(int i) {
		if (i >= 0 && i < tamanho()) {
			return vertices.get(i);
		} else {
			return null;
		}
	}

	public void remove(int i) {
		if (i >= 0 && i < tamanho()) {
			vertices.remove(i);
		}
	}

	/**
	 * Faz com que os vértices do poligono fiquem ordenados no sentido anti
	 * horario
	 */
	public void sentidoAntiHorario() {

		if (ehSentidoHorario()) {

			Ponto aux;

			for (int i = 1; i <= vertices.size() / 2; i++) {
				aux = vertices.get(i);
				vertices.set(i, vertices.get(vertices.size() - i));
				vertices.set(vertices.size() - i, aux);
			}
		}
		ehSentidoHorario = false;
	}

	public boolean ehConvexo() {
		for (int i = 0; i < tamanho(); i++) {
			if (!ehConvexo(i))
				return false;
		}
		return true;
	}

	public boolean ehConvexo(int i) {
		int ant = i - 1;
		int prox = (i + 1) % tamanho();

		if (ant < 0)
			ant += tamanho();

		boolean ehCurvaEsq;
		ehCurvaEsq = Ponto.ehCurvaEsquerda(getPonto(ant), getPonto(i), getPonto(prox));

		return ehCurvaEsq ^ ehSentidoHorario;
	}

}
