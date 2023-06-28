package PARAMETROS;

import java.awt.Color;
import java.awt.Font;
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
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;


import DISTRIBUICAO.Distribuicao;

public class Parametros extends JFrame implements ActionListener{
	
	//Label onde sera adiciona as imagens do plano de fundo;
	private JLabel segundaTela = new JLabel();
	
	//Tempo de troca entre as imagens do plano de fundo;
	private int tempo = 300;
	
	//Botoes responsaveis por setar a velocidade;
	private JRadioButton rbVelocidade01 = new JRadioButton();
	private JRadioButton rbVelocidade02 = new JRadioButton();
	private JRadioButton rbVelocidade03 = new JRadioButton();
	
	//Botoes responsaveis por setar a dificuldade;
	private JRadioButton rbDificuldadeE = new JRadioButton();
	private JRadioButton rbDificuldadeN = new JRadioButton();
	private JRadioButton rbDificuldadeH = new JRadioButton();
	
	//Agrupa os botoes de velocidade;
	private ButtonGroup velocidade = new ButtonGroup();
	
	//Agrupa os botoes de dificuldade;
	private ButtonGroup dificuldade = new ButtonGroup();
	
	//Botao de confirmacao;
	private JButton confirmaButton = new JButton();
	
	//Variavel responsavel pela musica;
	private Clip clip;
			
	//Imagens do plano de fundo	
	private ImageIcon foto01 = new ImageIcon(getClass().getResource("parametro img 01.png"));
	private ImageIcon foto02 = new ImageIcon(getClass().getResource("parametro img 02.png"));
	private ImageIcon foto03 = new ImageIcon(getClass().getResource("parametro img 03.png"));
	private ImageIcon foto04 = new ImageIcon(getClass().getResource("parametro img 04.png"));
	private ImageIcon foto05 = new ImageIcon(getClass().getResource("parametro img 05.png"));
	private ImageIcon foto06 = new ImageIcon(getClass().getResource("parametro img 06.png"));
	private ImageIcon foto07 = new ImageIcon(getClass().getResource("parametro img 07.png"));
	private ImageIcon foto08 = new ImageIcon(getClass().getResource("parametro img 08.png"));
	private ImageIcon foto09 = new ImageIcon(getClass().getResource("parametro img 09.png"));
	private ImageIcon foto10 = new ImageIcon(getClass().getResource("parametro img 10.png"));
	private ImageIcon foto11 = new ImageIcon(getClass().getResource("parametro img 11.png"));
	private ImageIcon foto12 = new ImageIcon(getClass().getResource("parametro img 12.png"));
	private ImageIcon foto13 = new ImageIcon(getClass().getResource("parametro img 13.png"));
	private ImageIcon foto14 = new ImageIcon(getClass().getResource("parametro img 14.png"));
	private ImageIcon foto15 = new ImageIcon(getClass().getResource("parametro img 15.png"));
	private ImageIcon foto16 = new ImageIcon(getClass().getResource("parametro img 16.png"));
	private ImageIcon foto17 = new ImageIcon(getClass().getResource("parametro img 17.png"));
	private ImageIcon foto18 = new ImageIcon(getClass().getResource("parametro img 18.png"));
	private ImageIcon foto19 = new ImageIcon(getClass().getResource("parametro img 19.png"));
	private ImageIcon foto20 = new ImageIcon(getClass().getResource("parametro img 20.png"));
	private ImageIcon foto21 = new ImageIcon(getClass().getResource("parametro img 21.png"));
	private ImageIcon foto22 = new ImageIcon(getClass().getResource("parametro img 22.png"));
	
	
	//Construtor da classe
	public Parametros() {				 		
		setSize(1295, 722 );
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(false);
		
		mudaTelaFundo();
		criaBotaoVelocidade();
		criaBotaoDificuldade();
		criaBotaoConfirma();
		
		//Botoes de velocidade
		add(rbVelocidade01);
		add(rbVelocidade02);
		add(rbVelocidade03);
		//Botoes de dificuldade;
		add(rbDificuldadeE);
		add(rbDificuldadeN);
		add(rbDificuldadeH);
		//Botao de confirmacao;
		add(confirmaButton);		
		//Adiciona as imagens ao plano de fundo
		add(segundaTela);
	}
	
