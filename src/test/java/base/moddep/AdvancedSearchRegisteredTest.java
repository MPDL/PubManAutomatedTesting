package test.java.base.moddep;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import test.java.base.BaseTest;
import main.java.pages.LoginPage;
import main.java.pages.StartPage;
import main.java.pages.homepages.CombinedHomePage;
import main.java.pages.search.AdvancedSearchPage;
import main.java.pages.search.SearchResultsPage;

/*
 * TODO improve test coverage
 */
public class AdvancedSearchRegisteredTest extends BaseTest {
	
	private String titleQuery = "test";
	private String authorQuery = "Müller";
	private String organisationQuery = "MPI of Cognitive Neuroscience";
	
	private CombinedHomePage combinedHomePage;
	
	@BeforeClass
	public void setup() {
		super.setup();
	}
	
	@Test(priority=1)
	public void logInAsCombinedUser() {
		LoginPage loginPage = new StartPage(driver).goToLoginPage();
		combinedHomePage = loginPage.loginAsCombinedUser(modDepUsername, modDepPassword);
	}
	
	@Test(priority=2)
	public void advancedSearchTitleTest() {
		searchTest(titleQuery, "", "");
	}
	
	@Test(priority=2)
	public void advancedSearchAuthorTest() {
		searchTest("", authorQuery, "");
	}
	
	@Test(priority=2)
	public void advancedSearchOrganisationTest() {
		searchTest("", "", organisationQuery);
	}
	
	@Test(priority=2)
	public void advancedSearchCombinedTest() {
		searchTest(titleQuery, authorQuery, organisationQuery);
	}
	
	private void searchTest(String titleQuery, String authorQuery, String organisationQuery) {
		combinedHomePage = (CombinedHomePage) new StartPage(driver).goToHomePage(combinedHomePage);
		AdvancedSearchPage advancedSearchPage = combinedHomePage.goToAdvancedSearchPage();
		SearchResultsPage searchResultsPage = advancedSearchPage.advancedSearch(titleQuery, authorQuery, organisationQuery);
		String headlineText = searchResultsPage.getHeadline();
		try {
			Assert.assertEquals(headlineText, "Search Results");
		}
		catch (AssertionError exc) {
			Assert.assertEquals(headlineText, "Suchergebnisse", "Search results page was not displayed.");
		}
	}
	
	@AfterClass
	public void logout() {
		combinedHomePage = (CombinedHomePage) new StartPage(driver).goToHomePage(combinedHomePage);
		combinedHomePage.logout();
	}
}
