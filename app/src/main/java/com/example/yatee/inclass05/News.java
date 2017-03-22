package com.example.yatee.inclass05;
//Yateen Kedare
//InClass_05
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by yatee on 2/13/2017.
 */

public class News {
    private String title, pubDate, description, imageURL;
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    @Override
    public String toString() {
        return
                "" + title +
                "\n\nPublished on: " + getDate(pubDate) +
                "\n\nDescription:\n" + description ;
    }

    public String getDate(String _date){
        Date dob=new Date(_date);//this will take date as Fri Jan 06 00:00:00 IST 2012


        SimpleDateFormat newDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        String new_date=newDateFormat.format(dob);
        return new_date;
    }

}
