package com.tourguide.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.tourguide.fragments.AudioFragment;
import com.tourguide.fragments.InformacionFragment;

public class InformacionTabsAdapter extends FragmentPagerAdapter {

  public static Fragment[] fragments = {new InformacionFragment(),
                                        new AudioFragment()};

  public InformacionTabsAdapter(FragmentManager fm) {
    super(fm);
  }

  @Override
  public Fragment getItem(int position) {
    return InformacionTabsAdapter.fragments[position];
  }

  @Override
  public int getCount() {
    return InformacionTabsAdapter.fragments.length;
  }

}
