package json;

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

import structures.map.*;

public class ParserJSON {

	private Object obj ;
	private ObjectMapper mapper;
	private ArrayList<Area> listArea;

	// Default constructor
	public ParserJSON(){
		this("Default");
	}

	// Overload constructor
	public ParserJSON(String jsonFrame){
		this.mapper = new ObjectMapper();
		this.listArea = new ArrayList<Area>();
	}

	private Area parsingArea(String area) throws ParseException, JsonParseException, JsonMappingException, IOException{ 
		
		// Jackson lib function to put the JSON's informations in an object
		Area myArea = this.mapper.readValue(area, Area.class);
		
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
			if(myStreet.getPath().get(cnt).equals(tempVert.getName())){
				switch(cnt){
				case 0:
					firstVert=tempVert;
					cnt++;
					break;
				case 1:
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
			if(myBridge.getSrc().equals(tempVert.getName())){
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
			//JsonText=fileRead();
			obj = parser.parse(JsonText);
			jsonObject =  (JSONObject) obj;
			//Parsing for areas
			array = (JSONArray) jsonObject.get("areas");
			for(Object obj:array){
				//System.out.println(parsingArea(obj.toString()));
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
	
	//************** GETTERS **************//
	
	public ArrayList<Area> getListArea() {
		return listArea;
	}

	//************** SETTERS *************//
	
	public void setListArea(ArrayList<Area> listArea) {
		this.listArea = listArea;
	}
}
