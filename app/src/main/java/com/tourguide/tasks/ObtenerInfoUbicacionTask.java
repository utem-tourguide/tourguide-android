package com.tourguide.tasks;

import android.os.AsyncTask;

import com.tourguide.activities.InformacionActivity;
import com.tourguide.factories.UbicacionesTuristicasFactory;
import com.tourguide.fragments.ProgressFragment;
import com.tourguide.handlers.BackendResponseHandler;
import com.tourguide.models.UbicacionTuristica;

import java.util.Map;

public class ObtenerInfoUbicacionTask extends AsyncTask<Void, Void, UbicacionTuristica> {

    private int ubicacionId;
    private InformacionActivity actividad;
    private ProgressFragment fragment;
    private Map<Boolean, BackendResponseHandler> handlers;

    public ObtenerInfoUbicacionTask(int ubicacionId, InformacionActivity actividad, ProgressFragment fragment, Map<Boolean, BackendResponseHandler> handlers) {
        this.ubicacionId = ubicacionId;
        this.actividad = actividad;
        this.fragment = fragment;
        this.handlers = handlers;
    }

    @Override
    protected UbicacionTuristica doInBackground(Void... params) {
        return UbicacionesTuristicasFactory.construirPorId(ubicacionId);
    }

    @Override
    protected void onPreExecute() {
        fragment.mostrarProgreso(true);
    }

    @Override
    protected void onPostExecute(UbicacionTuristica ubicacion) {
        fragment.mostrarProgreso(false);

        actividad.setUbicacion(ubicacion);

        handlers.get(ubicacion != null).handle();
    }

}
