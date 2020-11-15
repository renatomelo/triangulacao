
public class PoligonoEmListaCircular {

	private ListaCircular<Ponto> vertices;
	private ListaCircular<ListaCircular<Ponto>.ListNode> orelhas;

	private boolean ehSentidoHorario;

	public PoligonoEmListaCircular() {
		vertices = new ListaCircular<>();
		orelhas = new ListaCircular<>();
	}

	public PoligonoEmListaCircular(Poligono p) {
		this();
		for (int i = p.tamanho() - 1; i >= 0; i--) {
			vertices.insere(p.getPonto(i));
		}

		constroiListaOrelhas();
	}
	
	public void mostraVertices(){
		ListaCircular<Ponto>.ListNode atual = vertices.getCabeca();
		System.out.println();
		
		do {
			System.out.println(atual.conteudo+" ");
			atual = atual.prox;
		} while (atual != vertices.getCabeca());
		System.out.println();
	}

	private void constroiListaOrelhas() {
		ListaCircular<Ponto>.ListNode atual = vertices.getCabeca();
		if (atual == null)
			return;

		ehSentidoHorario = ehSentidoHorario();

		atual = vertices.getCabeca().ant;

		do {
			if (ehOrelha(atual, ehSentidoHorario())) {
				orelhas.insere(atual);
			}
			atual = atual.ant;
		} while (atual != vertices.getCabeca().ant);
	}

	private boolean ehOrelha(ListaCircular<Ponto>.ListNode v, boolean sentidohorario) {
		
		if (!ehConvexo(v, sentidohorario))
			return false;

		Triangulo tri = new Triangulo(v.ant.conteudo, v.conteudo, v.prox.conteudo);
		ListaCircular<Ponto>.ListNode atual = vertices.getCabeca();
		do {
			if (tri.ehPontoInterno(atual.conteudo)) 
				return false;
			
			atual = atual.prox;
		} while (atual != vertices.getCabeca());

		return true;
	}

	private boolean ehConvexo(ListaCircular<Ponto>.ListNode v, boolean sentidohorario) {
		boolean curvaEsquerda = Ponto.ehCurvaEsquerda(v.ant.conteudo, v.conteudo, v.prox.conteudo);
		return curvaEsquerda ^ sentidohorario;
	}

	public Triangulo removeOrelha() {
		ListaCircular<Ponto>.ListNode adjEsq = orelhas.getCabeca().conteudo.ant;
		ListaCircular<Ponto>.ListNode adjDir = orelhas.getCabeca().conteudo.prox;

		Triangulo orelha = new Triangulo(adjEsq.conteudo,
								orelhas.getCabeca().conteudo.conteudo, 
								adjDir.conteudo);

		vertices.remove(orelhas.getCabeca().conteudo);
		orelhas.remove(orelhas.getCabeca());
		
		if(ehConvexo(adjEsq, ehSentidoHorario)){
			
			if(adjEsq == orelhas.getCabeca().ant.conteudo){
				if(!ehOrelha(orelhas.getCabeca().ant.conteudo, ehSentidoHorario))
					orelhas.remove(orelhas.getCabeca().ant);
				
			}else if(ehOrelha(adjEsq, ehSentidoHorario)){
				orelhas.insere(adjEsq);
				orelhas.prox();
			}
		}
		
		if(ehConvexo(orelhas.getCabeca().conteudo, ehSentidoHorario)){
			// não é mais orelha?
			if(adjDir == orelhas.getCabeca().conteudo){
				if(!ehOrelha(orelhas.getCabeca().conteudo, ehSentidoHorario))
					orelhas.remove(orelhas.getCabeca());
				
			//nova orelha
			}else if(ehOrelha(adjDir, ehSentidoHorario)){
				orelhas.insere(adjDir);
			}
		}
		
		return orelha;
	}

	public boolean ehSentidoHorario() {
		double soma = 0;
		
		ListaCircular<Ponto>.ListNode atual = vertices.getCabeca();
		do {
			soma += (atual.prox.conteudo.getX() - atual.conteudo.getX())
					* (atual.prox.conteudo.getY() + atual.conteudo.getY());
			atual = atual.prox;
		} while (atual != vertices.getCabeca());

		return (soma > 0);
	}

}
