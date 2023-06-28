package TELAINICIAL;

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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import PARAMETROS.Parametros;

public class TelaInicial extends JFrame implements ActionListener{

	//Label onde sera adiciona as imagens do plano de fundo;
	private JLabel telaInicial = new JLabel();
	
	//Imagens tela de fundo
	private ImageIcon foto01 = new ImageIcon(getClass().getResource("start img 01.png"));
	private ImageIcon foto02 = new ImageIcon(getClass().getResource("start img 02.png"));
	private ImageIcon foto03 = new ImageIcon(getClass().getResource("start img 03.png"));
	private ImageIcon foto04 = new ImageIcon(getClass().getResource("start img 04.png"));
	private ImageIcon foto05 = new ImageIcon(getClass().getResource("start img 05.png"));
	private ImageIcon foto06 = new ImageIcon(getClass().getResource("start img 06.png"));
	private ImageIcon foto07 = new ImageIcon(getClass().getResource("start img 07.png"));
	private ImageIcon foto08 = new ImageIcon(getClass().getResource("start img 08.png"));
	private ImageIcon foto09 = new ImageIcon(getClass().getResource("start img 09.png"));
	private ImageIcon foto10 = new ImageIcon(getClass().getResource("start img 10.png"));
	private ImageIcon foto11 = new ImageIcon(getClass().getResource("start img 11.png"));
	private ImageIcon foto12 = new ImageIcon(getClass().getResource("start img 12.png"));
	private ImageIcon foto13 = new ImageIcon(getClass().getResource("start img 13.png"));
	private ImageIcon foto14 = new ImageIcon(getClass().getResource("start img 14.png"));
	private ImageIcon foto15 = new ImageIcon(getClass().getResource("start img 15.png"));	
			
	//Tempo para trocar as imagens
	private int tempo = 400;
	private int tempo2 = 700;
	private Clip clip;
	
	//Botao de iniciar jogo
	private JButton startButton = new JButton();
		
	//Construtor da classe
	public TelaInicial() {
		setSize(1295, 722 );
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
			
		mudarTela();
		criaBotao();
				
		
		add(startButton);
		add(telaInicial);		
	}
	
	//Inicio do jogo
	public static void main(String[] args) {
		new TelaInicial();				
	}
	
	//Responsavel pela criacao do botao Start Gaming
	public void criaBotao() {
		startButton.addActionListener(this);
		startButton.setBounds(540, 400, 200, 50);
		startButton.setText("Start Gaming");
		startButton.setFont(new Font("Tahoma", Font.BOLD, 24));
		startButton.setBackground(new Color(255, 255, 255));		
	}
	
	//Acao quando o botao de start e clicado
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()== startButton) {
			tocarMusica(false);			
			cliqueBotao();
			this.dispose();				
			Parametros tela2 = new Parametros();
			tela2.setVisible(true);
		}
	}
	
	//Responsavel por selecionar o som de clique
	public void cliqueSom() throws UnsupportedAudioFileException, IOException, LineUnavailableException {							
		File somClique = new File("clique.wav");									
		AudioInputStream audioClique = AudioSystem.getAudioInputStream(somClique);
		Clip som = AudioSystem.getClip();;				
		som.open(audioClique);	
		som.start();
	}
	
	//Responsavel por reproduzir o som de clique do botao
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
	
	//Responsavel por selecionar a musica da tela inicial
	public void escolheMusica() throws UnsupportedAudioFileException, IOException, LineUnavailableException {							
		File file = new File("musicaTelaInicial.wav");									
		AudioInputStream audio = AudioSystem.getAudioInputStream(file);
		clip = AudioSystem.getClip();
		clip.open(audio);									
	}		
	
	//Responsavel por reproduzir a musica da tela inicial
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
		
	//Funcao responsavel pela animacao da tela inicial
	public void mudarTela() {
		
		Timer timer = new Timer();
		
		TimerTask tarefa = new TimerTask() {
			
			@Override
			public void run() {
					//Toca a musica da tela inicial					
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
						telaInicial.setIcon(foto01);						
						sleep(tempo);
						//Foto 02
						telaInicial.setIcon(foto02);						
						sleep(tempo);
						//Foto 03
						telaInicial.setIcon(foto03);						
						sleep(tempo);
						//Foto 04
						telaInicial.setIcon(foto04);						
						sleep(tempo);
						//Foto 05
						telaInicial.setIcon(foto05);
						sleep(tempo);
						//Foto 06
						telaInicial.setIcon(foto06);
						sleep(tempo);
						//Foto 07
						telaInicial.setIcon(foto07);
						sleep(tempo);
						//Foto 08
						telaInicial.setIcon(foto08);
						sleep(tempo);	
						//Foto 09
						telaInicial.setIcon(foto09);
						sleep(tempo);
						//Foto 10
						telaInicial.setIcon(foto10);
						sleep(tempo);	
						//Foto 11
						telaInicial.setIcon(foto11);
						sleep(tempo2);
						//Foto 12
						telaInicial.setIcon(foto12);
						sleep(tempo2);
						//Foto 13
						telaInicial.setIcon(foto13);
						sleep(tempo2);
						//Foto 14
						telaInicial.setIcon(foto14);
						sleep(tempo2);
						//Foto 15
						telaInicial.setIcon(foto15);
						sleep(tempo2);											
					}																							
			}			
		};
		 timer.schedule(tarefa, 0);
	}

}
