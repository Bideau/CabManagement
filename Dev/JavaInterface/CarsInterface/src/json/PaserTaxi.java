package json;

import org.codehaus.jackson.map.ObjectMapper;

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
		jsonFrame = "/media/guinux/Data/Cours/Actuel/IntMobile/CabManagement/Dev/JavaInterface/json/taxi.json";
		this.MyJsonFrame = jsonFrame;

	}
	private void parsingFrame(){
		
	}
	public static void main(String[] args){
		PaserTaxi MyParser = new PaserTaxi("toto");
		MyParser.parsingFrame();
	}

}
