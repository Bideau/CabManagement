package structures;

import java.util.ArrayList;

public class Area {

	private String MyName;
	private ArrayList<Vertice> MyVerticesList;
	private ArrayList<Streets> MyStreetsList;
	private ArrayList<Bridges> MyBridgesList;
	
	public Area(){
		MyName = "Default";
		MyVerticesList = new ArrayList<Vertice>();
		MyStreetsList = new ArrayList<Streets>();
		MyBridgesList = new ArrayList<Bridges>();
		
	}

	// ***************** GETTERS *******************//
	
	public String getMyName() {
		return MyName;
	}
	
	public ArrayList<Vertice> getMyVerticesList() {
		return MyVerticesList;
	}

	public ArrayList<Streets> getMyStreetsList() {
		return MyStreetsList;
	}
	
	public ArrayList<Bridges> getMyBridgesList() {
		return MyBridgesList;
	}

	// **************** SETTERS ****************//
	public void setMyName(String myName) {
		MyName = myName;
	}

	public void setMyVerticesList(ArrayList<Vertice> myVerticesList) {
		MyVerticesList = myVerticesList;
	}

	public void setMyStreetsList(ArrayList<Streets> myStreetsList) {
		MyStreetsList = myStreetsList;
	}

	public void setMyBridgesList(ArrayList<Bridges> myBridgesList) {
		MyBridgesList = myBridgesList;
	}	
	
}
