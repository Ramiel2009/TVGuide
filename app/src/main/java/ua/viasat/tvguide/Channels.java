package ua.viasat.tvguide;

import java.util.ArrayList;

/**
 * Created by mmaloshtan on 06.01.2015.
 */

public class Channels {
    public static String ChannelUrl;
    public static ArrayList <Integer> channelsLogo = new ArrayList<Integer>();

    public static void setChannelsLogo() {
        channelsLogo.add(R.drawable.tv1000);
        channelsLogo.add(R.drawable.tv1000rus);
        channelsLogo.add(R.drawable.tv1000action);
        channelsLogo.add(R.drawable.premium);
        channelsLogo.add(R.drawable.megahit);
        channelsLogo.add(R.drawable.comedy_hd);
        channelsLogo.add(R.drawable.history);
        channelsLogo.add(R.drawable.explore);
        channelsLogo.add(R.drawable.nature);
        channelsLogo.add(R.drawable.nature_hd);
        channelsLogo.add(R.drawable.sport);
        channelsLogo.add(R.drawable.dicovery_channel);
        channelsLogo.add(R.drawable.dicovery_showcase_hd);
        channelsLogo.add(R.drawable.ut1);
        channelsLogo.add(R.drawable.inter);
        channelsLogo.add(R.drawable.oneplusone);
        channelsLogo.add(R.drawable.ictv);
        channelsLogo.add(R.drawable.novy);
    }


    public static String setChannelUrl(){
        switch (Channels_fragment.selectedChannel){
            case (0): //tv1000
                ChannelUrl = "http://ru.viasat.ua/channels/1";
                break;
            case (1): //tv1000rus
                ChannelUrl = "http://ru.viasat.ua/channels/2";
                break;
            case (2): //tv1000 action
                ChannelUrl = "http://ru.viasat.ua/channels/3";
                break;
            case (3): //premium hd
                ChannelUrl = "http://ru.viasat.ua/channels/246";
                break;
            case (4): //megahit hd
                ChannelUrl = "http://ru.viasat.ua/channels/242";
                break;
            case (5): //comedy
                ChannelUrl = "http://ru.viasat.ua/channels/244";
                break;
            case (6): //history
                ChannelUrl = "http://ru.viasat.ua/channels/17";
                break;
            case (7): //explore
                ChannelUrl = "http://ru.viasat.ua/channels/18";
                break;
            case (8): //nature
                ChannelUrl = "http://ru.viasat.ua/channels/120";
                break;
            case (9): //nat-hist hd
                ChannelUrl = "http://ru.viasat.ua/channels/203";
                break;
            case (10): //viasat sport
                ChannelUrl = "http://ru.viasat.ua/channels/322";
                break;
            case (11): //discovery channel
                ChannelUrl = "http://ru.viasat.ua/channels/19";
                break;
            case (12): //discovery showcase hd
                ChannelUrl = "http://ru.viasat.ua/channels/258";
                break;
            case (13): //ut1
                ChannelUrl = "http://ru.viasat.ua/channels/68";
                break;
            case (14): //inter
                ChannelUrl = "http://ru.viasat.ua/channels/305";
                break;
            case (15): //1+1
                ChannelUrl = "http://ru.viasat.ua/channels/64";
                break;
            case (16): //ictv
                ChannelUrl = "http://ru.viasat.ua/channels/66";
                break;
            case (17): //novy
                ChannelUrl = "http://ru.viasat.ua/channels/65";
                break;
        }
        return ChannelUrl;
    }
}
