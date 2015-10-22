package json;

import java.io.IOException;

// lib for parsing JSON and put into an object
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import structures.taxi.CabInfo;

public class ParserCabInfo {

	private ObjectMapper mapper;

	// Default constructor
	public ParserCabInfo(){
		this("Default");
		
	}

	// Overload constructor
	public ParserCabInfo(String jsonFrame){
		mapper = new ObjectMapper();
	}
	
	// function to put the informations of the JSON in a CabInfo object
	public CabInfo parsingFrame(String msg) throws JsonParseException, JsonMappingException, IOException{
		
		System.out.println(msg);
		
		// Jackson lib function of transfert
		CabInfo myCab = mapper.readValue(msg, CabInfo.class);
		System.out.println(myCab);
		return myCab;
	}
}