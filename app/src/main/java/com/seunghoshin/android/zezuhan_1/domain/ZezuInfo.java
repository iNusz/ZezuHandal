package com.seunghoshin.android.zezuhan_1.domain;

/**
 * Created by SeungHoShin on 2017. 7. 10..
 */

public class ZezuInfo {

    public String dtMainImage;
    public String dtHomeName;
    public String dtAdress;
    public String dtIntro;
    public String dtPrice;
    public String dtPreprice;
    public String dtImageinfo;
    public String fileUriString;

    // 기본 생성자를 만들어주지 않으면 parameter를 가진 객체를 오버로드를 하지 못한다
    // 그렇지 않으면 자바쪽에서 컴파일 에러를 낸다
    public ZezuInfo() {

    }

    // todo 이건 항목별로 다 추가 시켜야 한다 .
    public ZezuInfo(String dtHomeName, String dtAdress, String dtPrice) {
        this.dtHomeName = dtHomeName;
        this.dtAdress = dtAdress;
        this.dtPrice = dtPrice;
    }


}
