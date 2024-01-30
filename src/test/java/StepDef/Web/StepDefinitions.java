package StepDef.Web;

import Functions.SeleniumBase;
import WebElements.HomePage;
import WebElements.CartPage;
import io.cucumber.java.en.*;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import java.util.List;


public class StepDefinitions extends SeleniumBase implements HomePage, CartPage {

    @Given("the user navigates to webstaurantstore")
    public void theUserNavigatesToWebstaurantstore() {
    	driver.get("https://www.webstaurantstore.com/");
    }

    @When("^the user enters '(.*)' in the home page search box and clicks search icon$")
    public void theUserEntersInTheHomePageSearchBoxAndClicksSearchIcon(String searchText) {
    	textInput(HomePage.searchBox, searchText);
    	click(HomePage.searchBoxIcon);
    }
    
    @Then("^the home page search results for '(.*)' are displayed$")
    public void theHomePageSearchResultsForAreDisplayed(String searchText) {
    	assert isDisplayed(HomePage.searchResultsContainer);
    	
        List<WebElement> resultTitles = driver.findElements(HomePage.searchResultItemTitles);

        for (WebElement title : resultTitles) {
            assert title.getText().contains(searchText);
        }
    }
    
	@When("the user clicks 'Add to Cart' for the last search result")
	public void theUserClicksAddToCartForTheLastSearchResult() {
        List<WebElement> resultTitles = driver.findElements(HomePage.searchResultItemContainers);
        
        if (!resultTitles.isEmpty()) {
            WebElement lastItem = resultTitles.get(resultTitles.size() - 1);
            lastItem.click();
        }
	}
	
	@And("the user clicks the notification 'View Cart' button")
	public void theUserClicksTheNotificationViewCartButton() {
		click(HomePage.notificationViewCartButton);
	}
	
	@And("the user clicks the 'Empty Cart' button")
	public void theUserClicksTheEmptyCartButton() {
		click(CartPage.emptyCartButton);
	}
	
	@And("the user clicks the 'Empty Cart' modal button")
	public void theUserClicksTheEmptyCartModalButton() {
		click(CartPage.emptyCartModalButton);
		waitInSeconds(5);
	}
	
	@Then("the Cart is empty")
	public void theCartIsEmpty() {
        List<WebElement> cartItems = driver.findElements(CartPage.cartItems);
        
        Assert.assertTrue("The cart is not empty", cartItems.isEmpty());
	}

}
