package ua.viasat.tvguide;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by mmaloshtan on 27.01.2015.
 */
public class ScheduleParser {

    public static String Url = Channels.setChannelUrl();
    public static String nextEvTime = "00:00";
    public static String pervEvTime = "00:00";
    public static int flag;
    public static boolean check =false;
    public static String listp[] = new String[1000];
    public static String listt[] = new String [1000];
    public static String liveEvents[] = new String [Channels.urlList.length];
    public static String liveEventsTime[] = new String [Channels.urlList.length];
    public static String nextEvents[] = new String [Channels.urlList.length];
    public static String nextEventsTime[] = new String [Channels.urlList.length];
    public static int mark;

    public void getLiveEvents(){
    }

    public static void Parse () throws IOException {

        for (int b = 0; b < Channels.urlList.length; b++) {
            System.out.println("Processing: "+Channels.urlList[b]);
            Url = Channels.urlList[b];
            Document doc = Jsoup.connect(Url).get();
            Elements item = doc.select(".time-block");
            int i = 0;
            for (Element e : item) {
                i++;
                nextEvTime = e.getElementsByClass("ch-time").text(); //perv Event time
                getCurrentEvent();
                pervEvTime = e.getElementsByClass("ch-time").text(); //next Event time
                System.out.println(e.getElementsByClass("ch-time").text());
                System.out.println(e.getElementsByClass("ch-title").text());

                listp[i]=e.getElementsByClass("ch-title").text();
                listt[i]=e.getElementsByClass("ch-time").text();

              /*  mItems.add(new ParserList((Channels.names[i]),
                        e.getElementsByClass("ch-title").text(), e.getElementsByClass("ch-time").text()));*/

                if (check == true) {
                    mark = i - 1;
                    System.out.println(mark);
                    liveEventsTime[b]=listt[mark];
                    liveEvents[b]=listp[mark];
                    nextEventsTime[b]=listt[i];
                    nextEvents[b]=listp[i];
                    System.out.println(liveEvents[b]);
                    check = false;
                }
            }
        }
    }

    public static void getCurrentEvent(){
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        try {
            Date nextDate = sdf.parse(nextEvTime); //event time
            Date curDate = sdf.parse(sdf.format(new Date())); //current time
            Date pervDate = sdf.parse(pervEvTime);
            flag = nextDate.compareTo(curDate);
            System.out.println(flag);
            if (pervDate.compareTo(curDate) ==-1 && curDate.compareTo(nextDate)==-1){
                System.out.println("Current Date: "+curDate+" PervDate: "+pervEvTime+" nextDate: "+nextEvTime);
                check=true;
            }
            // Outputs -1 as date1 is before date2
            // Outputs 1 as date1 is after date2
            // Outputs 0 as the dates are now equal
        } catch (ParseException e){
            e.printStackTrace();
        }
    }
}

