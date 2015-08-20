package com.tourguide.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.tourguide.R;

import java.util.List;

public class PostalesAdapter extends ArrayAdapter<Bitmap> {

  public PostalesAdapter(Context context, List<Bitmap> bitmaps) {
    super(context, R.layout.partial_postal, bitmaps);
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    ImageView view = (ImageView) convertView;

    view.setImageBitmap(getItem(position));

    return view;
  }
}
