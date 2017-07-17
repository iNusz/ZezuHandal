package com.seunghoshin.android.zezuhan_1.domain;

import java.io.Serializable;
import java.util.List;

/**
 * Created by SeungHoShin on 2017. 7. 10..
 */

public class ZezuInfo implements Serializable{

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
    public String phoneNumb; //숙소 전화번호
    public String mPublicTransport; // 교통
    public String mParking; // 주차장
    public String mConvenience; // 편의시설
    public String mUpTown;
    public String mSchool;
    public String mChild;
    public String mPet; // 반려동물
    public String mFullOption;
    public String mQuiet;
    public String mYard;
    public String mNearTheSea;
    public String mNearTheForest;
    public String mNearTheAirport;
    public String filePath;

    private List<String> mFacility, mSafety;

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getPublicTransport() {
        return mPublicTransport;
    }

    public String getParking() {
        return mParking;
    }

    public void setParking(String mParking) {
        this.mParking = mParking;
    }

    public String getConvenience() {
        return mConvenience;
    }

    public void setConvenience(String mConvenience) {
        this.mConvenience = mConvenience;
    }

    public String getUpTown() {
        return mUpTown;
    }

    public void setUpTown(String mUpTown) {
        this.mUpTown = mUpTown;
    }

    public String getSchool() {
        return mSchool;
    }

    public void setSchool(String mSchool) {
        this.mSchool = mSchool;
    }

    public String getChild() {
        return mChild;
    }

    public void setChild(String mChild) {
        this.mChild = mChild;
    }

    public String getPet() {
        return mPet;
    }

    public void setPet(String mPet) {
        this.mPet = mPet;
    }

    public String getFullOption() {
        return mFullOption;
    }

    public void setFullOption(String mFullOption) {
        this.mFullOption = mFullOption;
    }

    public String getQuiet() {
        return mQuiet;
    }

    public void setQuiet(String mQuiet) {
        this.mQuiet = mQuiet;
    }

    public String getYard() {
        return mYard;
    }

    public void setYard(String mYard) {
        this.mYard = mYard;
    }

    public String getNearTheSea() {
        return mNearTheSea;
    }

    public void setNearTheSea(String mNearTheSea) {
        this.mNearTheSea = mNearTheSea;
    }

    public String getNearTheForest() {
        return mNearTheForest;
    }

    public void setNearTheForest(String mNearTheForest) {
        this.mNearTheForest = mNearTheForest;
    }

    public String getNearTheAirport() {
        return mNearTheAirport;
    }

    public void setNearTheAirport(String mNearTheAirport) {
        this.mNearTheAirport = mNearTheAirport;
    }
    public void setPublicTransport(String mPublicTransport) {
        this.mPublicTransport = mPublicTransport;
    }

    public String getphoneNumb() {
        return phoneNumb;
    }

    public void setphoneNumb(String phoneNumb) {
        this.phoneNumb = phoneNumb;
    }

    public List<String> getFacility() {
        return mFacility;
    }

    public void setFacility(List<String> mFacility) {
        this.mFacility = mFacility;
    }

    public List<String> getSafety() {
        return mSafety;
    }

    public void setSafety(List<String> mSafety) {
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
    public ZezuInfo(String dtHomeName, String dtAdress, String dtIntro, String monthPrice, String phoneNumb,
                    String dtPreprice, String dtDeposit, String startDate, String endDate, String area,
                    String houseStyle, String numPeople, String numRoom, String numBed, String numShower, String numParking) {
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
        this.phoneNumb = phoneNumb;
    }



}
