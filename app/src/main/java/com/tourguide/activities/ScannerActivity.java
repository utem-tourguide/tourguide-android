package com.tourguide.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.tourguide.R;
import com.tourguide.listeners.QRScannerListener;
import com.tourguide.managers.LoginManager;

import eu.livotov.zxscan.ScannerView;

public class ScannerActivity extends Activity {

    private ScannerView scanner;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_scanner, menu);
        return true;
    }

    public void onPerfilMenuItemClick(MenuItem item) {
        startActivity(new Intent(this, PerfilActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanner);

        inicializarScanner();
    }

    @Override
    protected void onResume() {
        super.onResume();

        scanner.startScanner();
    }

    @Override
    protected void onPause() {
        super.onPause();

        scanner.stopScanner();
    }

    private void inicializarScanner() {
        scanner = (ScannerView) findViewById(R.id.scanner);
        scanner.setPlaySound(true);
        scanner.setScannerViewEventListener(new QRScannerListener(scanner, this));
    }

    public void onSalirMenuItemClick(MenuItem item) {
        LoginManager.deleteLoginId();

        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

}
