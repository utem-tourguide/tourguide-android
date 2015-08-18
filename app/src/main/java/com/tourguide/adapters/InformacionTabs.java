package com.tourguide.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.tourguide.fragments.Audio;
import com.tourguide.fragments.Informacion;

public class InformacionTabs extends FragmentPagerAdapter {

  public static Fragment[] fragments = {new Informacion(),
                                        new Audio()};

  public InformacionTabs(FragmentManager fm) {
    super(fm);
  }

  @Override
  public Fragment getItem(int position) {
    return InformacionTabs.fragments[position];
  }

  @Override
  public int getCount() {
    return InformacionTabs.fragments.length;
  }

}
