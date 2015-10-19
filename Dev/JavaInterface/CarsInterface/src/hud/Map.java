package hud;

import structures.map.*;
import webSocket.Client;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Map extends JPanel implements MouseListener{

	/**
	 * SERIAL VERSION
	 */
	private static final long serialVersionUID = 1L;

	private Area area;
	private StreetDrawer streetPainter;
	private VertexDrawer vertexPainter;
	private CabDrawer cabPainter;

	private static double VertexHeight = 30.0;
	private static double VertexWidth = 30.0;

	public Map(){}

	public Map(Area _Area){
		this.area = _Area;
		this.streetPainter = new StreetDrawer(this.area, VertexWidth, VertexHeight);
		this.vertexPainter = new VertexDrawer(this.area, VertexWidth, VertexHeight);
		this.cabPainter = new CabDrawer();
	}

	public void paintComponent(Graphics g){

		super.paintComponent(g);
		Graphics2D g2d;
		g2d = (Graphics2D) g.create();

		addMouseListener((MouseListener) this);

		// Draw the streets
		this.streetPainter.paintStreets(g2d);

		// Draw the vertices
		this.vertexPainter.paintVertices(g2d);
		
		this.cabPainter.setX(50.0);
		this.cabPainter.setY(50.0);
		
		// Draw the Cab
		this.cabPainter.paintCab(g2d);

		System.out.println("--------------------------");
		g2d.dispose();
	}
	
	public void sendNearVertex(double _x, double _y){
		double distance = 0.0, vertexX = 0.0, vertexY = 0.0, lessDistance = 9999999.9;
		Vertex destination = new Vertex();
		Client client = new Client();		
		
		System.out.println("Taille ArrayList Vertex : " + this.area.getMap().getVertices().size());
		
		// browse all the vertex to find the nearly one
		for(int i=0; i<this.area.getMap().getVertices().size(); i++){
			
			// Point du clic SOURIS (550,1300)
			//System.out.println(_x + "  " + _y);
			
			// Point du VERTEX (550,1300)
			vertexX = this.area.getMap().getVertices().get(i).getX();
			vertexY = this.area.getMap().getVertices().get(i).getY();
			
			// Affichage NOM et COORD du VERTEX
			//System.out.println(this.area.getMap().getVertices().get(i).getName());
			//System.out.println("i : " + i + "\nx = " + vertexX + "\ny = " + vertexY);
			
			// Calcul de la distance entre les deux points
			distance = Math.sqrt(Math.pow(vertexX - _x,2) + Math.pow(vertexY - _y,2));
			//System.out.println("distance : " + distance);
			
			// A less distance with a vertex is find
			if(distance < lessDistance){
				lessDistance = distance;
				//System.out.println("less distance : " + lessDistance);
				destination = this.area.getMap().getVertices().get(i);
			}
		}
		client.sendVertex(this.area.getName(),destination.getName());
	}

	public void mouseClicked( MouseEvent e ){
		
	}

	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
int x,y;
		
		// Clique gauche de la souris
		if(e.getButton()==MouseEvent.BUTTON1){
			x = e.getX();
			y = e.getY();
			// Récupération de la position
			System.out.println("Left Click\nx : " + x + "\ny : " + y);
			
			sendNearVertex(x,y);
		}
		
	}
}
