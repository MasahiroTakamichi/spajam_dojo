package com.example.g015c1140.f_roulette;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

public class OverviewActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String image1 = intent.getStringExtra("image1");
        String pr = intent.getStringExtra("pr");

        Log.e("Overview", "name: " + name + "\nimage1: " + image1 + "\npr: " + pr);

        //imageを取得
        ImageView image = findViewById(R.id.imageView);

        TextView textView = findViewById(R.id.textView);
        textView.setText(pr);

        TextView textView2 = findViewById(R.id.name);
        textView2.setText(name);

        //画像取得スレッド起動
        ImageGetTask task = new ImageGetTask(image);
        task.execute(image1);
    }
}