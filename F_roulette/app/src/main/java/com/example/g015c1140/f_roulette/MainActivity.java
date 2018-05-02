package com.example.g015c1140.f_roulette;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;
    int rt, k = 0;
    float toDegrees = 0;
    Random rnd;
    String result[] = new String[4];
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView);

        Button buttonFadeOut = findViewById(R.id.start);
        buttonFadeOut.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                rnd = new Random();
                rt = rnd.nextInt(2);

                startRotationXml();



                // アクセスキー
                String acckey = "a408fe819421bb02777124b05553557a";
                // 緯度
                String lat = "35.634165";
                // 経度
                String lon = "139.714698";
                // 範囲
                String range = "3";
                // 返却形式
                String format = "json";
                //フリーワード
                String freeWord = result[rt];
                // エンドポイント
                String gnaviRestUri = "https://api.gnavi.co.jp/RestSearchAPI/20150630/";
                String prmFormat = "?format=" + format;
                String prmKeyid = "&keyid=" + acckey;
                String prmLat = "&latitude=" + lat;
                String prmLon = "&longitude=" + lon;
                String prmRange = "&range=" + range;
                String prmFreeWord = "&freeword=" + freeWord;

                // URI組み立て
                StringBuffer uri = new StringBuffer();
                uri.append(gnaviRestUri);
                uri.append(prmFormat);
                uri.append(prmKeyid);
                uri.append(prmLat);
                uri.append(prmLon);
                uri.append(prmRange);
                uri.append(prmFreeWord);

                // API実行、結果を取得し出力
                getNodeList(uri.toString());
            }
        });
    }



    private void getNodeList(final String url) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL restSearch = new URL(url);
                    HttpURLConnection http = (HttpURLConnection)restSearch.openConnection();
                    http.setRequestMethod("GET");
                    http.connect();
                    //Jackson
                    ObjectMapper mapper = new ObjectMapper();
                    viewJsonNode(mapper.readTree(http.getInputStream()));

                } catch (Exception e){
                    System.out.println("例外が発生しました");
                    Log.e("getNodeList", "error", e);
                }

            }
        }).start();
    }



    private void viewJsonNode(JsonNode nodeList){
        if(nodeList != null){
            //トータルヒット件数
            String hitcount   = "total:" + nodeList.path("total_hit_count").asText();
            System.out.println(hitcount);
            //restのみ取得
            JsonNode restList = nodeList.path("rest");
            Iterator<JsonNode> rest = restList.iterator();
            //店舗名、店舗画像1のURL,PR文（短）を出力
                //while(rest.hasNext()){
            JsonNode r = rest.next();
            final String name = r.path("name").asText();
            final String image1 = r.path("image_url").path("shop_image1").asText();
            final String pr = r.path("pr").path("pr_short").asText();
            System.out.println("***************************************************");
            System.out.println("name: " + name + "\nimage1: " + image1 + "\npr: " + pr);
              // }

            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(MainActivity.this, com.example.g015c1140.f_roulette.OverviewActivity.class);
                    intent.putExtra("name", name);
                    intent.putExtra("image1", image1);
                    intent.putExtra("pr", pr);
                    startActivity(intent);
                }
            },2000);

        }
    }


    private void startRotationXml() {

        //result[0] = "中華";
        //result[1] = "焼き肉";
        result[0] = "カレー";
        result[1] = "すし";

        rnd = new Random();
        k = rnd.nextInt(8) + 8;
        toDegrees = 360f * k;
        switch (rt) {
          /*  case 0:
                toDegrees = toDegrees + 45;
                break;
            case 1:
                toDegrees = toDegrees + 45 * 3;
                break;
                */
            case 0:
                toDegrees = toDegrees + 45 * 5;
                break;
            case 1:
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