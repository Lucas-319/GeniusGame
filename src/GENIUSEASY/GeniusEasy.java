package GENIUSEASY;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import GAMEOVER.GameOver;
import GENIUS.GeniusGame;

public class GeniusEasy extends JFrame implements ActionListener{
	
	//Label's para estilizacao da tela
	private JLabel geniusLabel = new JLabel();
	private JLabel cronometroLabel = new JLabel();
	
	//Variavel que armazena o nivel do jogo
	private int nivel;
	
	//Criacao das variaveis do tipo TimerTask (auxilia na hora de trocar de janela)
	TimerTask tarefaPlay;
	TimerTask tarefaTela;
	TimerTask tarefaBotao;
	TimerTask tarefaVamosSom;
	TimerTask tarefaCorSom;
	
	private int quebrarWhile = 0;
	
	//Botoes coloridos
	private JButton btnVerde;
	private JButton btnVermelho;
	private JButton btnAmarelo;
	private JButton btnAzul;
	
	//GeniusGame onde a logica do jogo esta implementada
	private GeniusGame jogo;
	
	//Variavel que armazena o botao selecionado pelo jogador
	private int corEscolhida = -1;
	
	//Define a velocidade do jogo - 1, 2, 3 (lento, normal, rapido)
	private int velocidade;
	
	//Determina o tempo de troca das imagens da animacao
	private int tempoTrocaImagem = 320; //milissegundos
	
	//Imagens tela de fundo
	private ImageIcon img01 = new ImageIcon(getClass().getResource("play img 01.png"));
	private ImageIcon img02 = new ImageIcon(getClass().getResource("play img 02.png"));
	private ImageIcon img03 = new ImageIcon(getClass().getResource("play img 03.png"));
	private ImageIcon img04 = new ImageIcon(getClass().getResource("play img 04.png"));
	private ImageIcon img05 = new ImageIcon(getClass().getResource("play img 05.png"));
	
	//Imagens Botao Verde
	private ImageIcon verde01 = new ImageIcon(getClass().getResource("green img 01.png"));
	private ImageIcon verde02 = new ImageIcon(getClass().getResource("green img 02.png"));
	//Imagens Botao Vermelho
	private ImageIcon vermelho01 = new ImageIcon(getClass().getResource("red img 01.png"));
	private ImageIcon vermelho02 = new ImageIcon(getClass().getResource("red img 02.png"));
	//Imagens Botao Amarelo
	private ImageIcon amarelo01 = new ImageIcon(getClass().getResource("yellow img 01.png"));
	private ImageIcon amarelo02 = new ImageIcon(getClass().getResource("yellow img 02.png"));
	//Imagens Botao Azul
	private ImageIcon azul01 = new ImageIcon(getClass().getResource("blue img 01.png"));
	private ImageIcon azul02 = new ImageIcon(getClass().getResource("blue img 02.png"));
	
	//Imagens Cronometro
	private ImageIcon cronometro0 = new ImageIcon(getClass().getResource("cronometro0.png"));
	private ImageIcon cronometro1 = new ImageIcon(getClass().getResource("cronometro1.png"));
	private ImageIcon cronometro2 = new ImageIcon(getClass().getResource("cronometro2.png"));
	private ImageIcon cronometro3 = new ImageIcon(getClass().getResource("cronometro3.png"));
	private ImageIcon cronometro4 = new ImageIcon(getClass().getResource("cronometro4.png"));
	//Imagem para quando o cronometro nao estiver em uso
	private ImageIcon semCronometro = new ImageIcon(getClass().getResource("semCronometro.png"));
	
	//Construtor da Classe
	public GeniusEasy(int velocidade) {		
		setSize(1295, 724);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(false);
		jogo = new GeniusGame();
		this.velocidade = velocidade;
		nivel = 1;
		
		//Definicao da imagem padrao da tela de fundo		
		geniusLabel.setIcon(img01);
		//Definicao da posicao e dimensao da tela de fundo
		geniusLabel.setBounds(0, 0, 1280, 720);
		//Definicao da posicao e dimensao do cronometro
		cronometroLabel.setBounds(587, 550, 120, 120);
						
		criarBotoes();
							
		add(btnVerde);
		add(btnVermelho);
		add(btnAmarelo);
		add(btnAzul);
		add(cronometroLabel);
		add(geniusLabel);
		
		play();
	}
	
