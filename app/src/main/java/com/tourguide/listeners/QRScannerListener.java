package com.tourguide.listeners;

import android.content.Intent;

import com.tourguide.activities.Activity;
import com.tourguide.activities.InformacionActivity;

import eu.livotov.zxscan.ScannerView;

public class QRScannerListener implements ScannerView.ScannerViewEventListener {

    private ScannerView scanner;
    private Activity actividad;

    public QRScannerListener(ScannerView scanner, Activity actividad) {
        this.scanner = scanner;
        this.actividad = actividad;
    }

    @Override
    public void onScannerReady() {

    }

    @Override
    public void onScannerFailure(int i) {

    }

    @Override
    public boolean onCodeScanned(String data) {
        scanner.setPlaySound(false);
        scanner.stopScanner();

        Intent intent = new Intent(actividad, InformacionActivity.class);
        intent.putExtra("ubicacionId", Integer.valueOf(data));

        actividad.startActivity(intent);

        return true;
    }

}
