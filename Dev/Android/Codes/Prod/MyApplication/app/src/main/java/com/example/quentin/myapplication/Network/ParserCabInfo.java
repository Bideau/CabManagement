package com.example.quentin.myapplication.Network;

import java.io.IOException;

// lib for parsing JSON and put into an object
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.example.quentin.myapplication.structures.taxi.CabInfo;

public class ParserCabInfo {

    private String MyJsonFrame;
    private Object obj ;
    private ObjectMapper mapper;

    // Default constructor
    public ParserCabInfo(){
        this("Default");
    }

    // Overload constructor
    public ParserCabInfo(String jsonFrame){
        // Path Test
        mapper = new ObjectMapper(); //jsonFrame = "/media/guinux/Data/Cours/Actuel/IntMobile/CabManagement/Dev/JavaInterface/json/taxi.json";
        this.MyJsonFrame = jsonFrame;
    }

    // function to put the informations of the JSON in a CabInfo object
    public CabInfo parsingFrame(String msg) throws JsonParseException, JsonMappingException, IOException{
        CabInfo myCab = mapper.readValue(msg, CabInfo.class);
        System.out.println(myCab);
        return myCab;
    }
}
