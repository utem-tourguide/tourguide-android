package com.tourguide.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.tourguide.R;


public class InformacionFragment extends ProgressFragment {

  private TextView informacionTituloText;
  private TextView informacionContenidoText;
  private TextView informacionLocalizacionText;

  public InformacionFragment() {
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {

    View view = inflater.inflate(R.layout.fragment_informacion, container, false);
    cargarReferenciasDeViews(view);

    return view;
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
