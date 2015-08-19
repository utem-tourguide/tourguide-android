package com.tourguide.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.tourguide.R;
import com.tourguide.listeners.QRScannerListener;

import eu.livotov.zxscan.ScannerView;

public class MainActivity extends Activity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void onPerfilMenuItemClick(MenuItem item) {
        startActivity(new Intent(this, PerfilActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();

        inicializarQRScanner();
    }

    private void inicializarQRScanner() {
        ScannerView scanner = (ScannerView) findViewById(R.id.scanner);
        scanner.setScannerViewEventListener(new QRScannerListener(scanner, this));
        scanner.startScanner();
    }

}
