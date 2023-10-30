package com.example.project1.service;

import com.example.project1.Dto.TextDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;

@Service
public class TextService {
    private LinkedHashMap<String, String[]> textMap = new LinkedHashMap<>();
    public void saveText(String receivedTextID, String rpm, String speed){
        String[] drivingInformation = {rpm, speed};
        textMap.put(receivedTextID,drivingInformation);

    }

    public void dataChecking(String textID){
        String[] drivingInfo = textMap.get(textID);
        if(drivingInfo != null && drivingInfo.length >= 2){
            String RPM = drivingInfo[0];
            String Speed = drivingInfo[1];
            System.out.println("RPM :" + RPM +" "+"Speed:" + Speed);
        } else{
            System.out.println("TextID not found or invalid data.");
        }
    }

    public String[] getDrivingInformation(String textID){
        String[] drivingInfo = textMap.get(textID);
        return drivingInfo;
    }

    public HashMap<String,String[]> getAllDrivingInformation(){
        return textMap;
    }

     public void removeData(String textID){
        textMap.remove(textID);
        getAllDrivingInformation();
    }

    public void removeAllData(){
        textMap.clear();
        getAllDrivingInformation();
    }


}
