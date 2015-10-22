package hud;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import structures.map.*;
import webSocket.SocketIO;

public class InterfaceMap extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	private static int Width = 700;
	private static int Height = 500;

	private int xMaxPixels = 1300;
	private int yMaxPixels = 550;

	private JFrame frame;
	private JPanel mainPanel;
	private JPanel titlePanel;
	private JTextArea title;
	private JButton stopListenSocket;

	private Component mouseListenerArea;

	private Map map;
	private Area area;
	public SocketIO socketIO;

	public InterfaceMap(Area _area,SocketIO socketIO){
		this.frame = new JFrame();
		this.mainPanel = new JPanel();
		this.titlePanel = new JPanel();
		this.title = new JTextArea();
		this.mouseListenerArea = new JTextArea();
		this.area = _area;
		
		this.map = new Map(area,xMaxPixels,yMaxPixels);
		this.stopListenSocket= new JButton("Stop Listen Socket");
		this.socketIO = socketIO;

		System.out.println("Overload Constructor");
	}

	public void DrawInterface(){

		frame.setSize(Width,Height);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 

		mainPanel.setLayout(new BorderLayout());

		titlePanel = new JPanel();
		titlePanel.setLayout(new FlowLayout());
		Font fontNothing = new Font(null, Font.BOLD,60);
		title.setFont(fontNothing);
		mouseListenerArea.setFont(fontNothing);

		stopListenSocket.addActionListener(this);

		title.append(area.getName());
		titlePanel.add(stopListenSocket);
		titlePanel.add(title);

		mainPanel.add(titlePanel,BorderLayout.NORTH);

		mainPanel.add(map,BorderLayout.CENTER);

		map.setVisible(true);

		title.setEditable(false);
		frame.setResizable(true);
		frame.getContentPane().add(mainPanel);
		mainPanel.setVisible(true);
		frame.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e){
		this.socketIO.setSocketOpened(false);
	}

	//********************* GETTERS ******************//

	public Map getMap() {
		return map;
	}

	public Area getArea() {
		return area;
	}

	//******************** SETTERS*****************//

	public void setMap(Map map) {
		this.map = map;
	}

	public void setArea(Area area) {
		this.area = area;
	}
}