package com.jinpaihushi.jphs.nurse.model;

import java.util.List;

import com.jinpaihushi.model.BaseModel;

@SuppressWarnings("serial")
public class NursePojo extends BaseModel {
    private String nurseId;

    private String nurseName;

    private String nurseSex;

    private String nurseHospital;

    private String url;

    private String workYear;

    private String serviceNumber;

    private String lon;

    private String lat;

    private String distance;

    private List<NursePrice> list;

    public String getNurseId() {
        return nurseId;
    }

    public void setNurseId(String nurseId) {
        this.nurseId = nurseId;
    }

    public String getNurseName() {
        return nurseName;
    }

    public void setNurseName(String nurseName) {
        this.nurseName = nurseName;
    }

    public String getNurseSex() {
        return nurseSex;
    }

    public void setNurseSex(String nurseSex) {
        this.nurseSex = nurseSex;
    }

    public String getNurseHospital() {
        return nurseHospital;
    }

    public void setNurseHospital(String nurseHospital) {
        this.nurseHospital = nurseHospital;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getWorkYear() {
        return workYear;
    }

    public void setWorkYear(String workYear) {
        this.workYear = workYear;
    }

    public String getServiceNumber() {
        return serviceNumber;
    }

    public void setServiceNumber(String serviceNumber) {
        this.serviceNumber = serviceNumber;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public List<NursePrice> getList() {
        return list;
    }

    public void setList(List<NursePrice> list) {
        this.list = list;
    }

}
