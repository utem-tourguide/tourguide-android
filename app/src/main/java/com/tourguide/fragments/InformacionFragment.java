package com.tourguide.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.tourguide.R;
import com.tourguide.activities.InformacionActivity;
import com.tourguide.handlers.BackendResponseHandler;
import com.tourguide.handlers.ObtenerInfoUbicacionErrorHandler;
import com.tourguide.handlers.ObtenerInfoUbicacionSuccessHandler;
import com.tourguide.tasks.ObtenerInfoUbicacionTask;

import java.util.HashMap;
import java.util.Map;


public class InformacionFragment extends ProgressFragment {

  private TextView informacionTituloText;
  private TextView informacionContenidoText;
  private TextView informacionLocalizacionText;

  private InformacionActivity actividad;

  public InformacionFragment() {
  }

  @SuppressLint("ValidFragment")
  public InformacionFragment(InformacionActivity actividad) {
    this.actividad = actividad;
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {

    View view = inflater.inflate(R.layout.fragment_informacion, container, false);
    cargarReferenciasDeViews(view);

    return view;
  }

  @Override
  public void onStart() {
    super.onStart();

    ObtenerInfoUbicacionTask tarea = new ObtenerInfoUbicacionTask(actividad.getUbicacionId(),
                                                                  actividad,
                                                                  this,
                                                                  generarHandlers());
    tarea.execute();
  }

  @NonNull
  private Map<Boolean, BackendResponseHandler> generarHandlers() {
    Map<Boolean, BackendResponseHandler> handlers = new HashMap<>();

    handlers.put(true, new ObtenerInfoUbicacionSuccessHandler(actividad, this));
    handlers.put(false, new ObtenerInfoUbicacionErrorHandler(actividad));

    return handlers;
  }

  private void cargarReferenciasDeViews(View view) {
    informacionTituloText = (TextView) view.findViewById(R.id.informacionTituloText);
    informacionLocalizacionText = (TextView) view.findViewById(R.id.informacionLocalizacionText);
    informacionContenidoText = (TextView) view.findViewById(R.id.informacionContenidoText);
  }

  @Override
  public View getEscondible() {
    return getView().findViewById(R.id.informacionProgress);
  }

  @Override
  public ProgressBar getProgressBar() {
    return (ProgressBar) getView().findViewById(R.id.informacionProgress);
  }

  public TextView getInformacionTituloText() {
    return informacionTituloText;
  }

  public void setInformacionTituloText(TextView informacionTituloText) {
    this.informacionTituloText = informacionTituloText;
  }

  public TextView getInformacionContenidoText() {
    return informacionContenidoText;
  }

  public void setInformacionContenidoText(TextView informacionContenidoText) {
    this.informacionContenidoText = informacionContenidoText;
  }

  public TextView getInformacionLocalizacionText() {
    return informacionLocalizacionText;
  }

  public void setInformacionLocalizacionText(TextView informacionLocalizacionText) {
    this.informacionLocalizacionText = informacionLocalizacionText;
  }
}
