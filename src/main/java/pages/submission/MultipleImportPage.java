package main.java.pages.submission;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import main.java.pages.BasePage;

public class MultipleImportPage extends BasePage {

	private WebDriverWait wait;
	
	@FindBy(id = "j_idt98:selFormat")
	private WebElement importFormatDropdown;
	
	private final String uploadInputID = "j_idt98:inpMultipleImportUploadedImportFile_input";
	private final String importButtonID = "j_idt98:lnkUploadFile";
	
	public MultipleImportPage(WebDriver driver) {
		super(driver);
		
		wait = new WebDriverWait(driver, 20);
		
		PageFactory.initElements(driver,  this);
	}
	
	public MultipleImportOptionsPage batchImportItems(String filepath, String format) {
		if (filepath == null)
			return null;
		
		Select importFormatSelect = new Select(importFormatDropdown);
		importFormatSelect.selectByVisibleText(format);
		
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("document.getElementById('" + uploadInputID + "').style.display = 'block';");
		driver.findElement(By.id(uploadInputID)).sendKeys(filepath);
		
		WebElement importButton = driver.findElement(By.id(importButtonID));
	    wait.until(ExpectedConditions.elementToBeClickable(importButton));
	    importButton.click();
	    
	    return PageFactory.initElements(driver, MultipleImportOptionsPage.class);
	}
}
