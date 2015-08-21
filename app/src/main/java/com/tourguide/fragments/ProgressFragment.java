package com.tourguide.fragments;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import android.widget.ProgressBar;

import com.tourguide.contracts.ProgressContract;

public abstract class ProgressFragment extends Fragment implements ProgressContract {

  private View escondible;
  private ProgressBar progressBar;

  @Override
  public void mostrarProgreso(final boolean show) {
    int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);
    mostrarEscondible(show, shortAnimTime);
    mostrarProgressBar(show, shortAnimTime);
  }

  @Override
  public abstract View getEscondible();

  @Override
  public abstract ProgressBar getProgressBar();

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

}
