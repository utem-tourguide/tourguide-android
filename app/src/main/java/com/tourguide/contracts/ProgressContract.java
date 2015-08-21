package com.tourguide.contracts;

import android.view.View;
import android.widget.ProgressBar;

public interface ProgressContract {

  /**
   * Muestra u oculta el progress bar
   *
   * @param show
   */
  void mostrarProgreso(boolean show);

  /**
   * Obtiene una referencia a la vista escondible de esta actividad.
   */
  View getEscondible();

  /**
   * Obtiene una referencia al progressbar de esta actividad.
   */
  ProgressBar getProgressBar();

}
