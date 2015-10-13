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
	private ArrayList<Area> Carte;
	private Coord Mypoint;
	private String JsonPath;

	private Map<String,String> json;
	private Map<String,String> json2;
	private Map<String,String> json3;
	private String toto;
	private JSONParser parser;

	public ParserJSON(){
		this.MyJsonFrame = "Default";
	}


	public ParserJSON(String jsonFrame){
		// Path Test
		jsonFrame = "D:\\IMERIR 3A Project\\CabManagement\\GIT\\CabManagement\\Dev\\JavaInterface\\Initialize.json";
		parser = new JSONParser();
		this.toto = "";
		this.MyJsonFrame = jsonFrame;

	}

	private void FrameParsing(){

		FileReader reader;
		BufferedReader br;
		StringBuilder builder = new StringBuilder();
		String aux = "";
		String JsonText;


		try {
			//******* FILE TO STRING ********//
			reader = new FileReader(this.MyJsonFrame);

			br = new BufferedReader(reader);

			while ((aux = br.readLine()) != null) {
				builder.append(aux);
			}

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
					tmpArea.
					
				}
				
				Carte.add(new cellTypes(entry.getKey().toString(),Boolean.valueOf(json2.get("causeDeath").toString()),
						Boolean.valueOf(json2.get("isAccessible").toString()),tmpRep));


			}


		}catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void main(String[] args){
		ParserJSON MyParser = new ParserJSON("toto");
		MyParser.FrameParsing();
	}

}
