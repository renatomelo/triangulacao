//package triangulation;

/**
 * Representa uma lista circular duplamente encadeada
 * @author renato
 *
 * @param <V> tipo do conteúdo
 */
public class ListaCircular <V> {
	private ListNode cabeca;
	
	public ListaCircular() {
		cabeca = null;
	}
	
	public void prox(){
		cabeca = cabeca.prox;
	}
	
	public void ant(){
		cabeca = cabeca.ant;
	}
	
	public ListNode getCabeca(){
		return cabeca;
	}
	
	public void insere (V v){
		ListNode novo = new ListNode(v);
		if (cabeca == null) {
			cabeca = novo;
			
			cabeca.prox = cabeca;
			cabeca.ant = cabeca;
			
			return;
		}
		
		cabeca.ant.prox = novo;
		novo.ant = cabeca.ant;
		
		novo.prox = cabeca;
		cabeca.ant = novo;
		cabeca = novo;
	}

	public void remove(ListNode v){
		if (v.ant != null) {
			v.ant.prox = v.prox;
		}
		if(v.prox != null){
			v.prox.ant = v.ant;
		}
		if(v == cabeca){
			cabeca = v.prox;
		}
	}
	/**
	 * Estrutura que representa um elemento da lista
	 * @author renato
	 *
	 * @param <V>
	 */
	public class ListNode{
		public V conteudo;
		public ListNode ant, prox;
		
		public ListNode(){
			ant = prox = null;
		}
		
		public ListNode(V v){
			ant = prox = null;
			this.conteudo = v;
		}
	}

}
