package com.example.aplikasidiskon;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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
                String nominalStr = etHarga.getText().toString();
                String diskonStr = etDiskon.getText().toString();

                double nominal = Double.parseDouble(nominalStr);
                double diskon = Double.parseDouble(diskonStr);

                if (nominalStr.isEmpty() || diskonStr.isEmpty()) {
                    tvHasil.setText("Please fill the blank fields");
                    return;
                }

                if (diskon < 0 || diskon > 100) {
                    tvHasil.setText("Diskon must be between 0 and 100");
                    return;
                }

                double hargaSetelahDiskon = nominal - (nominal * diskon / 100);

                NumberFormat formatter = NumberFormat.getNumberInstance(new Locale("id", "ID"));
                String price = formatter.format(hargaSetelahDiskon);

                tvHasil.setText("Rp "+price);
            }
        });
    }
}