package com.tourguide.activities;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import android.widget.ProgressBar;

abstract public class ProgressActivity extends Activity {

  public void mostrarProgreso(final boolean show) {
    int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);
    mostrarEscondible(show, shortAnimTime);
    mostrarProgressBar(show, shortAnimTime);
  }

  /**
   * Obtiene una referencia a la vista escondible de esta actividad.
   */
  public abstract View getEscondible();

  /**
   * Obtiene una referencia al progressbar de esta actividad.
   */
  public abstract ProgressBar getProgressBar();

  /**
   * Muestra u oculta la progressbar.
   *
   * @param show
   * @param shortAnimTime
   */
  private void mostrarProgressBar(final boolean show, int shortAnimTime) {
    final ProgressBar progressBar = getProgressBar();

    progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
    progressBar.animate().setDuration(shortAnimTime).alpha(
      show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
      @Override
      public void onAnimationEnd(Animator animation) {
        progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
      }
    });
  }

  private void mostrarEscondible(final boolean show, int shortAnimTime) {
    final View escondible = getEscondible();

    escondible.setVisibility(show ? View.GONE : View.VISIBLE);
    escondible.animate().setDuration(shortAnimTime).alpha(
      show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
      @Override
      public void onAnimationEnd(Animator animation) {
        escondible.setVisibility(show ? View.GONE : View.VISIBLE);
      }
    });
  }

}
