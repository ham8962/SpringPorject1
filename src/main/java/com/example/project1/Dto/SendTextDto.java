package com.example.project1.Dto;

import java.util.HashMap;

public class SendTextDto {
    private HashMap<String,String[]> drivingInfo;
    private String[] rpmSpeedData;

    public void setRpmSpeedData(String[] rpmSpeedData){
        this.rpmSpeedData = rpmSpeedData;
    }

    public String[] getRpmSpeedData(){
        return rpmSpeedData;
    }


    public void setAllDrivingInfo(HashMap<String,String[]> drivingInfo) {
        this.drivingInfo = drivingInfo;
    }

    public HashMap<String,String[]> getAllDrivingInfo() {
        return this.drivingInfo;
    }
}
