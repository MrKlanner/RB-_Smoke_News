/**Задачи:
 * 1) Класс ссылок для проектов. В будущем разделять по проектам! (В цели экономии ресурсов)
 * 2) Создать метод для перебора ссылок на всём рбк, принимающий параметр String и отдающий массив String
 * 3) Переместить метод modify_url сюда*/

package ru.rbc.kskabort;

class URLs {
    class Prod {
        static final String NEWS = "https://www.rbc.ru/";
        static final String AUTO = "https://www.autonews.ru/";
        static final String TV = "http://tv.rbc.ru/";
        static final String STYLE = "https://style.rbc.ru/";
        static final String PINK = "https://pink.rbc.ru/";
        static final String NEWSPAPER = "https://www.rbc.ru/newspaper/";
        static final String MAGAZINE = "https://www.rbc.ru/magazine/";
        static final String CRYPTO = "https://www.rbc.ru/crypto/";// !!
        static final String SPORT = "https://sportrbc.ru/";
        static final String REALTY = "https://realty.rbc.ru/";
        static final String QUOTE = "https://quote.rbc.ru/";
        static final String MARKETING = "https://marketing.rbc.ru/";
        static final String RBC500 = "https://rbc.ru/rbc500//";
        static final String RNV = "http://cash.rbc.ru/";
        static final String RBCPLUS = "http://www.rbcplus.ru/";
        static final String PRO = "https://pro.rbc.ru/";// !!
        //static final String ZOOM = "http://zoom.cnews.ru/";
    }
    class Staging {
        static final String NEWS = "https://staging.rbc.ru/";
        static final String AUTO = "https://staging.autonews.ru/";
        static final String TV = "http://staging.v4.tv.rbc.ru/";
        static final String STYLE = "https://staging.style.rbc.ru/";
        static final String PINK = "https://staging.v1.pink.rbc.ru/";
        static final String NEWSPAPER = "https://staging.rbc.ru/newspaper/";
        static final String MAGAZINE = "https://staging.rbc.ru/magazine/";
        static final String CRYPTO = "https://staging.rbc.ru/crypto/";// !!
        static final String SPORT = "https://staging.v4.sportrbc.ru/";
        static final String REALTY = "https://staging.realty.rbc.ru/";
        static final String QUOTE = "https://staging.v4.quote.rbc.ru/";
        static final String MARKETING = "https://staging.v4.marketing.rbc.ru/";
        static final String RBC500 = "https://staging.rbc.ru/rbc500//";
        static final String RNV = "http://staging.v5.cash.rbc.ru/";
        static final String RBCPLUS = "http://staging.v2.rbcplus.ru/";
        static final String PRO = "https://staging.pro.rbc.ru/";// !!

    }

    class Test{
        static final String NEWS_REGULAR = "https://test.rbc.ru/";
        static final String NEWS_2 = "https://test2.rbc.ru/";
        static final String NEWS_3 = "https://test3.rbc.ru/";

        static final String AUTO = "https://test.autonews.ru/";
        static final String TV = "http://test.v4.tv.rbc.ru/";
        static final String STYLE = "https://test.v4.style.rbc.ru/";
        static final String PINK = "https://test.v1.pink.rbc.ru/";
        static final String NEWSPAPER = "https://test.rbc.ru/newspaper/";
        static final String MAGAZINE = "https://test.rbc.ru/magazine/";
        static final String CRYPTO = "https://test.rbc.ru/crypto/"; //!!
        static final String SPORT = "https://test.sportrbc.ru/";
        static final String REALTY = "https://test.v4.realty.rbc.ru/";
        static final String QUOTE = "https://test.v4.quote.rbc.ru/";
        static final String MARKETING = "https://test.v4.marketing.rbc.ru/";
        static final String RBC500 = "https://test.rbc.ru/rbc500//";
        static final String RNV = "http://test.v5.cash.rbc.ru/";
        static final String RBCPLUS = "http://test.v2.rbcplus.ru/";
        static final String PRO = "https://staging.pro.rbc.ru/";// !!
    }

/*    String[] i_need_mass (String param){
        if (param == "topline")

    }*/
}
