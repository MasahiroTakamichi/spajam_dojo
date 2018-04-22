package com.example.bosanade.f_roulette;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;
    int rt, k = 0;
    float toDegrees = 0;
    Random rnd;
    String result[] = new String[4];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView);

        Button buttonFadeOut = findViewById(R.id.start);
        buttonFadeOut.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                rnd = new Random();
                rt = rnd.nextInt(4);

                startRotationXml();
            }
        });
    }

    private void startRotationXml() {

        result[0] = "中華";
        result[1] = "焼き肉";
        result[2] = "カレー";
        result[3] = "すし";

        rnd = new Random();
        k = rnd.nextInt(8) + 8;
        toDegrees = 360f * k;
        switch (rt) {
            case 0:
                toDegrees = toDegrees + 45;
                break;
            case 1:
                toDegrees = toDegrees + 45 * 3;
                break;
            case 2:
                toDegrees = toDegrees + 45 * 5;
                break;
            case 3:
                toDegrees = toDegrees + 45 * 7;
                break;
        }
        System.out.println(result[rt]);

        RotateAnimation rotate = new RotateAnimation(0.0f, toDegrees, 30f, 290f);

        // animation時間 msec
        rotate.setDuration(1000);
        // 繰り返し回数
        rotate.setRepeatCount(0);
        // animationが終わったそのまま表示にする
        rotate.setFillAfter(true);

        System.out.println(toDegrees);
        imageView.startAnimation(rotate);
    }
}
