import lib.CoreTestCase;
import lib.ui.*;
import org.junit.Test;
import org.openqa.selenium.*;

public class FirstTest extends CoreTestCase {

    private MainPageObject MainPageObject;

    protected void setUp() throws Exception
    {
        super.setUp();

        MainPageObject = new MainPageObject(driver);
    }

    @Test
    public void testSaveTwoArticlesToMyList()
    {
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        String value_for_search = "Java";
        MainPageObject.waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_container"),
                value_for_search,
                "Cannot find search input",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']//*[@text='Object-oriented programming language']"),
                "Cannot find 'Object-oriented programming language' topic searching by 'Java'",
                5
        );

        MainPageObject.waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Cannot find article title",
                15
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                "Cannot find button to open article options",
                10
        );

        MainPageObject.waitForElementPresent(
                By.xpath("//*[@text='Add to reading list']"),
                "Cannot find option to add article to reading list",
                10
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@text='Add to reading list']"),
                "Cannot find option to add article to reading list",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/onboarding_button"),
                "Cannot find 'Got it' tip overlay",
                5
        );

        MainPageObject.waitForElementAndClear(
                By.id("org.wikipedia:id/text_input"),
                "Cannot find input to set name of the article folder",
                5
        );

        String name_of_folder = "Learning programming";

        MainPageObject.waitForElementAndSendKeys(
                By.id("org.wikipedia:id/text_input"),
                name_of_folder,
                "Cannot put text into article folder input",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@text='OK']"),
                "Cannot press OK button",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cannot close article, cannot find X button",
                10
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[@text='Search…']"),
                value_for_search,
                "Cannot find search input",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']//*[@text='JavaScript']"),
                "Cannot find 'JavaScript' topic searching by 'Java'",
                5
        );

        String title_before_deleting_article = MainPageObject.waitForElementAndGetAttribute(
                By.xpath("//*[@resource-id='org.wikipedia:id/view_page_title_text']"),
                "text",
                "Cannot find the title of the article",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                "Cannot find button to open article options",
                10
        );

        MainPageObject.waitForElementPresent(
                By.xpath("//*[@text='Add to reading list']"),
                "Cannot find option to add article to reading list",
                10
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@text='Add to reading list']"),
                "Cannot find option to add article to reading list",
                10
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@text='" + name_of_folder + "']"),
                "Cannot find '" + name_of_folder + "' list",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cannot close article, cannot find X button",
                10
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.FrameLayout[@content-desc='My lists']"),
                "Cannot find 'My lists' button",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@text='" + name_of_folder + "']"),
                "Cannot find article folder",
                15
        );

        MainPageObject.swipeElementToLeft(
                By.xpath("//*[@text='Java (programming language)']"),
                "Cannot find saved article"
        );

        MainPageObject.waitForElementNotPresent(
                By.xpath("//*[@text='Java (programming language)']"),
                "Cannot delete saved article",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@text='JavaScript']"),
                "Cannot find second article after deleting the first article",
                5
        );

        String title_after_deleting_article = MainPageObject.waitForElementAndGetAttribute(
                By.xpath("//*[@resource-id='org.wikipedia:id/view_page_title_text']"),
                "text",
                "Cannot find the title of the article",
                5
        );

        assertEquals(
                "Title of the second article have changed after deleting the first article",
                title_before_deleting_article,
                title_after_deleting_article
        );

    }

    @Test
    public void testAssertTitleInTheArticle()
    {
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        String value_for_search = "Java";
        MainPageObject.waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_container"),
                value_for_search,
                "Cannot find search input",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']//*[@text='Object-oriented programming language']"),
                "Cannot find 'Object-oriented programming language' topic searching by 'Java'",
                5
        );

        MainPageObject.assertElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "cannot find article title"
        );
    }

}