	//Funcao responsavel pela criacao dos botoes
	public void criarBotoes() {
		//Criacao do botao da cor verde
		btnVerde = new JButton("");		
		btnVerde.addActionListener(this);
		btnVerde.setForeground(new Color(0, 0, 0));
		btnVerde.setBackground(new Color(0, 0, 0));
		btnVerde.setBounds(217, 260, 200, 200);
		//Criacao do botao da cor vermelha
		btnVermelho = new JButton("1");
		btnVermelho.addActionListener(this);
		btnVermelho.setBackground(new Color(0, 0, 0));
		btnVermelho.setBounds(437, 260, 200, 200);
		//Criacao do botao da cor amarelo
		btnAmarelo = new JButton("2");			
		btnAmarelo.addActionListener(this);
		btnAmarelo.setBackground(new Color(0, 0, 0));
		btnAmarelo.setBounds(657, 260, 200, 200);
		//Criacao do botao da cor azul
		btnAzul = new JButton("3");						
		btnAzul.addActionListener(this);
		btnAzul.setBackground(new Color(0, 0, 0));
		btnAzul.setBounds(877, 260, 200, 200);	
		
		preenchimentoEBorda();
		iconeBotao();		
	}
	
	//Seta as imagens dos botoes
	public void iconeBotao() {
		btnVerde.setIcon(verde01);
		btnVermelho.setIcon(vermelho01);
		btnAmarelo.setIcon(amarelo01);
		btnAzul.setIcon(azul01);
	}
	
	//Retira as bordas e o preenchimento dos botoes (para nao dar conflito com a imagem)
	public void preenchimentoEBorda() {
		btnVerde.setBorderPainted(false);
		btnVerde.setContentAreaFilled(false);
		btnVermelho.setBorderPainted(false);
		btnVermelho.setContentAreaFilled(false);
		btnAmarelo.setBorderPainted(false);
		btnAmarelo.setContentAreaFilled(false);
		btnAzul.setBorderPainted(false);
		btnAzul.setContentAreaFilled(false);
	}
	
	//Funcao responsavel por HABILITAR os botoes
	public void habilitaBotoes() {
		btnVerde.setEnabled(true);
		btnVermelho.setEnabled(true);
		btnAmarelo.setEnabled(true);
		btnAzul.setEnabled(true);
	}
	
	//Funcao responsavel por DESABILITAR os botoes
	public void desabilitaBotoes() {
		btnVerde.setDisabledIcon(btnVerde.getIcon());
		btnVerde.setEnabled(false);
		btnVermelho.setDisabledIcon(btnVermelho.getIcon());
		btnVermelho.setEnabled(false);
		btnAmarelo.setDisabledIcon(btnAmarelo.getIcon());
		btnAmarelo.setEnabled(false);
		btnAzul.setDisabledIcon(btnAzul.getIcon());
		btnAzul.setEnabled(false);
	}
	
