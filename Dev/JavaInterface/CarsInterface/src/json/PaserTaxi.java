package json;


import java.io.File;

import java.io.IOException;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import structures.taxi.CabInfo;

public class PaserTaxi {

	private String MyJsonFrame;
	private Object obj ;
	private ObjectMapper mapper;

	public PaserTaxi(){
		this("Default");
		
	}

	public PaserTaxi(String jsonFrame){
		// Path Test
		mapper = new ObjectMapper();
		//jsonFrame = "/media/guinux/Data/Cours/Actuel/IntMobile/CabManagement/Dev/JavaInterface/json/taxi.json";
		this.MyJsonFrame = jsonFrame;

	}
	private CabInfo parsingFrame() throws JsonParseException, JsonMappingException, IOException{
		CabInfo myCab = mapper.readValue(new File("/media/guinux/Data/Cours/Actuel/IntMobile/CabManagement/Dev/JavaInterface/json/taxi.json"), CabInfo.class);
		System.out.println(myCab);
		return myCab;
	}

	public static void main(String[] args){
		PaserTaxi MyParser = new PaserTaxi();
		try {
			MyParser.parsingFrame();
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
