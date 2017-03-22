package com.example.yatee.inclass05;
//Yateen Kedare
//InClass_05
import android.util.Log;
import android.util.Xml;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by yatee on 2/13/2017.
 */

public class NewsParser {
    static public class NewsSAXParser extends DefaultHandler {
        static ArrayList<News> newsList;
        News news;
        StringBuilder sb;
        Boolean item = false, pubDate = false, description = false;
        static public ArrayList<News> parseNews(InputStream in) throws IOException, SAXException {
            NewsSAXParser parser=new NewsSAXParser();
            Xml.parse(in,Xml.Encoding.UTF_8,parser);
            return parser.getNews();
        }

        @Override
        public void startDocument() throws SAXException {
            newsList=new ArrayList<News>();
            sb=new StringBuilder();
            super.startDocument();
        }

        @Override
        public void endDocument() throws SAXException {
            super.endDocument();
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            super.startElement(uri, localName, qName, attributes);
            if(localName.equals("item")){
                news=new News();
                item = true;
            }
            if(qName.equals("media:content") ){
                if(item)
                    news.setImageURL(attributes.getValue("url"));
            }
            if(localName.equals("pubDate")){
                if(item)
                    pubDate = true;
            }
            if(localName.equals("description")){
                if(item)
                    description = true;
            }
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            super.endElement(uri, localName, qName);
            if(item){
                switch (localName) {
                    case "item":
                        Log.d("Demo", news.toString());
                        newsList.add(news);
                        item = false;
                        break;
                    case "title":
                        news.setTitle(sb.toString().trim());
                        break;
                    case "pubDate":
                        if(pubDate){
                            news.setPubDate(sb.toString().trim());
                            pubDate = false;
                        }
                        break;
                    case "description":
                        if(description) {
                            news.setDescription(sb.toString().trim());
                        }
                        break;
                }
            }
            sb.setLength(0);
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            super.characters(ch, start, length);
            sb.append(ch, start, length);
        }

        static public ArrayList<News> getNews() {
            return newsList;
        }
    }

}
