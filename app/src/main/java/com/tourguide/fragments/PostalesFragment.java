package com.tourguide.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ProgressBar;

import com.tourguide.R;
import com.tourguide.activities.InformacionActivity;
import com.tourguide.tasks.ObtenerPostalesTask;

public class PostalesFragment extends ProgressFragment {

  private InformacionActivity actividad;
  private GridView postalesGrid;

  public PostalesFragment() {
  }

  @SuppressLint("ValidFragment")
  public PostalesFragment(InformacionActivity actividad) {
    this.actividad = actividad;
  }

  @Override
  public void onStart() {
    super.onStart();

    ObtenerPostalesTask tarea = new ObtenerPostalesTask(actividad.getUbicacionId(), this, actividad);
    tarea.execute();
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_postales, container, false);

    cargarReferenciasDeViews(view);

    return view;
  }

  @Override
  public View getEscondible() {
    return postalesGrid;
  }

  @Override
  public ProgressBar getProgressBar() {
    return (ProgressBar) getView().findViewById(R.id.postalesProgress);
  }

  public GridView getPostalesGrid() {
    return postalesGrid;
  }

  private void cargarReferenciasDeViews(View view) {
    postalesGrid = (GridView) view.findViewById(R.id.postalesLayout);
  }

}
