package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class SearchPageObject extends MainPageObject{

    private static final String
        SEARCH_INIT_ELEMENT = "//*[contains(@text,'Search Wikipedia')]",
        SEARCH_INPUT = "//*[contains(@text,'Search…')]",
        SEARCH_CANCEL_BUTTON = "org.wikipedia:id/search_close_btn",
        SEARCH_RESULT_BY_SUBSTRING_TPL = "//*[@resource-id='org.wikipedia:id/search_results_list']//*[@text='{SUBSTRING}']",
        SEARCH_RESULT_ELEMENT = "//*[@resource-id='org.wikipedia:id/search_results_list']/*[@resource-id='org.wikipedia:id/page_list_item_container']",
        SEARCH_EMPTY_RESULT_ELEMENT = "//*[@text='No results found']",
        SEARCH_RESULT_TITLE = "//*[@resource-id='org.wikipedia:id/page_list_item_title']",
        SEARCH_RESULT_BY_TITLE_AND_SUBTITLE_SUBSTRINGS_TPL = "//*[@resource-id='org.wikipedia:id/search_results_list']//*[@text='{SUBSTRING_TITLE}']/following-sibling::*[@text='{SUBSTRING_SUBTITLE}']";


    public SearchPageObject(AppiumDriver driver)
    {
        super(driver);
    }

    /* TEMPLATE METHODS */
    private static String getResultSearchElement(String substring)
    {
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }

    private static String getResultSearchElementByTitleAndSubtitleText(String substring_title, String substring_subtitle)
    {
        return SEARCH_RESULT_BY_TITLE_AND_SUBTITLE_SUBSTRINGS_TPL
                .replace("{SUBSTRING_TITLE}", substring_title)
                .replace("{SUBSTRING_SUBTITLE}", substring_subtitle);
    }
     /* TEMPLATE METHODS */

    public void initSearchInput()
    {
        this.waitForElementPresent(By.xpath(SEARCH_INIT_ELEMENT), "Cannot find search input after clicking search init element");
        this.waitForElementAndClick(By.xpath(SEARCH_INIT_ELEMENT), "Cannot find and click search init element", 5);
    }

    public void waitForCancelButtonToAppear()
    {
        this.waitForElementPresent((By.id(SEARCH_CANCEL_BUTTON)),"Cannot find search cancel button", 5);
    }

    public WebElement waitForSearchBarTextToAppear()
    {
        return this.waitForElementPresent(By.xpath(SEARCH_INPUT),"Cannot find search bar text", 5);
    }

    public String getSearchBarText()
    {
        WebElement search_bar = waitForSearchBarTextToAppear();
        return search_bar.getAttribute("text");
    }

    public void waitForCancelButtonToDisappear()
    {
        this.waitForElementNotPresent((By.id(SEARCH_CANCEL_BUTTON)),"Search cancel button is still present", 5);
    }

    public void clickCancelSearch()
    {
        this.waitForElementAndClick(By.id(SEARCH_CANCEL_BUTTON),"Cannot find and click search cancel button", 5);
    }

    public void typeSearchLine(String search_line)
    {
        this.waitForElementAndSendKeys(By.xpath(SEARCH_INPUT), search_line, "Cannot find and type into search input", 5);
    }

    public void waitForSearchResult(String substring)
    {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementPresent(By.xpath(search_result_xpath), "Cannot find search result with substring " + substring);
    }

    public void waitForSearchResultToDisappear(String substring)
    {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementNotPresent(By.xpath(search_result_xpath), "There is search result with substring " + substring,5);
    }

    public void clickByArticleWithSubstring(String substring)
    {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementAndClick(By.xpath(search_result_xpath), "Cannot find and click search result with substring " + substring, 10);
    }

    public int getAmountOfFoundArticles()
    {
        this.waitForElementPresent(
                By.xpath(SEARCH_RESULT_ELEMENT),
                "Cannot find anything by the request",
                15
        );

        return this.getAmountOfElements(By.xpath(SEARCH_RESULT_ELEMENT));
    }

    public void waitForEmptyResultsLabel()
    {
        this.waitForElementPresent(By.xpath(SEARCH_EMPTY_RESULT_ELEMENT), "Cannot find empty result element", 15);
    }

    public void assertThereIsNoResultOfSearch()
    {
        this.assertElementNotPresent(By.xpath(SEARCH_RESULT_ELEMENT),"We supposed not to find any results");
    }

    public void assertEachSearchResultContainsSubstring(String substring)
    {
        this.assertEachSameElementContainsText(
                By.xpath(SEARCH_RESULT_TITLE),
                substring,
                "There is a title, which doesn't contain " + substring
        );
    }

    public void waitForElementByTitleAndDescription(String title, String subtitle)
    {
        String search_result_xpath = getResultSearchElementByTitleAndSubtitleText(title, subtitle);
        this.waitForElementPresent(
                By.xpath(search_result_xpath),
                "Cannot find search result with title '" + title +"' and subtitle '" + subtitle + "'",
                15
        );
    }
}