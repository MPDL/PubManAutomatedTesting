package test.java.base.moddep;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import main.java.pages.LoginPage;
import main.java.pages.StartPage;
import main.java.pages.homepages.CombinedHomePage;
import main.java.pages.tools.citation.CitationSearchName;
import main.java.pages.tools.citation.CitationStyleEditor;
import test.java.base.BaseTest;

public class InstallCitationStyleTest extends BaseTest {

	private CombinedHomePage combinedHomePage;
	private CitationStyleEditor csEditor;
	
	private String styleName = "IEEE";
	
	@BeforeClass
	public void setup() {
		super.setup();
	}
	
	@Test(priority = 1)
	public void loginCombined() {
		LoginPage loginPage = new StartPage(driver).goToLoginPage();
		combinedHomePage = loginPage.loginAsCombinedUser(modDepUsername, modDepPassword);
	}
	
	@Test(priority = 2)
	public void searchCSName() {
		saveCurrentHandle();
		csEditor = combinedHomePage.goToAdvancedSearchPage().goToToolsPage().goToCitationStyleEditor();
		CitationSearchName searchName = csEditor.searchStyle(styleName);
		WebElement firstResult = searchName.getFirstResult();
		boolean canInstallStyle = searchName.openStylePage(firstResult).canSaveStyle();
		Assert.assertTrue(canInstallStyle, "Button for installing style is not active.");
	}
	
	@AfterClass
	public void tearDown() {
		backToBaseHandle();
		combinedHomePage = (CombinedHomePage) new StartPage(driver).goToHomePage(combinedHomePage);
		combinedHomePage.logout();
	}
}
