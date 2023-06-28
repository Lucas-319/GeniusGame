package DISTRIBUICAO;

import GENIUSEASY.GeniusEasy;
import GENIUSHARD.GeniusHard;
import GENIUSNORMAL.GeniusNormal;

public class Distribuicao {
	
	private int velocidade;
	private int dificuldade;
	
	//Construtor da classe
	public Distribuicao(int velocidade, int dificuldade) {		
		this.velocidade = velocidade;
		this.dificuldade = dificuldade;
		
		escolheJogo(this.velocidade, this.dificuldade);
	}
	
	//Baseado nas informacoes geradas em PARAMETROS, executa o jogo Easy, Normal ou Hard
	public void escolheJogo(int velocidade, int dificuldade) {
		//Easy Mode Gaming
		if(dificuldade == 1) {
			GeniusEasy genius = new GeniusEasy(velocidade);
			genius.setVisible(true);
		}
		//Normal Mode Gaming
		else if(dificuldade == 2) {
			GeniusNormal genius = new GeniusNormal(velocidade);
			genius.setVisible(true);
		}
		//Hard Mode Gaming
		else if(dificuldade == 3){
			GeniusHard genius = new GeniusHard(velocidade);
			genius.setVisible(true);
		}
	}
	
	
}
