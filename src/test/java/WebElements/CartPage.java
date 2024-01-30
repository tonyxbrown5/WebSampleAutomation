package WebElements;

import org.openqa.selenium.By;

public interface CartPage {
	
	public final By emptyCartButton = By.xpath("//button[contains(@class, 'emptyCartButton')]");
	
	public final By emptyCartModalButton = By.xpath("//footer[@data-testid='modal-footer']//button[contains(text(), 'Empty')]");
	
	public final By cartItems = By.xpath("//div[contains(@class, 'cartItem')]");
	
}

