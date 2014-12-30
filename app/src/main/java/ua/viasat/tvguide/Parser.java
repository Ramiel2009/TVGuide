package ua.viasat.tvguide;

/**
 * Created by mmaloshtan on 23.12.2014.
 */
    import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
    import java.util.List;

public class Parser {
        public static ArrayList<String> id = new ArrayList<String>();
        public static ArrayList<String> time = new ArrayList<String>();
        public static ArrayList<String> channel = new ArrayList<String>();
        public static ArrayList<String> title = new ArrayList<String>();
        public static ArrayList<String> imageLinks = new ArrayList<String>();

        public static ArrayList getItemInfo(String id) throws IOException {
            String url = "http://ru.viasat.ua/contents/" + id;

            Document doc = Jsoup.connect(url).timeout(60000).get();
            //System.out.println(doc);
            Elements item = doc.select(".film-text");
            String imageLink = doc.select(".l-film-ill").get(0)
                    .getElementsByTag("img").get(0).attributes().get("src");

            ArrayList res = new ArrayList();
            res.add(item.text().trim());
            res.add("http://ru.viasat.ua" + imageLink);
            System.out.println("http://ru.viasat.ua" + imageLink);
            return res;
        }
        public static void refreshItems() throws IOException {
            time.clear();
            channel.clear();
            title.clear();
            id.clear();

            String url = "http://ru.viasat.ua/contents";
            Document doc = Jsoup.connect(url).timeout(60000).get();
            Elements titles = doc.select(".data");
          //  System.out.println(titles.size());
            for (Element e : titles) {
                time.add(e.getElementsByClass("time").text());
                channel.add(e.getElementsByClass("channel").text());
                title.add(e.getElementsByClass("title").text());
                id.add(e.getElementsByClass("title").get(0).attr("href").replaceAll("/contents/", ""));
            }
            //System.out.println(doc.select(".data").select(".title"));
            System.out.println(id);
        }
    }
