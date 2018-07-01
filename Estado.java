import java.util.Random;

public class Estado {
	private int[][] espaco = {{-1,-2,-3,-4,-5},{-6,-7,-8,-9,-10},{-11,-12,-13,-14,-15},{-16,-17,-18,-19,-20},{-21,-22,-23,-24,-25}};

	public Estado() {
	}
	//Funções de movimento
	public void moverParaCima(int pos) {
		int posI = pos/10, posJ = pos%10;
		espaco[posI][posJ] = espaco[posI + 1][posJ];
		espaco[posI + 1][posJ] = 0;
	}
	public void moverParaEsquerda(int pos) {
		int posI = pos/10, posJ = pos%10;
		espaco[posI][posJ] = espaco[posI][posJ + 1];
		espaco[posI][posJ + 1] = 0;
	}
	public void moverParaDireita(int pos) {
		int posI = pos/10, posJ = pos%10;
		espaco[posI][posJ] = espaco[posI][posJ - 1];
		espaco[posI][posJ - 1] = 0;
	}
	public void moverParaBaixo(int pos) {
		int posI = pos/10, posJ = pos%10;
		espaco[posI][posJ] = espaco[posI - 1][posJ];
		espaco[posI - 1][posJ] = 0;
	}
	//--------------------------------------------------------------------------------------------
	public int coordVazio(int tamMatriz) {
		for( int i = 0; i < tamMatriz; i++ )
		{
			for( int j = 0; j < tamMatriz; j++)
			{
				if( espaco[i][j] == 0 )
				{
					return i*10 + j;
				}
			}
		}
		return -1;
	}
	public int movPossiveis(int tamMatriz) {
		int mov = 4, vazio = coordVazio(tamMatriz);
		int vazioI = vazio/10, vazioJ = vazio%10;
		if( vazioI == 0 || vazioI == (tamMatriz-1) ) {
			mov--;
		}
		if( vazioJ == 0 || vazioJ == (tamMatriz-1) ) {
			mov--;
		}
		return mov;
	}
	//--------------------------------------------------------------------------------------------
	//Getters e Setters
	public int[][] getEspaco() {
		return this.espaco;
	}
	public int setEspaco(int espaco,int i,int j, int tamMatriz) {
		for( int l = 0; l < tamMatriz; l++ )
		{
			for( int c = 0; c < tamMatriz; c++ )
			{
				if( this.espaco[l][c] == espaco && !(l == i && c == j))
				{
					return l*10 + c;
				}
			}
		}
		if( espaco < (tamMatriz*tamMatriz) && espaco >= 0)
		{
			this.espaco[i][j] = espaco;
			return -1;
		}
		else
		{
			return 100;
		}
	}
	//Overwrites
	public String toString(int tamMatriz){
		String s = "";
		for(int i = 0; i < tamMatriz; i++) {
			for(int j = 0; j < tamMatriz; j++) {
				s = s.concat("[" + espaco[i][j] + "] ");
				if(espaco[i][j] < 10) s = s.concat(" ");
			}
			s = s.concat("\n");
		}				
		return s;
	}
	public boolean equals(Object e, int tamMatriz){
		for(int i = 0; i < tamMatriz; i++) {
			for(int j = 0; j < tamMatriz; j++) {
				if( this.espaco[i][j] != ((Estado) e).getEspaco()[i][j] ) {
					return false;
				}
			}
		}
		return true;
	}
	//Outras
	public void ordenar(int tamMatriz) {
		for(int i = 0; i < tamMatriz*tamMatriz; i++) {
			espaco[i/tamMatriz][i%tamMatriz] = i;
		}
	}
	
	public void embaralhar(int numDeVezes, int tamMatriz) {
		int coordVazio;
		Aresta ultimoMov = Aresta.RAIZ;
		for(int i = 0; i < numDeVezes; i++) {
			coordVazio = coordVazio(tamMatriz);
			if(coordVazio/10 == 0 && coordVazio%10 == 0) {									//sup esq
				ultimoMov = movAleatorio(true, false, true, false, ultimoMov,coordVazio);
			} else if(coordVazio/10 == 0 && coordVazio%10 == (tamMatriz-1)) {				//sup dir
				ultimoMov = movAleatorio(true, false, false, true, ultimoMov,coordVazio);
			} else if(coordVazio/10 == (tamMatriz-1) && coordVazio%10 == 0) {				//inf esq
				ultimoMov = movAleatorio(false, true, true, false, ultimoMov,coordVazio);
			} else if(coordVazio/10 == (tamMatriz-1) && coordVazio%10 == (tamMatriz-1)) {	//inf dir
				ultimoMov = movAleatorio(false, true, false, true, ultimoMov,coordVazio);
			} else if(coordVazio/10 == 0) {													//sup meio
				ultimoMov = movAleatorio(true, false, true, true, ultimoMov,coordVazio);
			} else if(coordVazio%10 == (tamMatriz-1)) {										//dir meio
				ultimoMov = movAleatorio(true, true, false, true, ultimoMov,coordVazio);
			} else if(coordVazio/10 == (tamMatriz-1)) {										//inf meio
				ultimoMov = movAleatorio(false, true, true, true, ultimoMov,coordVazio);
			} else if(coordVazio%10 == 0) {													//esq meio
				ultimoMov = movAleatorio(true, true, true, false, ultimoMov,coordVazio);
			} else {																		//n eh borda
				ultimoMov = movAleatorio(true, true, true, true, ultimoMov,coordVazio);
			}
		}
	}

	private Aresta movAleatorio(boolean Cima, boolean Baixo, boolean Esquerda, boolean Direita, Aresta ultimoMov, int coordVazio) {
		boolean valido = false;
        Random gerador = new Random();
		int dado = gerador.nextInt(4);
		while(!valido) {
			if( dado == 0 ) {
				if(Cima && ultimoMov != Aresta.BAIXO) {
					valido = true;
					moverParaCima(coordVazio);
					return Aresta.CIMA;
				} else { dado = (dado + 1) % 4; }				
			} else if( dado == 1 ) {
				if(Baixo && ultimoMov != Aresta.CIMA) {
					valido = true;
					moverParaBaixo(coordVazio);
					return Aresta.BAIXO;
				} else { dado = (dado + 1) % 4; }				
			} else if( dado == 2 ) {
				if(Esquerda && ultimoMov != Aresta.DIREITA) {
					valido = true;
					moverParaEsquerda(coordVazio);
					return Aresta.ESQUERDA;
				} else { dado = (dado + 1) % 4; }				
			} else {
				if(Direita && ultimoMov != Aresta.ESQUERDA) {
					valido = true;
					moverParaDireita(coordVazio);
					return Aresta.DIREITA;
				} else { dado = (dado + 1) % 4; }				
			}
		}
		return Aresta.RAIZ;
	}
}
