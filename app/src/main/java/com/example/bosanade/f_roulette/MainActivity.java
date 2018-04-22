package com.example.bosanade.f_roulette;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;
    int rt, i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView);

        Button buttonFadeOut = findViewById(R.id.start);
        buttonFadeOut.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Random rnd = new Random();
                rt = rnd.nextInt(4);
                System.out.println(rt);

                startRotationXml();
            }
        });
    }

    private void startRotationXml() {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.rotation);

        i =+360*3;
        animation.setDuration(i);
        System.out.println(i);
        imageView.startAnimation(animation);
    }
}
