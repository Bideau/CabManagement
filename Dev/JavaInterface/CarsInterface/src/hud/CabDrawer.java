package hud;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import structures.map.Area;
import structures.map.Vertex;
import structures.taxi.CabInfo;

public class CabDrawer implements ImageObserver {

	private Area area;
	private CabInfo cabInfo;

	private static double CabWidth = 100;
	private static double CabHeight = 55;
	
	private int xOffsetCab = 30;
	private int yOffsetCab = 13;

	private double x;
	private double y;
	
	private int xMaxPixels;
	private int yMaxPixels;
	
	private int positionRatioBetweenTwoVertex;
	
	private double vertexOrigineX;
	private double vertexOrigineY;
	private double vertexDestX;
	private double vertexDestY;
	
	// Default constructor
	public CabDrawer(){
		this.x = 10000.0;
		this.y = 10000.0;
	}

	// Overload constructor
	public CabDrawer(Area area, int xMaxPixels, int yMaxPixels){
		
		// Initialization of the variables
		this.x = 10000.0;
		this.y = 10000.0;
		this.area = area;
		
		this.xMaxPixels = xMaxPixels;
		this.yMaxPixels = yMaxPixels;
		
		this.vertexOrigineX = 0.0;
		this.vertexOrigineY = 0.0;
		this.vertexDestX = 0.0;
		this.vertexDestY = 0.0;
	}

	// Set the object CabInfo which was traited by the JSON Parser
	public void setCabInfo(CabInfo cab){
		this.cabInfo = cab;
	}

	// Function which analyze and draw the informations of the CabInfo
	public void paintCab(Graphics2D g2d){

		// Name of the area of the cab informations must be the same as that of the area
		if(this.cabInfo.getLocNow().getArea().equals(this.area.getName())){
			// Function analyze and return the coordonates of the two vertex (Now and Next)
			getInformationFromCabInfo();
		}
		
		// Place the cab on the street between the two vertex
		this.x = this.positionRatioBetweenTwoVertex * (this.vertexDestX - this.vertexOrigineX);
		this.y = this.positionRatioBetweenTwoVertex * (this.vertexDestY - this.vertexOrigineY);
		
		this.x = this.x + vertexOrigineX;
		this.y = this.y + vertexOrigineY;
		
		// Scaling for frame
		this.x = Math.abs(this.x * this.xMaxPixels) - this.xOffsetCab;
		this.y = Math.abs(this.y * this.yMaxPixels) - this.yOffsetCab;
		
		System.out.println("x : " + this.x + "\ny : " + this.y);
		
		System.out.println("DOLOREAN DRAWER");
		
		try {
			Image dbImage = ImageIO.read(new File("img/dolorean_droite.png"));
			g2d.drawImage(dbImage, (int)this.x, (int)this.y, (int)CabWidth, (int)CabHeight,this);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// RECTANGLE		10.0,10.0,20.0,20.0
		// x - y
		// Longueur - Hauteur
		//Rectangle2D cab = new Rectangle2D.Double(this.x,this.y,CabWidth,CabHeight);
		//Rectangle2D _cab = new Rectangle2D.Double(this.x,this.y,CabWidth,CabHeight);

		// Affect color to the rectangle
		//g2d.setColor(new Color(Integer.parseInt("00FF00",16)));
		//g2d.fill(cab);
		//g2d.setColor(new Color(Integer.parseInt("FF0000",16)));
		//g2d.draw(_cab);
		
		
	}

	public void getInformationFromCabInfo(){

		//************* Research the two vertex  **************//
		for(Vertex object : this.area.getMap().getVertices()){

			// Search the first vertex of the CabInfo in the vertex of the map
			if(object.getName().equals(this.cabInfo.getLocNow().getLocation())){
				System.out.println("LocNow Found : " + object.getName());
				this.vertexOrigineX = object.getX();
				this.vertexOrigineY = object.getY();
				System.out.println("Org X : " + this.vertexOrigineX);
				System.out.println("Org Y : " + this.vertexOrigineY);
			}

			// Search the second vertex of the CabInfo in the vertex of the map
			if(object.getName().equals(this.cabInfo.getLocNext().getLocation())){
				System.out.println("LocNext Found : " + object.getName());
				this.vertexDestX = object.getX();
				this.vertexDestY = object.getY();
				System.out.println("Dest X : " + this.vertexDestX);
				System.out.println("Dest Y : " + this.vertexDestY);
			}
		}
		
		// Get the progression between the two vertex
		this.positionRatioBetweenTwoVertex = this.cabInfo.getLocNow().getProgression();
	}

	//********************** GETTERS ********************//
	
	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}
	
	//********************* SETTERS *********************//
	
	public void setX(double x) {
		this.x = x;
	}

	public void setY(double y) {
		this.y = y;
	}

	public boolean imageUpdate(Image arg0, int arg1, int arg2, int arg3,int arg4, int arg5) {
		// TODO Auto-generated method stub
		return false;
	}
}