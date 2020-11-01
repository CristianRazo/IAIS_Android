package com.andrea.iais2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;


public class Registro extends AppCompatActivity  {

    private EditText texto1;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        Toolbar mitoolbar2 = (Toolbar) findViewById(R.id.mitoolbar2);
        setSupportActionBar(mitoolbar2);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        texto1 = findViewById(R.id.codigo);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }





    public void Siguiente(View view){
        Intent siguiente = new Intent(this,Datos.class);
        startActivity(siguiente);
    }

    public void scanNow(View view){
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
        integrator.setPrompt(this.getString(R.string.scan_bar_code));
        integrator.setCameraId(0);
        integrator.setBarcodeImageEnabled(true);
        integrator.setCaptureActivity(AnyOrientationCaptureActivity.class);
        integrator.setOrientationLocked(false);
        integrator.initiateScan();

    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        //retrieve scan result
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);

        if (scanningResult != null) {
            if(scanningResult.getContents() != null){
                //we have a result
                String codeContent = scanningResult.getContents();
                String codeFormat = scanningResult.getFormatName();

                // display it on screen
                texto1.setText(codeContent);


            }else{
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
            }
        }else{
            Toast toast = Toast.makeText(getApplicationContext(),"No scan data received!", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}
