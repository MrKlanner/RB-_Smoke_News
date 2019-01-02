/**Задачи:
 * 1) Класс ссылок для проектов. В будущем разделять по проектам! (В цели экономии ресурсов)
 * 2) Создать метод для перебора ссылок на всём рбк, принимающий параметр String и отдающий массив String
 * 3) Переместить метод modify_url сюда*/

package ru.rbc.kskabort.pages;

public class URLs {

    public class News {
        public static final String PROD = "https://www.rbc.ru/inttotestv8A";
        public static final String STAGE = "https://staging.rbc.ru/";
        public static final String NEWS_REGULAR = "https://test.rbc.ru/";
        public static final String NEWS_2 = "https://test2.rbc.ru/";
        public static final String NEWS_3 = "https://test3.rbc.ru/";
    }

    public static class Prod {
        public static final String NEWS = "https://www.rbc.ru/";
        public static final String AUTO = "https://www.autonews.ru/";
        public static final String TV = "http://tv.rbc.ru/";
        public static final String STYLE = "https://style.rbc.ru/";
        public static final String PINK = "https://pink.rbc.ru/";
        public static final String NEWSPAPER = "https://www.rbc.ru/newspaper/";
        public static final String MAGAZINE = "https://www.rbc.ru/magazine/";
        public static final String CRYPTO = "https://www.rbc.ru/crypto/";// !!
        public static final String SPORT = "https://sportrbc.ru/";
        public static final String REALTY = "https://realty.rbc.ru/";
        public static final String QUOTE = "https://quote.rbc.ru/";
        public static final String MARKETING = "https://marketing.rbc.ru/";
        public static final String RBC500 = "https://rbc.ru/rbc500//";
        public static final String AWARDS = "https://www.rbc.ru/awards/";
        public static final String AWARDS_SPB = "https://www.rbc.ru/awards_spb/";
        public static final String RNV = "http://cash.rbc.ru/";
        public static final String RBCPLUS = "http://www.rbcplus.ru/";
        public static final String PRO = "https://pro.rbc.ru/";// !!
        public static final String BC = "https://bc.rbc.ru/";// !!
        //public static final String ZOOM = "http://zoom.cnews.ru/";
    }
    public class Staging {
        public static final String NEWS = "https://staging.rbc.ru/";
        public static final String AUTO = "https://staging.autonews.ru/";
        public static final String TV = "http://staging.v4.tv.rbc.ru/";
        public static final String STYLE = "https://staging.style.rbc.ru/";
        public static final String PINK = "https://staging.v1.pink.rbc.ru/";
        public static final String NEWSPAPER = "https://staging.rbc.ru/newspaper/";
        public static final String MAGAZINE = "https://staging.rbc.ru/magazine/";
        public static final String CRYPTO = "https://staging.rbc.ru/crypto/";// !!
        public static final String SPORT = "https://staging.v4.sport.rbc.ru/";
        public static final String REALTY = "https://staging.realty.rbc.ru/";
        public static final String QUOTE = "https://staging.v4.quote.rbc.ru/";
        public static final String MARKETING = "https://staging.v4.marketing.rbc.ru/";
        public static final String RBC500 = "https://staging.rbc.ru/rbc500//";
        public static final String AWARDS = "https://staging.rbc.ru/awards/";
        public static final String AWARDS_SPB = "https://staging.rbc.ru/awards_spb/";
        public static final String RNV = "http://staging.v5.cash.rbc.ru/";
        public static final String RBCPLUS = "http://staging.v2.rbcplus.ru/";
        public static final String PRO = "https://staging.pro.rbc.ru/";// !!
        public static final String BC = "https://staging.bc.rbc.ru/"; // !!

    }

    public class Test{
        public static final String NEWS_REGULAR = "https://test.rbc.ru/";
        public static final String NEWS_2 = "https://test2.rbc.ru/";
        public static final String NEWS_3 = "https://test3.rbc.ru/";

        public static final String AUTO = "https://test.autonews.ru/";
        public static final String TV = "http://test.v4.tv.rbc.ru/";
        public static final String STYLE = "https://test.v4.style.rbc.ru/";
        public static final String PINK = "https://test.v1.pink.rbc.ru/";
        public static final String NEWSPAPER = "https://test.rbc.ru/newspaper/";
        public static final String MAGAZINE = "https://test.rbc.ru/magazine/";
        public static final String CRYPTO = "https://test.rbc.ru/crypto/"; // !!
        public static final String SPORT = "https://test.sport.rbc.ru/";
        public static final String REALTY = "https://test.v4.realty.rbc.ru/";
        public static final String QUOTE = "https://test.v4.quote.rbc.ru/";
        public static final String MARKETING = "https://test.v4.marketing.rbc.ru/";
        public static final String RBC500 = "https://test.rbc.ru/rbc500//";
        public static final String AWARDS = "https://test.rbc.ru/awards/";
        public static final String AWARDS_SPB = "https://test.rbc.ru/awards_spb/";
        public static final String RNV = "http://test.v5.cash.rbc.ru/";
        public static final String RBCPLUS = "http://test.v2.rbcplus.ru/";
        public static final String PRO = "https://test.pro.rbc.ru/";// !!
        public static final String BC = "https://test.bc.rbc.ru/"; // !!
    }

    public class Other{
        public static final String BIZTORG = "http://biztorg.ru/offers/";
        public static final String FRANCHISES = "http://biztorg.ru/franchises/";
        public static final String CNEWS = "http://www.cnews.ru/";
        public static final String HEALTH = "http://health.rbc.ru/";
    }

    public class Static {
        public static final String ABCOMPANY = "http://www.rbcholding.ru/about.shtml";
        public static final String CONTACTS = "https://www.rbc.ru/contacts/";
        public static final String REDACTION = "https://www.rbc.ru/editors/";
        public static final String ADVERT = "https://rbc.ru/advert/";
        public static final String TRAINPROG = "http://intern.rbc.ru/";

        public static final String ABAUTORS = "";
        public static final String INFABLIM = "";
    }

/*    static String[] i_need_mass (String param){
        String[] mass = new String[0];
        if (param.equals("topline")) {*/
    public static class Mass{
        public static final String[] topline = {Prod.TV, Prod.NEWSPAPER, Prod.MAGAZINE, Prod.RBCPLUS, Prod.PRO, Prod.QUOTE, Prod.AUTO, Prod.SPORT,
                        Prod.REALTY, Prod.STYLE, Prod.CRYPTO, Prod.MARKETING, Other.BIZTORG, Other.FRANCHISES, Prod.BC,
                        Prod.AWARDS, Prod.AWARDS_SPB};
        public static final String[] top_add = {Other.HEALTH, Prod.PINK, Prod.AWARDS.substring(0, 24)};
    }

    public static String SPLIT(String url, String ver)
    {
        url += "inttotestv";
        return url + ver;
    }
    /*    else if (param.equals("tolpine_add")) {
            mass = new String[] {Other.HEALTH, Prod.PINK, Prod.AWARDS};
        }
        return mass;
    }*/
}