	//Coloca os botoes de velocidade nas posicoes corretas;
	public void criaBotaoVelocidade() {
		rbVelocidade01.setBounds(658, 325, 20, 20);
		rbVelocidade02.setBounds(770, 325, 20, 20);
		rbVelocidade03.setBounds(870, 325, 20, 20);
		
		//Seta um botao velocidade pra ficar selecionado
		rbVelocidade01.setSelected(true);
		modificaBotaoVelocidade();
		
		velocidade.add(rbVelocidade01);
		velocidade.add(rbVelocidade02);
		velocidade.add(rbVelocidade03);
	}
	
	//Modifica o botao pra deixar ele todo preto, deixando visivel apenas o circulo;
	public void modificaBotaoVelocidade() {		
		rbVelocidade01.setForeground(new Color(0, 0, 0));
		rbVelocidade01.setBackground(new Color(0, 0, 0));
		rbVelocidade02.setForeground(new Color(0, 0, 0));
		rbVelocidade02.setBackground(new Color(0, 0, 0));
		rbVelocidade03.setForeground(new Color(0, 0, 0));
		rbVelocidade03.setBackground(new Color(0, 0, 0));
	}
	
	//Coloca os botoes de dificuldade nas posicoes corretas;
	public void criaBotaoDificuldade() {
		rbDificuldadeE.setBounds(658, 440, 20, 20);
		rbDificuldadeN.setBounds(770, 440, 20, 20);
		rbDificuldadeH.setBounds(870, 440, 20, 20);
		
		//Seta um botao dificuldade para ficar selecionado
		rbDificuldadeN.setSelected(true);
					
		modificaBotaoDificuldade();
		
		dificuldade.add(rbDificuldadeE);
		dificuldade.add(rbDificuldadeN);
		dificuldade.add(rbDificuldadeH);
	}
	
	//Modifica o botao pra deixar ele todo preto, deixando visivel apenas o circulo;
	public void modificaBotaoDificuldade() {			
		rbDificuldadeE.setForeground(new Color(0, 0, 0));
		rbDificuldadeE.setBackground(new Color(0, 0, 0));
		rbDificuldadeN.setForeground(new Color(0, 0, 0));
		rbDificuldadeN.setBackground(new Color(0, 0, 0));
		rbDificuldadeH.setForeground(new Color(0, 0, 0));
		rbDificuldadeH.setBackground(new Color(0, 0, 0));
	}
	