	//Funcao que identifica qual botao selecionado
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()== btnVerde) {						
			mostrarBotoes(0);									
			corEscolhida = 0;
			mostrarBotoes(0);
		}
		else if(e.getSource() == btnVermelho) {
			mostrarBotoes(1);
			corEscolhida = 1;
			mostrarBotoes(1);
		}
		else if(e.getSource() == btnAmarelo) {
			mostrarBotoes(2);
			corEscolhida = 2;
			mostrarBotoes(2);
		}
		else if(e.getSource() == btnAzul) {
			mostrarBotoes(3);
			corEscolhida = 3;
			mostrarBotoes(3);
		}
	}
			
	//Funcao responsavel por pausar a execucao do codigo
	public void sleep(int milissegundos) {
		try {
			Thread.sleep(milissegundos);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//Funcao responsavel pela escolha do som referente a cada cor
	public void somDaCor(int indice) throws UnsupportedAudioFileException, IOException, LineUnavailableException{
		File file = null;
		
		//Responsavel pelo som da cor Verde
		if(indice == 0) {
			file = new File("greenSom.wav");
		}
		//Responsavel pelo som da cor Vermelho
		else if(indice == 1) {
			file = new File("redSom.wav");
		}
		//Responsavel pelo som da cor Amarelo
		else if(indice == 2) {
			file = new File("yellowSom.wav");
		}
		//Responsavel pelo som da cor Azul
		else if(indice == 3) {
			file = new File("blueSom.wav");
		}
		
		AudioInputStream audio = AudioSystem.getAudioInputStream(file);
		Clip clip = AudioSystem.getClip();
		clip.open(audio);		
		clip.start();
	}
		
	//Funcao responsavel por reproduzir o som (simplifica na hora da utilizacao)
	public void tocarSomCor(int indice) {
		Timer timer = new Timer();
		
		tarefaCorSom = new TimerTask() {			
			@Override
			public void run() {														
					try {
						somDaCor(indice);
					} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}																																
			}			
		};
		 timer.schedule(tarefaCorSom, 0);
	}
	
	//Funcao responsavel por selecionar o audio do mario falando HERE ARE GO!
	public void vamosLaSom() throws UnsupportedAudioFileException, IOException, LineUnavailableException{
		File som = new File("vamosLa.wav");		
		AudioInputStream audioMario = AudioSystem.getAudioInputStream(som);
		Clip clipMario = AudioSystem.getClip();
		clipMario.open(audioMario);		
		clipMario.start();
	}
	//Funcao responsavel por tocar o som do mario falando HERE ARE GO!
	public void tocarVamosLaSom() {
		Timer timer = new Timer();		
		tarefaVamosSom = new TimerTask() {			
			@Override
			public void run() {														
					try {
						vamosLaSom();
					} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}																																
			}			
		};
		 timer.schedule(tarefaVamosSom, 0);
	}
	
	//Funcao responsavel pelas animacoes dos botoes e tela de fundo
	public void mostrarBotoes(int indice) {
		Timer timer = new Timer();		
		tarefaBotao = new TimerTask() {			
			@Override
			public void run() {
				//Responsavel pela animacao de clique botao VERDE
				if(indice == 0) {					
					btnVerde.setIcon(verde02);
					btnVerde.setDisabledIcon(btnVerde.getIcon());
					mudarTelaFundo(0);
					tocarSomCor(indice);
					sleep(tempoTrocaImagem);																				
					btnVerde.setIcon(verde01);	
					btnVerde.setDisabledIcon(btnVerde.getIcon());
				}
				//Responsavel pela animacao de clique botao VERMELHO
				else if(indice == 1) {
					btnVermelho.setIcon(vermelho02);
					btnVermelho.setDisabledIcon(btnVermelho.getIcon());
					mudarTelaFundo(1);
					tocarSomCor(indice);
					sleep(tempoTrocaImagem);
					btnVermelho.setIcon(vermelho01);
					btnVermelho.setDisabledIcon(btnVermelho.getIcon());										
				}
				//Responsavel pela animacao de clique botao AMARELO
				else if(indice == 2) {
					btnAmarelo.setIcon(amarelo02);
					btnAmarelo.setDisabledIcon(btnAmarelo.getIcon());
					mudarTelaFundo(2);
					tocarSomCor(indice);
					sleep(tempoTrocaImagem);
					btnAmarelo.setIcon(amarelo01);
					btnAmarelo.setDisabledIcon(btnAmarelo.getIcon());											
				}
				//Responsavel pela animacao de clique botao AZUL
				else if(indice == 3) {
					btnAzul.setIcon(azul02);
					btnAzul.setDisabledIcon(btnAzul.getIcon());
					mudarTelaFundo(3);
					tocarSomCor(indice);
					sleep(tempoTrocaImagem);
					btnAzul.setIcon(azul01);
					btnAzul.setDisabledIcon(btnAzul.getIcon());											
				}				
			}			
		};
		 timer.schedule(tarefaBotao, 0);
	}
	
	//Funcao responsavel por trocar a cor de fundo baseado na cor selecionada
	public void mudarTelaFundo(int indice) {		
		Timer timer = new Timer();		
		tarefaTela = new TimerTask() {			
			@Override
			public void run() {															
				//Troca as imagens da tela do jogo - COR VERDE
				if(indice == 0) {
					geniusLabel.setIcon(img02);						
					sleep(tempoTrocaImagem);
					geniusLabel.setIcon(img01);
				}
				//Troca as imagens da tela do jogo - COR VERMELHO
				else if(indice == 1) {
					geniusLabel.setIcon(img03);						
					sleep(tempoTrocaImagem);
					geniusLabel.setIcon(img01);
				}
				//Troca as imagens da tela do jogo - COR AMARELO
				else if(indice == 2) {						
					geniusLabel.setIcon(img04);						
					sleep(tempoTrocaImagem);
					geniusLabel.setIcon(img01);
				}
				//Troca as imagens da tela do jogo - COR AZUL
				else if(indice == 3) {					
					geniusLabel.setIcon(img05);						
					sleep(tempoTrocaImagem);
					geniusLabel.setIcon(img01);
				}					
			}			
		};
		 timer.schedule(tarefaTela, 0);
	}
	
	public void play(){ 
		
		Timer time = new Timer();
		
		tarefaPlay = new TimerTask() {
			
			@Override
			public void run() {
				
				while(true) {					
					desabilitaBotoes();					
					jogo.gerarSequencia();
					int j = 0;
					
					//Mostra a sequencia de Cores
					for( j = 0; j <jogo.getSequencia().size(); j++) {
						sleep(450);																		
						mostrarBotoes(jogo.getSequencia().get(j));						
					}
					//Espera 2s antes de mostrar novamente a sequencia
					sleep(2000);
					// (EASY MODE - Mostra sequencia novamente)
					for( j = 0; j <jogo.getSequencia().size(); j++) {
						sleep(450);																		
						mostrarBotoes(jogo.getSequencia().get(j));						
					}
					
					//Da um tempo antes de liberar os botoes
					sleep(2000);
					
					//Toca um som indicando que é a vez do jogador
					tocarVamosLaSom();	
					
					
					for( j = 0; j < jogo.getSequencia().size(); j++) {
						
						//Habilita os Botoes para o jogador clicar
						habilitaBotoes();						
												
						//Baseado na velocidade que o jogador escolheu
						//Define o tempo que o botao fica ativado
						if(velocidade == 1) {
							cronometroLabel.setIcon(cronometro4);
							sleep(1000);
							cronometroLabel.setIcon(cronometro3);
							sleep(1000);
							cronometroLabel.setIcon(cronometro2);
							sleep(1000);
							cronometroLabel.setIcon(cronometro1);
							sleep(1000);
							cronometroLabel.setIcon(cronometro0);
						}
						else if(velocidade == 2) {
							cronometroLabel.setIcon(cronometro3);
							sleep(1000);
							cronometroLabel.setIcon(cronometro2);
							sleep(1000);
							cronometroLabel.setIcon(cronometro1);
							sleep(1000);
							cronometroLabel.setIcon(cronometro0);
						}
						else if(velocidade == 3) {
							cronometroLabel.setIcon(cronometro2);
							sleep(1000);
							cronometroLabel.setIcon(cronometro1);
							sleep(1000);
							cronometroLabel.setIcon(cronometro0);
						}
						
						//Depois que a contagem termina, apaga o cronometro
						cronometroLabel.setIcon(semCronometro);
						
						//Desabilita os botoes, para evitar clique acidental
						desabilitaBotoes();
						
						//Funcao responsavel por verificar se a cor selecionada
						//Esta de acordo com a cor da sequencia
						if(!jogo.verificaJogada(corEscolhida, j)) {
							quebrarWhile = 1;
							jogo.getSequencia().clear();
							tarefaCorSom.cancel();
							tarefaVamosSom.cancel();
							tarefaBotao.cancel();
							tarefaTela.cancel();
							tarefaPlay.cancel();							
							disposeJanela();
							break;
						}
																								
						//Espera 1s antes de habilitar novamente os botoes
						sleep(1000);													
					}
					
					//Se houve erro, eu quebro o While
					if(quebrarWhile == 1) {
						break;
					}
					
					//Mudança de rodada
					
					//Espera 2s para comecar uma nova rodada
					sleep(2000);
					//Retira a selecao da cor 
					corEscolhida = -1;
					//Aumenta o contator de nivel
					nivel++;
				}				
			}			
		};	
		time.schedule(tarefaPlay, 0);		
	}
	
	public void disposeJanela() {
		this.dispose();
		GameOver gameover = new GameOver(nivel);
		gameover.setVisible(true);
	}
	
	
	
}
