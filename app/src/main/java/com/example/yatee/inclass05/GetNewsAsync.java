package com.example.yatee.inclass05;
//Yateen Kedare
//InClass_05
import android.os.AsyncTask;
import android.util.Log;

import org.xml.sax.SAXException;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by yatee on 2/13/2017.
 */

public class GetNewsAsync extends AsyncTask<String, Void, ArrayList<News>> {
    IData activity;
    GetNewsAsync(IData activity){
        this.activity=activity;
    }
    @Override
    protected ArrayList<News> doInBackground(String... params) {
        try {
            URL url=new URL(params[0]);
            HttpURLConnection con= (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            Log.d("Stream",con.getInputStream().toString());
            NewsParser.NewsSAXParser.parseNews(con.getInputStream());
            return NewsParser.NewsSAXParser.getNews();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(ArrayList<News> newses) {
        super.onPostExecute(newses);
        try {
            activity.returnedValues(newses);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
    }

    public static interface IData{
        public void returnedValues(ArrayList<News> val) throws IOException, XmlPullParserException, SAXException;
    }
}
