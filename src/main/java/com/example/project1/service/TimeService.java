package com.example.project1.service;

import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class TimeService {
    public LocalDate getCurrentTime(){
        return LocalDate.now();
    }
}
