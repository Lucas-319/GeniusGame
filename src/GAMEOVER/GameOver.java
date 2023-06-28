package GAMEOVER;

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
import javax.swing.JOptionPane;

import TELAINICIAL.TelaInicial;

public class GameOver extends JFrame implements ActionListener{
	
	private JLabel gameOverLabel = new JLabel();
	
	private JLabel nivelAlcancado = new JLabel();
	
	private JButton continuaButton = new JButton();
	
	private JButton saiButton = new JButton();
		
	TimerTask tarefaFailSom;
	TimerTask tarefaGameOverSom;
	TimerTask tarefaAnimacao;
	
	private Clip msc;
	
	private ImageIcon gameOverImg01 = new ImageIcon(getClass().getResource("game over img 01.png"));
	private ImageIcon gameOverImg02 = new ImageIcon(getClass().getResource("game over img 02.png")); 
	private ImageIcon gameOverImg03 = new ImageIcon(getClass().getResource("game over img 03.png")); 
	private ImageIcon gameOverImg04 = new ImageIcon(getClass().getResource("game over img 04.png")); 
	private ImageIcon gameOverImg05 = new ImageIcon(getClass().getResource("game over img 05.png")); 
	private ImageIcon gameOverImg06 = new ImageIcon(getClass().getResource("game over img 06.png")); 
	
	//Construtor da Classe
	public GameOver(int nivel) {
		setSize(1295, 722 );
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(false);	
		
		animacaoGameOver(nivel);
				
		add(nivelAlcancado);
		add(continuaButton);
		add(saiButton);
		add(gameOverLabel);		
	}
	
	//Funcao responsavel por selecionar o audio FAIL
	public void failSom() throws UnsupportedAudioFileException, IOException, LineUnavailableException{
		File file = new File("fail.wav");		
		AudioInputStream audioFail = AudioSystem.getAudioInputStream(file);
		Clip clipFail = AudioSystem.getClip();
		clipFail.open(audioFail);		
		clipFail.start();
	}
	//Funcao responsavel por tocar o som FAIL
	public void tocarFailSom() {
		Timer timer = new Timer();		
		tarefaFailSom = new TimerTask() {			
			@Override
			public void run() {														
					try {
						failSom();
					} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}																																
			}			
		};
		 timer.schedule(tarefaFailSom, 0);
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
	
	//Responsavel por definir o que vai aparecer na tela GAME OVER
	public void animacaoGameOver(int nivel) {
		
		Timer timer = new Timer();
		
		tarefaAnimacao = new TimerTask() {
			
			@Override
			public void run() {													
				gameOverLabel.setIcon(gameOverImg01);
				tocarFailSom();
				sleep(5000);
				tocarGameOverSom();	
				gameOverLabel.setIcon(gameOverImg02);
				criaBotaoContinua();
				criaBotaoSai();
				nivel(nivel);
				sleep(3000);
				try {
					escolheMusica();
					tocarMusica(true);
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}				
				while(true) {								
				gameOverLabel.setIcon(gameOverImg03);
				sleep(400);
				gameOverLabel.setIcon(gameOverImg04);
				sleep(400);
				gameOverLabel.setIcon(gameOverImg05);
				sleep(400);
				gameOverLabel.setIcon(gameOverImg06);
				sleep(400);
				gameOverLabel.setIcon(gameOverImg02);
				sleep(400);
				}
			}			
		};
		 timer.schedule(tarefaAnimacao, 0);
	}
	
	//Funcao responsavel por criar o botao de continuar
	public void criaBotaoContinua() {
		continuaButton.addActionListener(this);
		continuaButton.setBounds(540, 520, 200, 50);
		continuaButton.setText("Continuar");
		continuaButton.setFont(new Font("Tahoma", Font.BOLD, 24));
		continuaButton.setBackground(new Color(255, 255, 255));		
	}
	
	//Funcao responsavel por criar o botao de sair
	public void criaBotaoSai() {
		saiButton.addActionListener(this);
		saiButton.setBounds(540, 600, 200, 50);
		saiButton.setText("Sair");
		saiButton.setFont(new Font("Tahoma", Font.BOLD, 24));
		saiButton.setForeground(new Color(255, 255, 255));
		saiButton.setBackground(new Color(255, 0, 0));		
	}
	
	public void nivel(int nivel) {
		nivelAlcancado.setBounds(530, 440, 300, 70);
		nivelAlcancado.setText("Nivel alcancado: "+nivel);
		nivelAlcancado.setFont(new Font("Tahoma", Font.BOLD, 24));	
		nivelAlcancado.setForeground(new Color(255, 255, 255));
		nivelAlcancado.setBackground(new Color(0, 0, 0));
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()== continuaButton) {						
			cliqueBotao();	
			tocarMusica(false);
			this.dispose();					
			new TelaInicial();			
		}
		else if(e.getSource() == saiButton) {
			cliqueBotao();
			if(JOptionPane.showConfirmDialog(saiButton, "Deseja realmente sair?", "SAIR",
					JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION) 
			{	
				tocarMusica(false);
				System.exit(0);
			}
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
	
	//Responsavel por reproduzir o som de clique
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
	
	//Responsavel por selecionar o audio GAME OVER
	public void gameOverSom() throws UnsupportedAudioFileException, IOException, LineUnavailableException{
		File file = new File("gameover.wav");		
		AudioInputStream audioGameOver = AudioSystem.getAudioInputStream(file);
		Clip clipGameOver = AudioSystem.getClip();
		clipGameOver.open(audioGameOver);		
		clipGameOver.start();
	}
	
	//Responsavel pela reproducao do som GAME OVER
	public void tocarGameOverSom() {
		Timer timer = new Timer();		
		tarefaGameOverSom = new TimerTask() {			
			@Override
			public void run() {														
					try {
						gameOverSom();
					} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}																																
			}			
		};
		 timer.schedule(tarefaGameOverSom, 0);
	}
	
	//Funcao responsavel por selecionar a musica
	public void escolheMusica() throws UnsupportedAudioFileException, IOException, LineUnavailableException {							
		File file = new File("chandelier.wav");									
		AudioInputStream audio = AudioSystem.getAudioInputStream(file);
		msc = AudioSystem.getClip();
		msc.open(audio);									
	}		
		
	//Funcao responsavel pela reproducao da musica
	public void tocarMusica(boolean opcao) {
		if(opcao) {
			msc.start();
			msc.loop(Clip.LOOP_CONTINUOUSLY);
		}
		else {
			msc.stop();
		}		
	}
}
