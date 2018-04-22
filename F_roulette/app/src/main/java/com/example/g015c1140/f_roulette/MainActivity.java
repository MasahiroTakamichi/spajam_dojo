package com.example.g015c1140.f_roulette;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;


public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

            // アクセスキー
            String acckey = "a408fe819421bb02777124b05553557a";
            // 緯度
            String lat = "35.634165";
            // 経度
            String lon = "139.714698";
            // 範囲
            String range = "2";
            // 返却形式
            String format = "json";
            //フリーワード
            String freeWord = "そば";
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

   // }

    private static void getNodeList(final String url) {
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
                    //TODO: 例外を考慮していません
                }

            }
        }).start();
    }

    private static void viewJsonNode(JsonNode nodeList){
        if(nodeList != null){
            //トータルヒット件数
            String hitcount   = "total:" + nodeList.path("total_hit_count").asText();
            System.out.println(hitcount);
            //restのみ取得
            JsonNode restList = nodeList.path("rest");
            Iterator<JsonNode> rest = restList.iterator();
            //店舗名、店舗画像1のURL,PR文（短）を出力
            JsonNode r = rest.next();
            String name = r.path("name").asText();
            String image1 = r.path("image_url").path("shop_image1").asText();
            String pr = r.path("pr").path("pr_short").asText();
            System.out.println("***************************************************");
            System.out.println("name: " + name + "\nimage1: " + image1 + "\npr: " + pr);
        }
    }
}
