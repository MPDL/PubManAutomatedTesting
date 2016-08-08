package pages.submission;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import base.Genre;
import pages.BasePage;

public class FullSubmissionPage extends BasePage {

	@FindBy(id = "form1:cboGenre")
	private WebElement genreDropdown;
	
	@FindBy(id = "form1:fileUploads:0:selFileContentCategory")
	private WebElement contentCategoryDropdown;
	
	@FindBy(id = "form1:inputTitleText")
	private WebElement titleBox;
	
	@FindBy(id = "form1:j_idt497:0:inpcreator_persons_person_family_name_optional")
	private WebElement personFamilyNameBox;
	
	@FindBy(id = "form1:j_idt497:0:inppersons_person_ous_optional")
	private WebElement orgNrBox;
	
	@FindBy(id = "form1:j_idt545:0:inporganizations_organization_name")
	private WebElement organisationNameBox;
	
	@FindBy(id = "form1:txtDatePublishedInPrint")
	private WebElement datePublishedInPrintBox;
	
	@FindBy(id = "form1:j_idt827:0:selChooseSourceGenre")
	private WebElement sourceGenreDropdown;
	
	@FindBy(id = "form1:j_idt827:0:inpSourceTitle_Journal")
	private WebElement sourceTitleBox;
	
	@FindBy(id = "form1:lnkRelease")
	private WebElement releaseButton;
	
	private final String abstractValue = "http://purl.org/escidoc/metadata/ves/content-categories/abstract";
	
	public FullSubmissionPage(WebDriver driver) {
		super(driver);
		
		PageFactory.initElements(driver, this);
	}
	
	public FinaliseSubmissionPage fullSubmission(Genre genre, String title, String filepath) {
		fillInData(genre, title);
		fillInGenericData();
		uploadFile(filepath);
		return submit();
	}
	
	private void fillInData(Genre genre, String title) {
		Select genreSelect = new Select(genreDropdown);
		genreSelect.selectByValue(genre.toString());
		titleBox.sendKeys(title);
	}
	
	private void uploadFile(String filepath) {
		String addFileButtonID = "form1:inpFile_input";
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("document.getElementById('" + addFileButtonID + "').style.display = 'block';");
		driver.findElement(By.id(addFileButtonID)).sendKeys(filepath);
		
		Select contentCategorySelect = new Select(contentCategoryDropdown);
		contentCategorySelect.selectByValue(abstractValue);
	}
	
	private void fillInGenericData() {
		personFamilyNameBox.sendKeys("Testermann, Testo (MPI for Social Anthropology, Max Planck Society)");
		orgNrBox.sendKeys("1");
		organisationNameBox.sendKeys("MPI for Social Anthropology, Max Planck Society");
		datePublishedInPrintBox.sendKeys("2016-01-01");
		Select genreSourceSelect = new Select(sourceGenreDropdown);
		genreSourceSelect.selectByValue(Genre.BOOK.toString());
		sourceTitleBox.sendKeys("Test Book");
	}
	
	private FinaliseSubmissionPage submit() {
		releaseButton.click();
		
		return PageFactory.initElements(driver, FinaliseSubmissionPage.class);
	}
}