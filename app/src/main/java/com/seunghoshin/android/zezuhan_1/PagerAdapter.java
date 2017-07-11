package com.seunghoshin.android.zezuhan_1;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by SeungHoShin on 2017. 7. 10..
 */

public class PagerAdapter extends FragmentStatePagerAdapter {

    List<Fragment> datas;

    public PagerAdapter(FragmentManager frag, List<Fragment> datas) {
        super(frag);
        this.datas = datas;
    }

    @Override
    public Fragment getItem(int position) {
        return datas.get(position);
    }

    @Override
    public int getCount() {
        return datas.size();
    }
}
