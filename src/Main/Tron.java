package Main;

import java.awt.Color;

import Model.Player;
import View.GameFrame;


public class Tron {
	//PARAMETRES DE LA PARTIE:
	
	//Taille en pixel de chaque "Carré"
	public static int PIXELSIZE = 2;
	
	//Coordonnées de départ des joueurs(NB: Elle sera mise à l'échelle par rapport au PIXELSIZE! CàD 1x1 = Un carré de PIXELSIZE pas de Pixels)
	public static int P1StartX = 600;
	public static int P1StartY = 200;
	
	public static int P2StartX = 0;
	public static int P2StartY = 200;
	
	//Orientation de départ(Player.LEFT/RIGHT/UP/DOWN)
	public static int p1InitOrientation = Player.LEFT;
	public static int p2InitOrientation = Player.RIGHT;
	
	//Taille de la grille(NB: Elle sera mise à l'échelle par rapport au PIXELSIZE! CàD 1x1 = Un carré de PIXELSIZE, pas un pixel)
	//La consigne = 600 X 400
	public static int GRIDW = 600;
	public static int GRIDH = 400;
	
	//Couleur RGB des deux joueurs
	public static Color p1Color = new Color(3,3,173); //Bleu
	public static Color p2Color = new Color(173,3,3); //Rouge
	
	//Pseudonymes des joueurs
	public static String p1Name = "BLEU";
	public static String p2Name = "ROUGE";
	
	//Type de Mouvement(Pivot: utilisez les touches Q,D et <-,-> pour changer d'angles(false), Direction: utilisez touches directionnelles(true). 
	public static boolean Gametype = false;
	
	
	
	public static void main(String[] args) {
		
		 System.out.println(">>> Taille de la grille: " + GRIDW + " X " + GRIDH);
		 System.out.println(">>> Taille des joueurs: " + PIXELSIZE + " pixels");
		 GameFrame f = new GameFrame();
		 f.addKeyListener(f.gamePanel);
	}
}

