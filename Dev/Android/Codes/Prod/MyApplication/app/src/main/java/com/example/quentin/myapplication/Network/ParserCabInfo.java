package com.example.quentin.myapplication.Network;

import java.io.File;

import java.io.IOException;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.example.quentin.myapplication.structures.taxi.CabInfo;

public class ParserCabInfo {

    private String MyJsonFrame;
    private Object obj ;
    private ObjectMapper mapper;

    public ParserCabInfo(){
        this("Default");

    }

    public ParserCabInfo(String jsonFrame){
        // Path Test
        mapper = new ObjectMapper();
        //jsonFrame = "/media/guinux/Data/Cours/Actuel/IntMobile/CabManagement/Dev/JavaInterface/json/taxi.json";
        this.MyJsonFrame = jsonFrame;

    }
    public CabInfo parsingFrame(String msg) throws JsonParseException, JsonMappingException, IOException{
        CabInfo myCab = mapper.readValue(msg, CabInfo.class);
        System.out.println(myCab);
        return myCab;
    }
}
