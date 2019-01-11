/*Задачи:
 * 1) Класс ссылок для проектов. В будущем разделять по проектам! (В цели экономии ресурсов)
 * 2) Создать метод для перебора ссылок на всём рбк, принимающий параметр String и отдающий массив String
 * 3) Переместить метод modify_url сюда*/

package ru.rbc.kskabort.pages;

public class URLs {

    private class News {
        public static final String PROD = "https://www.rbc.ru/";
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

    private class Test{
        private static final String NEWS_REGULAR = "https://test.rbc.ru/";
        private static final String NEWS_2 = "https://test2.rbc.ru/";
        private static final String NEWS_3 = "https://test3.rbc.ru/";

        private static final String AUTO = "https://test.autonews.ru/";
        private static final String TV = "http://test.v4.tv.rbc.ru/";
        private static final String STYLE = "https://test.v4.style.rbc.ru/";
        private static final String PINK = "https://test.v1.pink.rbc.ru/";
        private static final String NEWSPAPER = "https://test.rbc.ru/newspaper/";
        private static final String MAGAZINE = "https://test.rbc.ru/magazine/";
        private static final String CRYPTO = "https://test.rbc.ru/crypto/"; // !!
        private static final String SPORT = "https://test.sport.rbc.ru/";
        private static final String REALTY = "https://test.v4.realty.rbc.ru/";
        private static final String QUOTE = "https://test.v4.quote.rbc.ru/";
        private static final String MARKETING = "https://test.v4.marketing.rbc.ru/";
        private static final String RBC500 = "https://test.rbc.ru/rbc500//";
        private static final String AWARDS = "https://test.rbc.ru/awards/";
        private static final String AWARDS_SPB = "https://test.rbc.ru/awards_spb/";
        private static final String RNV = "http://test.v5.cash.rbc.ru/";
        private static final String RBCPLUS = "http://test.v2.rbcplus.ru/";
        private static final String PRO = "https://test.pro.rbc.ru/";// !!
        private static final String BC = "https://test.bc.rbc.ru/"; // !!
    }

    private class Other{
        private static final String BIZTORG = "http://biztorg.ru/offers/";
        private static final String FRANCHISES = "http://biztorg.ru/franchises/";
        private static final String CNEWS = "http://www.cnews.ru/";
        private static final String HEALTH = "http://health.rbc.ru/";
    }

    private class Static {
        private static final String ABCOMPANY = "http://www.rbcholding.ru/about.shtml";
        private static final String CONTACTS = "https://www.rbc.ru/contacts/";
        private static final String REDACTION = "https://www.rbc.ru/editors/";
        private static final String ADVERT = "https://rbc.ru/advert/";
        private static final String TRAINPROG = "http://intern.rbc.ru/";



        private static final String ABAUTORS = "";
        private static final String INFABLIM = "";
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

    //Ссылки для проверки отображения вторых страниц
    static class SecondPagesUrls {
        static class test {
            static String spec = "https://test.rbc.ru/politics/09/10/2018/5bbc6db7308a9f727d3f7337?story=5b754070308a9f4161f2d41e";
            static String article = "https://test.rbc.ru/business/08/10/2018/5bbb6629308a9fa3098c0c35";
            static String opin = "https://test.rbc.ru/opinions/society/08/10/2018/5bbb78bb308a9fe87be8a80f";
            static String photorep = "https://test.rbc.ru/photoreport/08/10/2018/5bbb7a9e308a9ff5417e7da7";
            static String black_gallary = "https://test.rbc.ru/photoreport/08/10/2018/5bbb7dac308a9ffad5d22393";
            static String infografic = "https://test.rbc.ru/infographics/08/10/2018/5bbb7f1f308a9f01abb2f8bf";
            static String videogallary = "https://test.rbc.ru/own_business/09/10/2018/5bbc7541308a9f07cb0370be";
            static String online = "https://test.rbc.ru/textonlines/09/10/2018/5bbc75d9308a9f07cb0370c2?story=5b754070308a9f4161f2d41e";
            static String rating = "https://test.rbc.ru/own_business/08/10/2018/5bbf7b1e308a9fd322659df4";
            static String investigation = "https://test.rbc.ru/investigation/technology_and_media/08/10/2018/5bbf7ec0308a9fe2bcf4ec62"; //Расследование
            static String article_of_combined_redaction = "https://test.rbc.ru/ins/technology_and_media/11/10/2018/5bbf7f2b308a9fe2c17e393c";
            static String short_news = "https://test.rbc.ru/rbcfreenews/5bbf7fb5308a9fe2bcf4ec6a";
            static String interview = "https://test.rbc.ru/interview/own_business/08/10/2018/5bbcbd75308a9f21d3164c4c";
            static String research = "https://test.rbc.ru/research/technology_and_media/11/10/2018/5bbf806d308a9fe711221ae7";
            static String insert_in_table = "https://test.rbc.ru/politics/09/10/2018/5bbf1e1c308a9ff64059c826?story=5b754070308a9f4161f2d41e";
        }

        static class staging {
            static String spec = "";
            static String article = "";
            static String opin = "";
            static String photorep = "";
            static String black_gallary = "";
            static String infografic = "";
            static String videogallary = "";
            static String online = "";
            static String rating = "";
            static String investigation = "";
            static String article_of_combined_redaction = "";
            static String short_news = "";
            static String interview = "";
            static String research = "";
            static String insert_in_table = "";
        }
    }
}
    /*    else if (param.equals("tolpine_add")) {
            mass = new String[] {Other.HEALTH, Prod.PINK, Prod.AWARDS};
        }
        return mass;
    }*/

