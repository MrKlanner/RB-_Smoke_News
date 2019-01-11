package ru.rbc.kskabort.pages;

import com.codeborne.selenide.Condition;
import org.checkerframework.checker.nullness.compatqual.NonNullType;
import ru.rbc.kskabort.ConsoleColors;

import java.nio.channels.NotYetBoundException;

import static com.codeborne.selenide.Selenide.*;
import static ru.rbc.kskabort.pages.URLs.SecondPagesUrls.*;

public class SecondPage extends StaticPageObjects {
    public SecondPage () {
        toplineLogo.shouldBe(Condition.visible);
    }
    /*@FindBy(how = How.CSS, className = "div.search-item")*/
    //private SelenideElement searchQuerys = $(".search-item:nth-child(1) .search-item__text");

    public String serchQuerys(int i) {
        return $(".search-item:nth-child(" + Integer.toString(i) + ") span.search-item__text").getText();
    }

    public String[] getSecondPagesUrls (String project) {
        if (project.equals("test"))
            return new String[]{test.spec, test.article, test.opin, test.photorep, test.black_gallary, test.infografic, test.videogallary, test.online, test.rating, test.investigation,
                    test.article_of_combined_redaction, test.short_news, test.interview, test.research, test.insert_in_table};
        else if (project.equals("staging"))
            return new String[]{staging.spec, staging.article, staging.opin, staging.photorep, staging.black_gallary, staging.infografic, staging.videogallary, staging.online, staging.rating, staging.investigation,
                    staging.article_of_combined_redaction, staging.short_news, staging.interview, staging.research, staging.insert_in_table};
        else return new String[] {ConsoleColors.RED_BOLD_BRIGHT + "Please paste correct project value.\nProject value \"".concat(project).concat("\" is not defined in this scope") + ConsoleColors.RESET};
    }

}
