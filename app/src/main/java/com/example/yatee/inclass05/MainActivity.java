package com.example.yatee.inclass05;
//Yateen Kedare
//InClass_05
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.xml.sax.SAXException;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements GetNewsAsync.IData {
    ArrayList<News> newsList;
    int displayedNews = 0;
    ImageView image;
    TextView textView;
    int newsSize = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setLogo(R.drawable.cnn);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);;
        image = (ImageView) findViewById(R.id.IVNewsImage);
        textView = (TextView) findViewById(R.id.textView);
        Button getNews = (Button) findViewById(R.id.btnGetNews);
        getNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new GetNewsAsync(MainActivity.this).execute("http://rss.cnn.com/rss/cnn_tech.rss");
            }
        });


        final Button finish = (Button) findViewById(R.id.button2);
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        ImageView first = (ImageView) findViewById(R.id.btnFirst);
        first.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayedNews = 0;
                displayNews();
            }
        });
        ImageView prev = (ImageView) findViewById(R.id.btnPrev);
        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(displayedNews != 0)
                    displayedNews--;
                displayNews();
            }
        });
        ImageView next = (ImageView) findViewById(R.id.btnNext);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(displayedNews != newsSize-1)
                    displayedNews++;
                displayNews();
            }
        });
        ImageView last = (ImageView) findViewById(R.id.btnLast);
        last.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayedNews = newsSize-1;
                displayNews();
            }
        });


    }


    @Override
    public void returnedValues(ArrayList<News> val) throws IOException, XmlPullParserException, SAXException {
        newsList = val;
        newsSize = val.size();
        displayNews();
    }

    public void displayNews() {
        Picasso.with(this)
                .load(newsList.get(displayedNews).getImageURL())
                .into(image);

        textView.setText(newsList.get(displayedNews).toString());
    }
}
