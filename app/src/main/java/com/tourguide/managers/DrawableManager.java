package com.tourguide.managers;

import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.ImageView;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

public class DrawableManager {
  private final Map<String, Drawable> drawableMap;
  private static DrawableManager instance;

  private DrawableManager() {
    drawableMap = new HashMap<String, Drawable>();
  }

  public static DrawableManager getInstance() {
    if (instance == null) instance = new DrawableManager();

    return instance;
  }

  public void fetchDrawableOnThread(final String urlString, final ImageView imageView) {
    if (drawableMap.containsKey(urlString)) {
      putDrawableIntoImage(drawableMap.get(urlString), imageView);
    }

    final Handler handler = new Handler() {
      @Override
      public void handleMessage(Message message) {
        putDrawableIntoImage((Drawable) message.obj, imageView);
      }
    };

    Thread thread = new Thread() {
      @Override
      public void run() {
        //TODO : set imageView to a "pending" image
        Drawable drawable = fetchDrawable(urlString);
        Message message = handler.obtainMessage(1, drawable);
        handler.sendMessage(message);
      }
    };
    thread.start();
  }

  public Drawable fetchDrawable(String urlString) {
    if (drawableMap.containsKey(urlString)) {
      return drawableMap.get(urlString);
    }

    Log.d(this.getClass().getSimpleName(), "image url:" + urlString);
    try {
      InputStream is = fetch(urlString);
      Drawable drawable = Drawable.createFromStream(is, "src");


      if (drawable != null) {
        drawableMap.put(urlString, drawable);
        Log.d(this.getClass().getSimpleName(), "got a thumbnail drawable: " + drawable.getBounds() + ", "
            + drawable.getIntrinsicHeight() + "," + drawable.getIntrinsicWidth() + ", "
            + drawable.getMinimumHeight() + "," + drawable.getMinimumWidth());
      } else {
        Log.w(this.getClass().getSimpleName(), "could not get thumbnail");
      }

      return drawable;
    } catch (MalformedURLException e) {
      Log.e(this.getClass().getSimpleName(), "fetchDrawable failed", e);
      return null;
    } catch (IOException e) {
      Log.e(this.getClass().getSimpleName(), "fetchDrawable failed", e);
      return null;
    }
  }

  private InputStream fetch(String urlString) throws MalformedURLException, IOException {
    DefaultHttpClient httpClient = new DefaultHttpClient();
    HttpGet request = new HttpGet(urlString);
    HttpResponse response = httpClient.execute(request);
    return response.getEntity().getContent();
  }

  private void putDrawableIntoImage(Drawable drawable, ImageView imageView) {
    imageView.setImageDrawable(drawable);
  }

}