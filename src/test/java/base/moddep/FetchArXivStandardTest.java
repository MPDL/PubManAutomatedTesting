package test.java.base.moddep;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import main.java.pages.LoginPage;
import main.java.pages.StartPage;
import main.java.pages.homepages.CombinedHomePage;
import main.java.pages.submission.FetchSubmissionPage;
import main.java.pages.submission.ViewItemPage;
import test.java.base.BaseTest;

/**
 * TestLink UC: 12
 * Submit item using the Fetch metadata method.
 * Identifier Type: arXiv
 *
 */
public class FetchArXivStandardTest extends BaseTest {

	private String arXivID = "arXiv:1304.2685";
	
	private CombinedHomePage combinedHomePage;
	private ViewItemPage viewItemPage;
	
	@BeforeClass
	public void setup() {
		super.setup();
	}
	
	@Test(priority = 1)
	public void logInModDep() {
		LoginPage loginPage = new StartPage(driver).goToLoginPage();
		combinedHomePage = loginPage.loginAsCombinedUser(modDepUsername, modDepPassword);
	}
	
	@Test(priority = 2)
	public void fetchSubmission() {
		FetchSubmissionPage fetchPage = combinedHomePage.goToSubmissionPage().goToFetchSubmissionStandardPage();
		viewItemPage = fetchPage.fetchSubmission(arXivID);
	}
	
	@Test(priority = 3)
	public void deleteItem() {
		viewItemPage = viewItemPage.sendBackForRework();
		viewItemPage.deleteItem();
	}
	
	@AfterClass
	public void tearDown() {
		combinedHomePage = (CombinedHomePage) new StartPage(driver).goToHomePage(combinedHomePage);
		combinedHomePage.logout();
	}
}
