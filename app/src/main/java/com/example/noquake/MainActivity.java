package com.example.noquake;

import androidx.appcompat.app.AppCompatActivity;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.widget.Button;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;



public class MainActivity extends AppCompatActivity {

    BluetoothAdapter bAdapter = BluetoothAdapter.getDefaultAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn = findViewById(R.id.btn);
        Button button = findViewById(R.id.numbers);
        Button button1 = findViewById(R.id.guide);
        Button button2 = findViewById(R.id.todo);
        Button button3 = findViewById(R.id.blu);
        Button button4 = findViewById(R.id.deprem);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(bAdapter == null)
                {
                    Toast.makeText(getApplicationContext(),"Bluetooth Not Supported",Toast.LENGTH_SHORT).show();
                }
                else{
                    if(!bAdapter.isEnabled()){
                        startActivityForResult(new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE),1);
                        Toast.makeText(getApplicationContext(),"Bluetooth Turned ON",Toast.LENGTH_SHORT).show();
                        WifiManager wifi = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
                        assert wifi != null;
                        wifi.setWifiEnabled(false);
                    }
                }
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity1();
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity2();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity3();
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity4();
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity5();
            }
        });

    }


    public void openActivity1() {
        Intent intent = new Intent(this, Main2Activity.class);
        startActivity(intent);
    }
    public void openActivity2() {
        Intent intent = new Intent(this, Guide.class);
        startActivity(intent);
    }

    public void openActivity3() {
        Intent intent = new Intent(this, Guide2.class);
        startActivity(intent);
    }
    public void openActivity4() {
        Intent intent = new Intent(this, Blue.class);
        startActivity(intent);
    }
    public void openActivity5() {
        Intent intent = new Intent(this, Quake.class);
        startActivity(intent);
    }


}
