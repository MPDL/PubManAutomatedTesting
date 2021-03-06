package main.java.pages.tools.citation;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CitationSearchExample {

	private WebDriver driver;
	
	@FindBy(id = "styleFormatInputControls")
	private WebElement styleFormat;
	
	@FindBy(id = "searchButton")
	private WebElement searchButton;
	
	private List<WebElement> searchResults;
	
	public CitationSearchExample(WebDriver driver) {
		this.driver = driver;
		
		PageFactory.initElements(driver, this);
	}
	
	public List<WebElement> getSearchResults() {
		styleFormat.findElement(By.tagName("button")).click();
		WebElement allResults = driver.findElement(By.id("searchResults"));
		searchResults = allResults.findElements(By.tagName("table"));
		
		return searchResults;
	}
	
	public VisualEditor editFirstResult() {
		WebElement firstResult = searchResults.get(0);
		firstResult.findElement(By.className("editStyle")).click();
		
		return PageFactory.initElements(driver, VisualEditor.class);
	}
}
