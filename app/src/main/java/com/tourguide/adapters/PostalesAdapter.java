package com.tourguide.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.tourguide.R;
import com.tourguide.managers.DrawableManager;
import com.tourguide.models.Postal;

public class PostalesAdapter extends ArrayAdapter<Postal> {

  public PostalesAdapter(Context context, Postal[] postales) {
    super(context, R.layout.partial_postal, postales);
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    if (convertView == null) {
      convertView = View.inflate(getContext(), R.layout.partial_postal, null);
    }

    // La imagen de la postal se cargará desde Internet en segundo plano.
    Postal postal = getItem(position);
    DrawableManager.getInstance().fetchDrawableOnThread(postal.getUrlParaDescargar(), (ImageView) convertView);

    return convertView;
  }

}
