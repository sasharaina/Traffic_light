package com.example.traffic_light;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    private LinearLayout l1, l2, l3;
    private boolean start_stop = false;
    private Button button;
    private int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        l1 = findViewById(R.id.light1);
        l2 = findViewById(R.id.light2);
        l3 = findViewById(R.id.light3);
        button = findViewById(R.id.button);
    }

    public void onClickStart(View view) {
        if (!start_stop) {
            button.setBackgroundColor(Color.CYAN);
            button.setText("Stop");
            start_stop = true;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (start_stop) {
                        counter++;
                        switch (counter){
                            case 1:
                                l1.setBackgroundColor(getResources().getColor(R.color.green1));
                                l2.setBackgroundColor(getResources().getColor(R.color.GREY));
                                l3.setBackgroundColor(getResources().getColor(R.color.GREY));
                                break;
                            case 2:
                                l1.setBackgroundColor(getResources().getColor(R.color.GREY));
                                l2.setBackgroundColor(getResources().getColor(R.color.yellow));
                                l3.setBackgroundColor(getResources().getColor(R.color.GREY));
                                break;
                            case 3:
                                l1.setBackgroundColor(getResources().getColor(R.color.GREY));
                                l2.setBackgroundColor(getResources().getColor(R.color.GREY));
                                l3.setBackgroundColor(getResources().getColor(R.color.red));
                                counter = 0;
                                break;
                        }
                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }

                }
            }).start();
        }
        else {
            start_stop = false;
            button.setBackgroundColor(Color.rgb(255, 131, 0));
            button.setText("Start");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        start_stop = false;
    }
}
