package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;

import Controller.GameLogic;
import Main.Tron;
import Model.Player;

public class GamePanel extends JPanel implements ActionListener, KeyListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private GameLogic logic;
	
	public static final int W = Tron.GRIDW * Tron.PIXELSIZE, H = Tron.GRIDH * Tron.PIXELSIZE;

	Timer Clock;
	public static long  startTime;

	public GamePanel() {
		
		
		setBackground(Color.BLACK);
		setLayout(new BorderLayout());
		setSize(W, H);
	
		
		
		logic = new GameLogic(W, H);
		Clock = new Timer(30, this);

	}

	

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		logic.draw(g);
	}


	
	public void start() {
		//Prends le temps actuel pour calculer ensuite la durée de la partie
		startTime = System.currentTimeMillis();
		//Reset de la logic, donc de la partie
		logic.reset();
		//On démarre la Clock
		Clock.start();
		//
		System.out.println(">>> Début d'une nouvelle partie!");
	}
	
	

	public void actionPerformed(ActionEvent e) {
		//A chaque cycle de Clock
		if(e.getSource()==Clock)
		{
			//Le jeu continue
			if(logic.stepAndOk())
				//Afficher les avancements des deux joueurs calculés dans stepAndOk
				repaint();
			else
			{   //Sinon il s'arrête
				Clock.stop();	
			}
			repaint();
		}

	}

public void keyPressed(KeyEvent e) {
	/*
	 * Touches du joueur 1 si mode "directionnel"
	 */
	if(e.getKeyCode() == KeyEvent.VK_DOWN && Tron.Gametype == true){
		if(logic.getP1().getDirection() != Player.UP)
			logic.setP1Direction(Player.DOWN);
	}
	else if(e.getKeyCode() == KeyEvent.VK_UP && Tron.Gametype == true){
		if(logic.getP1().getDirection() != Player.DOWN)
			logic.setP1Direction(Player.UP);
	}
	else if(e.getKeyCode() == KeyEvent.VK_LEFT && Tron.Gametype == true){
		if(logic.getP1().getDirection() != Player.RIGHT)
			logic.setP1Direction(Player.LEFT);
	}
	else if(e.getKeyCode() == KeyEvent.VK_RIGHT && Tron.Gametype == true){
		if(logic.getP1().getDirection() != Player.LEFT)
			logic.setP1Direction(Player.RIGHT);
	}

	/*
	 * Touches du joueur 2 si mode "directionnel"
	 */
	if(e.getKeyCode() == KeyEvent.VK_S && Tron.Gametype == true){
		if(logic.getP2().getDirection() != Player.UP)
			logic.setP2Direction(Player.DOWN);
	}
	else if(e.getKeyCode() == KeyEvent.VK_Z && Tron.Gametype == true){
		if(logic.getP2().getDirection() != Player.DOWN)
			logic.setP2Direction(Player.UP);
	}
	else if(e.getKeyCode() == KeyEvent.VK_Q && Tron.Gametype == true){
		if(logic.getP2().getDirection() != Player.RIGHT)
			logic.setP2Direction(Player.LEFT);
	}
	else if(e.getKeyCode() == KeyEvent.VK_D && Tron.Gametype == true){
		if(logic.getP2().getDirection() != Player.LEFT)
			logic.setP2Direction(Player.RIGHT);
	}
	
	//Touches du joueur 1 si mode "pivot"
	//En Java % n'est pas MODULO, mais RESTE donc si reste négatif, le player.step() va gerer.
	if(e.getKeyCode() == KeyEvent.VK_LEFT && Tron.Gametype == false){
		logic.setP1Direction(((logic.getP1().getDirection()) - 1) % 4);
	}
	else if(e.getKeyCode() == KeyEvent.VK_RIGHT && Tron.Gametype == false){
		logic.setP1Direction(((logic.getP1().getDirection()) + 1) % 4);
	}

			
	// Touches du joueur 2 si mode "pivot"
	//En Java % n'est pas MODULO, mais RESTE donc si reste négatif, le player.step() va gerer.
	if(e.getKeyCode() == KeyEvent.VK_Q && Tron.Gametype == false){
		logic.setP2Direction(((logic.getP2().getDirection()) - 1) % 4);
	}
	else if(e.getKeyCode() == KeyEvent.VK_D && Tron.Gametype == false){
		logic.setP2Direction(((logic.getP2().getDirection()) + 1) % 4);
	}
			
	/*
	 * Recommencer la partie
	 */
	if(e.getKeyCode() == KeyEvent.VK_F8)
	{
		start();
	}
}

	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}



}
