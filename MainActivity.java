package com.example.flanker.f_roulette;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    int rt = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void startButtonClick(View view){
        try {
            Random rnd = new Random();
            rt = rnd.nextInt(4);
            System.out.println(rt);

            switch (rt) {
                case 0:
                    ((TextView) findViewById(R.id.rText)).setText("カレー");
                    Thread.sleep(50);
                    return;
                case 1:
                    ((TextView) findViewById(R.id.rText)).setText("ラーメン");
                    Thread.sleep(50);
                    return;
                case 2:
                    ((TextView) findViewById(R.id.rText)).setText("パン");
                    Thread.sleep(50);
                    return;
                case 3:
                    ((TextView) findViewById(R.id.rText)).setText("焼肉");
                    Thread.sleep(50);
                    return;
            }
        }catch(InterruptedException e){}
    }
}
