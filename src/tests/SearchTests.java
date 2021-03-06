package tests;

import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class SearchTests extends CoreTestCase
{
    @Test
    public void testSearch()
    {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResult("Object-oriented programming language");
    }

    @Test
    public void testCancelSearch()
    {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.waitForCancelButtonToAppear();
        SearchPageObject.clickCancelSearch();
        SearchPageObject.waitForCancelButtonToDisappear();

    }

    @Test
    public void testAmountOfNotEmptySearch()
    {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        String search_line = "Linking Park Discography";
        SearchPageObject.typeSearchLine(search_line);
        int amount_of_search_results = SearchPageObject.getAmountOfFoundArticles();

        assertTrue(
                "We found too few results",
                amount_of_search_results > 0
        );

    }

    @Test
    public void testAmountOfEmptySearch()
    {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        String search_line = "zadfsdftgwwf";
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.waitForEmptyResultsLabel();
        SearchPageObject.assertThereIsNoResultOfSearch();

    }

    @Test
    public void testCompareSearchBarText()
    {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();

        String search_bar_text = SearchPageObject.getSearchBarText();

        assertEquals(
                "Search bar doesn't contain text 'Search…'",
                "Search…",
                search_bar_text
        );

    }

    @Test
    public void testCancelSearchAfterSuccessfulSearch()
    {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResult("Object-oriented programming language");
        SearchPageObject.waitForSearchResult("Island of Indonesia");

        SearchPageObject.waitForCancelButtonToAppear();
        SearchPageObject.clickCancelSearch();

        SearchPageObject.waitForSearchResultToDisappear("Object-oriented programming language");
        SearchPageObject.waitForSearchResultToDisappear("Island of Indonesia");

    }

    @Test
    public void testEachSearchResultContainsSearchKey()
    {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        String search_text = "Java";
        SearchPageObject.typeSearchLine(search_text);
        SearchPageObject.waitForSearchResult("Object-oriented programming language");
        SearchPageObject.assertEachSearchResultContainsSubstring(search_text);

    }

    @Test
    public void testEachSearchResultContainsTitleAndSubtitle()
    {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        String search_text = "Java";
        SearchPageObject.typeSearchLine(search_text);
        SearchPageObject.waitForElementByTitleAndDescription("Java", "Island of Indonesia");
        SearchPageObject.waitForElementByTitleAndDescription("JavaScript", "Programming language");
        SearchPageObject.waitForElementByTitleAndDescription("Java (programming language)", "Object-oriented programming language");
    }
}
