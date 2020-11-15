
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Calcula a triangulação por "corte de orelhas" para um poligono dado. Esta
 * implementação executa em tempo O(n^2).
 * 
 * @param Poligono
 *            p
 * @return Uma lisa de triangulos.
 */
public class triangulate {

	public static ArrayList<Triangulo> run(Poligono p) {
		ArrayList<Triangulo> triangulacao = new ArrayList<>();

		PoligonoEmListaCircular pc = new PoligonoEmListaCircular(p);

		for (int i = 0; i < p.tamanho() - 2; i++)
			triangulacao.add(pc.removeOrelha());

		return triangulacao;
	}

	public static void main(String[] args) {
		
		Poligono p = new Poligono();

		Scanner sc = new Scanner(System.in);
		
		int x, y;
		while (sc.hasNextLine()) {
			x = sc.nextInt();
			y = sc.nextInt();
			p.adiciona(new Ponto(x, y));
			sc.nextLine();
		}

		ArrayList<Triangulo> triangulacao = triangulate.run(p);
		
		System.out.println(p.tamanho());
		for (int i = 0; i < p.tamanho(); i++) {
			System.out.println(p.getPonto(i));
		}

		System.out.println(triangulacao.size());

		for (Triangulo triangulo : triangulacao) {
			System.out.println(triangulo);
		}

	}
}
