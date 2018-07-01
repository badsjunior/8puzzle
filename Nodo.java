

public class Nodo {

	private Nodo pai;
	public Nodo[] filhos;
	private Aresta aresta;
	private Estado estado;
	private int peso;
	public boolean expandido;
	
	public Nodo() {
		this.estado = new Estado();
		setAresta(Aresta.RAIZ);
		setPai(null);
		setFilhos(null);
		setPeso(0);
		expandido = false;
	}
	//Getters e Setters
	public Nodo getPai() {
		return pai;
	}
	public void setPai(Nodo pai) {
		this.pai = pai;
	}
	public Nodo[] getFilhos() {
		return filhos;
	}
	public void setFilhos(Nodo[] filhos) {
		this.filhos = filhos;
	}
	public Estado getEstado() {
		return estado;
	}
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	public Aresta getAresta() {
		return aresta;
	}
	public void setAresta(Aresta aresta) {
		this.aresta = aresta;
	}	
	public int getPeso() {
		return peso;
	}
	public void setPeso(int peso) {
		this.peso = peso;
	}	

}
