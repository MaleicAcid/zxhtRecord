package com.maleic.demo.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Entity
public class Record {
    @Id
    @GeneratedValue
    private Integer rid;

    private Integer did;

    private String starttime;

    private String endtime;

    private String explain;

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public Integer getDid() {
        return did;
    }

    public void setDid(Integer did) {
        this.did = did;
    }

    public String getStartTime() {
        return starttime;
    }

    public void setStartTime(String startTime) {
        this.starttime = startTime;
    }

    public String getEndTime() {
        return endtime;
    }

    public void setEndTime(String endTime) {
        this.endtime = endTime;
    }

    public String getExplain() {
        return explain;
    }

    public void setExplain(String explain) {
        this.explain = explain;
    }

    public long getStartTimeMillis() {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
        String startTimeStr = this.getDid().toString() + " " + this.getStartTime();
        Date startTimeDate = null;
        try {
            startTimeDate = format.parse(startTimeStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        long startTimeMillis = startTimeDate.getTime();
        return startTimeMillis;
    }

    public long getEndTimeMillis() {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
        String endTimeStr = this.getDid().toString() + " " + this.getEndTime();
        Date endTimeDate = null;
        try {
            endTimeDate = format.parse(endTimeStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        long endTimeMillis = endTimeDate.getTime();
        return endTimeMillis;
    }

    public String calHours(){
        SimpleDateFormat sdf= new SimpleDateFormat("HH:mm:ss");
        Date time1 = null;
        try {
            time1 = sdf.parse(this.getStartTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date time2 = null;
        try {
            time2 = sdf.parse(this.getEndTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        sdf = null;

        Calendar instance = Calendar.getInstance();
        instance.setTime(time1);
        long timeInMillis1 = instance.getTimeInMillis();
        instance = null;
        time1 = null;

        Calendar instance2 = Calendar.getInstance();
        instance2.setTime(time2);
        long timeInMillis2 = instance2.getTimeInMillis();
        instance2 = null;
        time2 = null;

        double hours = (timeInMillis2 - timeInMillis1)/1000/60/60.0;
        DecimalFormat df = new DecimalFormat("#.##");
        String dff=df.format(hours);
        df = null;

        return dff;
    }
}
