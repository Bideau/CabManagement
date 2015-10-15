package Interface;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import structures.Area;
import structures.Coord;
import structures.Streets;
import structures.Vertice;

public class InterfaceMap extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int Width = 700;
	private int Height = 500;
	
	private JFrame frame;
	private JPanel mainPanel;
	private JPanel nothing;
	private JTextArea Titre;
	
	private Map MyMap;
	private Area MyArea;
	
	public InterfaceMap(){
		frame = new JFrame();
		mainPanel = new JPanel();
		nothing = new JPanel();
		Titre = new JTextArea();
		MyMap = new Map();
		MyArea = new Area();
	}
	
	public InterfaceMap(Area _Area){
		frame = new JFrame();
		mainPanel = new JPanel();
		nothing = new JPanel();
		Titre = new JTextArea();
		MyArea = _Area;
		MyMap = new Map(MyArea);
		System.out.println("Constructeur Surcharge");
	}
	
	public void DrawInterface(){
		
		frame.setSize(Width,Height);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		
		mainPanel.setLayout(new BorderLayout());
		
		nothing = new JPanel();
		nothing.setLayout(new FlowLayout());
		Titre = new JTextArea();
		Font fontNothing = new Font(null, Font.BOLD,60);
		Titre.setFont(fontNothing);
		
		Titre.append(MyArea.getMyName());
		nothing.add(Titre);
		
		mainPanel.add(nothing,BorderLayout.NORTH);
		
		mainPanel.add(MyMap,BorderLayout.CENTER);
		
		MyMap.setVisible(true);
		
		mainPanel.repaint();
		
		Titre.setEditable(false);
		frame.setResizable(true);
		frame.getContentPane().add(mainPanel);
		mainPanel.setVisible(true);
		frame.setVisible(true);
		
		System.out.println("toto");
		
	}
	
	public static void main(String[] args){
		
		//**** TEST ****//
		double testX[] = { 10.0, 500.0, 10.0, 500.0};
		double testY[] = { 10.0, 10.0, 500.0, 500.0};
		//**************//
		
		Area East = new Area();
		ArrayList<Vertice> myVerticesList = new ArrayList<Vertice>();
		Streets MyStreet;
		Streets MyStreet2;
		Vertice MyVertice;
		Coord MyCoord;
		
		East.setMyName("EAST");
		
		for(int i=0; i<testX.length; i++){
			MyVertice = new Vertice();
			MyCoord = new Coord();
			
			MyCoord.setX(testX[i]);
			MyCoord.setY(testY[i]);
			
			//Point FixeVertice 
			MyVertice.setMyName(Integer.toString(i+1));
			MyVertice.setCoord(MyCoord);
			
			myVerticesList.add(MyVertice);
			System.out.println("x Vertice : " + myVerticesList.get(i).getCoord().getX());
			System.out.println("name Vertice : " + myVerticesList.get(i).getMyName());
			
		}
		
		MyStreet = new Streets();
		MyStreet2 = new Streets();
		
		MyStreet.setMyFirstVertice(myVerticesList.get(0));
		MyStreet.setMySecondVertice(myVerticesList.get(1));
		MyStreet2.setMyFirstVertice(myVerticesList.get(0));
		MyStreet2.setMySecondVertice(myVerticesList.get(2));
		
		East.getMyStreetsList().add(MyStreet);
		East.getMyStreetsList().add(MyStreet2);

		East.setMyVerticesList(myVerticesList);
		
		InterfaceMap MyInterface = new InterfaceMap(East);
		
		MyInterface.DrawInterface();
	}
}