	//Cria o botao de confirmacao
	public void criaBotaoConfirma() {
		confirmaButton.addActionListener(this);
		confirmaButton.setBounds(540, 600, 200, 50);
		confirmaButton.setText("Confirmar");
		confirmaButton.setFont(new Font("Tahoma", Font.BOLD, 24));
		confirmaButton.setBackground(new Color(255, 255, 255));
		
	}
		
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()== confirmaButton) {			
			tocarMusica(false);
			cliqueBotao();
			int vel = velocidadeSelecionada();
			int dif = dificuldadeSelecionada();			
			this.dispose();					
			new Distribuicao(vel, dif);
			
		}		
	}		
	
	//Descobre qual velocidade foi selecionada (1, 2, 3)
	public int velocidadeSelecionada() {
		if(rbVelocidade01.isSelected()) {
			return 1;
		}
		else if(rbVelocidade02.isSelected()) {
			return 2;
		}
		else if(rbVelocidade03.isSelected()){
			return 3;
		}
		return 0;
	}
	
	//Descobre qual dificuldade foi selecionada (FACIL, NORMAL, DIFICIL)
	public int dificuldadeSelecionada() {
		if(rbDificuldadeE.isSelected()) {
			return 1;
		}
		else if(rbDificuldadeN.isSelected()) {
			return 2;
		}
		else if(rbDificuldadeH.isSelected()){
			return 3;
		}
		return 0;
	}
	
	//Funcao responsavel por selecionar o som de clique
	public void cliqueSom() throws UnsupportedAudioFileException, IOException, LineUnavailableException {							
		File somClique = new File("clique.wav");									
		AudioInputStream audioClique = AudioSystem.getAudioInputStream(somClique);
		Clip som = AudioSystem.getClip();;				
		som.open(audioClique);	
		som.start();
	}	
	
	//Funcao responsavel pela reproducao do som de clique
	public void cliqueBotao() {
		Timer timer = new Timer();
		
		TimerTask tarefa = new TimerTask() {			
			@Override
			public void run() {														
					try {
						cliqueSom();
					} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}																																
			}			
		};
		 timer.schedule(tarefa, 0);
	}
	
	//Funcao responsavel por selecionar a musica
	public void escolheMusica() throws UnsupportedAudioFileException, IOException, LineUnavailableException {							
		File file = new File("musicaTelaInicial.wav");									
		AudioInputStream audio = AudioSystem.getAudioInputStream(file);
		clip = AudioSystem.getClip();
		clip.open(audio);									
	}		
	
	//Funcao responsavel pela reproducao da musica
	public void tocarMusica(boolean opcao) {
		if(opcao) {
			clip.start();
			clip.loop(Clip.LOOP_CONTINUOUSLY);
		}
		else {
			clip.stop();
		}		
	}
	
	//Pausa a execucao do codigo
	public void sleep(int milissegundos) {
		try {
			Thread.sleep(milissegundos);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//Funcao responsavel pela animacao da tela parametros
	public void mudaTelaFundo() {
		
		Timer timer = new Timer();
		
		TimerTask tarefa = new TimerTask() {
			
			@Override
			public void run() {
				//Toca a musica da tela de escolha dos parametros
				try {
					escolheMusica();
					tocarMusica(true);
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}				
				//Troca as imagens da tela inicial
				while(true) {
					//Foto 01
					segundaTela.setIcon(foto01);														
					sleep(tempo);
					//Foto 02
					segundaTela.setIcon(foto02);														
					sleep(tempo);
					//Foto 03
					segundaTela.setIcon(foto03);														
					sleep(tempo);
					//Foto 04
					segundaTela.setIcon(foto04);														
					sleep(tempo);
					//Foto 05
					segundaTela.setIcon(foto05);														
					sleep(tempo);
					//Foto 06
					segundaTela.setIcon(foto06);														
					sleep(tempo);
					//Foto 07
					segundaTela.setIcon(foto07);														
					sleep(tempo);
					//Foto 01 - NOVAMENTE
					segundaTela.setIcon(foto01);														
					sleep(tempo);
					//Foto 08
					segundaTela.setIcon(foto08);														
					sleep(tempo);
					//Foto 09
					segundaTela.setIcon(foto09);														
					sleep(tempo);
					//Foto 10
					segundaTela.setIcon(foto10);														
					sleep(tempo);
					//Foto 11
					segundaTela.setIcon(foto11);														
					sleep(tempo);
					//Foto 12
					segundaTela.setIcon(foto12);														
					sleep(tempo);
					//Foto 07 - NOVAMENTE
					segundaTela.setIcon(foto07);														
					sleep(tempo);
					//Foto 01 - NOVAMENTE
					segundaTela.setIcon(foto01);														
					sleep(tempo);				
					//Foto 13
					segundaTela.setIcon(foto13);														
					sleep(tempo);
					//Foto 14
					segundaTela.setIcon(foto14);														
					sleep(tempo);
					//Foto 15
					segundaTela.setIcon(foto15);														
					sleep(tempo);
					//Foto 16
					segundaTela.setIcon(foto16);														
					sleep(tempo);
					//Foto 17
					segundaTela.setIcon(foto17);														
					sleep(tempo);
					//Foto 07 - NOVAMENTE
					segundaTela.setIcon(foto07);														
					sleep(tempo);
					//Foto 01 - NOVAMENTE
					segundaTela.setIcon(foto01);														
					sleep(tempo);
					//Foto 18
					segundaTela.setIcon(foto18);														
					sleep(tempo);
					//Foto 19
					segundaTela.setIcon(foto19);														
					sleep(tempo);
					//Foto 20
					segundaTela.setIcon(foto20);														
					sleep(tempo);
					//Foto 21
					segundaTela.setIcon(foto21);														
					sleep(tempo);
					//Foto 22
					segundaTela.setIcon(foto22);														
					sleep(tempo);
					//Foto 07 - NOVAMENTE
					segundaTela.setIcon(foto07);														
					sleep(tempo);																					
				}																										
			}			
		};
		 timer.schedule(tarefa, 0);
	}
}
