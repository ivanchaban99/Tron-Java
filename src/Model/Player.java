package Model;

import java.awt.Color;
import java.awt.Graphics;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import Main.Tron;

public class Player {

	private String name;
	private int direction;
	private LengthNode head;
	private Color color;

	public static int UP = 0, RIGHT = 1, DOWN = 2, LEFT = 3;

	public Player(String name, int direction, Color color, int x, int y) {
		
		this.name = name;
		this.direction = direction;
		this.color = color;
		head = new LengthNode(x, y, null);
	}

	public String getName() {
		
		return name;
	}

	public void setDirection(int direction) {
		
		this.direction = direction;
	}



	public int getDirection() {
		
		return direction;
	}

	public LengthNode getHead() {
		
		return head;
	}

	public void step()
	{
		switch(direction)
		{
		case 0: 		//UP
			head = new LengthNode(head.getX(), head.getY() - Tron.PIXELSIZE, head);
			break;
		case 1: 		//RIGHT
			head = new LengthNode(head.getX() + Tron.PIXELSIZE, head.getY(), head);
			break;
		case 2: 		//DOWN
			head = new LengthNode(head.getX(), head.getY() + Tron.PIXELSIZE, head);
			break;
		case 3: 		//LEFT
			head = new LengthNode(head.getX() - Tron.PIXELSIZE, head.getY(), head);
			break;
		case -1: 		//LEFT DEBUG: En Java % n'est pas MODULO, mais RESTE donc si reste négatif, alors revenir à la norme.
			this.direction = 3;
			head = new LengthNode(head.getX(), head.getY() - Tron.PIXELSIZE, head);
			break;
		}

	}



	public boolean loosed(Player other, int maxWidth, int maxHeight)
	{
		//Le joueur se rentre dedans
		for(LengthNode p = head.getNext(); p.getNext() != null ; p = p.getNext())
			if(head.sameValues(p))
				return true;
		//Le joueur rentre dans l'autre joueur
		for(LengthNode p = other.getHead(); p.getNext() != null ; p = p.getNext())
			if(head.sameValues(p))
				return true;
		//Le joueur est à l'extérieur des murs
		if(head.getX() < 0 || head.getY() < 0 || head.getX() > maxWidth + Tron.PIXELSIZE || head.getY() > maxHeight + Tron.PIXELSIZE)
			return true;

		return false;
	}
	
	public void isWinner(long gameTimer) {
		
		//La commande pour le Uptime return un long, il faut le convertir en float pour l'avoir en s au lieu de ms, puis en string pour envoyer dans la bdd
		float GameTimer = gameTimer;
		GameTimer = GameTimer/1000;
		String Time = Float.toString(GameTimer);
		
		try {
			String url="jdbc:mysql://chabanvpn.ovh:3306/tron?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false";
		    String user="tron";
		    String password="root";
		   
		    Connection conn = null;
		    conn = DriverManager.getConnection(url, user, password);
		    System.out.println("[SQL] - Connecté à la BDD!");
		    
		    CallableStatement cStmt = conn.prepareCall("{call add_record(?, ?)}");
		    cStmt.setString(1, this.getName());
		    cStmt.setString(2, Time);
		    cStmt.execute();
		    System.out.println("[SQL] - Enregistré sur la BDD!");


		} catch (SQLException ex) {
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
		
		
		System.out.println(">>> Le joueur " + this.getName() + " à gagné!");
		System.out.println(">>> Durée de la partie: " + GameTimer + "s");
		System.out.println(">>> FIN DE LA PARTIE !");
		
		//Fenêtre Pop-up
		JOptionPane.showMessageDialog(null, "Le joueur " + this.getName() + " à gagné!\nAppuyez sur OK puis F8 pour recommencer!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
	}


	public void draw(Graphics g)
	{
		g.setColor(color);
		LengthNode p = head;
		//Pour tout les joueurs...
		for(;p != null; p=p.getNext()) {
			g.fillRect(p.getX(), p.getY(), Tron.PIXELSIZE, Tron.PIXELSIZE);	//AJOUTER au joueur un carré de pos getX, getY de la taille PIXELSIZE
		}
	}
	
	
}




