package com.tourguide.managers;

import android.content.SharedPreferences;

public class LoginManager {

  private static LoginManager instance;

  private static final String LOGIN_ID = "LOGIN_ID";

  private SharedPreferences preferences;

  private LoginManager(SharedPreferences preferences) {
    this.preferences = preferences;
  }

  public static LoginManager getInstance() {
    if (instance == null) throw new RuntimeException("LoginManager debe ser inicializado.");

    return instance;
  }

  public static void initialize(SharedPreferences preferences) {
    instance = new LoginManager(preferences);
  }

  public static boolean hasLoginId() {
    return getInstance().getPreferences().contains(LOGIN_ID);
  }

  public static int getLoginId() {
    return getInstance().getPreferences().getInt(LOGIN_ID, 0);
  }

  public static void setLoginId(int id) {
    getInstance().getPreferences().edit().putInt(LOGIN_ID, id).commit();
  }

  public SharedPreferences getPreferences() {
    return preferences;
  }

}
