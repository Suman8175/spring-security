package com.suman.springsecurity.utils;

import com.suman.springsecurity.exception.DateTimeParsingException;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;


@Service
public class DateTimeCustomFormatter {

    public LocalDate formatDateToYearMonthDayFormat(String date){

        try{

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            return LocalDate.parse(date,formatter);
        }
        catch (DateTimeParseException exception){
            throw new DateTimeParsingException("Date should be in format of yyyy-mm-dd");
        }
    }

    public String formatDateToString(LocalDate date){
            SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
            if (date==null){
                return "";
            }
            return date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }



}
