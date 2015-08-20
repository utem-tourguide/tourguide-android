package com.tourguide.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;

import com.tourguide.R;
import com.tourguide.adapters.InformacionTabsAdapter;
import com.tourguide.fragments.InformacionFragment;
import com.tourguide.fragments.PostalesFragment;
import com.tourguide.models.UbicacionTuristica;


public class InformacionActivity extends Activity {

  private ViewPager viewPager;
  private UbicacionTuristica ubicacion;

  public UbicacionTuristica getUbicacion() {
    return ubicacion;
  }

  public void setUbicacion(UbicacionTuristica ubicacion) {
    this.ubicacion = ubicacion;
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_informacion);

    mostrarTabsEnActionBar();
  }

  private void mostrarTabsEnActionBar() {
    InformacionTabsAdapter adapter = new InformacionTabsAdapter(getSupportFragmentManager());
    adapter.añadirFragment(new InformacionFragment(this));
    adapter.añadirFragment(new PostalesFragment());

    viewPager = (ViewPager) findViewById(R.id.viewPager);
    viewPager.setAdapter(adapter);

    ActionBar actionBar = getSupportActionBar();
    actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
    ActionBar.TabListener tabListener = generarTabListener();

    actionBar.addTab(actionBar.newTab().setText(R.string.informacion).setTabListener(tabListener));
    actionBar.addTab(actionBar.newTab().setText(R.string.postales).setTabListener(tabListener));

    viewPager.setOnPageChangeListener(generarOnPageChangeListener());
  }

  private ActionBar.TabListener generarTabListener() {
    return new ActionBar.TabListener() {
      @Override
      public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
        viewPager.setCurrentItem(tab.getPosition());
      }

      @Override
      public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

      }

      @Override
      public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

      }
    };
  }

  private ViewPager.OnPageChangeListener generarOnPageChangeListener() {
    return new ViewPager.SimpleOnPageChangeListener() {
      @Override
      public void onPageSelected(int position) {
        getSupportActionBar().setSelectedNavigationItem(position);
      }
    };
  }

  public int getUbicacionId() {
    return getIntent().getIntExtra("ubicacionId", 0);
  }
}
