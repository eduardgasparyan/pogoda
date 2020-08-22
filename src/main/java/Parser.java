import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    private static Document getPage() throws IOException {
String url = "https://www.accuweather.com/ru/am/yerevan/16890/current-weather/16890";
Document page = Jsoup.parse(new URL(url), 3000);
return page;
    }
    private static Pattern pattern = Pattern.compile("\\d{2}\\:\\d{2}");
    private static   String getDateFromString(String stringDate) throws Exception {
        Matcher matcher = pattern.matcher(stringDate);
        if  (matcher.find())
            return matcher.group();
        throw new Exception("Can't find date");
    }
    public static void main(String[] args) throws Exception {
        Document page = getPage();
        Element day = page.select("div[class=conditions-card card panel conditions-card]").last();
        Elements days = day.select("p");
        Element specs = page.select("div[class=accordion-item accordion-content]").first();
        Elements names = specs.select("div[class=list]");
        for (Element or : days)
        {
            String date = day.select("p[class=module-header sub date]").text();
            System.out.println(date);
        }
        for (Element name : names)
        {
            String dateString =  specs.select("p").text();
            String date = getDateFromString(dateString);
            System.out.println(date);
        }
    }
}
