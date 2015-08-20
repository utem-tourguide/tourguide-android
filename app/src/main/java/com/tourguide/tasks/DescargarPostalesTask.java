package com.tourguide.tasks;

import android.os.AsyncTask;

import com.tourguide.activities.InformacionActivity;
import com.tourguide.adapters.PostalesAdapter;
import com.tourguide.fragments.ProgressFragment;

public class DescargarPostalesTask extends AsyncTask<Void, Void, PostalesAdapter>{

  private ProgressFragment fragment;
  private InformacionActivity actividad;

  public DescargarPostalesTask(ProgressFragment fragment, InformacionActivity actividad) {
    this.fragment = fragment;
    this.actividad = actividad;
  }

  @Override
  protected void onPreExecute() {
    fragment.mostrarProgreso(true);
  }

  @Override
  protected PostalesAdapter doInBackground(Void... params) {
    return null;
  }

  @Override
  protected void onPostExecute(PostalesAdapter postalesAdapter) {
    fragment.mostrarProgreso(true);
  }
}
