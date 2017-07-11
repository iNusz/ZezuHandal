package com.seunghoshin.android.zezuhan_1;

import com.seunghoshin.android.zezuhan_1.domain.ZezuInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SeungHoShin on 2017. 7. 12..
 */

public class Data {
    //  공용으로 사용되는 데이터 저장소
    // 모든 Activity에서 접근할 수 있다 .
    public static List<ZezuInfo> list = new ArrayList<ZezuInfo>();
}
