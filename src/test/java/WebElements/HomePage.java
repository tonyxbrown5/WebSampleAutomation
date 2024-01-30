package WebElements;

import org.openqa.selenium.By;

public interface HomePage {
	
	public final By searchBox = By.xpath("//input[@data-testid='searchval']");
	
	public final By searchBoxIcon = By.xpath("//div[@data-testid='search-input']//button[@type='submit']");
	
	public final By searchResultsContainer = By.xpath("//div[@data-testid='product-listing-container']");
	
	public final By searchResultItemTitles = By.xpath("//div[@data-testid='product-listing-container']//span[@data-testid='itemDescription']");

	public final By searchResultItemContainers = By.xpath("//div[@data-testid='product-listing-container']//div[@class='btn-container']");
	
	public final By notificationViewCartButton = By.xpath("//div[contains(@class, 'notification')]//a[contains(@class, 'btn-primary')]");
	
}

