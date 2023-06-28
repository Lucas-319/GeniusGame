package GENIUS;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JOptionPane;

import GAMEOVER.GameOver;

public class GeniusGame {
			
	private List<Integer> sequencia;	
	private Random random;
	private int qtdCor = 4;
			
	//Construtor da Classe
	public GeniusGame() {
		random = new Random();
		sequencia = new ArrayList<Integer>();		
	}
	
	// - Easy ou Normal Mode Gaming;
	//Gera o valor da sequencia de maneira aleatoria
	public int gerarSequencia() {
		int num = random.nextInt(qtdCor);
		addSequencia(num);	
		return num;
	}
	
	// - Hard Mode Gaming;
	//Gera 2 valores da sequencia de maneira aleatoria
	public void gerarSequenciaDupla() {
		int num = random.nextInt(qtdCor);
		int num2 = random.nextInt(qtdCor);
		addSequenciaDupla(num, num2);		
	}
	
	// - Easy ou Normal Mode Gaming;
	//Metodo responsavel por adicionar o numero gerado a lista;
	private void addSequencia(int num) {
		sequencia.add(num);
	}
		
	// - Hard Mode Gamimg;
	//Metodo responsavel por adicionar os numeros gerado a lista;
	private void addSequenciaDupla(int num, int num2) {
		sequencia.add(num);
		sequencia.add(num2);
	}
	
	//Metodo que retorna a lista com os valores (cores);
	public List<Integer> getSequencia() {
		return sequencia;
	}
	
	//Metodo para verificar se a jogada esta correta;
	public boolean verificaJogada(int botao, int indice) {		
		if(botao != -1) {								
			if(botao == this.getSequencia().get(indice)) {				
				return true;
			}
			else {																
				return false;
			}
		}
		else {						
			return false;
		}
	}
	
}

