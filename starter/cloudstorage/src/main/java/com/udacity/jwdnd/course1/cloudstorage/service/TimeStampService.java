package com.udacity.jwdnd.course1.cloudstorage.service;

import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class TimeStampService {

    public String getCurrentTimeStamp() {
        Date date = new Date();
        String strDateFormat = "HH:mm:ss";
        DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
        String formattedDate= dateFormat.format(date);

        return formattedDate;
    }
}
