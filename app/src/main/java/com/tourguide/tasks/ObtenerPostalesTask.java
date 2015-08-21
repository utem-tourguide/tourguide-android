package com.tourguide.tasks;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.tourguide.activities.InformacionActivity;
import com.tourguide.adapters.PostalesAdapter;
import com.tourguide.fragments.PostalesFragment;
import com.tourguide.models.Postal;
import com.tourguide.support.Constants;

import java.io.IOException;

public class ObtenerPostalesTask extends AsyncTask<Void, Void, Postal[]>{

  private int ubicacionId;
  private PostalesFragment fragment;
  private InformacionActivity actividad;

  public ObtenerPostalesTask(int ubicacionId, PostalesFragment fragment, InformacionActivity actividad) {
    this.ubicacionId = ubicacionId;
    this.fragment = fragment;
    this.actividad = actividad;
  }

  @Override
  protected void onPreExecute() {
    fragment.mostrarProgreso(true);
  }

  @Override
  protected Postal[] doInBackground(Void... params) {
    String url = Constants.BACKEND_URL + "/ubicaciones/" + ubicacionId + "/postales";

    Request request = new Request.Builder().url(url)
                                           .addHeader("Accept", "application/json")
                                           .build();

    Postal[] postales = {};
    try {
      Response response = new OkHttpClient().newCall(request).execute();
      postales = new Gson().fromJson(response.body().string(), Postal[].class);
    } catch (IOException e) {
      e.printStackTrace();
    }

    return postales;
  }

  @Override
  protected void onPostExecute(Postal[] postales) {
    fragment.getPostalesGrid().setAdapter(new PostalesAdapter(actividad, postales));

    fragment.mostrarProgreso(false);
  }
}
