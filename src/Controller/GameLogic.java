package Controller;

import java.awt.Graphics;
import java.util.Date;

import Main.Tron;
import Model.Player;
import View.GamePanel;

public class GameLogic {
	private Player player1, player2;
	private int width, height;

	public GameLogic(int maxWidth, int maxHeight) {
		
		width = maxWidth;
		height = maxHeight;

		player1 = new Player(Tron.p1Name,Tron.p1InitOrientation, Tron.p1Color, Tron.P1StartX * Tron.PIXELSIZE, Tron.P1StartY * Tron.PIXELSIZE);  
		player2 = new Player(Tron.p2Name, Tron.p2InitOrientation, Tron.p2Color, Tron.P2StartX * Tron.PIXELSIZE, Tron.P2StartY * Tron.PIXELSIZE); 
		
	}


	public void setP1Direction(int direct) {
		
		player1.setDirection(direct);
	}

	public void setP2Direction(int direct) {
		
		player2.setDirection(direct);
	}

	public String getP1Name() 
	{
		return player1.getName();
	}

	public String getP2Name() {
		
		return player2.getName();
	}

	public Player getP1() {
		
		return player1;
	}

	public Player getP2() {
		
		return player2;
	}


	public boolean stepAndOk() {

		player1.step();
		player2.step();
		
		//Vérification du status fini de la partie.
		if(player1.loosed(player2, width, height))
		{
			player2.isWinner(GameOverTimer());
			return false;
		}
		else if(player2.loosed(player1, width, height))
		{
			player1.isWinner(GameOverTimer());
			return false;
		}
		//Si personne n'a perdu, alors la partie continue.
		return true;
	}
	
	public long GameOverTimer() {
		
		//Prend date actuelle, soustrait le temps initial = Durée d'execution.
		long GameTimer = (new Date()).getTime() - GamePanel.startTime;
		return GameTimer;
	}


	public void reset() {
		
		//Reset Position des deux joueurs
		player1 = new Player(Tron.p1Name,Tron.p1InitOrientation, Tron.p1Color, Tron.P1StartX * Tron.PIXELSIZE, Tron.P1StartY * Tron.PIXELSIZE);
		player2 = new Player(Tron.p2Name,Tron.p2InitOrientation, Tron.p2Color, Tron.P2StartX * Tron.PIXELSIZE, Tron.P2StartY * Tron.PIXELSIZE);
	}



	public void draw(Graphics g) {
		
		player1.draw(g);
		player2.draw(g);
	}

}
