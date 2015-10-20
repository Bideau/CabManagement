package json;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import structures.*;

public class ParserJSON {

	private String MyJsonFrame;
	private Object obj ;
	private ObjectMapper mapper;

	public ParserJSON(){
		this("Default");
		
	}


	public ParserJSON(String jsonFrame){
		// Path Test
		mapper = new ObjectMapper();
		jsonFrame = "/media/guinux/Data/Cours/Actuel/IntMobile/CabManagement/Dev/JavaInterface/json/test.json";
		this.MyJsonFrame = jsonFrame;

	}

	private Area ParsingArea(String area) throws ParseException, JsonParseException, JsonMappingException, IOException{ 
		//System.out.println(area);
		Area myArea = mapper.readValue(area, Area.class);
		for(Street tmpStreet:myArea.getMap().getStreets()){
			ParsingStreets(myArea, tmpStreet);
		}
		for(Bridge tmpBridge:myArea.getMap().getBridges()){
			ParsingBridge(myArea, tmpBridge);
		}
		return myArea;
	} 

	/*
	 * Create Streets from Json informations
	 */
	private Street ParsingStreets(Area myArea ,Street myStreet) throws ParseException{
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
	private Bridge ParsingBridge(Area myArea,Bridge myBridge){

		for(Vertex tempVert:myArea.getMap().getVertices()){
			if(myBridge.getFrom().equals(tempVert.getName())){
				myBridge.setFromVertice(tempVert);
			}
		}
		return myBridge;
	}

	private void FrameParsing(){
		JSONArray array;
		String JsonText;
		JSONParser parser = new JSONParser();
		JSONObject jsonObject= new JSONObject();
		try {
			//Get file data
			JsonText=fileRead();
			obj = parser.parse(JsonText);
			jsonObject =  (JSONObject) obj;
			//Parsing for areas
			array = (JSONArray) jsonObject.get("areas");
			for(Object obj:array){
				System.out.println(ParsingArea(obj.toString()));
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

	public static void main(String[] args){
		ParserJSON MyParser = new ParserJSON("toto");
		MyParser.FrameParsing();
	}

}
