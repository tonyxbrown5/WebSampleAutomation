Feature: Main search on webstaurant home page
	As a user I want to navigate to the webstaurant home page,
	execute a search, see the correct search results, and add an
	item to a cart

  Scenario: Navigate to home page
    Given the user navigates to webstaurantstore
    When the user enters 'stainless work table' in the home page search box and clicks search icon
    Then the home page search results for 'Table' are displayed
		When the user clicks 'Add to Cart' for the last search result
		And the user clicks the notification 'View Cart' button
		And the user clicks the 'Empty Cart' button
		And the user clicks the 'Empty Cart' modal button
		Then the Cart is empty
		
		