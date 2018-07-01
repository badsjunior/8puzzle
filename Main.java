import java.lang.management.ManagementFactory;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
  
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.formula.FormulaParseException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Main {
	static Nodo raiz = new Nodo();
	static Nodo destino = new Nodo();
	static int numTestados = 0, numParaSol = 0;
	static double time = 0, memory = 0, interfaceMemory = 0;
	
	public static void main(String[] args) {
		
  	  	try { UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); } 
	      catch (UnsupportedLookAndFeelException e) {		      }
	      catch (ClassNotFoundException e) {		      }
	      catch (InstantiationException e) {		      }
	      catch (IllegalAccessException e) {		      }

		Interface.main(null);
		return;
	}

	public static String verificarSolucionabilidade( Estado inicial, Estado meta, int tamMatriz ) {
		int inversoes = 0;
		String sol = "";
		boolean achou = false;
		int metaI,metaJ;
		for( int iniI = 0; iniI < tamMatriz; iniI++ ) {
			for( int iniJ = 0; iniJ < tamMatriz; iniJ++ ) {
				if( meta.getEspaco()[iniI][iniJ] != 0 ) {
					metaI = -1; metaJ = -1;
					for( int buscaI = 0; buscaI < tamMatriz; buscaI++ ) {
						for( int buscaJ = 0; buscaJ < tamMatriz; buscaJ++ ) {
							if( meta.getEspaco()[buscaI][buscaJ] == inicial.getEspaco()[iniI][iniJ] ) {
								metaI = buscaI;
								metaJ = buscaJ;
							}
						}
					}
					if( meta.getEspaco()[metaI][metaJ] != 0 ) {
						for( int i = 0; i < tamMatriz; i++ ) {
							for( int j = 0; j < tamMatriz; j++ ) {
								if( i*tamMatriz + j < metaI*tamMatriz + metaJ ) {
									achou = false;
									for( int compI = 0; compI < tamMatriz; compI++ ) {
										for( int compJ = 0; compJ < tamMatriz; compJ++ ) {
											if( compI*tamMatriz + compJ < iniI*tamMatriz + iniJ && 
												meta.getEspaco()[i][j] == inicial.getEspaco()[compI][compJ]) {
												achou = true;
											}
										}
									}
									if( !achou ) {
										sol = sol.concat("Inversão #" + ++inversoes + " - [" + meta.getEspaco()[i][j] +
											"] não encontrado antes do [" +	meta.getEspaco()[metaI][metaJ] + "]\n");
									}
								}
							}
						}
					}
				}
			}			
		}
		sol = sol.concat("Total de Inversões = " + inversoes + " (" );
		if( inversoes == 0 ) sol = sol.concat("Solucionável!");
		else {
			if( inversoes % 2 == 0 ) {
				sol = sol.concat("par)\n");
				if( tamMatriz % 2 == 0 ) {
					sol = sol.concat("Tamanho da Matriz = " + tamMatriz + "(par)\n(par - par) Insolucionável!" );
				} else {
					sol = sol.concat("Tamanho da Matriz = " + tamMatriz + "(ímpar)\n(par - ímpar) Solucionável!" );				
				}
			} else {
				sol = sol.concat("Ímpar)\n");
				if( tamMatriz % 2 == 0 ) {
					sol = sol.concat("Tamanho da Matriz = " + tamMatriz + "(par)\n(ímpar - par) Solucionável!" );
				} else {
					sol = sol.concat("Tamanho da Matriz = " + tamMatriz + "(ímpar)\n(ímpar - ímpar) Insolucionável!" );				
				}
			}
		}
		return sol;
	}

	public static void Buscar(String metodo, Estado inicial, Estado meta, int limite, int tamMatriz, boolean impedirCiclos ) {
		memory = 0;
		interfaceMemory = getCurrentlyUsedMemory();
		raiz.setEstado(inicial);
		Nodo atual = raiz;
		ArrayList<Nodo> lista = new ArrayList<>();
		lista.add(atual);
		double start = System.currentTimeMillis();
		if( metodo.equals("BuscaEmLargura")) {
			BuscaEmLargura(lista,meta,limite,tamMatriz,impedirCiclos);
		} else if( metodo.equals("BuscaEmProfundidade")) {
			BuscaEmProfundidade(lista,meta,limite,tamMatriz,impedirCiclos);
		} else if( metodo.equals("BuscaEmProfundidadeIterativa")) {
			BuscaEmProfundidadeIterativa(lista,meta,limite,tamMatriz,impedirCiclos);
		} else if( metodo.equals("AestrelaNumForaDoMeta")) {
			Aestrela(meta,limite,"NumForaDoMeta",tamMatriz,impedirCiclos);
		} else if( metodo.equals("AestrelaManhatan")) {
			Aestrela(meta,limite,"distanciaManhatan",tamMatriz,impedirCiclos);			
		} else if( metodo.equals("RandonRestartHillClimbing")) {
			RandonRestartHillClimbing(meta,limite,tamMatriz,impedirCiclos);
		}
		double end = System.currentTimeMillis();
		time = end - start;
		System.gc();
	}
	
	public static void BuscaParaEscrita(String metodo, Estado inicial, Estado meta, int limite, int tamMatriz, boolean impedirCiclos ) {
		memory = 0;
		interfaceMemory = getCurrentlyUsedMemory();
		raiz.setEstado(inicial);
		Nodo atual = raiz;
		ArrayList<Nodo> lista = new ArrayList<>();
		lista.add(atual);
		double start = System.currentTimeMillis();
		if( metodo.equals("BFS")) {
			BuscaEmLargura(lista,meta,limite,tamMatriz,impedirCiclos);
		} else if( metodo.equals("DFS")) {
			BuscaEmProfundidade(lista,meta,limite,tamMatriz,impedirCiclos);
		} else if( metodo.equals("IDS")) {
			BuscaEmProfundidadeIterativa(lista,meta,limite,tamMatriz,impedirCiclos);
		} else if( metodo.equals("A*1")) {
			Aestrela(meta,limite,"NumForaDoMeta",tamMatriz,impedirCiclos);
		} else if( metodo.equals("A*2")) {
			Aestrela(meta,limite,"distanciaManhatan",tamMatriz,impedirCiclos);			
		}
		double end = System.currentTimeMillis();
		time = end - start;
	}
	//--------------------------------------------------------------------------------------------
	public static void BuscaEmLargura(ArrayList<Nodo> lista, Estado meta, int limite, int tamMatriz, boolean impedirCiclos ) {
		while( !lista.get(0).getEstado().equals(meta,tamMatriz) && (numTestados < limite || limite < 0) )
		{
			expandir(lista.get(0),tamMatriz,impedirCiclos);
			for( int i = 0; i < lista.get(0).getFilhos().length; i++)
			{
				lista.add(lista.get(0).getFilhos()[i]);
			}
			lista.remove(0);
			//system.out.println("Testando... Estados Testados: " + numTestados);
		}
		destino = lista.get(0);
	}
	//Funções da BuscaEmLargura
	//--------------------------------------------------------------------------------------------
	public static void BuscaEmProfundidade(ArrayList<Nodo> lista, Estado meta, int limite, int tamMatriz, boolean impedirCiclos ) {
		while( !lista.get(0).getEstado().equals(meta,tamMatriz) && (numTestados < limite || limite < 0) )
		{
			expandir(lista.get(0),tamMatriz,impedirCiclos);
			for( int i = 0; i < lista.get(0).getFilhos().length; i++)
			{
				lista.add(1,lista.get(0).getFilhos()[i]);
			}
			lista.remove(0);
			//system.out.println("Testando... Estados Testados: " + numTestados);
		}
		destino = lista.get(0);
	}
	
	public static void BuscaEmProfundidadeIterativa(ArrayList<Nodo> lista, Estado meta, int limite, int tamMatriz, boolean impedirCiclos ) {
		int profAtual = 1, posPilha = 0;
		while( !lista.get(posPilha).getEstado().equals(meta,tamMatriz) && (numTestados < limite || limite < 0) )
		{
			if(profundidade(lista.get(posPilha),tamMatriz) < profAtual && !lista.get(posPilha).expandido )
			{
				expandir(lista.get(posPilha),tamMatriz,impedirCiclos);
				Nodo[] aux = lista.get(posPilha).getFilhos();
				for( int i = 0; i < aux.length; i++)
				{
					lista.add(posPilha+1,aux[i]);
				}
				lista.remove(posPilha);
				if(posPilha == lista.size()) posPilha--;
			}
			else
			{
				if(posPilha >= lista.size()-1)
				{
					profAtual++;
					posPilha = 0;
				}
				else
					posPilha++;
			}
			//system.out.println("Testando... Estados Testados: " + numTestados);
		}
		destino = lista.get(posPilha);
	}
	//Funções da BuscaEmProfundidade
	//--------------------------------------------------------------------------------------------
	public static void Aestrela( Estado meta, int limite, String heuristica, int tamMatriz, boolean impedirCiclos ) {
		int g,h,f,p;
		Nodo atual = raiz;
		ArrayList<Nodo> pesosOrdenados = new ArrayList<>();
		
		if(heuristica.equals("distanciaManhatan"))
			h = distanciaManhatan(atual.getEstado(), meta, tamMatriz);
		else
			h = numerosForaDaMeta(atual.getEstado(), meta, tamMatriz);
		
		g = profundidade(atual,tamMatriz);
		f = g + h;
		atual.setPeso(f);
		pesosOrdenados.add(atual);
		while( !atual.getEstado().equals(meta,tamMatriz) && (numTestados < limite || limite < 0) )
		{
			expandir(atual,tamMatriz,impedirCiclos);
			for( int i = 0; i < atual.getFilhos().length; i++ ) {
				if(heuristica.equals("distanciaManhatan"))
					h = distanciaManhatan(atual.getEstado(), meta, tamMatriz);
				else
					h = numerosForaDaMeta(atual.getEstado(), meta, tamMatriz);
				g = profundidade(atual.getFilhos()[i],tamMatriz);
				f = g + h;
				atual.getFilhos()[i].setPeso(f);
				p = 0;
				while(true) {
					p++;
					if (p == pesosOrdenados.size()) break;
					else if (f <= pesosOrdenados.get(p).getPeso()) break;
				}
				pesosOrdenados.add(p, atual.getFilhos()[i]);
			}
			pesosOrdenados.remove(atual);
			atual = pesosOrdenados.get(0);
			//atual = buscarMenorPeso();
			//system.out.println("Testando... Estados Testados: " + numTestados);
		}
		destino = atual;
	}
	//Funções do Aestrela
	private static Nodo buscarMenorPeso() {
		ArrayList<Nodo> listaAux = new ArrayList<>();
		Nodo menorPeso = new Nodo();
		listaAux.add(raiz);
		menorPeso.setPeso(99999999);;
	
		while( !listaAux.isEmpty() ) {
			if( listaAux.get(0).getFilhos() != null ) {
				for(Nodo filho: listaAux.get(0).getFilhos()) {
					listaAux.add(filho);
				}
			}
			if( listaAux.get(0).getPeso() < menorPeso.getPeso() &&
					!listaAux.get(0).expandido ) {
				menorPeso = listaAux.get(0);
			}
			listaAux.remove(0);
		}
		return menorPeso;
	}
	
	//--------------------------------------------------------------------------------------------
	public static void RandonRestartHillClimbing(Estado meta, int limite, int tamMatriz, boolean impedirCiclos ) {
		int f;
		Nodo atual = raiz;
		f = distanciaManhatan(atual.getEstado(), meta, tamMatriz);
		atual.setPeso(f);
		if( limite < 0 ) {
			while( !atual.getEstado().equals(meta,tamMatriz)  ) {
				expandir(atual,tamMatriz, impedirCiclos);
				Estado aux = atual.getEstado();
				int menorF = atual.getPeso();
				if( atual.getPai() != null ) {
					menorF = atual.getPai().getPeso();
				}
				for( int i = 0; i < atual.getFilhos().length; i++ ) {
					f = distanciaManhatan(atual.getFilhos()[i].getEstado(), meta, tamMatriz);
					atual.getFilhos()[i].setPeso(f);
					if( f < menorF ) {
						atual = atual.getFilhos()[i];
						menorF = f;
					}
				}
				if( aux.equals(atual.getEstado(),tamMatriz) ) {
					atual = randomRestart();
				}
			}
			destino = atual;
		} else {
			Nodo menorCam = new Nodo();
			menorCam.setPai(menorCam);
			while(numTestados < limite) {
				expandir(atual,tamMatriz,impedirCiclos);
				Estado aux = atual.getEstado();
				int menorF = atual.getPeso();
				if( atual.getPai() != null ) {
					menorF = atual.getPai().getPeso();
				}
				for( int i = 0; i < atual.getFilhos().length; i++ ) {
					f = distanciaManhatan(atual.getFilhos()[i].getEstado(), meta, tamMatriz);
					atual.getFilhos()[i].setPeso(f);
					if( f < menorF ) {
						atual = atual.getFilhos()[i];
						menorF = f;
					}
				}
				if( aux.equals(atual.getEstado(),tamMatriz) ) {
						atual = randomRestart();
				}
				if( atual.getEstado().equals(meta,tamMatriz) && profundidade(atual,tamMatriz) < profundidade(menorCam,tamMatriz) ) {
					menorCam = atual;
				}
			}
			destino = menorCam;
		}
	}
	//Funções do RandonRestartHillClimbing
	private static Nodo randomRestart() {
		ArrayList<Nodo> listaAux = new ArrayList<>();
		ArrayList<Nodo> naoExpandidos = new ArrayList<>();
		listaAux.add(raiz);
	
		while( !listaAux.isEmpty() ) {
			if( listaAux.get(0).getFilhos() != null ) {
				for(Nodo filho: listaAux.get(0).getFilhos()) {
					listaAux.add(filho);
				}
			}
			if(	!listaAux.get(0).expandido ) {
				naoExpandidos.add(listaAux.get(0));
			}
			listaAux.remove(0);
		}
		Random r = new Random();
		return naoExpandidos.get(r.nextInt(naoExpandidos.size()));
	}
	//--------------------------------------------------------------------------------------------

	//Heurísticas
	private static int numerosForaDaMeta( Estado atual, Estado meta, int tamMatriz ) {
		int desloc = 0;
		for( int atualI = 0; atualI < tamMatriz; atualI++ ) {
			for( int atualJ = 0; atualJ < tamMatriz; atualJ++ ) {
				if(meta.getEspaco()[atualI][atualJ] != atual.getEspaco()[atualI][atualJ]) {
					desloc++;
				}
			}
		}
		return desloc;
	}
	
	private static int distanciaManhatan( Estado atual, Estado meta, int tamMatriz ) {
		int metaI = 0, metaJ = 0, dist = 0;
		for( int atualI = 0; atualI < tamMatriz; atualI++ ) {
			for( int atualJ = 0; atualJ < tamMatriz; atualJ++ ) {
				for( int buscaI = 0; buscaI < tamMatriz; buscaI++ ) {
					for( int buscaJ = 0; buscaJ < tamMatriz; buscaJ++ ) {
						if( meta.getEspaco()[buscaI][buscaJ] == atual.getEspaco()[atualI][atualJ] ) {
							metaI = buscaI;
							metaJ = buscaJ;
						}
					}
				}
				dist += Math.abs(metaI - atualI);
				dist += Math.abs(metaJ - atualJ);
			}
		}
		return dist;
	}
	//--------------------------------------------------------------------------------------------
	//Funções de Árvore
	private static int profundidade( Nodo nodo, int tamMatriz) {
		int p = 0;
		Nodo aux = nodo;
		if( nodo.getPai() != null ) {
			if( nodo.getPai().getEstado().equals(nodo.getEstado(),tamMatriz) ) {
				return 999999999;
			}
		}
		while( aux.getPai() != null ) {
			aux = aux.getPai();
			p++;
		}
		return p;
	}

	private static void expandir( Nodo nodo, int tamMatriz, boolean impedirCiclos )	{
		numTestados++;
		nodo.expandido = true;
		ArrayList<Nodo> aux = new ArrayList<>();
		for( int i = 0; i < nodo.getEstado().movPossiveis(tamMatriz); i++ ){
			Nodo fil = new Nodo();
			Estado copia = new Estado();
			for( int l = 0; l < tamMatriz; l++ ) {
				for( int c = 0; c < tamMatriz; c++) {
					copia.setEspaco(nodo.getEstado().getEspaco()[l][c], l, c, tamMatriz);
				}
			}
			fil.setEstado(copia);
			fil.setPai(nodo);
			fil.filhos = new Nodo[0];
			aux.add(fil);
		}
		int i = 0;
		int coordVazio = nodo.getEstado().coordVazio(tamMatriz);
		if ( coordVazio/10 != (tamMatriz-1) ) {					//Se o 0 não está na última linha, pode mover para cima
			aux.get(i).getEstado().moverParaCima(coordVazio);
			aux.get(i).setAresta(Aresta.CIMA);
			if( impedirCiclos ) {
				if( temNaArvore( aux.get(i).getEstado(),tamMatriz) ) {	//Verifica em toda a árvore
					aux.remove(i);
				} else { i++; }
			} else {
				if( nodo.getPai() != null ) {
					if( nodo.getPai().getEstado().equals(aux.get(i).getEstado(), tamMatriz) ) { 	//Verifica só o avô
						aux.remove(i);
					} else { i++; }
				} else { i++; }
			}
		}
		if ( coordVazio/10 != 0 ) {								//Se o 0 não está na primeira linha, pode mover para baixo
			aux.get(i).getEstado().moverParaBaixo(coordVazio);
			aux.get(i).setAresta(Aresta.BAIXO);
			if( impedirCiclos ) {
				if( temNaArvore( aux.get(i).getEstado(),tamMatriz) ) {	//Verifica em toda a árvore
					aux.remove(i);
				} else { i++; }
			} else {
				if( nodo.getPai() != null ) {
					if( nodo.getPai().getEstado().equals(aux.get(i).getEstado(), tamMatriz) ) { 	//Verifica só o avô
						aux.remove(i);
					} else { i++; }
				} else { i++; }
			}
		}
		if ( coordVazio%10 != (tamMatriz-1) ) {					//Se o 0 não está na última coluna, pode mover para esquerda
			aux.get(i).getEstado().moverParaEsquerda(coordVazio);
			aux.get(i).setAresta(Aresta.ESQUERDA);
			if( impedirCiclos ) {
				if( temNaArvore( aux.get(i).getEstado(),tamMatriz) ) {	//Verifica em toda a árvore
					aux.remove(i);
				} else { i++; }
			} else {
				if( nodo.getPai() != null ) {
					if( nodo.getPai().getEstado().equals(aux.get(i).getEstado(), tamMatriz) ) { 	//Verifica só o avô
						aux.remove(i);
					} else { i++; }
				} else { i++; }
			}
		}
		if ( coordVazio%10 != 0 ) {								//Se o 0 não está na primeira coluna, pode mover para direita
			aux.get(i).getEstado().moverParaDireita(coordVazio);
			aux.get(i).setAresta(Aresta.DIREITA);
			if( impedirCiclos ) {
				if( temNaArvore( aux.get(i).getEstado(),tamMatriz) ) {	//Verifica em toda a árvore
					aux.remove(i);
				} else { i++; }
			} else {
				if( nodo.getPai() != null ) {
					if( nodo.getPai().getEstado().equals(aux.get(i).getEstado(), tamMatriz) ) { 	//Verifica só o avô
						aux.remove(i);
					} else { i++; }
				} else { i++; }
			}
		}
		//for( Nodo filho: aux ) {	//Remove as cópias que não foram usadas para gerar movimentos
		//	if(filho.getEstado().equals(nodo.getEstado(),tamMatriz)) aux.remove(filho);
		///}
		nodo.setFilhos(aux.toArray(new Nodo[aux.size()]));
		
		if(memory < getCurrentlyUsedMemory())
			memory = getCurrentlyUsedMemory();
	}
	
	private static boolean temNaArvore( Estado procurado, int tamMatriz ) {
		ArrayList<Nodo> listaAux = new ArrayList<>();
		listaAux.add(raiz);
	
		while( !listaAux.get(0).getEstado().equals(procurado,tamMatriz) ) {
			if( listaAux.get(0).getFilhos() != null ) {
				for(Nodo filho: listaAux.get(0).getFilhos()) {
					listaAux.add(filho);
				}
			}
			listaAux.remove(0);
			if( listaAux.isEmpty() ) {
				return false;
			}
		}
		return true;
	}

	//--------------------------------------------------------------------------------------------
	//Funções de Interface
	public static String CaminhoParaASolucao(int tamMatriz) {
		String s = "ESTADO META\n";
		int contador = 0;
		Nodo aux = new Nodo();
		aux = destino;
		while( aux.getPai() != null ) {
			s = s.concat(aux.getEstado().toString(tamMatriz) + "" + aux.getAresta() + "\n" );
			aux = aux.getPai();
			contador++;
		}
		s = s.concat(aux.getEstado().toString(tamMatriz) + "ESTADO INICIAL\n" );
		numParaSol = contador;
		return s;
	}
	
	public static String ArvoreDeExploracao(String string, Nodo nodo, int prof, int tamMatriz) {
		String s = "";
		if( numTestados > 500 ) {
			return "Desenho desabilitado por default\nÁrvore de Exploração muito grande (>500)";
		}
		s = s.concat(string);
		for( int i = -1; i < tamMatriz; i++ ) {
			for( int p = 0; p < prof; p++ ) {
				s = s.concat("\t");
			}
			if( i == -1 ) {
				s = s.concat("<" + prof + "> (" + nodo.getAresta() + ")");
				if( nodo.getPeso() > 0 ) {
					s = s.concat(" F: " + nodo.getPeso() );
				}
				if( nodo.expandido ) {
					s = s.concat(" X");					
				}
				s = s.concat("\n");
			} else {
				for(int j = 0; j < tamMatriz; j++) {
					s = s.concat("[" + nodo.getEstado().getEspaco()[i][j] + "] ");
					if(nodo.getEstado().getEspaco()[i][j] < 10) s = s.concat(" ");
				}
				s = s.concat("\n");
			}
		}
		s = s.concat("\n");
		if( nodo.getFilhos() != null ) {
			for( int f = 0; f < nodo.getFilhos().length; f++ ) {
				s = ArvoreDeExploracao(s,nodo.getFilhos()[f],prof + 1,tamMatriz);
			}
		}
		return s;
	}
	
	public static String Estatisticas(int tamMatriz) {
		DecimalFormat doisNum = new DecimalFormat("#.##");
		String s = "";
		s = s.concat("Número de movimentos para a solução: " + numParaSol + "\n" );	
		s = s.concat("Número de estados testados: " + numTestados + "\n" );
		s = s.concat("Tempo em execução: " + time + "ms\n" );
		s = s.concat("Memória usada na interface: " + doisNum.format(interfaceMemory/(1024*1024)) + "mb\n" );
		s = s.concat("Memória usada no algoritmo: " + doisNum.format((memory-interfaceMemory)/(1024*1024)) + "mb\n" );
		s = s.concat("Memória usada no total: " + doisNum.format(memory/(1024*1024)) + "mb\n" );
		return s;
	}

	public static double getCurrentlyUsedMemory() {
		return
			ManagementFactory.getMemoryMXBean().getHeapMemoryUsage().getUsed() +
			ManagementFactory.getMemoryMXBean().getNonHeapMemoryUsage().getUsed();
	}

	public static int numEstadosParaSolucao(int tamMatriz) {
		int contador = 0;
		Nodo aux = new Nodo();
		aux = destino;
		while( aux.getPai() != null ) {
			aux = aux.getPai();
			contador++;
		}
		return contador;
	}
	
	public static void gerarArquivos() {
		Estado inicial = new Estado();
		Estado meta = new Estado();
		ArrayList<String> algoritmos = new ArrayList<>();
		/*algoritmos.add("BFS"); algoritmos.add("DFS"); algoritmos.add("IDS");*/ algoritmos.add("A*1"); algoritmos.add("A*2");
		for(String algoritmo: algoritmos) {
			for(int tamMatriz = 2; tamMatriz < 6; tamMatriz++) {
				meta.ordenar(tamMatriz);
				for(int numEmbar = 5; numEmbar <= 50; numEmbar += 5) {
					for(int i = 0; i < 100; i++) {
						inicial.ordenar(tamMatriz);
						inicial.embaralhar(numEmbar, tamMatriz);
						BuscaParaEscrita(algoritmo, inicial, meta, 100000, tamMatriz, false);
						escreveLinha(i+1,numEmbar,algoritmo,tamMatriz,numEstadosParaSolucao(tamMatriz),numTestados,time,interfaceMemory,memory);
						reset();
						System.out.println(i + ";" + numEmbar + ";" + tamMatriz + ";" + algoritmo);
						System.gc();
					}
					escreveEstatisticas(numEmbar,algoritmo,tamMatriz);
				}
				for(int i = 0; i < 100; i++) {
					inicial.ordenar(tamMatriz);
					inicial.embaralhar(500, tamMatriz);
					BuscaParaEscrita(algoritmo, inicial, meta, 100000, tamMatriz, false);
					escreveLinha(i+1,500,algoritmo,tamMatriz,numEstadosParaSolucao(tamMatriz),numTestados,time,interfaceMemory,memory);
					reset();
					System.out.println(i + ";" + 500 + ";" + tamMatriz + ";" + algoritmo);
					System.gc();
				}
				escreveEstatisticas(500,algoritmo,tamMatriz);
			}
		}
	}
	
	//--------------------------------------------------------------------------------------------
	//Funções de saída
	public static void criaTabela() {
		try { FileInputStream file = new FileInputStream(new File("PATH"));
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		XSSFSheet report = workbook.getSheetAt(0);
				
		Row row = report.createRow(0);
		Cell cellNumExec	= row.createCell(10);
		Cell cellEmbar 		= row.createCell(1);
		Cell cellTamMatriz	= row.createCell(2);
		Cell cellAlgoritmo 	= row.createCell(3);
		Cell cellCaminho 	= row.createCell(4);
		Cell cellTestados 	= row.createCell(5);
		Cell cellTempo 		= row.createCell(6);
		Cell cellMemAntes 	= row.createCell(7);
		Cell cellMemDepois 	= row.createCell(8);
		Cell cellMemDifer	= row.createCell(9);

		cellNumExec		.setCellValue("Execução Nº");
		cellEmbar		.setCellValue("Nº de Embaralhamentos");
		cellTamMatriz	.setCellValue("Tamanho da Matriz");
		cellAlgoritmo	.setCellValue("Algoritmo Usado");
		cellCaminho		.setCellValue("Nº de Movimentos p/ Solução");
		cellTestados	.setCellValue("Nº de Estados Testados");
		cellTempo		.setCellValue("Tempo de Execução");
		cellMemAntes	.setCellValue("Memória Usada (Antes)");
		cellMemDepois	.setCellValue("Memória Usada (Depois)");
		cellMemDifer	.setCellValue("Memória Usada pelo Algoritmo");
			
		file.close();
		FileOutputStream outFile = new FileOutputStream(new File("PATH"));
		workbook.write(outFile);
		outFile.close();
		workbook.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("Arquivo Excel não encontrado!");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Erro na edição do arquivo!");
		}
	}
	
	public static void agruparExecValidas() {
		try { FileInputStream file = new FileInputStream(new File("PATH"));
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		XSSFSheet report = workbook.getSheetAt(0);
		
		XSSFSheet validasTable = workbook.createSheet("numValidas");
		Row validasHeader = validasTable.createRow(0);

		validasHeader.createCell(0).setCellValue("Algoritmo");
		validasHeader.createCell(1).setCellValue("Tam. da Matriz");
		validasHeader.createCell(2).setCellValue("5 Emb.");
		validasHeader.createCell(3).setCellValue("10 Emb.");
		validasHeader.createCell(4).setCellValue("15 Emb.");
		validasHeader.createCell(5).setCellValue("20 Emb.");
		validasHeader.createCell(6).setCellValue("25 Emb.");
		validasHeader.createCell(7).setCellValue("30 Emb.");
		validasHeader.createCell(8).setCellValue("35 Emb.");
		validasHeader.createCell(9).setCellValue("40 Emb.");
		validasHeader.createCell(10).setCellValue("45 Emb.");
		validasHeader.createCell(11).setCellValue("50 Emb.");
		validasHeader.createCell(12).setCellValue("500 Emb.");
		
		ArrayList<String> algoritmos = new ArrayList<>();
		algoritmos.add("BFS"); algoritmos.add("DFS"); algoritmos.add("IDS"); algoritmos.add("AS1"); algoritmos.add("AS2");
		
		for(int alg = 0; alg < 5; alg++) {
			for(int tamMatriz = 2; tamMatriz < 6; tamMatriz++) {
				Row validasRow = validasTable.createRow(validasTable.getLastRowNum()+1);
				validasRow.createCell(0).setCellValue(algoritmos.get(alg));
				validasRow.createCell(1).setCellValue(tamMatriz + "x" + tamMatriz);
				for(int numEmb = 5; numEmb <= 55; numEmb += 5) {
					int rowNum = 102 + 1144*(tamMatriz-2) + 104*((numEmb/5)-1) + 4576*alg;
					int numValidas = (int) report.getRow(rowNum).getCell(0).getNumericCellValue();
					validasRow.createCell((numEmb/5)+1).setCellValue(numValidas);
				}
			}
		}
				
		file.close();
		FileOutputStream outFile = new FileOutputStream(new File("PATH"));
		workbook.write(outFile);
		outFile.close();
		workbook.close();
		System.out.println("Done!");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("Arquivo Excel não encontrado!");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Erro na edição do arquivo!");
		}
	}
	
	public static void agruparEstatisticas() {
		try { FileInputStream file = new FileInputStream(new File("PATH"));
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		XSSFSheet report = workbook.getSheetAt(0);
		
		XSSFSheet mediaTable = workbook.createSheet("media");
		XSSFSheet medianaTable = workbook.createSheet("mediana");
		XSSFSheet desvPadTable = workbook.createSheet("desvPad");

		Row mediaHeader = mediaTable.createRow(0);
		Row medianaHeader = medianaTable.createRow(0);
		Row desvPadHeader = desvPadTable.createRow(0);

		mediaHeader.createCell(0).setCellValue("Tam. da Matriz");
		mediaHeader.createCell(1).setCellValue("N de Embar.");
		mediaHeader.createCell(2).setCellValue("[BFS]Caminho");
		mediaHeader.createCell(3).setCellValue("[DFS]Caminho");
		mediaHeader.createCell(4).setCellValue("[IDS]Caminho");
		mediaHeader.createCell(5).setCellValue("[A*1]Caminho");
		mediaHeader.createCell(6).setCellValue("[A*2]Caminho");
		mediaHeader.createCell(7).setCellValue("[BFS]Testados");
		mediaHeader.createCell(8).setCellValue("[DFS]Testados");
		mediaHeader.createCell(9).setCellValue("[IDS]Testados");
		mediaHeader.createCell(10).setCellValue("[A*1]Testados");
		mediaHeader.createCell(11).setCellValue("[A*2]Testados");
		mediaHeader.createCell(12).setCellValue("[BFS]Tempo");
		mediaHeader.createCell(13).setCellValue("[DFS]Tempo");
		mediaHeader.createCell(14).setCellValue("[IDS]Tempo");
		mediaHeader.createCell(15).setCellValue("[A*1]Tempo");
		mediaHeader.createCell(16).setCellValue("[A*2]Tempo");
		mediaHeader.createCell(17).setCellValue("[BFS]Memoria");
		mediaHeader.createCell(18).setCellValue("[DFS]Memoria");
		mediaHeader.createCell(19).setCellValue("[IDS]Memoria");
		mediaHeader.createCell(20).setCellValue("[A*1]Memoria");
		mediaHeader.createCell(21).setCellValue("[A*2]Memoria");

		medianaHeader.createCell(0).setCellValue("Tam. da Matriz");
		medianaHeader.createCell(1).setCellValue("N de Embar.");
		medianaHeader.createCell(2).setCellValue("[BFS]Caminho");
		medianaHeader.createCell(3).setCellValue("[DFS]Caminho");
		medianaHeader.createCell(4).setCellValue("[IDS]Caminho");
		medianaHeader.createCell(5).setCellValue("[A*1]Caminho");
		medianaHeader.createCell(6).setCellValue("[A*2]Caminho");
		medianaHeader.createCell(7).setCellValue("[BFS]Testados");
		medianaHeader.createCell(8).setCellValue("[DFS]Testados");
		medianaHeader.createCell(9).setCellValue("[IDS]Testados");
		medianaHeader.createCell(10).setCellValue("[A*1]Testados");
		medianaHeader.createCell(11).setCellValue("[A*2]Testados");
		medianaHeader.createCell(12).setCellValue("[BFS]Tempo");
		medianaHeader.createCell(13).setCellValue("[DFS]Tempo");
		medianaHeader.createCell(14).setCellValue("[IDS]Tempo");
		medianaHeader.createCell(15).setCellValue("[A*1]Tempo");
		medianaHeader.createCell(16).setCellValue("[A*2]Tempo");
		medianaHeader.createCell(17).setCellValue("[BFS]Memoria");
		medianaHeader.createCell(18).setCellValue("[DFS]Memoria");
		medianaHeader.createCell(19).setCellValue("[IDS]Memoria");
		medianaHeader.createCell(20).setCellValue("[A*1]Memoria");
		medianaHeader.createCell(21).setCellValue("[A*2]Memoria");
		
		desvPadHeader.createCell(0).setCellValue("Tam. da Matriz");
		desvPadHeader.createCell(1).setCellValue("N de Embar.");
		desvPadHeader.createCell(2).setCellValue("[BFS]Caminho");
		desvPadHeader.createCell(3).setCellValue("[DFS]Caminho");
		desvPadHeader.createCell(4).setCellValue("[IDS]Caminho");
		desvPadHeader.createCell(5).setCellValue("[A*1]Caminho");
		desvPadHeader.createCell(6).setCellValue("[A*2]Caminho");
		desvPadHeader.createCell(7).setCellValue("[BFS]Testados");
		desvPadHeader.createCell(8).setCellValue("[DFS]Testados");
		desvPadHeader.createCell(9).setCellValue("[IDS]Testados");
		desvPadHeader.createCell(10).setCellValue("[A*1]Testados");
		desvPadHeader.createCell(11).setCellValue("[A*2]Testados");
		desvPadHeader.createCell(12).setCellValue("[BFS]Tempo");
		desvPadHeader.createCell(13).setCellValue("[DFS]Tempo");
		desvPadHeader.createCell(14).setCellValue("[IDS]Tempo");
		desvPadHeader.createCell(15).setCellValue("[A*1]Tempo");
		desvPadHeader.createCell(16).setCellValue("[A*2]Tempo");
		desvPadHeader.createCell(17).setCellValue("[BFS]Memoria");
		desvPadHeader.createCell(18).setCellValue("[DFS]Memoria");
		desvPadHeader.createCell(19).setCellValue("[IDS]Memoria");
		desvPadHeader.createCell(20).setCellValue("[A*1]Memoria");
		desvPadHeader.createCell(21).setCellValue("[A*2]Memoria");

		for(int tamMatriz = 2; tamMatriz < 6; tamMatriz++) {
			for(int numEmb = 5; numEmb <= 50; numEmb += 5) {
				int BFSrowNum = 101 + 1144*(tamMatriz-2) + 104*((numEmb/5)-1);
				int DFSrowNum = BFSrowNum + 4576;
				int IDSrowNum = BFSrowNum + 2*4576;
				int AS1rowNum = BFSrowNum + 3*4576;
				int AS2rowNum = BFSrowNum + 4*4576;
				
				Row BFSmediaRow	= 	report.getRow(BFSrowNum);
				Row BFSmedianaRow = report.getRow(BFSrowNum+1);
				Row BFSdesvPadRow = report.getRow(BFSrowNum+2);				
				Row DFSmediaRow = 	report.getRow(DFSrowNum);
				Row DFSmedianaRow = report.getRow(DFSrowNum+1);
				Row DFSdesvPadRow = report.getRow(DFSrowNum+2);
				Row IDSmediaRow = 	report.getRow(IDSrowNum);
				Row IDSmedianaRow = report.getRow(IDSrowNum+1);
				Row IDSdesvPadRow = report.getRow(IDSrowNum+2);
				Row AS1mediaRow = 	report.getRow(AS1rowNum);
				Row AS1medianaRow = report.getRow(AS1rowNum+1);
				Row AS1desvPadRow = report.getRow(AS1rowNum+2);
				Row AS2mediaRow = 	report.getRow(AS2rowNum);
				Row AS2medianaRow = report.getRow(AS2rowNum+1);
				Row AS2desvPadRow = report.getRow(AS2rowNum+2);
				
				Row linhaMedia = mediaTable.createRow(mediaTable.getLastRowNum()+1);
				Row linhaMediana = medianaTable.createRow(medianaTable.getLastRowNum()+1);
				Row linhaDesvPad = desvPadTable.createRow(desvPadTable.getLastRowNum()+1);

				linhaMedia	.createCell(0).setCellValue(tamMatriz + "x" + tamMatriz);
				linhaMediana.createCell(0).setCellValue(tamMatriz + "x" + tamMatriz);
				linhaDesvPad.createCell(0).setCellValue(tamMatriz + "x" + tamMatriz);

				linhaMedia	.createCell(1).setCellValue(numEmb);
				linhaMediana.createCell(1).setCellValue(numEmb);
				linhaDesvPad.createCell(1).setCellValue(numEmb);

				linhaMedia	.createCell(2).setCellValue(BFSmediaRow		.getCell(4).getNumericCellValue());
				linhaMediana.createCell(2).setCellValue(BFSmedianaRow	.getCell(4).getNumericCellValue());
				linhaDesvPad.createCell(2).setCellValue(BFSdesvPadRow	.getCell(4).getNumericCellValue());

				linhaMedia	.createCell(3).setCellValue(DFSmediaRow		.getCell(4).getNumericCellValue());
				linhaMediana.createCell(3).setCellValue(DFSmedianaRow	.getCell(4).getNumericCellValue());
				linhaDesvPad.createCell(3).setCellValue(DFSdesvPadRow	.getCell(4).getNumericCellValue()); 

				linhaMedia	.createCell(4).setCellValue(IDSmediaRow		.getCell(4).getNumericCellValue());
				linhaMediana.createCell(4).setCellValue(IDSmedianaRow	.getCell(4).getNumericCellValue());
				linhaDesvPad.createCell(4).setCellValue(IDSdesvPadRow	.getCell(4).getNumericCellValue());

				linhaMedia	.createCell(5).setCellValue(AS1mediaRow		.getCell(4).getNumericCellValue());
				linhaMediana.createCell(5).setCellValue(AS1medianaRow	.getCell(4).getNumericCellValue());
				linhaDesvPad.createCell(5).setCellValue(AS1desvPadRow	.getCell(4).getNumericCellValue());

				linhaMedia	.createCell(6).setCellValue(AS2mediaRow		.getCell(4).getNumericCellValue());
				linhaMediana.createCell(6).setCellValue(AS2medianaRow	.getCell(4).getNumericCellValue());
				linhaDesvPad.createCell(6).setCellValue(AS2desvPadRow	.getCell(4).getNumericCellValue());

				linhaMedia	.createCell(7).setCellValue(BFSmediaRow		.getCell(5).getNumericCellValue());
				linhaMediana.createCell(7).setCellValue(BFSmedianaRow	.getCell(5).getNumericCellValue());
				linhaDesvPad.createCell(7).setCellValue(BFSdesvPadRow	.getCell(5).getNumericCellValue());

				linhaMedia	.createCell(8).setCellValue(DFSmediaRow		.getCell(5).getNumericCellValue());
				linhaMediana.createCell(8).setCellValue(DFSmedianaRow	.getCell(5).getNumericCellValue());
				linhaDesvPad.createCell(8).setCellValue(DFSdesvPadRow	.getCell(5).getNumericCellValue()); 

				linhaMedia	.createCell(9).setCellValue(IDSmediaRow		.getCell(5).getNumericCellValue());
				linhaMediana.createCell(9).setCellValue(IDSmedianaRow	.getCell(5).getNumericCellValue());
				linhaDesvPad.createCell(9).setCellValue(IDSdesvPadRow	.getCell(5).getNumericCellValue());

				linhaMedia	.createCell(10).setCellValue(AS1mediaRow	.getCell(5).getNumericCellValue());
				linhaMediana.createCell(10).setCellValue(AS1medianaRow	.getCell(5).getNumericCellValue());
				linhaDesvPad.createCell(10).setCellValue(AS1desvPadRow	.getCell(5).getNumericCellValue());

				linhaMedia	.createCell(11).setCellValue(AS2mediaRow	.getCell(5).getNumericCellValue());
				linhaMediana.createCell(11).setCellValue(AS2medianaRow	.getCell(5).getNumericCellValue());
				linhaDesvPad.createCell(11).setCellValue(AS2desvPadRow	.getCell(5).getNumericCellValue());
				
				linhaMedia	.createCell(12).setCellValue(BFSmediaRow	.getCell(6).getNumericCellValue());
				linhaMediana.createCell(12).setCellValue(BFSmedianaRow	.getCell(6).getNumericCellValue());
				linhaDesvPad.createCell(12).setCellValue(BFSdesvPadRow	.getCell(6).getNumericCellValue());

				linhaMedia	.createCell(13).setCellValue(DFSmediaRow	.getCell(6).getNumericCellValue());
				linhaMediana.createCell(13).setCellValue(DFSmedianaRow	.getCell(6).getNumericCellValue());
				linhaDesvPad.createCell(13).setCellValue(DFSdesvPadRow	.getCell(6).getNumericCellValue()); 

				linhaMedia	.createCell(14).setCellValue(IDSmediaRow	.getCell(6).getNumericCellValue());
				linhaMediana.createCell(14).setCellValue(IDSmedianaRow	.getCell(6).getNumericCellValue());
				linhaDesvPad.createCell(14).setCellValue(IDSdesvPadRow	.getCell(6).getNumericCellValue());

				linhaMedia	.createCell(15).setCellValue(AS1mediaRow	.getCell(6).getNumericCellValue());
				linhaMediana.createCell(15).setCellValue(AS1medianaRow	.getCell(6).getNumericCellValue());
				linhaDesvPad.createCell(15).setCellValue(AS1desvPadRow	.getCell(6).getNumericCellValue());

				linhaMedia	.createCell(16).setCellValue(AS2mediaRow	.getCell(6).getNumericCellValue());
				linhaMediana.createCell(16).setCellValue(AS2medianaRow	.getCell(6).getNumericCellValue());
				linhaDesvPad.createCell(16).setCellValue(AS2desvPadRow	.getCell(6).getNumericCellValue());
				
				linhaMedia	.createCell(17).setCellValue(BFSmediaRow	.getCell(9).getNumericCellValue());
				linhaMediana.createCell(17).setCellValue(BFSmedianaRow	.getCell(9).getNumericCellValue());
				linhaDesvPad.createCell(17).setCellValue(BFSdesvPadRow	.getCell(9).getNumericCellValue());

				linhaMedia	.createCell(18).setCellValue(DFSmediaRow	.getCell(9).getNumericCellValue());
				linhaMediana.createCell(18).setCellValue(DFSmedianaRow	.getCell(9).getNumericCellValue());
				linhaDesvPad.createCell(18).setCellValue(DFSdesvPadRow	.getCell(9).getNumericCellValue()); 

				linhaMedia	.createCell(19).setCellValue(IDSmediaRow	.getCell(9).getNumericCellValue());
				linhaMediana.createCell(19).setCellValue(IDSmedianaRow	.getCell(9).getNumericCellValue());
				linhaDesvPad.createCell(19).setCellValue(IDSdesvPadRow	.getCell(9).getNumericCellValue());

				linhaMedia	.createCell(20).setCellValue(AS1mediaRow	.getCell(9).getNumericCellValue());
				linhaMediana.createCell(20).setCellValue(AS1medianaRow	.getCell(9).getNumericCellValue());
				linhaDesvPad.createCell(20).setCellValue(AS1desvPadRow	.getCell(9).getNumericCellValue());

				linhaMedia	.createCell(21).setCellValue(AS2mediaRow	.getCell(9).getNumericCellValue());
				linhaMediana.createCell(21).setCellValue(AS2medianaRow	.getCell(9).getNumericCellValue());
				linhaDesvPad.createCell(21).setCellValue(AS2desvPadRow	.getCell(9).getNumericCellValue());
			}
		}

		file.close();
		FileOutputStream outFile = new FileOutputStream(new File("PATH"));
		workbook.write(outFile);
		outFile.close();
		workbook.close();
		System.out.println("Done!");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("Arquivo Excel não encontrado!");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Erro na edição do arquivo!");
		}
	}
	
	public static void removerInvalidos() {
		try { FileInputStream file = new FileInputStream(new File("PATH"));
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		XSSFSheet report = workbook.getSheetAt(0);
		
		int numValidos;
		
		for(int rowNum = 1; rowNum < report.getLastRowNum()-100; rowNum += 104) {
			numValidos = 100;
			for(int i = 0; i < 100; i++) {
				Row row = report.getRow(rowNum + i);
				Cell cellTestados = row.getCell(5);
				
				if( cellTestados.getNumericCellValue() >= 100000 ) {
					//System.out.println("Excluindo a linha " + row.getRowNum());
					report.removeRow(row);
					numValidos--;
				} else {
					//System.out.println("Atualizando a formula da memória na linha " + row.getRowNum());
					row.getCell(9).setCellFormula("I" + (rowNum + i + 1) + "-H" + (rowNum + i + 1));
				}
			}
			Row mediaRow = report.getRow(rowNum + 100);
			Row medianaRow = report.getRow(rowNum + 101);
			Row DesvPadRow = report.getRow(rowNum + 102);
			for(int i = 4; i < 10; i++) {
				char column = 'A';
				column += i;
				Cell mediaCell = mediaRow.getCell(i);
				Cell medianaCell = medianaRow.getCell(i);
				Cell desvPadCell = DesvPadRow.getCell(i);
				if( numValidos > 0 ) {
					mediaCell.setCellFormula("AVERAGE("		+ column + (rowNum + 1) + ":" + column + (rowNum + 100) + ")");						
					medianaCell.setCellFormula("MEDIAN("	+ column + (rowNum + 1) + ":" + column + (rowNum + 100) + ")");
				} else {
					mediaCell.setCellValue(0);
					medianaCell.setCellValue(0);
				}
				if( numValidos > 1 ) desvPadCell.setCellFormula("STDEV(" + column + (rowNum + 1) + ":" + column + (rowNum + 100) + ")");
				else desvPadCell.setCellValue(0);
			}
			System.out.println("Atualizando estatisticas das linhas " + (rowNum+100) + "...");
			Cell cellValidos = report.getRow(rowNum + 101).createCell(0);
			cellValidos.setCellValue(numValidos);
		}

		file.close();
		FileOutputStream outFile = new FileOutputStream(new File("PATH"));
		workbook.write(outFile);
		outFile.close();
		workbook.close();
		System.out.println("Done!");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("Arquivo Excel não encontrado!");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Erro na edição do arquivo!");
		}
	}
	
	public static void escreveLinha(int numExec, int embar, String algoritmo, int tamMatriz, int caminho, int testados, double tempo, double memAntes, double memDepois) {
		try { FileInputStream file = new FileInputStream(new File("PATH"));
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		XSSFSheet report = workbook.getSheetAt(0);
		
		int rowNum = report.getPhysicalNumberOfRows();
		
		Row row = report.createRow(rowNum);
		Cell cellNumExec	= row.createCell(10);
		Cell cellEmbar 		= row.createCell(1);
		Cell cellTamMatriz	= row.createCell(2);
		Cell cellAlgoritmo 	= row.createCell(3);
		Cell cellCaminho 	= row.createCell(4);
		Cell cellTestados 	= row.createCell(5);
		Cell cellTempo 		= row.createCell(6);
		Cell cellMemAntes 	= row.createCell(7);
		Cell cellMemDepois 	= row.createCell(8);
		Cell cellMemDifer	= row.createCell(9);

		cellNumExec		.setCellValue(numExec);
		cellEmbar		.setCellValue(embar);
		cellTamMatriz	.setCellValue(tamMatriz);
		cellAlgoritmo	.setCellValue(algoritmo);
		cellCaminho		.setCellValue(caminho);
		cellTestados	.setCellValue(testados);
		cellTempo		.setCellValue(tempo);
		cellMemAntes	.setCellValue(memAntes);
		cellMemDepois	.setCellValue(memDepois);
	try {cellMemDifer	.setCellFormula("" + cellMemDepois.getAddress() + "-" + cellMemAntes.getAddress());} catch (FormulaParseException e) { e.printStackTrace(); System.out.println("Fórmula mal escrita!");}
	
		file.close();
		FileOutputStream outFile = new FileOutputStream(new File("PATH"));
		workbook.write(outFile);
		outFile.close();
		workbook.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("Arquivo Excel não encontrado!");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Erro na edição do arquivo!");
		}
	}
	
	public static void escreveEstatisticas(int embar, String algoritmo, int tamMatriz) {
		try { FileInputStream file = new FileInputStream(new File("PATH"));
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		XSSFSheet report = workbook.getSheetAt(0);
		
		int rowNum = report.getPhysicalNumberOfRows();
		
		Row mediaRow = report.createRow(rowNum);
		Cell cellResumo		= mediaRow.createCell(0);
		Cell cellNumExec	= mediaRow.createCell(10);
		Cell cellEmbar 		= mediaRow.createCell(1);
		Cell cellTamMatriz	= mediaRow.createCell(2);
		Cell cellAlgoritmo 	= mediaRow.createCell(3);
		Cell cellCaminho 	= mediaRow.createCell(4);
		Cell cellTestados 	= mediaRow.createCell(5);
		Cell cellTempo 		= mediaRow.createCell(6);
		Cell cellMemAntes 	= mediaRow.createCell(7);
		Cell cellMemDepois 	= mediaRow.createCell(8);
		Cell cellMemDifer	= mediaRow.createCell(9);

		Row firstRow = report.getRow(rowNum-100);
		Cell firstCaminho 	= firstRow.getCell(4);
		Cell firstTestados 	= firstRow.getCell(5);
		Cell firstTempo 	= firstRow.getCell(6);
		Cell firstMemAntes 	= firstRow.getCell(7);
		Cell firstMemDepois = firstRow.getCell(8);
		Cell firstMemDifer	= firstRow.getCell(9);
		
		Row lastRow = report.getRow(rowNum-1);
		Cell lastCaminho 	= lastRow.getCell(4);
		Cell lastTestados 	= lastRow.getCell(5);
		Cell lastTempo 		= lastRow.getCell(6);
		Cell lastMemAntes 	= lastRow.getCell(7);
		Cell lastMemDepois 	= lastRow.getCell(8);
		Cell lastMemDifer	= lastRow.getCell(9);

		cellResumo		.setCellValue("Estatísticas:");
		cellNumExec		.setCellValue("Média");
		cellEmbar		.setCellValue(embar);
		cellTamMatriz	.setCellValue(tamMatriz);
		cellAlgoritmo	.setCellValue(algoritmo);
	try {cellCaminho	.setCellFormula("AVERAGE(" + firstCaminho.getAddress() + ":" + lastCaminho.getAddress() + ")");} catch (FormulaParseException e) { e.printStackTrace(); System.out.println("Fórmula mal escrita!");}
	try {cellTestados	.setCellFormula("AVERAGE(" + firstTestados.getAddress() + ":" + lastTestados.getAddress() + ")");} catch (FormulaParseException e) { e.printStackTrace(); System.out.println("Fórmula mal escrita!");}
	try {cellTempo		.setCellFormula("AVERAGE(" + firstTempo.getAddress() + ":" + lastTempo.getAddress() + ")");} catch (FormulaParseException e) { e.printStackTrace(); System.out.println("Fórmula mal escrita!");}
	try {cellMemAntes	.setCellFormula("AVERAGE(" + firstMemAntes.getAddress() + ":" + lastMemAntes.getAddress() + ")");} catch (FormulaParseException e) { e.printStackTrace(); System.out.println("Fórmula mal escrita!");}
	try {cellMemDepois	.setCellFormula("AVERAGE(" + firstMemDepois.getAddress() + ":" + lastMemDepois.getAddress() + ")");} catch (FormulaParseException e) { e.printStackTrace(); System.out.println("Fórmula mal escrita!");}
	try {cellMemDifer	.setCellFormula("AVERAGE(" + firstMemDifer.getAddress() + ":" + lastMemDifer.getAddress() + ")");}catch (FormulaParseException e) { e.printStackTrace(); System.out.println("Fórmula mal escrita!");}
		
		Row medianaRow 			= report.createRow(rowNum+1);
		Cell medianaNumExec		= medianaRow.createCell(10);
		Cell medianaEmbar 		= medianaRow.createCell(1);
		Cell medianaTamMatriz	= medianaRow.createCell(2);
		Cell medianaAlgoritmo 	= medianaRow.createCell(3);
		Cell medianaCaminho 	= medianaRow.createCell(4);
		Cell medianaTestados 	= medianaRow.createCell(5);
		Cell medianaTempo 		= medianaRow.createCell(6);
		Cell medianaMemAntes 	= medianaRow.createCell(7);
		Cell medianaMemDepois 	= medianaRow.createCell(8);
		Cell medianaMemDifer	= medianaRow.createCell(9);

		medianaNumExec		.setCellValue("Mediana");
		medianaEmbar		.setCellValue(embar);
		medianaTamMatriz	.setCellValue(tamMatriz);
		medianaAlgoritmo	.setCellValue(algoritmo);
	try {medianaCaminho		.setCellFormula("MEDIAN(" + firstCaminho.getAddress() + ":" + lastCaminho.getAddress() + ")");} catch (FormulaParseException e) { e.printStackTrace(); System.out.println("Fórmula mal escrita!");}
	try {medianaTestados	.setCellFormula("MEDIAN(" + firstTestados.getAddress() + ":" + lastTestados.getAddress() + ")");} catch (FormulaParseException e) { e.printStackTrace(); System.out.println("Fórmula mal escrita!");}
	try {medianaTempo		.setCellFormula("MEDIAN(" + firstTempo.getAddress() + ":" + lastTempo.getAddress() + ")");} catch (FormulaParseException e) { e.printStackTrace(); System.out.println("Fórmula mal escrita!");}
	try {medianaMemAntes	.setCellFormula("MEDIAN(" + firstMemAntes.getAddress() + ":" + lastMemAntes.getAddress() + ")");} catch (FormulaParseException e) { e.printStackTrace(); System.out.println("Fórmula mal escrita!");}
	try {medianaMemDepois	.setCellFormula("MEDIAN(" + firstMemDepois.getAddress() + ":" + lastMemDepois.getAddress() + ")");} catch (FormulaParseException e) { e.printStackTrace(); System.out.println("Fórmula mal escrita!");}
	try {medianaMemDifer	.setCellFormula("MEDIAN(" + firstMemDifer.getAddress() + ":" + lastMemDifer.getAddress() + ")");}catch (FormulaParseException e) { e.printStackTrace(); System.out.println("Fórmula mal escrita!");}

		Row desvPadRow 			= report.createRow(rowNum+2);
		Cell desvPadNumExec		= desvPadRow.createCell(10);
		Cell desvPadEmbar 		= desvPadRow.createCell(1);
		Cell desvPadTamMatriz	= desvPadRow.createCell(2);
		Cell desvPadAlgoritmo 	= desvPadRow.createCell(3);
		Cell desvPadCaminho 	= desvPadRow.createCell(4);
		Cell desvPadTestados 	= desvPadRow.createCell(5);
		Cell desvPadTempo 		= desvPadRow.createCell(6);
		Cell desvPadMemAntes 	= desvPadRow.createCell(7);
		Cell desvPadMemDepois 	= desvPadRow.createCell(8);
		Cell desvPadMemDifer	= desvPadRow.createCell(9);
	
		desvPadNumExec		.setCellValue("Desvio Padrão");
		desvPadEmbar		.setCellValue(embar);
		desvPadTamMatriz	.setCellValue(tamMatriz);
		desvPadAlgoritmo	.setCellValue(algoritmo);
	try {desvPadCaminho		.setCellFormula("STDEV(" + firstCaminho.getAddress() + ":" + lastCaminho.getAddress() + ")");} catch (FormulaParseException e) { e.printStackTrace(); System.out.println("Fórmula mal escrita!");}
	try {desvPadTestados	.setCellFormula("STDEV(" + firstTestados.getAddress() + ":" + lastTestados.getAddress() + ")");} catch (FormulaParseException e) { e.printStackTrace(); System.out.println("Fórmula mal escrita!");}
	try {desvPadTempo		.setCellFormula("STDEV(" + firstTempo.getAddress() + ":" + lastTempo.getAddress() + ")");} catch (FormulaParseException e) { e.printStackTrace(); System.out.println("Fórmula mal escrita!");}
	try {desvPadMemAntes	.setCellFormula("STDEV(" + firstMemAntes.getAddress() + ":" + lastMemAntes.getAddress() + ")");} catch (FormulaParseException e) { e.printStackTrace(); System.out.println("Fórmula mal escrita!");}
	try {desvPadMemDepois	.setCellFormula("STDEV(" + firstMemDepois.getAddress() + ":" + lastMemDepois.getAddress() + ")");} catch (FormulaParseException e) { e.printStackTrace(); System.out.println("Fórmula mal escrita!");}
	try {desvPadMemDifer	.setCellFormula("STDEV(" + firstMemDifer.getAddress() + ":" + lastMemDifer.getAddress() + ")");}catch (FormulaParseException e) { e.printStackTrace(); System.out.println("Fórmula mal escrita!");}
		
		Row separacaoRow = report.createRow(rowNum+3);	
	
		file.close();
		FileOutputStream outFile = new FileOutputStream(new File("PATH"));
		workbook.write(outFile);
		outFile.close();
		workbook.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("Arquivo Excel não encontrado!");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Erro na edição do arquivo!");
		}
	}
	
	public static void reset() {
		raiz = new Nodo();
		destino = new Nodo();
		numTestados = 0;
		numParaSol = 0;
	}


	
}
