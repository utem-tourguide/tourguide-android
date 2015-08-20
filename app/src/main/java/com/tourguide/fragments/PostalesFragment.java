package com.tourguide.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.tourguide.R;

public class PostalesFragment extends ProgressFragment {

  public PostalesFragment() {
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {

    return inflater.inflate(R.layout.fragment_postales, container, false);
  }

  @Override
  public View getEscondible() {
    return getView().findViewById(R.id.postalesLayout);
  }

  @Override
  public ProgressBar getProgressBar() {
    return (ProgressBar) getView().findViewById(R.id.postalesProgress);
  }
}
