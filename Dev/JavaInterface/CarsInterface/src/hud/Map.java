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

	private static double VertexHeight = 30.0;
	private static double VertexWidth = 30.0;

	public Map(){}

	public Map(Area _Area){
		this.area = _Area;
		this.streetPainter = new StreetDrawer(this.area, VertexWidth, VertexHeight);
		this.vertexPainter = new VertexDrawer(this.area, VertexWidth, VertexHeight);
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

		System.out.println("--------------------------");
		g2d.dispose();
	}
	
	public void sendNearVertex(int _x, int _y){
		double distance = 0.0;
		double vertexX, vertexY = 0.0;
		double lessDistance = 999999.9;
		Vertex destination = new Vertex();
		Client client = new Client();
		
		// browse all the vertex to find the nearly one
		for(int i=0; i<this.area.getMap().getVertices().size(); i++){
			vertexX = this.area.getMap().getVertices().get(i).getX();
			vertexY = this.area.getMap().getVertices().get(i).getY();
			distance = Math.sqrt((vertexX - _x) + (vertexY - _y));
			// A less distance with a vertex is find
			if(distance < lessDistance){
				lessDistance = distance;
				System.out.println("less distance : " + lessDistance);
				destination = this.area.getMap().getVertices().get(i);
			}
		}
		client.sendVertex(this.area.getName(),destination.getName());
	}

	public void mouseClicked( MouseEvent e ){
		
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

	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
