package com.example.quentin.myapplication;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

// Jackson lib to put the JSON's information in a object
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

// Json.Simple lib to parse the differents areas before use Jackson lib
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

// All structures
import com.example.quentin.myapplication.structures.*;

public class ParserJSON {

	private String MyJsonFrame;
	private Object obj ;
	private ObjectMapper mapper;
	private ArrayList<Area> listArea;


    // Default constructor
	public ParserJSON(){
		this("Default");
	}

    // Overload constructor
	public ParserJSON(String jsonFrame){
		mapper = new ObjectMapper();
		this.listArea = new ArrayList<Area>();
		this.MyJsonFrame = jsonFrame;

	}

	private Area parsingArea(String area) throws ParseException, JsonParseException, JsonMappingException, IOException{


        // Jackson lib function to put the JSON's informations in an object
		Area myArea = mapper.readValue(area, Area.class);

        // Parsing streets and bridges in another functions
		for(Street tmpStreet:myArea.getMap().getStreets()){
			parsingStreets(myArea, tmpStreet);
		}
		for(Bridge tmpBridge:myArea.getMap().getBridges()){
			parsingBridge(myArea, tmpBridge);
		}
		return myArea;
	} 

	/*
	 * Create Streets from Json informations
	 */
	private Street parsingStreets(Area myArea ,Street myStreet) throws ParseException{
		Vertex firstVert = new Vertex();
		Vertex lastVert = new Vertex();
		int cnt=0;

		for(Vertex tempVert:myArea.getMap().getVertices()){
			if(myStreet.getPath().get(1).equals(tempVert.getName())){
				switch(cnt){
				case 1:
					firstVert=tempVert;
					break;
				case 2:
					lastVert=tempVert;
					break;
				}
			}
		}
		myStreet.setFirstVertice(firstVert);
		myStreet.setSecondVertice(lastVert);
		return myStreet;
	}

	/*
	 * Create Bridge from Json informations
	 */
	private Bridge parsingBridge(Area myArea,Bridge myBridge){

		for(Vertex tempVert:myArea.getMap().getVertices()){
			if(myBridge.getFrom().equals(tempVert.getName())){
				myBridge.setFromVertice(tempVert);
			}
		}
		return myBridge;
	}

	public void parsingFrame(String JsonText){
		JSONArray array;
		//String JsonText;
		JSONParser parser = new JSONParser();
		JSONObject jsonObject= new JSONObject();
		try {
			//Get file data
            //JsonText=fileRead(); // DEBUG
			obj = parser.parse(JsonText);
			jsonObject =  (JSONObject) obj;
			//Parsing for areas
			array = (JSONArray) jsonObject.get("areas");
			for(Object obj:array){
				System.out.println(parsingArea(obj.toString()));
				this.listArea.add(parsingArea(obj.toString()));
			}
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	private String fileRead() throws IOException{
		//******* FILE TO STRING ********//
		FileReader reader;
		BufferedReader br;
		StringBuilder builder = new StringBuilder();
		String aux = "";
		String JsonText;

		reader = new FileReader(this.MyJsonFrame);
		br = new BufferedReader(reader);

		while ((aux = br.readLine()) != null) {
			builder.append(aux);
		}
		JsonText = builder.toString();
		br.close();
		return JsonText;
	}
	
	public ArrayList<Area> getListArea() {
		return listArea;
	}

	public void setListArea(ArrayList<Area> listArea) {
		this.listArea = listArea;
	}
}
