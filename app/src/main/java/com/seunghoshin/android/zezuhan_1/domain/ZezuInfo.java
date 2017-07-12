package com.seunghoshin.android.zezuhan_1.domain;

import java.util.List;

/**
 * Created by SeungHoShin on 2017. 7. 10..
 */

public class ZezuInfo {

    public String dtMainImage; //메인 이미지 , 더미용
    public String dtHomeName; // 숙소명
    public String dtAdress; // 주소
    public String dtIntro; // 숙소 소개
    public String monthPrice; // 숙소 한달 가격
    public String dtPreprice; // 한달 계약금
    public String dtDeposit; // 한달 보증금
    public String fileUriString; // 이미지 Uri저장주소
    public String startDate; // 시작일
    public String endDate; //종료일
    public String area; // 지역 분할
    public String houseStyle; //거주형태(원룸/투룸/쓰리룸/복층)
    public String numPeople; // 인원수
    public String numRoom; //침실수
    public String numBed; //침대수
    public String numShower; //욕실수
    public String numParking; //주차장 수

    private List<String> mFacility, mSafety;


    public List<String> getmFacility() {
        return mFacility;
    }

    public void setmFacility(List<String> mFacility) {
        this.mFacility = mFacility;
    }

    public List<String> getmSafety() {
        return mSafety;
    }

    public void setmSafety(List<String> mSafety) {
        this.mSafety = mSafety;
    }


    public String getDtMainImage() {
        return dtMainImage;
    }

    public void setDtMainImage(String dtMainImage) {
        this.dtMainImage = dtMainImage;
    }

    public String getDtHomeName() {
        return dtHomeName;
    }

    public void setDtHomeName(String dtHomeName) {
        this.dtHomeName = dtHomeName;
    }

    public String getDtAdress() {
        return dtAdress;
    }

    public void setDtAdress(String dtAdress) {
        this.dtAdress = dtAdress;
    }

    public String getDtIntro() {
        return dtIntro;
    }

    public void setDtIntro(String dtIntro) {
        this.dtIntro = dtIntro;
    }

    public String getMonthPrice() {
        return monthPrice;
    }

    public void setMonthPrice(String monthPrice) {
        this.monthPrice = monthPrice;
    }

    public String getDtPreprice() {
        return dtPreprice;
    }

    public void setDtPreprice(String dtPreprice) {
        this.dtPreprice = dtPreprice;
    }

    public String getDtDeposit() {
        return dtDeposit;
    }

    public void setDtDeposit(String dtDeposit) {
        this.dtDeposit = dtDeposit;
    }

    public String getFileUriString() {
        return fileUriString;
    }

    public void setFileUriString(String fileUriString) {
        this.fileUriString = fileUriString;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getHouseStyle() {
        return houseStyle;
    }

    public void setHouseStyle(String houseStyle) {
        this.houseStyle = houseStyle;
    }

    public String getNumPeople() {
        return numPeople;
    }

    public void setNumPeople(String numPeople) {
        this.numPeople = numPeople;
    }

    public String getNumRoom() {
        return numRoom;
    }

    public void setNumRoom(String numRoom) {
        this.numRoom = numRoom;
    }

    public String getNumBed() {
        return numBed;
    }

    public void setNumBed(String numBed) {
        this.numBed = numBed;
    }

    public String getNumShower() {
        return numShower;
    }

    public void setNumShower(String numShower) {
        this.numShower = numShower;
    }

    public String getNumParking() {
        return numParking;
    }

    public void setNumParking(String numParking) {
        this.numParking = numParking;
    }

    // 기본 생성자를 만들어주지 않으면 parameter를 가진 객체를 오버로드를 하지 못한다
    // 그렇지 않으면 자바쪽에서 컴파일 에러를 낸다
    public ZezuInfo() {

    }



    // file Uri는 빼줌
    public ZezuInfo(String dtHomeName, String dtAdress, String dtIntro, String monthPrice,
                    String dtPreprice, String dtDeposit, String startDate, String endDate, String area,
                    String houseStyle, String numPeople, String numRoom, String numBed, String numShower, String numParking)
    {
        this.dtHomeName = dtHomeName;
        this.dtAdress = dtAdress;
        this.dtIntro = dtIntro;
        this.monthPrice = monthPrice;
        this.dtPreprice = dtPreprice;
        this.dtDeposit = dtDeposit;
        this.startDate = startDate;
        this.endDate = endDate;
        this.area = area;
        this.houseStyle = houseStyle;
        this.numPeople = numPeople;
        this.numRoom = numRoom;
        this.numBed = numBed;
        this.numShower = numShower;
        this.numParking = numParking;
    }
}
