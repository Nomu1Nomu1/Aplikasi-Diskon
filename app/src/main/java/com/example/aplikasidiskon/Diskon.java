package com.example.aplikasidiskon;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;
import java.util.Locale;

public class Diskon extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diskon);

        EditText etHarga = findViewById(R.id.nominal);
        EditText etDiskon = findViewById(R.id.diskon);
        TextView tvHasil = findViewById(R.id.hargaDiskon);
        Button diskonButton = findViewById(R.id.diskonButton);

        diskonButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nominalStr = etHarga.getText().toString().trim();
                String diskonStr = etDiskon.getText().toString().trim();

                if (nominalStr.isEmpty() || diskonStr.isEmpty()) {
                    Toast.makeText(Diskon.this, "Masukkan Nominal/Diskon", Toast.LENGTH_SHORT).show();
                    return;
                }

                try {
                    double nominal = Double.parseDouble(nominalStr);
                    double diskon = Double.parseDouble(diskonStr);

                    if (diskon < 0 || diskon > 100) {
                        Toast.makeText(Diskon.this, "Range Diskon 1-100", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    double hasil = nominal - (nominal * diskon/100);

                    NumberFormat format = NumberFormat.getNumberInstance(new Locale("id", "ID"));
                    String price = format.format(hasil);

                    tvHasil.setText("Rp " + price);
                } catch (NumberFormatException e) {
                    Toast.makeText(Diskon.this, "Masukkan Input", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}