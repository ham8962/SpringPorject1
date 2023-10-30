package com.example.project1.controller;

import com.example.project1.Dto.SendTextDto;
import com.example.project1.Dto.TextDto;
import com.example.project1.Dto.TimeDto;
import com.example.project1.service.TextService;
import com.example.project1.service.TimeService;
import com.fasterxml.jackson.databind.deser.impl.CreatorCandidate;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import org.apache.commons.io.IOUtils;


@RestController
@RequiredArgsConstructor
public class ProjectController {
    private final TimeService timeService;
    @GetMapping("/time")
    @ResponseBody
    public TimeDto localDateTime(){
        TimeDto timeDto = new TimeDto();
        timeDto.setNow(timeService.getCurrentTime());
        return timeDto;
    }

    private final TextService textService;
    @PostMapping("/text/{textid}")
    public void saveBody(@RequestBody List<TextDto> textDtoList){
        for(TextDto textDto : textDtoList) {
            String receivedTextID = textDto.getTextID();
            String RPM = textDto.getRPM();
            String Speed = textDto.getSpeed();
            textService.saveText(receivedTextID, RPM, Speed);
            textService.dataChecking(receivedTextID);
        }
    }

    @GetMapping("/text")
    @ResponseBody
    public SendTextDto getALLBodyString(){
        SendTextDto sendTextDto = new SendTextDto();
        sendTextDto.setAllDrivingInfo(textService.getAllDrivingInformation());
        return sendTextDto;
    }

    @GetMapping("/text/{textid}")
    @ResponseBody
    public SendTextDto getBodyString(@PathVariable String textid){
        SendTextDto sendTextDto = new SendTextDto();
        sendTextDto.setRpmSpeedData(textService.getDrivingInformation(textid));
        return sendTextDto;
    }

    @DeleteMapping("/text/{textid}")
    public SendTextDto deleteBodyString(@PathVariable String textid){
        SendTextDto sendTextDto = new SendTextDto();
        textService.removeData(textid);
        sendTextDto.setAllDrivingInfo(textService.getAllDrivingInformation());
        return sendTextDto;
    }


    @DeleteMapping("/text")
    public SendTextDto deleteAllBodyString(){
        SendTextDto sendTextDto = new SendTextDto();
        textService.removeAllData();
        sendTextDto.setAllDrivingInfo(textService.getAllDrivingInformation());
        return sendTextDto;
    }

    @GetMapping(value= "/image", produces = MediaType.IMAGE_JPEG_VALUE )
    @ResponseBody
    public byte[] getImage() throws IOException {
        FileInputStream fileInputStream;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        String fileDir = "C:/Users/dydwls/Pictures/img.jpg";

        fileInputStream = new FileInputStream(fileDir);

        byte[] buffer = new byte[1024];
        int readCount;

        while ((readCount = fileInputStream.read(buffer)) != -1){
            byteArrayOutputStream.write(buffer,0, readCount);
        }

        byte[] fileArray = byteArrayOutputStream.toByteArray();

        fileInputStream.close();
        byteArrayOutputStream.close();

         return fileArray;

    }
}


