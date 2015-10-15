package json;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ContainerFactory;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
















import structures.*;

public class ParserJSON {

	private String MyJsonFrame;
	private Object obj ;
	private JSONParser parser;
	private JSONObject jsonObject;
	private JSONObject json;
	private ArrayList<Vertice> listVertice;

	public ParserJSON(){
		this.MyJsonFrame = "Default";
	}


	public ParserJSON(String jsonFrame){
		// Path Test
		jsonFrame = "/media/guinux/Data/Cours/Actuel/IntMobile/CabManagement/Dev/JavaInterface/json/test.json";
		parser = new JSONParser();
		this.MyJsonFrame = jsonFrame;

	}

	private void ParsingArea(String area, String value) throws ParseException{
		JSONObject json2;
		JSONObject json3;
		JSONArray array;
		ArrayList<Streets> listStreet=new ArrayList<Streets>();
		ArrayList<Bridges> listBridges=new ArrayList<Bridges>();
		Area tmpArea = new Area();

		listVertice=new ArrayList<Vertice>();


		tmpArea.setMyName(area);
		json2 = (JSONObject) parser.parse(value);
		json3 = (JSONObject) parser.parse(json2.get("weight").toString());
		tmpArea.setWidth(Integer.parseInt(json3.get("w").toString()));
		tmpArea.setHeight(Integer.parseInt(json3.get("h").toString()));
		array =  (JSONArray) json2.get("vertices");

		// For Vertice list
		for(Object obj:array){
			JSONObject tempVertice=(JSONObject) obj;
			listVertice.add(ParsingVertices(tempVertice));

		}
		tmpArea.setMyVerticesList(listVertice);

<<<<<<< HEAD
			JsonText = builder.toString();
			System.out.println(JsonText);

			//****************************//

			json = (Map)parser.parse(JsonText);
			
			json2 = (Map)parser.parse(json.get("areas").toString());
			//json2 = (Map)parser.parse(json2.get("map").toString());
			//json2 = (Map)parser.parse(json2.get("vertices").toString());
			
			Iterator iter = json2.entrySet().iterator();
			
			while(iter.hasNext()){
				Area tmpArea = new Area(); 
				
				Map.Entry entry = (Map.Entry)iter.next();
				json2 = (Map)parser.parse(entry.getValue().toString());
				
				String tmpName = json2.get("name").toString();
				tmpArea.setMyName(tmpName);
				
				json3 = (Map)parser.parse(json2.get("map").toString());
				
				Iterator iter2 = json3.entrySet().iterator();
				
				while(iter2.hasNext()){
					Map.Entry entry2 = (Map.Entry)iter.next();
					json3 = (Map)parser.parse(entry2.getValue().toString());
					
					String tmpWeight = json2.get("weight").toString();
					//tmpArea.
					
				}
				
				//Carte.add(new cellTypes(entry.getKey().toString(),Boolean.valueOf(json2.get("causeDeath").toString()),
				//Boolean.valueOf(json2.get("isAccessible").toString()),tmpRep));
=======
		array =  (JSONArray) json2.get("streets");
		// For Streets list
		for(Object obj:array){
			JSONObject tempStreet=(JSONObject) obj;
			listStreet.add(ParsingStreets(tempStreet));
		}
		tmpArea.setMyStreetsList(listStreet);

		// For Bridge List
		array =  (JSONArray) json2.get("bridges");
		for(Object obj:array){
			JSONObject tempBridge=(JSONObject) obj;
			listBridges.add(ParsinBridge(tempBridge));
		}
		tmpArea.setMyBridgesList(listBridges);
	} 
	
	/*
	 * Create Streets from Json informations
	 */
	private Streets ParsingStreets(JSONObject tempStreet) throws ParseException{
		Vertice firstVert = new Vertice();
		Vertice lastVert = new Vertice();
		int cnt=0;
		Streets myStreet=new Streets();
		myStreet.setMyStreetName(tempStreet.get("name").toString());
		myStreet.setOneWay(Boolean.parseBoolean(tempStreet.get("oneway").toString()));
		JSONArray array2 =  (JSONArray) parser.parse(tempStreet.get("path").toString());
		// 
		for(Object obj2:array2){
			cnt++;
			String name= obj2.toString();
			// Go throught the know list
			for(Vertice tempVert:listVertice){
				if(name.equals(tempVert.getMyName())){
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
		}
		myStreet.setMyFirstVertice(firstVert);
		myStreet.setMySecondVertice(lastVert);
		return myStreet;
	}
	
	/*
	 * Create Bridge from Json informations
	 */
	private Bridges ParsinBridge(JSONObject tempBridge){
		Bridges myBridges = new Bridges();
		myBridges.setMyWeight(Double.parseDouble(tempBridge.get("weight").toString()));

		// Get Vertex From
		String nameFrom= tempBridge.get("from").toString();
		// Go throught the know list
		for(Vertice tempVert:listVertice){
			if(nameFrom.equals(tempVert.getMyName())){
				myBridges.setFromVertice(tempVert);
			}
		}
		// Get Vertex To
		JSONObject tempTo=(JSONObject) tempBridge.get("to");
		myBridges.setToArea(tempTo.get("area").toString());
		myBridges.setToVertice(tempTo.get("vertex").toString());
		return myBridges;
	}
>>>>>>> a5f58f9f907698ebd608fb6e8f0cf52009c60748

	/*
	 * Create Vetex from JSON informations
	 */
	private Vertice ParsingVertices(JSONObject tempVertice){
		Vertice myVertice = new Vertice();
		Coord tempCoord = new Coord();

		myVertice.setMyName(tempVertice.get("name").toString());
		tempCoord.setX(Double.parseDouble(tempVertice.get("x").toString()));
		tempCoord.setY(Double.parseDouble(tempVertice.get("y").toString()));
		myVertice.setCoord(tempCoord);

		return myVertice;
	}

	private void FrameParsing(){

		String JsonText;
		String tmpName,tmpMap;
		JSONArray array;
		try {
			//Get file data
			JsonText=fileRead();
			obj = parser.parse(JsonText);
			jsonObject =  (JSONObject) obj;

			//Parsing for areas
			array = (JSONArray) jsonObject.get("areas");
			for(int i =0; i<array.size();i++){
				json = (JSONObject) parser.parse(array.get(i).toString());
				tmpName = (String) json.get("name");
				tmpMap=json.get("map").toString();
				ParsingArea(tmpName,tmpMap);
				System.out.println(tmpName+" "+tmpMap);
			}

<<<<<<< HEAD
=======
		}catch (FileNotFoundException e) {
			e.printStackTrace();
>>>>>>> a5f58f9f907698ebd608fb6e8f0cf52009c60748
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

	/*public static void main(String[] args){
		ParserJSON MyParser = new ParserJSON("toto");
		MyParser.FrameParsing();
	}*/

}
