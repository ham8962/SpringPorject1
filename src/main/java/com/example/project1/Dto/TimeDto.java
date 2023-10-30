package com.example.project1.Dto;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.ZoneId;


@Getter
@Setter
public class TimeDto {
    private LocalDate now;
}
